package testla.api.actions;

import com.microsoft.playwright.options.RequestOptions;
import testla.screenplay.actor.IActor;
import testla.api.RequestMethod;
import testla.api.ResponseBodyFormat;
import testla.api.abilities.UseAPI;

import java.util.Map;

/**
 * Action Class. Send a HTTP HEAD Request.
 */
public class Head extends ARequest {

    private final String url;
    private final RequestOptions options;

    private Head(String url) {
        this.url = url;
        this.options = RequestOptions.create();
    }

    /**
     * Send a HTTP HEAD request to the specified url.
     *
     * @param actor the actor that performs the request.
     */
    @Override
    public Object performAs(IActor actor) {
        return UseAPI.as(actor).sendRequest(RequestMethod.HEAD, this.url, this.options, ResponseBodyFormat.NONE);
    }

    /**
     * Send a HTTP head request to the specified url.
     *
     * @param url the URL of the target.
     */
    public static Head from(String url) {
        return new Head(url);
    }

    /**
     * Add headers to the HTTP HEAD request to send.
     *
     * @param headers the headers.
     */
    public Head withHeaders(Map<String, String> headers) {
        // replaced by Java compiler: headers.forEach((k,v) -> this.options.setHeader(k, v));
        headers.forEach(this.options::setHeader);
        return this;
    }
}
