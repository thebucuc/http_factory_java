package HttpFactory;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;


public abstract sealed class HttpRequest implements HttpActions permits GetRequest, PostRequest, PutRequest, DeleteRequest, HeadRequest, OptionsRequest, PatchRequest, TraceRequest, ConnectRequest{
    Map<String, String> _headers = new LinkedHashMap<>(); // antetele HTTP
    HttpMethods _method; // tipul cererii
    String _url;
    URL _urlObject;//obiect URL
    String _body;//corpul cererii
    Map<String, String> _queryParams = new LinkedHashMap<>();//parametrii dupa ? in URL
    HttpVersion _version = HttpVersion.HTTP_1_1;//versiunea protocolului

    //constructor pentru cerere fara body
    public HttpRequest(HttpMethods method, String url) throws URISyntaxException, MalformedURLException {
        this(method, url, null, null, null);
    }
    
    //constructor extrage din adresa URL si configureaza body
    protected HttpRequest(HttpMethods method, String url, String body, Map<String, String> headers, Map<String, String> queryParams) throws URISyntaxException, MalformedURLException {
        _method = method;
        _url = url;
        _urlObject = new URI(url).toURL();
        this.addHeader(WellKnownHttpHeaders.HOST.getHeader(), _urlObject.getHost());
        if (body != null) {
            setBody(body);
        }
        if (headers != null) {
            _headers.putAll(headers);
        }
        if (queryParams != null) {
            _queryParams.putAll(queryParams);
        }
    }
    public void addQueryParam(String key, String value) {
        _queryParams.put(key, value);
    }
    public void addQueryParams(Map<String, String> params) {
        if (params != null) {
            _queryParams.putAll(params);
        }
    }
    public Map<String, String> getQueryParams() {
        return _queryParams;
    }
    public String getQueryParam(String key) {
        return _queryParams.get(key);
    }

    
    public String buildUrlWithQuery() {
        if (_queryParams.isEmpty() || _url == null || _url.isEmpty()) {
            return _url;
        }
        StringJoiner joiner = new StringJoiner("&");
        _queryParams.forEach((k, v) -> {
            String encodedKey = URLEncoder.encode(k, StandardCharsets.UTF_8);
            String encodedValue = v == null ? "" : URLEncoder.encode(v, StandardCharsets.UTF_8);
            joiner.add(encodedKey + "=" + encodedValue);
        });
        String separator = _url.contains("?") ? "&" : "?";
        return _url + separator + joiner;
    }

    public void setBody(String body){
        _body = body;
        this.addHeader(WellKnownHttpHeaders.CONTENT_LENGTH.getHeader(), String.valueOf(_body.getBytes().length));
    }
    public String getBody(){
        return _body;
    }

    public void addHeader(String key, String value){
        _headers.put(key, value);
    }
    public void addHeaders(Map<String, String> headers) {
        if (headers != null) {
            _headers.putAll(headers);
        }
    }
    public void addHeader(WellKnownHttpHeaders key, String value){
        _headers.put(key.getHeader(), value);
    }
    public Map<String, String> getHeaders(){
        return _headers;
    }
    public String getHeader(String key){
        return _headers.get(key);
    }
    public String getHeader(WellKnownHttpHeaders key){
        return _headers.get(key.getHeader());
    }

    public void setVersion(HttpVersion version){
        _version = version;
    }
    public HttpVersion getVersion(){
        return _version;
    }

    public void setMethod(HttpMethods method){
        _method = method;
    }
    public void setUrl(String url){
        _url = url;
    }

    public String getUrlPath(){
        return _url.split("/")[3];
    }

    public String buildUrlPathWithQuery() {
        String path = _urlObject.getPath();
        if (path == null || path.isEmpty()) {
            path = "/";
        }
        if (_queryParams.isEmpty()) {
            return path;
        }
        StringJoiner joiner = new StringJoiner("&");
        _queryParams.forEach((k, v) -> {
            String encodedKey = URLEncoder.encode(k, StandardCharsets.UTF_8);
            String encodedValue = v == null ? "" : URLEncoder.encode(v, StandardCharsets.UTF_8);
            joiner.add(encodedKey + "=" + encodedValue);
        });
        return path + "?" + joiner;
    }

    public String getRawHttpRequest(){
        StringBuilder builder = new StringBuilder();
        builder.append(_method.getMethod()).append(" ");
        builder.append(buildUrlPathWithQuery()).append(" ").append(_version.getRaw()).append("\r\n");

        _headers.forEach((k, v) -> builder.append(k).append(": ").append(v).append("\r\n"));
        builder.append("\r\n");
        if(_body != null) builder.append(_body);
        return builder.toString().trim();
    }

    public String getHost() {
        return _urlObject.getHost();
    }

    public String getProtocol() {
        return _urlObject.getProtocol();
    }

    public int getPort() {
        int port = _urlObject.getPort();
        if (port == -1) {
            return _urlObject.getDefaultPort();
        }
        return port;
    }

    @Override
    public HttpResponse execute() throws java.io.IOException, InterruptedException {
        return HttpExecutor.execute(this);
    }
}
