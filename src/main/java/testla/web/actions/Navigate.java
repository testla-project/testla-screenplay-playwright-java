package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Navigate extends Action {

    private final String url;

    private Navigate(String url) {
        this.url = url;
    }

    @Override
    public Object performAs(IActor actor) {
        return BrowseTheWeb.as(actor).navigate(this.url);
    }

    public static Navigate to(String url) {
        return new Navigate(url);
    }
}
