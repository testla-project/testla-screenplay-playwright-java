package testla.api.questions;

import testla.api.abilities.UseAPI;
import testla.screenplay.actor.IActor;
import testla.screenplay.question.Question;

import java.util.Map;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Response extends Question<Boolean> {

    // private testla.api.Response response = new testla.api.Response(null, 0, null, 0.0);

    private testla.api.Response response;
    private final String checkMode;
    private String mode;

    private Object payload;
    private Class<?> oldType; // this.oldType = int;

    public Response(String checkMode) {
        this.checkMode = checkMode;
    }

    public static Response has() {
        return new Response("has");
    }

    public static Response hasNot() {
        return new Response("hasNot");
    }

    public Response statusCode(testla.api.Response response, int statusCode) {
        this.response = response;
        this.mode = "status";
        this.payload = statusCode;
        this.oldType = int.class;

        return this;
    }

    public Response body(testla.api.Response response, String body) {
        this.response = response;
        this.mode = "body";
        this.payload = body;
        this.oldType = String.class;

        return this;
    }

    public Response body(testla.api.Response response, Object body) {
        this.response = response;
        this.mode = "body";
        this.payload = body;
        this.oldType = Object.class;

        return this;
    }

    public Response body(testla.api.Response response, byte[] body) {
        this.response = response;
        this.mode = "body";
        this.payload = body;
        this.oldType = byte.class;

        return this;
    }

    public Response headers(testla.api.Response response, Map<String, String> headers) {
        this.response = response;
        this.mode = "headers";
        this.payload = headers;
        this.oldType = Map.class;

        return this;
    }

    public Response beenReceivedWithin(testla.api.Response response, long duration) {
        this.response = response;
        this.mode = "duration";
        this.payload = duration;
        this.oldType = long.class;

        return this;
    }

    @Override
    public Boolean answeredBy(IActor actor) {
        switch (this.mode) {
            case "status" -> {
                return UseAPI.as(actor).checkStatus(this.response, ((int) this.payload), this.checkMode.equals("has") ? "equal" : "unequal");
            }
            case "body" -> {
                if(this.oldType == String.class) {
                    return UseAPI.as(actor).checkBody(this.response, ((String) this.payload), this.checkMode.equals("has") ? "equal" : "unequal");
                }
                if(this.oldType == Object.class) {
                    return UseAPI.as(actor).checkBody(this.response, this.payload, this.checkMode.equals("has") ? "equal" : "unequal");
                }
                if(this.oldType == byte.class) {
                    return UseAPI.as(actor).checkBody(this.response, ((byte[]) this.payload), this.checkMode.equals("has") ? "equal" : "unequal");
                }
            }
            case "headers" -> {
                return UseAPI.as(actor).checkHeaders(this.response, ((Map<String, String>) this.payload), this.checkMode.equals("has") ? "equal" : "unequal");
            }
            case "duration" -> {
                return UseAPI.as(actor).checkDuration(this.response, ((long) this.payload), this.checkMode.equals("has") ? "lessOrEqual" : "greater");
            }
            default -> throw new RuntimeException("Error: Response.answeredBy(): unknown mode");
        }
        return true;
    }
}
