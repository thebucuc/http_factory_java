package HttpFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

public final class PutRequest extends HttpRequest {
    public PutRequest(String url) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.PUT, url);
    }

    public PutRequest(String url, String body) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.PUT, url, body, null, null);
    }

    public PutRequest(String url, String body, Map<String, String> headers) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.PUT, url, body, headers, null);
    }
}
