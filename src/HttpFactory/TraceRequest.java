package HttpFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

public final class TraceRequest extends HttpRequest {
    public TraceRequest(String url) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.TRACE, url);
    }

    public TraceRequest(String url, Map<String, String> headers) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.TRACE, url, null, headers, null);
    }
}
