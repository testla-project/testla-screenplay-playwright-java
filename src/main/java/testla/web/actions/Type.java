package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Type specified input into an element specified by a selector string.
 */
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

    /**
     * find the specified selector and fill it.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        if(options == null) {
            BrowseTheWeb.as(actor).type(this.selector, this.input);
        } else {
            BrowseTheWeb.as(actor).type(this.selector, this.input, this.options);
        }
        return null;
    }

    /**
     * Finds the specified selector and will it with the specified input string.
     *
     * @param selector the selector.
     * @param input the input.
     */
    public static Type in(String selector, String input) {
        return new Type(selector, input);
    }

    /**
     * Finds the specified selector and will it with the specified input string.
     *
     * @param selector the selector.
     * @param input the input.
     * @param options advanced selector lookup options.
     */
    public static Type in(String selector, String input, SelectorOptions options) {
        return new Type(selector, input, options);
    }
}
