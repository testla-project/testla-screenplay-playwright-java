package testla.web.actions;

import com.microsoft.playwright.options.Cookie;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

import java.util.List;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Add extends Action {

    private final List<Cookie> cookies;

    private Add(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    public static Add cookies(List<Cookie> cookies) {
        return new Add(cookies);
    }

    @Override
    public Object performAs(IActor actor) {
        BrowseTheWeb.as(actor).addCookies(this.cookies);
        return null;
    }
}
