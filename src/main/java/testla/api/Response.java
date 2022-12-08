package testla.api;

import java.util.Map;

/**
 * Response type which is returned from any request
 */
public class Response {

    /**
     * The body object
     */
    public Object body;

    /**
     * Response status
     */
    public int status;

    /**
     * Response headers
     */
    public Map<String, String> headers;

    /**
     * Response duration
     */
    public long duration;

    /**
     * Constructor of the Response object
     *
     * @param body - response body as object
     * @param status - response status code
     * @param headers - response headers
     * @param duration - response duration
     */
    public Response(Object body, int status, Map<String, String> headers, long duration) {
        this.body = body;
        this.status = status;
        this.headers = headers;
        this.duration = duration;
    }
}
