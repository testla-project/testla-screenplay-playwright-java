package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Add Cookies to the Browser.
 */
public class Clear extends Action {

    /**
     * Clear all cookies.
     */
    public static Clear cookies() {
        return new Clear();
    }

    /**
     * add the cookies to the browser.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        BrowseTheWeb.as(actor).clearCookies();
        return null;
    }
}
