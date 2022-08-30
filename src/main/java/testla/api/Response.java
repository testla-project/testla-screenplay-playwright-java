package testla.api;

import java.util.Map;

public class Response {
    public Object body;
    public int status;
    public Map<String, String> headers;
    public long duration;

    public Response(Object body, int status, Map<String, String> headers, long duration) {
        this.body = body;
        this.status = status;
        this.headers = headers;
        this.duration = duration;
    }
}
