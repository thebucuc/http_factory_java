package HttpFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HttpExecutor { // interactiune cu serverul

    public static HttpResponse execute(HttpRequest request) throws IOException, InterruptedException {
        try {
            return executeWithNetcat(request);
        } catch (Exception e) {
            System.err.println("Netcat execution failed: " + e.getMessage());
            System.out.println("Falling back to Curl...");
            return executeWithCurl(request);
        }
    }
    
    // trimite cererea la stream-ul ncat (trimitere bruta) si primeste raspuns
    public static HttpResponse executeWithNetcat(HttpRequest request) throws IOException, InterruptedException {
        String host = request.getHost();
        int port = request.getPort();
        String rawRequest = request.getRawHttpRequest();
        boolean isHttps = "https".equalsIgnoreCase(request.getProtocol());

        List<String> command = new ArrayList<>();
        command.add("ncat");
        if (isHttps) {
            command.add("--ssl");
        }
        command.add(host);
        command.add(String.valueOf(port));

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        try (OutputStream os = process.getOutputStream()) {
            os.write(rawRequest.getBytes(StandardCharsets.UTF_8));
            os.write("\r\n\r\n".getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        HttpResponse response = parseResponse(process.getInputStream());
        process.waitFor();
        
        
        if (response.getRawHttpResponse() == null || response.getRawHttpResponse().isEmpty()) {
             throw new IOException("Netcat received no response.");
        }

        return response;
    }
    
    //trimite cererea la stream-ul curl (trimitere prin comenzi) si primeste raspuns
    public static HttpResponse executeWithCurl(HttpRequest request) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("curl");
        command.add("-i");
        command.add("-X");
        command.add(request._method.getMethod());

        if (request._version == HttpVersion.HTTP_1_1) {
            command.add("--http1.1");
        } else if (request._version == HttpVersion.HTTP_2) {
            command.add("--http2");
        }

        request._headers.forEach((k, v) -> {
            command.add("-H");
            command.add(k + ": " + v);
        });

        if (request.getBody() != null) {
            command.add("--data");
            command.add(request.getBody());
        }

        command.add(request.buildUrlWithQuery());

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        HttpResponse response = parseResponse(process.getInputStream());

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("curl exited with code " + exitCode + ". Raw output:\n" + response.getRawHttpResponse());
        }

        return response;
    }

    // parseaza textul trimis de server intr-un obiect de tip HttpResponse
    private static HttpResponse parseResponse(InputStream inputStream) throws IOException {
        HttpResponse response = new HttpResponse();
        StringBuilder rawResponse = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean statusParsed = false;
            boolean headersParsed = false;

            while ((line = reader.readLine()) != null) {
                if (rawResponse.length() > 0) {
                    rawResponse.append("\n");
                }
                rawResponse.append(line);
                if (!statusParsed && line.toUpperCase().startsWith("HTTP/")) {
                    String[] parts = line.trim().split("\\s+", 3);
                    if (parts.length >= 2) {
                        String versionToken = parts[0];
                        String statusToken = parts[1];
                        String reason = parts.length == 3 ? parts[2] : "";
                        response.setVersion(versionToken.toUpperCase().contains("HTTP/2") ? HttpVersion.HTTP_2 : HttpVersion.HTTP_1_1);
                        response.setStatusCode(Integer.parseInt(statusToken));
                        response.setReasonPhrase(reason);
                    }
                    statusParsed = true;
                } else if (!headersParsed) {
                    if (line.trim().isEmpty()) {
                        headersParsed = true;
                    } else {
                        int idx = line.indexOf(':');
                        if (idx > 0) {
                            String key = line.substring(0, idx).trim();
                            String value = line.substring(idx + 1).trim();
                            response.addHeader(key, value);
                        }
                    }
                } else {
                    String currentBody = response.getBody() == null ? "" : response.getBody();
                    if (!currentBody.isEmpty()) {
                        currentBody += "\n";
                    }
                    response.setBody(currentBody + line);
                }
            }
        }

        response.setRawHttpResponse(rawResponse.toString());
        return response;
    }
}
