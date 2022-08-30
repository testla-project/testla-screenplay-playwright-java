package testla.api.abilities;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import testla.api.RequestMethod;
import testla.api.Response;
import testla.api.ResponseBodyFormat;
import testla.screenplay.ability.Ability;
import testla.screenplay.actor.IActor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UseAPI extends Ability {

    private final APIRequestContext requestContext;
    private static UseAPI instance;

    private UseAPI(APIRequestContext requestContext){
        this.requestContext = requestContext;
    }

    public static UseAPI using(APIRequestContext requestContext) {
        UseAPI.instance = new UseAPI(requestContext);
        return UseAPI.instance;
    }

    public static UseAPI as(IActor actor) {
        return (UseAPI) actor.withAbilityTo(UseAPI.instance);
    }

    public Response sendRequest(RequestMethod method, String url, RequestOptions options, ResponseBodyFormat responseBodyFormat) {
        long start = System.currentTimeMillis();

        APIResponse res;
        switch(method) {
            case GET -> {
                res = this.requestContext.get(url, options);
            }
            case POST -> {
                res = this.requestContext.post(url, options);
            }
            case PATCH -> {
                res = this.requestContext.patch(url, options);
            }
            case PUT -> {
                res = this.requestContext.put(url, options);
            }
            case HEAD -> {
                res = this.requestContext.head(url, options);
            }
            case DELETE -> {
                res = this.requestContext.delete(url, options);
            }
            default -> throw new RuntimeException("Error: unknown RequestMethod!");
        }

        long end = System.currentTimeMillis();

        Object resBody;
        switch (responseBodyFormat) {
            case TEXT -> {
                resBody = res.text();
            }
            case BUFFER -> {
                resBody = res.body();
            }
            case NONE -> {
                resBody = null;
            }
            // case JSON -> no res.json() ?
            // ToDo: Validate if Byte Array can be converted to JSON
            default -> resBody = res.text();
        }

        return new Response(resBody, res.status(), res.headers(), end-start);
    }

    // ToDo: Mode as Enum
    public boolean checkStatus(Response response, int status, String mode) {
        // ToDo: TEST IT!
        assertEquals(response.status == status, mode.equals("equal"));
        return true;
    }

    // ToDo: Mode as Enum
    public boolean checkBody(Response response, String body, String mode) {
        assertEquals(response.body.toString().equals(body), mode.equals("equal"));
        return true;
    }

    public boolean checkBody(Response response, Object body, String mode) {
        //ToDo: Use JsonMapper Jackson
        return true;
    }

    public boolean checkBody(Response response, byte[] body, String mode) {
        // copied from https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(response.body);
            assertEquals(bos.toByteArray() == body, mode.equals("equal"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean checkHeaders(Response response, Map<String, String> headers, String mode) {
        // headers should be subset of response.headers
        assertEquals(response.headers.entrySet().containsAll(headers.entrySet()), mode.equals("included"));
        return true;
    }

    public boolean checkDuration(Response response, long duration, String mode) {
        assertEquals(response.duration <= duration, mode.equals("lessOrEqual"));
        return true;
    }
}
