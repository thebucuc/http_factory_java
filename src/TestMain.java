import HttpFactory.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class TestMain {
    public static void main(String[] args) {
        String testUrl = "http://www.example.com";
        HttpMethods[] methods = HttpMethods.values();

        System.out.println("Starting comprehensive HTTP method tests...");
        System.out.println("===========================================");

        for (HttpMethods method : methods) {
            System.out.println("\nTesting Method: " + method);
            try {
                HttpRequest request = RequestFactory.createRequest(method, testUrl);
                
                // Add body for methods that typically use it
                if (method == HttpMethods.POST || method == HttpMethods.PUT || method == HttpMethods.PATCH) {
                    request.setBody("{\"test\": \"data for " + method + "\"}");
                }

                System.out.println("--- Request Line ---");
                String raw = request.getRawHttpRequest();
                String firstLine = raw.split("\n")[0];
                System.out.println(firstLine);

                HttpResponse response = request.execute();
                
                System.out.println("--- Response Status ---");
                System.out.println(response.getVersion() + " " + response.getStatusCode() + " " + response.getReasonPhrase());
                
                if (response.getBody() != null && !response.getBody().isEmpty()) {
                    String bodySnippet = response.getBody().length() > 50 
                        ? response.getBody().substring(0, 50) + "..." 
                        : response.getBody();
                    System.out.println("--- Response Body Snippet ---");
                    System.out.println(bodySnippet);
                } else {
                    System.out.println("--- No Response Body ---");
                }

            } catch (Exception e) {
                System.err.println("Test failed for method " + method + ": " + e.getMessage());
                // We don't want one failure to stop the whole test suite
            }
            System.out.println("-------------------------------------------");
        }
    }
}
