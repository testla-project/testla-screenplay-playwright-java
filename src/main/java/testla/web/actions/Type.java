package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

public class Type extends Action {

    private final String selector;
    private final String input;
    private final SelectorOptions options;

    private Type(String selector, String input) {
        this.selector = selector;
        this.input = input;
        this.options = null;
    }

    private Type(String selector, String input, SelectorOptions options) {
        this.selector = selector;
        this.input = input;
        this.options = options;
    }

    public Object performAs(IActor actor) {
        if(options == null) {
            BrowseTheWeb.as(actor).type(this.selector, this.input);
        } else {
            BrowseTheWeb.as(actor).type(this.selector, this.input, this.options);
        }
        return null;
    }

    public static Type in(String selector, String input) {
        return new Type(selector, input);
    }

    public static Type in(String selector, String input, SelectorOptions options) {
        return new Type(selector, input, options);
    }
}
