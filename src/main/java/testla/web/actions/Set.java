package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.abilities.BrowseTheWeb;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Set extends Action {

    private final String mode;
    private String payloadKey;
    private Object payloadValue;

    private Set(String mode, String payloadKey, Object payloadValue) {
        this.mode = mode;
        this.payloadKey = payloadKey;
        this.payloadValue = payloadValue;
    }

    public static Set sessionStorageItem(String key, Object value) {
        return new Set("sessionStorage", key, value);
    }

    public static Set localStorageItem(String key, Object value) {
        return new Set("localStorage", key, value);
    }

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
