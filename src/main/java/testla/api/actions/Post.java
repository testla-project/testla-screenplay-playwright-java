package testla.api.actions;

import com.microsoft.playwright.options.RequestOptions;
import testla.api.RequestMethod;
import testla.api.ResponseBodyFormat;
import testla.api.abilities.UseAPI;
import testla.screenplay.actor.IActor;

import java.util.Map;

/**
 * Action Class. Send a HTTP POST Request.
 */
public class Post extends ARequest {

    private final String url;
    private final RequestOptions options;
    private ResponseBodyFormat responseBodyFormat = ResponseBodyFormat.JSON;

    private Post(String url) {
        this.url = url;
        this.options = RequestOptions.create();
    }

    /**
     * Send a HTTP POST request to the specified url.
     *
     * @param actor the actor that performs the request.
     */
    @Override
    public Object performAs(IActor actor) {
        return UseAPI.as(actor).sendRequest(RequestMethod.POST, this.url, this.options, this.responseBodyFormat);
    }

    /**
     * Send an HTTP POST request to the specified url.
     *
     * @param url the URL of the target.
     */
    public static Post to(String url) {
        return new Post(url);
    }

    /**
     * Add headers to the HTTP POST request to send.
     *
     * @param headers the headers.
     */
    public Post withHeaders(Map<String, String> headers) {
        // replaced by Java compiler: headers.forEach((k,v) -> this.options.setHeader(k, v));
        headers.forEach(this.options::setHeader);
        return this;
    }

    /**
     * Set the format the response body should be returned as.
     *
     * @param responseBodyFormat the format of the response body.
     */
    public Post withResponseBodyFormat(ResponseBodyFormat responseBodyFormat) {
        this.responseBodyFormat = responseBodyFormat;
        return this;
    }

    /**
     * Add data to the HTTP POST request to send.
     *
     * @param data the data.
     */
    public Post withData(Object data) {
        this.options.setData(data);
        return this;
    }

    /**
     * Add data to the HTTP POST request to send.
     *
     * @param data the data.
     */
    public Post withData(String data) {
        this.options.setData(data);
        return this;
    }

    /**
     * Add data to the HTTP POST request to send.
     *
     * @param data the data.
     */
    public Post withData(byte[] data) {
        this.options.setData(data);
        return this;
    }
}
