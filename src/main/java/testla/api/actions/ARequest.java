package testla.api.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;

import java.util.Map;

public abstract class ARequest extends Action {

    protected Map<String, String> headers;

    public abstract Object performAs(IActor actor);
}
