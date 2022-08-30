package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Press extends Action {

    private final String input;
    private Press(String input) {
        this.input = input;
    }

    public static Press key(String input) {
        return new Press(input);
    }

    @Override
    public Object performAs(IActor actor) {
        BrowseTheWeb.as(actor).press(this.input);
        return null;
    }
}
