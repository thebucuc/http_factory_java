import HttpFactory.*;
import java.util.HashMap;
import java.util.Map;

public class WikipediaTestMain {
    public static void main(String[] args) {
        try {
            System.out.println("=== Wikipedia Complex Test ===");

            // 1. Fetch Main Page
            System.out.println("\n--- Step 1: Fetching Wikipedia Main Page ---");
            GetRequest mainPageRequest = RequestFactory.createGetRequest("https://en.wikipedia.org/wiki/Main_Page");
            mainPageRequest.addHeader(WellKnownHttpHeaders.USER_AGENT, "HttpFactoryTester/1.0");
            
            HttpResponse mainPageResp = mainPageRequest.execute();
            System.out.println("Full Raw Response Length: " + (mainPageResp.getRawHttpResponse() != null ? mainPageResp.getRawHttpResponse().length() : 0));
            System.out.println("Parsed Headers count: " + mainPageResp.getHeaders().size());
            mainPageResp.getHeaders().forEach((k, v) -> System.out.println("  " + k + ": " + v));
            
            System.out.println("Status Code: " + mainPageResp.getStatusCode() + " " + mainPageResp.getReasonPhrase());
            System.out.println("Content-Type: " + mainPageResp.getHeader(WellKnownHttpHeaders.CONTENT_TYPE));
            System.out.println("Server: " + mainPageResp.getHeader(WellKnownHttpHeaders.SERVER));
            
            if (mainPageResp.getBody() != null && mainPageResp.getBody().length() > 200) {
                System.out.println("Body Snippet: " + mainPageResp.getBody().substring(0, 200).replace("\n", " ") + "...");
            }

            // 2. Search for 'HTTP' using query parameters
            System.out.println("\n--- Step 2: Searching for 'HTTP' via Query Parameters ---");
            Map<String, String> searchParams = new HashMap<>();
            searchParams.put("search", "HTTP");
            searchParams.put("title", "Special:Search");
            searchParams.put("go", "Go");
            
            GetRequest searchRequest = new GetRequest("https://en.wikipedia.org/w/index.php", searchParams);
            searchRequest.addHeader(WellKnownHttpHeaders.USER_AGENT, "HttpFactoryTester/1.0");
            System.out.println("Request URL with Query: " + searchRequest.buildUrlWithQuery());
            
            HttpResponse searchResp = searchRequest.execute();
            System.out.println("Status Code: " + searchResp.getStatusCode() + " " + searchResp.getReasonPhrase());
            System.out.println("Final URL (if redirected, check Location): " + searchResp.getHeader(WellKnownHttpHeaders.LOCATION));

            // 3. Complex Request with Headers
            System.out.println("\n--- Step 3: Request with custom User-Agent and Accept-Language ---");
            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put(WellKnownHttpHeaders.USER_AGENT.getHeader(), "HttpFactoryTester/1.0 (https://github.com/example/httpFactory)");
            customHeaders.put(WellKnownHttpHeaders.ACCEPT_LANGUAGE.getHeader(), "en-US,en;q=0.5");
            
            GetRequest complexRequest = new GetRequest("https://en.wikipedia.org/wiki/Special:Random", customHeaders, null);
            HttpResponse randomResp = complexRequest.execute();
            System.out.println("Status Code: " + randomResp.getStatusCode() + " " + randomResp.getReasonPhrase());
            System.out.println("Random Page Location: " + randomResp.getHeader(WellKnownHttpHeaders.LOCATION));

            System.out.println("\n=== Wikipedia Complex Test Completed Successfully ===");

        } catch (Exception e) {
            System.err.println("Test failed with exception:");
            e.printStackTrace();
        }
    }
}
