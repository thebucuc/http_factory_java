package HttpFactory;
import java.util.Map;

public interface HttpActions {
    HttpResponse execute() throws java.io.IOException, InterruptedException;
    void addHeader(String key, String value);
    void addHeaders(Map<String, String> headers);
    void addQueryParam(String key, String value);
    void addQueryParams(Map<String, String> params);
    void setVersion(HttpVersion version);
}
