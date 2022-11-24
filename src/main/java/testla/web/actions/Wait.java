package testla.web.actions;

import com.microsoft.playwright.options.LoadState;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Wait for either a specified loading state or for a selector to become visible/active.
 */
public class Wait extends Action {

    private final String mode;
    private String selector;
    private final SelectorOptions options;
    private LoadState state;

    private Wait(String mode, LoadState state) {
        this.mode = mode;
        this.state = state;
        this.options = null;
    }

    private Wait(String mode, String selector){
        this.mode = mode;
        this.selector = selector;
        this.options = null;
    }

    private Wait(String mode, String selector, SelectorOptions options){
        this.mode = mode;
        this.selector = selector;
        this.options = options;
    }

    /**
     * wait for either a specified loading state or for a selector to become visible/active.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        switch (this.mode) {
            case "loadState" -> {
                BrowseTheWeb.as(actor).waitForLoadState(this.state);
                return null;
            }
            case "selector" -> {
                if(this.options == null) {
                    BrowseTheWeb.as(actor).waitForSelector(this.selector);
                } else {
                    BrowseTheWeb.as(actor).waitForSelector(this.selector, this.options);
                }
                return null;
            }
            default -> throw new RuntimeException("Error: no match for Wait.performAs()!");
        }
    }

    /**
     * Wait for a specific status of the page.
     *
     * @param state either 'load', 'domcontentloaded' or 'networkidle'
     */
    public static Wait forLoadState(LoadState state) {
        return new Wait("loadState", state);
    }

    /**
     * Wait for a specific selector to exist.
     *
     * @param selector the selector.
     */
    public static Wait forSelector(String selector) {
        return new Wait("selector", selector);
    }

    /**
     * Wait for a specific selector to exist.
     *
     * @param selector the selector.
     * @param options advanced selector lookup options.
     */
    public static Wait forSelector(String selector, SelectorOptions options) {
        return new Wait("selector", selector, options);
    }
}
