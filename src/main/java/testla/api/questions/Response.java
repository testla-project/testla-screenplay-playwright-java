package testla.api.questions;

import testla.api.Modes;
import testla.api.abilities.UseAPI;
import testla.screenplay.actor.IActor;
import testla.screenplay.question.Question;

import java.util.Map;

/**
 * Question Class. Verify certain aspects of an API Response.
 */
public class Response extends Question<Boolean> {

    // private testla.api.Response response = new testla.api.Response(null, 0, null, 0.0);

    private testla.api.Response response;
    private final String checkMode;
    private String mode;

    private Object payload;
    private Class<?> oldType; // this.oldType = int;

    private Response(String checkMode) {
        this.checkMode = checkMode;
    }

    /**
     * make the Question check for the positive.
     */
    public static Response has() {
        return new Response("has");
    }

    /**
     * make the Question check for the negative.
     */
    public static Response hasNot() {
        return new Response("hasNot");
    }

    /**
     * Verify if the given status is equal to the given response's status.
     *
     * @param response the response to check.
     * @param statusCode the expected status code.
     */
    public Response statusCode(testla.api.Response response, int statusCode) {
        this.response = response;
        this.mode = "status";
        this.payload = statusCode;
        this.oldType = int.class;

        return this;
    }

    /**
     * Verify if the given body is equal to the given response's body.
     *
     * @param response the response to check.
     * @param body the expected body.
     */
    public Response body(testla.api.Response response, String body) {
        this.response = response;
        this.mode = "body";
        this.payload = body;
        this.oldType = String.class;

        return this;
    }

    /**
     * Verify if the given body is equal to the given response's body.
     *
     * @param response the response to check.
     * @param body the expected body.
     */
    public Response body(testla.api.Response response, Object body) {
        this.response = response;
        this.mode = "body";
        this.payload = body;
        this.oldType = Object.class;

        return this;
    }

    /**
     * Verify if the given body is equal to the given response's body.
     *
     * @param response the response to check.
     * @param body the expected body.
     */
    public Response body(testla.api.Response response, byte[] body) {
        this.response = response;
        this.mode = "body";
        this.payload = body;
        this.oldType = byte.class;

        return this;
    }

    /**
     * Verify if the given headers are included in the given response.
     *
     * @param response the response to check.
     * @param headers the expected header.
     */
    public Response headers(testla.api.Response response, Map<String, String> headers) {
        this.response = response;
        this.mode = "headers";
        this.payload = headers;
        this.oldType = Map.class;

        return this;
    }

    /**
     * Verify if the response (including receiving body) was received within a given duration.
     *
     * @param response the response to check
     * @param duration expected duration (in milliseconds) not to be exceeded
     */
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
                return UseAPI.as(actor).checkStatus(this.response, ((int) this.payload), this.checkMode.equals("has") ? Modes.EQUAL : Modes.UNEQUAL);
            }
            case "body" -> {
                if(this.oldType == String.class) {
                    return UseAPI.as(actor).checkBody(this.response, ((String) this.payload), this.checkMode.equals("has") ? Modes.EQUAL : Modes.UNEQUAL);
                }
                if(this.oldType == Object.class) {
                    return UseAPI.as(actor).checkBody(this.response, this.payload, this.checkMode.equals("has") ? Modes.EQUAL : Modes.UNEQUAL);
                }
                if(this.oldType == byte.class) {
                    return UseAPI.as(actor).checkBody(this.response, ((byte[]) this.payload), this.checkMode.equals("has") ? Modes.EQUAL : Modes.UNEQUAL);
                }
            }
            case "headers" -> {
                return UseAPI.as(actor).checkHeaders(this.response, ((Map<String, String>) this.payload), this.checkMode.equals("has") ? Modes.EQUAL : Modes.UNEQUAL);
            }
            case "duration" -> {
                return UseAPI.as(actor).checkDuration(this.response, ((long) this.payload), this.checkMode.equals("has") ? Modes.LESS_OR_EQUAL : Modes.GREATER_THAN);
            }
            default -> throw new RuntimeException("Error: Response.answeredBy(): unknown mode");
        }
        return true;
    }
}
