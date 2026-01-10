package HttpFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponse {
    Map<String, String> _headers = new LinkedHashMap<>();
    HttpVersion _version = HttpVersion.HTTP_1_1;
    int _statusCode = 200;
    String _reasonPhrase = "OK";
    String _body;

    public HttpResponse() {
    }

    public HttpResponse(int statusCode, String reasonPhrase) {
        _statusCode = statusCode;
        _reasonPhrase = reasonPhrase;
    }

    public void addHeader(String key, String value) {
        _headers.put(key, value);
    }

    public void addHeader(WellKnownHttpHeaders key, String value) {
        _headers.put(key.getHeader(), value);
    }

    public Map<String, String> getHeaders() {
        return _headers;
    }

    public String getHeader(String key) {
        if (key == null) return null;
        for (Map.Entry<String, String> entry : _headers.entrySet()) {
            if (key.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public String getHeader(WellKnownHttpHeaders key) {
        return getHeader(key.getHeader());
    }

    public void setVersion(HttpVersion version) {
        _version = version;
    }

    public HttpVersion getVersion() {
        return _version;
    }

    public void setStatusCode(int statusCode) {
        _statusCode = statusCode;
    }

    public int getStatusCode() {
        return _statusCode;
    }

    public void setReasonPhrase(String reasonPhrase) {
        _reasonPhrase = reasonPhrase;
    }

    public String getReasonPhrase() {
        return _reasonPhrase;
    }

    public void setBody(String body) {
        _body = body;
        if (_body != null) {
            this.addHeader(WellKnownHttpHeaders.CONTENT_LENGTH.getHeader(), String.valueOf(_body.getBytes().length));
        }
    }

    public String getBody() {
        return _body;
    }

    private String _rawHttpResponse;

    public void setRawHttpResponse(String raw) {
        _rawHttpResponse = raw;
    }

    public String getRawHttpResponse() {
        if (_rawHttpResponse != null) {
            return _rawHttpResponse.trim();
        }
        StringBuilder builder = new StringBuilder();
        builder.append(_version.getRaw()).append(" ").append(_statusCode).append(" ").append(_reasonPhrase).append("\r\n");
        _headers.forEach((k, v) -> builder.append(k).append(": ").append(v).append("\r\n"));
        builder.append("\r\n");
        if (_body != null) {
            builder.append(_body);
        }
        return builder.toString().trim();
    }
}
