package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Press the specified key on the keyboard.
 */
public class Press extends Action {

    private final String input;


    private Press(String input) {
        this.input = input;
    }

    /**
     * Press a key on the keyboard. (or multiple keys with +, e.g. Shift+A)
     *
     * @param input the key(s) to press.
     */
    public static Press key(String input) {
        return new Press(input);
    }

    /**
     * Press the specified key.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        BrowseTheWeb.as(actor).press(this.input);
        return null;
    }
}
