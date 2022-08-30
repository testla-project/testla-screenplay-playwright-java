package testla.api.actions;

import com.microsoft.playwright.options.RequestOptions;
import testla.screenplay.actor.IActor;
import testla.api.RequestMethod;
import testla.api.ResponseBodyFormat;
import testla.api.abilities.UseAPI;

import java.util.Map;

public class Patch extends ARequest {

    private final String url;

    private final RequestOptions options;

    private ResponseBodyFormat responseBodyFormat = ResponseBodyFormat.JSON;

    private Patch(String url) {
        this.url = url;
        this.options = RequestOptions.create();
    }

    @Override
    public Object performAs(IActor actor) {
        return UseAPI.as(actor).sendRequest(RequestMethod.PATCH, this.url, this.options, this.responseBodyFormat);
    }

    public static Patch to(String url) {
        return new Patch(url);
    }

    public Patch withHeaders(Map<String, String> headers) {
        // replaced by Java compiler: headers.forEach((k,v) -> this.options.setHeader(k, v));
        headers.forEach(this.options::setHeader);
        return this;
    }

    public Patch withResponseBodyFormat(ResponseBodyFormat responseBodyFormat) {
        this.responseBodyFormat = responseBodyFormat;
        return this;
    }

    public Patch withData(Object data) {
        this.options.setData(data);
        return this;
    }

    public Patch withData(String data) {
        this.options.setData(data);
        return this;
    }

    public Patch withData(byte[] data) {
        this.options.setData(data);
        return this;
    }
}
