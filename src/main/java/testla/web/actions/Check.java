package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
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

    public static Check element(String selector) {
        return new Check(selector);
    }

    public static Check element(String selector, SelectorOptions options) {
        return new Check(selector, options);
    }
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
