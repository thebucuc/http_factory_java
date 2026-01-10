package HttpFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

public final class ConnectRequest extends HttpRequest {
    public ConnectRequest(String url) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.CONNECT, url);
    }

    public ConnectRequest(String url, Map<String, String> headers) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.CONNECT, url, null, headers, null);
    }
}
