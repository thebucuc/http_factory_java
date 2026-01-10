package HttpFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

public final class GetRequest extends HttpRequest {
    public GetRequest(HttpMethods method, String url) throws MalformedURLException, URISyntaxException {
        super(method, url);
    }

    public GetRequest(String url) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.GET, url);
    }

    public GetRequest(String url, Map<String, String> queryParams) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.GET, url, null, null, queryParams);
    }

    public GetRequest(String url, Map<String, String> headers, Map<String, String> queryParams) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.GET, url, null, headers, queryParams);
    }
}
