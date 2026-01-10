package HttpFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

public final class OptionsRequest extends HttpRequest {
    public OptionsRequest(String url) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.OPTIONS, url);
    }

    public OptionsRequest(String url, Map<String, String> headers) throws MalformedURLException, URISyntaxException {
        super(HttpMethods.OPTIONS, url, null, headers, null);
    }
}
