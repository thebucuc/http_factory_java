package HttpFactory;

public enum HttpVersion {//definire versiuni de protocol
    HTTP_1_1("HTTP/1.1"),
    HTTP_2("HTTP/2");

    private final String raw;

    HttpVersion(String raw) {
        this.raw = raw;
    }

    public String getRaw() {
        return raw;
    }

    @Override
    public String toString() {
        return raw;
    }
}
