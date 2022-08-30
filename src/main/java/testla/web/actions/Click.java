package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

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

    public static Click on(String selector) {
        return new Click(selector);
    }

    public static Click on(String selector, SelectorOptions options) {
        return new Click(selector, options);
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
