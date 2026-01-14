package HttpFactory;

public enum WellKnownHttpHeaders { //dictionar headere standard
    ACCEPT("Accept"),
    ACCEPT_CHARSET("Accept-Charset"),
    ACCEPT_ENCODING("Accept-Encoding"),
    ACCEPT_LANGUAGE("Accept-Language"),
    ACCEPT_PATCH("Accept-Patch"),
    ACCEPT_RANGES("Accept-Ranges"),
    ACCESS_CONTROL_ALLOW_CREDENTIALS("Access-Control-Allow-Credentials"),
    ACCESS_CONTROL_ALLOW_HEADERS("Access-Control-Allow-Headers"),
    ACCESS_CONTROL_ALLOW_METHODS("Access-Control-Allow-Methods"),
    ACCESS_CONTROL_ALLOW_ORIGIN("Access-Control-Allow-Origin"),
    ACCESS_CONTROL_EXPOSE_HEADERS("Access-Control-Expose-Headers"),
    ACCESS_CONTROL_MAX_AGE("Access-Control-Max-Age"),
    ACCESS_CONTROL_REQUEST_HEADERS("Access-Control-Request-Headers"),
    ACCESS_CONTROL_REQUEST_METHOD("Access-Control-Request-Method"),
    AGE("Age"),
    ALLOW("Allow"),
    AUTHORIZATION("Authorization"),
    CACHE_CONTROL("Cache-Control"),
    CONNECTION("Connection"),
    CONTENT_DISPOSITION("Content-Disposition"),
    CONTENT_ENCODING("Content-Encoding"),
    CONTENT_LANGUAGE("Content-Language"),
    CONTENT_LENGTH("Content-Length"),
    CONTENT_LOCATION("Content-Location"),
    CONTENT_RANGE("Content-Range"),
    CONTENT_SECURITY_POLICY("Content-Security-Policy"),
    CONTENT_TYPE("Content-Type"),
    COOKIE("Cookie"),
    DATE("Date"),
    ETAG("ETag"),
    EXPECT("Expect"),
    EXPIRES("Expires"),
    FORWARDED("Forwarded"),
    FROM("From"),
    HOST("Host"),
    IF_MATCH("If-Match"),
    IF_MODIFIED_SINCE("If-Modified-Since"),
    IF_NONE_MATCH("If-None-Match"),
    IF_RANGE("If-Range"),
    IF_UNMODIFIED_SINCE("If-Unmodified-Since"),
    LAST_MODIFIED("Last-Modified"),
    LINK("Link"),
    LOCATION("Location"),
    MAX_FORWARDS("Max-Forwards"),
    ORIGIN("Origin"),
    PRAGMA("Pragma"),
    PROXY_AUTHENTICATE("Proxy-Authenticate"),
    PROXY_AUTHORIZATION("Proxy-Authorization"),
    RANGE("Range"),
    REFERER("Referer"),
    REFRESH("Refresh"),
    RETRY_AFTER("Retry-After"),
    SEC_WEBSOCKET_ACCEPT("Sec-WebSocket-Accept"),
    SEC_WEBSOCKET_KEY("Sec-WebSocket-Key"),
    SEC_WEBSOCKET_VERSION("Sec-WebSocket-Version"),
    SERVER("Server"),
    SET_COOKIE("Set-Cookie"),
    STRICT_TRANSPORT_SECURITY("Strict-Transport-Security"),
    TE("TE"),
    TRAILER("Trailer"),
    TRANSFER_ENCODING("Transfer-Encoding"),
    UPGRADE("Upgrade"),
    UPGRADE_INSECURE_REQUESTS("Upgrade-Insecure-Requests"),
    USER_AGENT("User-Agent"),
    VARY("Vary"),
    VIA("Via"),
    WARNING("Warning"),
    WWW_AUTHENTICATE("WWW-Authenticate");

    private final String header;

    WellKnownHttpHeaders(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    @Override
    public String toString() {
        return header;
    }
}
