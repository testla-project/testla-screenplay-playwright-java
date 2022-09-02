package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Check a checkbox specified by a selector string.
 */
public class Check extends Action {

    private final String selector;
    private final SelectorOptions options;

    private Check(String selector) {
        this.selector = selector;
        this.options = null;
    }

    private Check(String selector, SelectorOptions options) {
        this.selector = selector;
        this.options = options;
    }

    /**
     * specify which element should be clicked on
     *
     * @param selector the string representing the selector.
     */
    public static Check element(String selector) {
        return new Check(selector);
    }

    /**
     * specify which element should be clicked on
     *
     * @param selector the string representing the selector.
     * @param options advanced selector lookup options.
     */
    public static Check element(String selector, SelectorOptions options) {
        return new Check(selector, options);
    }

    /**
     * find the specified selector and click on it.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        if (this.options == null) {
            BrowseTheWeb.as(actor).checkBox(this.selector);
        } else {
            BrowseTheWeb.as(actor).checkBox(this.selector, this.options);
        }
        return null;
    }
}
