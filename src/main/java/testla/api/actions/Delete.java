package testla.api.actions;

import com.microsoft.playwright.options.RequestOptions;
import testla.screenplay.actor.IActor;
import testla.api.RequestMethod;
import testla.api.ResponseBodyFormat;
import testla.api.abilities.UseAPI;

import java.util.Map;

/**
 * Action Class. Send a HTTP DELETE Request.
 */
public class Delete extends ARequest {

    private final String url;
    private final RequestOptions options;
    private ResponseBodyFormat responseBodyFormat = ResponseBodyFormat.JSON;

    private Delete(String url) {
        this.url = url;
        this.options = RequestOptions.create();
    }

    /**
     * Send a HTTP DELETE request to the specified url.
     *
     * @param actor the actor that performs the request.
     */
    @Override
    public Object performAs(IActor actor) {
        return UseAPI.as(actor).sendRequest(RequestMethod.DELETE, this.url, this.options, this.responseBodyFormat);
    }

    /**
     * Send a HTTP DELETE request to the specified url.
     *
     * @param url the URL of the target.
     */
    public static Delete from(String url) {
        return new Delete(url);
    }

    /**
     * Add headers to the HTTP DELETE request to send.
     *
     * @param headers the headers.
     */
    public Delete withHeaders(Map<String, String> headers) {
        // replaced by Java compiler: headers.forEach((k,v) -> this.options.setHeader(k, v));
        headers.forEach(this.options::setHeader);
        return this;
    }

    /**
     * Set the format the response body should be returned as.
     *
     * @param responseBodyFormat the format of the response body.
     */
    public Delete withResponseBodyFormat(ResponseBodyFormat responseBodyFormat) {
        this.responseBodyFormat = responseBodyFormat;
        return this;
    }
}
