package testla.api.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;

import java.util.Map;


/**
 * Abstract parent class for all HTTP request methods. This class extends the testla Action.
 */
public abstract class ARequest extends Action {

    /**
     * HTTP headers to send with the request.
     */
    protected Map<String, String> headers;

    public abstract Object performAs(IActor actor);
}
