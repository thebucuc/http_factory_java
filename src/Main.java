import HttpFactory.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        //Test HTTP Get request creation and execution.
    	GetRequest request = (GetRequest) RequestFactory.createRequest(HttpMethods.GET, "http://www.example.com");
        System.out.println("--- Request ---");
        System.out.println(request.getRawHttpRequest());
        System.out.println("--- Response ---");
        try {
            HttpResponse resp = request.execute();
            System.out.println(resp.getRawHttpResponse());
        } catch (Exception e) {
            System.err.println("Execution failed: " + e.getMessage());
        }
    }
}