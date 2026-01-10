package HttpFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

public final class PostRequest extends HttpRequest {
    public PostRequest(String url) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.POST, url);
    }

    public PostRequest(String url, String body) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.POST, url, body, null, null);
    }

    public PostRequest(String url, String body, Map<String, String> headers) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.POST, url, body, headers, null);
    }

    public PostRequest(String url, String body, Map<String, String> headers, Map<String, String> queryParams) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.POST, url, body, headers, queryParams);
    }
}
