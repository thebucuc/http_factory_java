package HttpFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

public final class DeleteRequest extends HttpRequest {
    public DeleteRequest(String url) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.DELETE, url);
    }

    public DeleteRequest(String url, Map<String, String> headers) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.DELETE, url, null, headers, null);
    }
}
