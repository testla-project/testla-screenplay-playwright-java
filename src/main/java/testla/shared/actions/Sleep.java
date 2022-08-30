package testla.shared.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Sleep extends Action {

    private final Long millis;

    private Sleep(Long millis) {
        this.millis = millis;
    }

    public static Sleep forMillis(Long millis) {
        return new Sleep(millis);
    }
    @Override
    public Object performAs(IActor actor) {
        try {
            Thread.sleep(this.millis);
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }
        return null;
    }
}
