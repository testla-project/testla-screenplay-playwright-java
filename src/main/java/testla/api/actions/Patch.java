package testla.api.actions;

import com.microsoft.playwright.options.RequestOptions;
import testla.screenplay.actor.IActor;
import testla.api.RequestMethod;
import testla.api.ResponseBodyFormat;
import testla.api.abilities.UseAPI;

import java.util.Map;

/**
 * Action Class. Send a HTTP PATCH Request.
 */
public class Patch extends ARequest {

    private final String url;
    private final RequestOptions options;
    private ResponseBodyFormat responseBodyFormat = ResponseBodyFormat.JSON;

    private Patch(String url) {
        this.url = url;
        this.options = RequestOptions.create();
    }

    /**
     * Send a HTTP PATCH request to the specified url.
     *
     * @param actor the actor that performs the request.
     */
    @Override
    public Object performAs(IActor actor) {
        return UseAPI.as(actor).sendRequest(RequestMethod.PATCH, this.url, this.options, this.responseBodyFormat);
    }

    /**
     * Send a HTTP PATCH request to the specified url.
     *
     * @param url the URL of the target.
     */
    public static Patch to(String url) {
        return new Patch(url);
    }

    /**
     * Add headers to the HTTP PATCH request to send.
     *
     * @param headers the headers.
     */
    public Patch withHeaders(Map<String, String> headers) {
        // replaced by Java compiler: headers.forEach((k,v) -> this.options.setHeader(k, v));
        headers.forEach(this.options::setHeader);
        return this;
    }

    /**
     * Set the format the response body should be returned as.
     *
     * @param responseBodyFormat the format of the response body.
     */
    public Patch withResponseBodyFormat(ResponseBodyFormat responseBodyFormat) {
        this.responseBodyFormat = responseBodyFormat;
        return this;
    }

    /**
     * Add data to the HTTP PATCH request to send.
     * PATCH requests bodies hold partial updates of the entities to be updated.
     *
     * @param data the data.
     */
    public Patch withData(Object data) {
        this.options.setData(data);
        return this;
    }

    /**
     * Add data to the HTTP PATCH request to send.
     * PATCH requests bodies hold partial updates of the entities to be updated.
     *
     * @param data the data.
     */
    public Patch withData(String data) {
        this.options.setData(data);
        return this;
    }

    /**
     * Add data to the HTTP PATCH request to send.
     * PATCH requests bodies hold partial updates of the entities to be updated.
     *
     * @param data the data.
     */
    public Patch withData(byte[] data) {
        this.options.setData(data);
        return this;
    }
}
