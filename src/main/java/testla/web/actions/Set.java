package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Set either Session Storage Items or Local Storage Items on the Browser.
 */
public class Set extends Action {

    private final String mode;
    private final String payloadKey;
    private final Object payloadValue;

    private Set(String mode, String payloadKey, Object payloadValue) {
        this.mode = mode;
        this.payloadKey = payloadKey;
        this.payloadValue = payloadValue;
    }

    /**
     * Set a session storage item identified by the given key + value, creating a new key/value pair if none existed for key previously.
     *
     * @param key the key that specifies the item.
     * @param value the value of the item.
     */
    public static Set sessionStorageItem(String key, Object value) {
        return new Set("sessionStorage", key, value);
    }

    /**
     * Set a local storage item identified by the given key + value, creating a new key/value pair if none existed for key previously.
     *
     * @param key the key that specifies the item.
     * @param value the value of the item.
     */
    public static Set localStorageItem(String key, Object value) {
        return new Set("localStorage", key, value);
    }

    /**
     * set the specified storage item.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        switch (this.mode) {
            case "sessionStorage" -> {
                BrowseTheWeb.as(actor).setSessionStorageItem(this.payloadKey, this.payloadValue);
                return null;
            }
            case "localStorage" -> {
                BrowseTheWeb.as(actor).setLocalStorageItem(this.payloadKey, this.payloadValue);
                return null;
            }
            default -> throw new RuntimeException("Error: No match for Set.performAs()!");
        }
    }
}
