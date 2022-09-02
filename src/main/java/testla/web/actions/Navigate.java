package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

/**
* Action Class. Navigate to a URL using the specified url string.
*/
public class Navigate extends Action {

    private final String url;

    private Navigate(String url) {
        this.url = url;
    }

    /**
     * navigate to the specified URL.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        return BrowseTheWeb.as(actor).navigate(this.url);
    }

    /**
     * Use the page to navigate to a certain URL.
     *
     * @param url the url which should be accessed.
     */
    public static Navigate to(String url) {
        return new Navigate(url);
    }
}
