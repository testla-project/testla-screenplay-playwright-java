package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Remove extends Action {
    private final String mode;
    private String payload;

    private Remove(String mode, String payload) {
        this.mode = mode;
        this.payload = payload;
    }

    public static Remove sessionStorageItem(String key) {
        return new Remove("sessionStorage", key);
    }

    public static Remove localStorageItem(String key) {
        return new Remove("localStorage", key);
    }

    @Override
    public Object performAs(IActor actor) {
        switch (this.mode) {
            case "sessionStorage" -> {
                BrowseTheWeb.as(actor).removeSessionStorageItem(this.payload);
                return null;
            }
            case "localStorage" -> {
                BrowseTheWeb.as(actor).removeLocalStorageItem(this.payload);
                return null;
            }
            default -> throw new RuntimeException("Error: No match for Remove.performAs()!");
        }
    }
}
