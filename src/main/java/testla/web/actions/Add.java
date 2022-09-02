package testla.web.actions;

import com.microsoft.playwright.options.Cookie;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

import java.util.List;

/**
 * Action Class. Add Cookies to the Browser.
 */
public class Add extends Action {

    private final List<Cookie> cookies;

    private Add(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    /**
     * Add the specified cookies.
     *
     * @param cookies the cookies to add.
     */
    public static Add cookies(List<Cookie> cookies) {
        return new Add(cookies);
    }

    /**
     * add the cookies to the browser.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        BrowseTheWeb.as(actor).addCookies(this.cookies);
        return null;
    }
}
