package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

import java.util.List;

/**
 * Action Class. Get either Cookies, Session Storage Items or Local Storage Items from the Browser.
 */
public class Get extends Action {

    private final String mode;
    private List<String> urls;
    private String payload;


    private Get(String mode) {
        this.mode = mode;
    }

    private Get(String mode, List<String> urls) {
        this.mode = mode;
        this.urls = urls;
    }

    private Get(String mode, String payload) {
        this.mode = mode;
        this.payload = payload;
    }

    /**
     * Wait for either a specified loading state or for a selector to become visible/active.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        switch (this.mode) {
            case "cookies":
                if (this.urls != null) {
                    return BrowseTheWeb.as(actor).getCookies(this.urls);
                } else if (this.payload != null) {
                    return BrowseTheWeb.as(actor).getCookies(this.payload);
                } else {
                    return BrowseTheWeb.as(actor).getCookies();
                }
            case "sessionStorage":
                return BrowseTheWeb.as(actor).getSessionStorageItem(this.payload);
            case "localStorage":
                return BrowseTheWeb.as(actor).getLocalStorageItem(this.payload);
            default:
                throw new RuntimeException("Error: No match for Get.performAs()!");
        }
    }

    /**
     * Get all cookies.
     */
    public static Get cookies() {
        return new Get("cookies");
    }

    /**
     * Get the specified cookies.
     *
     * @param urls If URLs are specified, only cookies that affect those URLs are returned. If no URLs are specified, this all cookies are returned.
     */
    public static Get cookies(List<String> urls) {
        return new Get("cookies", urls);
    }

    /**
     * Get the specified cookies.
     *
     * @param url If URLs are specified, only cookies that affect those URLs are returned. If no URLs are specified, this all cookies are returned.
     */
    public static Get cookies(String url) {
        return new Get("cookies", url);
    }

    /**
     * Get a session storage item.
     *
     * @param key the key that specifies the item.
     */
    public static Get sessionStorageItem(String key) {
        return new Get("sessionStorage", key);
    }

    /**
     * Get a local storage item.
     *
     * @param key the key that specifies the item.
     */
    public static Get localStorageItem(String key) {
        return new Get("localStorage", key);
    }
}
