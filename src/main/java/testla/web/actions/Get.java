package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

import java.util.List;

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

    public static Get cookies() {
        return new Get("cookies");
    }

    public static Get cookies(List<String> urls) {
        return new Get("cookies", urls);
    }

    public static Get cookies(String url) {
        return new Get("cookies", url);
    }

    public static Get sessionStorageItem(String key) {
        return new Get("sessionStorage", key);
    }

    public static Get localStorageItem(String key) {
        return new Get("localStorage", key);
    }
}
