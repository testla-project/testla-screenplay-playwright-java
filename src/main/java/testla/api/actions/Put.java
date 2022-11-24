package testla.api.actions;

import com.microsoft.playwright.options.RequestOptions;
import testla.screenplay.actor.IActor;
import testla.api.RequestMethod;
import testla.api.ResponseBodyFormat;
import testla.api.abilities.UseAPI;

import java.util.Map;

/**
 * Action Class. Send a HTTP PUT Request.
 */
public class Put extends ARequest {

    private final String url;
    private final RequestOptions options;
    private ResponseBodyFormat responseBodyFormat = ResponseBodyFormat.JSON;

    private Put(String url) {
        this.url = url;
        this.options = RequestOptions.create();
    }

    /**
     * Send a HTTP PUT request to the specified url.
     *
     * @param actor the actor that performs the request.
     */
    @Override
    public Object performAs(IActor actor) {
        return UseAPI.as(actor).sendRequest(RequestMethod.PUT, this.url, this.options, this.responseBodyFormat);
    }

    /**
     * Send a HTTP PUT request to the specified url.
     *
     * @param url the URL of the target.
     */
    public static Put to(String url) {
        return new Put(url);
    }

    /**
     * Add headers to the HTTP PUT request to send.
     *
     * @param headers the headers.
     */
    public Put withHeaders(Map<String, String> headers) {
        // replaced by Java compiler: headers.forEach((k,v) -> this.options.setHeader(k, v));
        headers.forEach(this.options::setHeader);
        return this;
    }

    /**
     * Set the format the response body should be returned as.
     *
     * @param responseBodyFormat the format of the response body.
     */
    public Put withResponseBodyFormat(ResponseBodyFormat responseBodyFormat) {
        this.responseBodyFormat = responseBodyFormat;
        return this;
    }

    /**
     * Add data to the HTTP PUT request to send.
     * PUT requests bodies hold the full entity information to be updated.
     *
     * @param data the data.
     */
    public Put withData(Object data) {
        this.options.setData(data);
        return this;
    }

    /**
     * Add data to the HTTP PUT request to send.
     * PUT requests bodies hold the full entity information to be updated.
     *
     * @param data the data.
     */
    public Put withData(String data) {
        this.options.setData(data);
        return this;
    }

    /**
     * Add data to the HTTP PUT request to send.
     * PUT requests bodies hold the full entity information to be updated.
     *
     * @param data the data.
     */
    public Put withData(byte[] data) {
        this.options.setData(data);
        return this;
    }
}
