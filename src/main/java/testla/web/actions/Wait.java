package testla.web.actions;

import com.microsoft.playwright.options.LoadState;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

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

    public static Wait forLoadState(LoadState state) {
        return new Wait("loadState", state);
    }

    public static Wait forSelector(String selector) {
        return new Wait("selector", selector);
    }

    public static Wait forSelector(String selector, SelectorOptions options) {
        return new Wait("selector", selector, options);
    }
}
