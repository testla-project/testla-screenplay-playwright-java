package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Fill an element specified by a selector string with the specified input.
 */
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

    /**
     * find the specified selector and fill it.
     *
     * @param actor the actor.
     */
    public Object performAs(IActor actor) {
        if(options == null) {
            BrowseTheWeb.as(actor).fill(this.selector, this.input);
        } else {
            BrowseTheWeb.as(actor).fill(this.selector, this.input, this.options);
        }
        return null;
    }

    /**
     * Finds the specified selector and will it with the specified input string.
     *
     * @param selector the selector.
     * @param input the input.
     */
    public static Fill in(String selector, String input) {
        return new Fill(selector, input);
    }

    /**
     * Finds the specified selector and will it with the specified input string.
     *
     * @param selector the selector.
     * @param input the input.
     * @param options advanced selector lookup options.
     */
    public static Fill in(String selector, String input, SelectorOptions options) {
        return new Fill(selector, input, options);
    }
}
