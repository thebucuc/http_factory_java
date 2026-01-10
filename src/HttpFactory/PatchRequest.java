package HttpFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

public final class PatchRequest extends HttpRequest {
    public PatchRequest(String url) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.PATCH, url);
    }

    public PatchRequest(String url, String body) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.PATCH, url, body, null, null);
    }

    public PatchRequest(String url, String body, Map<String, String> headers) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.PATCH, url, body, headers, null);
    }
}
