package testla.shared.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;

/**
 * Action Class. Pauses further test execution for a while. Does not require a particular Ability.
 */
public class Sleep extends Action {

    private final Long millis;


    private Sleep(Long millis) {
        this.millis = millis;
    }

    /**
     * Performs the sleep.
     */
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

    /**
     * Pause the execution of further test steps for a given interval in milliseconds.
     *
     * @param millis interval in milliseconds.
     */
    public static Sleep For(Long millis) {
        return new Sleep(millis);
    }
}
