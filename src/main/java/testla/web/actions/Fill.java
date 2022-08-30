package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

public class Fill extends Action {

    private final String selector;
    private final String input;
    private final SelectorOptions options;

    private Fill(String selector, String input) {
        this.selector = selector;
        this.input = input;
        this.options = null;
    }

    private Fill(String selector, String input, SelectorOptions options) {
        this.selector = selector;
        this.input = input;
        this.options = options;
    }

    public Object performAs(IActor actor) {
        if(options == null) {
            BrowseTheWeb.as(actor).fill(this.selector, this.input);
        } else {
            BrowseTheWeb.as(actor).fill(this.selector, this.input, this.options);
        }
        return null;
    }

    public static Fill in(String selector, String input) {
        return new Fill(selector, input);
    }

    public static Fill in(String selector, String input, SelectorOptions options) {
        return new Fill(selector, input, options);
    }
}
