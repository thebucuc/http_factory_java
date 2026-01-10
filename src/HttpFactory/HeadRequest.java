package HttpFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

public final class HeadRequest extends HttpRequest {
    public HeadRequest(String url) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.HEAD, url);
    }

    public HeadRequest(String url, Map<String, String> headers) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.HEAD, url, null, headers, null);
    }
}
