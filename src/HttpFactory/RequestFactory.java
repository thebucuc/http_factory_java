package HttpFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class RequestFactory {

    public static GetRequest createGetRequest(String url) throws MalformedURLException, URISyntaxException {
        return new GetRequest(HttpMethods.GET, url);
    }

    public static PostRequest createPostRequest(String url) throws MalformedURLException, URISyntaxException {
        return new PostRequest(url);
    }

    public static PostRequest createPostRequest(String url, String body) throws MalformedURLException, URISyntaxException {
        return new PostRequest(url, body);
    }

    public static PutRequest createPutRequest(String url) throws MalformedURLException, URISyntaxException {
        return new PutRequest(url);
    }

    public static PutRequest createPutRequest(String url, String body) throws MalformedURLException, URISyntaxException {
        return new PutRequest(url, body);
    }

    public static DeleteRequest createDeleteRequest(String url) throws MalformedURLException, URISyntaxException {
        return new DeleteRequest(url);
    }

    public static HeadRequest createHeadRequest(String url) throws MalformedURLException, URISyntaxException {
        return new HeadRequest(url);
    }

    public static OptionsRequest createOptionsRequest(String url) throws MalformedURLException, URISyntaxException {
        return new OptionsRequest(url);
    }

    public static PatchRequest createPatchRequest(String url) throws MalformedURLException, URISyntaxException {
        return new PatchRequest(url);
    }

    public static PatchRequest createPatchRequest(String url, String body) throws MalformedURLException, URISyntaxException {
        return new PatchRequest(url, body);
    }

    public static TraceRequest createTraceRequest(String url) throws MalformedURLException, URISyntaxException {
        return new TraceRequest(url);
    }

    public static ConnectRequest createConnectRequest(String url) throws MalformedURLException, URISyntaxException {
        return new ConnectRequest(url);
    }

    /**
     * Generic method to create requests based on the method type.
     */
    public static HttpRequest createRequest(HttpMethods method, String url) throws MalformedURLException, URISyntaxException {
        return switch (method) {
            case GET -> new GetRequest(method, url);
            case POST -> new PostRequest(url);
            case PUT -> new PutRequest(url);
            case DELETE -> new DeleteRequest(url);
            case HEAD -> new HeadRequest(url);
            case OPTIONS -> new OptionsRequest(url);
            case PATCH -> new PatchRequest(url);
            case TRACE -> new TraceRequest(url);
            case CONNECT -> new ConnectRequest(url);
        };
    }
}
