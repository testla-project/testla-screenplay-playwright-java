package testla.api.abilities;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import testla.api.Modes;
import testla.api.RequestMethod;
import testla.api.Response;
import testla.api.ResponseBodyFormat;
import testla.screenplay.ability.Ability;
import testla.screenplay.actor.IActor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class represents the actor's ability to send API requests.
 */
public class UseAPI extends Ability {

    private final APIRequestContext requestContext;
    private static UseAPI instance;

    private UseAPI(APIRequestContext requestContext){
        this.requestContext = requestContext;
    }

    /**
     * Get the request context object
     *
     *  @returns ApiRequestContext
     */
    public APIRequestContext getRequestContext() {
        return this.requestContext;
    }

    /**
     * Initialize this Ability by passing an already existing Playwright APIRequestContext object.
     *
     * @param requestContext the Playwright APIRequestContext that will be used to send REST requests.
     * @returns UseAPI
     */
    public static UseAPI using(APIRequestContext requestContext) {
        UseAPI.instance = new UseAPI(requestContext);
        return UseAPI.instance;
    }

    /**
     * Use this Ability as an Actor.
     *
     * @param actor the actor.
     * @returns UseAPI
     */
    public static UseAPI as(IActor actor) {
        return (UseAPI) actor.withAbilityTo(UseAPI.instance);
    }

    /**
     * Send a HTTP request (GET, POST, PATCH, PUT, HEAD or DELETE) to the specified url. Headers and data can also be sent.
     *
     * @param method GET, POST, PATCH, PUT, HEAD or DELETE.
     * @param url the full URL to the target.
     * @param options optional headers and data to send.
     * @param responseBodyFormat (optional) specify the desired format the response body should be in.
     * @returns a Response object consisting of status, body and headers.
     */
    public Response sendRequest(RequestMethod method, String url, RequestOptions options, ResponseBodyFormat responseBodyFormat) {
        long start = System.currentTimeMillis();

        APIResponse res = switch(method) {
            case GET        -> this.requestContext.get(url, options);
            case POST       -> this.requestContext.post(url, options);
            case PATCH      -> this.requestContext.patch(url, options);
            case PUT        -> this.requestContext.put(url, options);
            case HEAD       -> this.requestContext.head(url, options);
            case DELETE     -> this.requestContext.delete(url, options);
        };

        long end = System.currentTimeMillis();

        Object resBody = switch (responseBodyFormat) {
            case TEXT       -> res.text();
            case BUFFER     -> res.body();
            case NONE       -> null;
            // case JSON -> no res.json() ?
            // ToDo: Validate if Byte Array can be converted to JSON
            default         -> res.text();
        };

        return new Response(resBody, res.status(), res.headers(), end-start);
    }

    /**
     * Verify if the given status is equal or unequal to the given response's status.
     *
     * @param mode the result to check for.
     * @param response the response to check.
     * @param status the status to check.
     * @returns true if the status is equal/unequal as expected.
     */
    public boolean checkStatus(Response response, int status, Modes mode) {
        switch (mode) {
            case EQUAL      -> assertEquals(response.status, status);
            case UNEQUAL    -> assertNotEquals(response.status, status);
            default         -> throw new RuntimeException("Wrong mode for checkStatus(): " + mode +
                                " Please use EQUAL or UNEQUAL.");
        }
        return true;
    }

    /**
     * Verify if the given body is equal or unequal to the given response's body.
     *
     * @param mode the result to check for.
     * @param response the response to check.
     * @param body the body to check.
     * @returns true if the body equal/unequal as expected.
     */
    public boolean checkBody(Response response, String body, Modes mode) {
        switch (mode) {
            case EQUAL      -> assertEquals(response.body.toString(), body);
            case UNEQUAL    -> assertNotEquals(response.body.toString(), body);
            default         -> throw new RuntimeException("Wrong mode for checkBody(): " + mode +
                    " Please use EQUAL or UNEQUAL.");
        }
        return true;
    }

    /**
     * Verify if the given body is equal or unequal to the given response's body.
     *
     * @param mode the result to check for.
     * @param response the response to check.
     * @param body the body to check.
     * @returns true if the body equal/unequal as expected.
     */
    public boolean checkBody(Response response, Object body, Modes mode) {
        //ToDo: Use JsonMapper Jackson
        return true;
    }

    /**
     * Verify if the given body is equal or unequal to the given response's body.
     *
     * @param mode the result to check for.
     * @param response the response to check.
     * @param body the body to check.
     * @returns true if the body equal/unequal as expected.
     */
    public boolean checkBody(Response response, byte[] body, Modes mode) {
        // copied from https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(response.body);

            switch (mode) {
                case EQUAL      -> assertEquals(bos.toByteArray(), body);
                case UNEQUAL    -> assertNotEquals(bos.toByteArray(), body);
                default         -> throw new RuntimeException("Wrong mode for checkBody(): " + mode +
                        " Please use EQUAL or UNEQUAL.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Verify if the given headers are included/excluded in the given response.
     *
     * @param mode the result to check for.
     * @param response the response to check.
     * @param headers the headers to check.
     * @returns true if the headers are is included/excluded as expected.
     */
    public boolean checkHeaders(Response response, Map<String, String> headers, Modes mode) {
        // headers should be subset of response.headers
        switch (mode) {
            case INCLUDED   -> assertTrue(response.headers.entrySet().containsAll(headers.entrySet()));
            case EXCLUDED   -> assertFalse(response.headers.entrySet().containsAll(headers.entrySet()));
            default         -> throw new RuntimeException("Wrong mode for checkHeaders(): " + mode +
                    " Please use INCLUDED or EXCLUDED.");
        }
        return true;
    }

    /**
     * Verify if the response (including receiving body) was received within a given duration or not.
     *
     * @param mode the result to check for.
     * @param response the response to check
     * @param duration expected duration (in milliseconds) not to be exceeded
     * @returns true if response was received within given duration, false otherwise
     */
    public boolean checkDuration(Response response, long duration, Modes mode) {
        switch (mode) {
            case LESS_OR_EQUAL  -> assertTrue(response.duration <= duration);
            case GREATER_THAN   -> assertFalse(response.duration <= duration);
            default         -> throw new RuntimeException("Wrong mode for checkHeaders(): " + mode +
                    " Please use LESS_OR_EQUAL or GREATER_THAN.");
        }
        return true;
    }
}
