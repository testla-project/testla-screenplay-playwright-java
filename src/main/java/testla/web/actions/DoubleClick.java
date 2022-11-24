package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Click on an element specified by a selector string.
 */
public class DoubleClick extends Action {

    private final String selector;
    private final SelectorOptions options;

    private DoubleClick(String selector) {
        this.selector = selector;
        this.options = null;
    }

    private DoubleClick(String selector, SelectorOptions options) {
        this.selector = selector;
        this.options = options;
    }

    /**
     * specify which element should be clicked on
     *
     * @param selector the string representing the selector.
     */
    public static DoubleClick on(String selector) {
        return new DoubleClick(selector);
    }

    /**
     * specify which element should be clicked on
     *
     * @param selector the string representing the selector.
     * @param options advanced selector lookup options.
     */
    public static DoubleClick on(String selector, SelectorOptions options) {
        return new DoubleClick(selector, options);
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
