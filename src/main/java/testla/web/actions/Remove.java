package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Remove either Session Storage Items or Local Storage Items from the Browser.
 */
public class Remove extends Action {

    private final String mode;
    private final String payload;

    private Remove(String mode, String payload) {
        this.mode = mode;
        this.payload = payload;
    }

    /**
     * Remove a session storage item, if a key/value pair with the given key exists.
     *
     * @param key the key that specifies the item.
     */
    public static Remove sessionStorageItem(String key) {
        return new Remove("sessionStorage", key);
    }

    /**
     * Remove a local storage item, if a key/value pair with the given key exists.
     *
     * @param key the key that specifies the item.
     */
    public static Remove localStorageItem(String key) {
        return new Remove("localStorage", key);
    }

    /**
     * wait for either a specified loading state or for a selector to become visible/active.
     *
     * @param actor the actor.
     */
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
