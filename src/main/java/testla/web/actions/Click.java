package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Click on an element specified by a selector string.
 */
public class Click extends Action {

    private final String selector;
    private final SelectorOptions options;

    private Click(String selector) {
        this.selector = selector;
        this.options = null;
    }

    private Click(String selector, SelectorOptions options) {
        this.selector = selector;
        this.options = options;
    }

    /**
     * specify which element should be clicked on
     *
     * @param selector the string representing the selector.
     */
    public static Click on(String selector) {
        return new Click(selector);
    }

    /**
     * specify which element should be clicked on
     *
     * @param selector the string representing the selector.
     * @param options advanced selector lookup options.
     */
    public static Click on(String selector, SelectorOptions options) {
        return new Click(selector, options);
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
