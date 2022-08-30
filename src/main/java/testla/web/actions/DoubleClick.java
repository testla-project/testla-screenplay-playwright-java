package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

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

    public static DoubleClick on(String selector) {
        return new DoubleClick(selector);
    }

    public static DoubleClick on(String selector, SelectorOptions options) {
        return new DoubleClick(selector, options);
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
