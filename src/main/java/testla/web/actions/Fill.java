package testla.web.actions;

import com.microsoft.playwright.Locator;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Fill an element specified by a selector string with the specified input.
 */
public class Fill extends Action {

    private final String selector;
    private final Locator locator;
    private final String input;
    private final SelectorOptions options;

    private Fill(String selector, String input) {
        this.selector = selector;
        this.locator = null;
        this.input = input;
        this.options = null;
    }

    private Fill(String selector, String input, SelectorOptions options) {
        this.selector = selector;
        this.locator = null;
        this.input = input;
        this.options = options;
    }

    private Fill(Locator locator, String input) {
        this.selector = null;
        this.locator = locator;
        this.input = input;
        this.options = null;
    }

    private Fill(Locator locator, String input, SelectorOptions options) {
        this.selector = null;
        this.locator = locator;
        this.input = input;
        this.options = options;
    }

    /**
     * find the specified selector and fill it.
     *
     * @param actor the actor.
     */
    public Object performAs(IActor actor) {
        if (this.options == null) {
            if (this.selector != null) {
                BrowseTheWeb.as(actor).fill(this.selector, this.input);
            } else if (this.locator != null) {
                BrowseTheWeb.as(actor).fill(this.locator, this.input);
            }
        } else {
            if (this.selector != null) {
                BrowseTheWeb.as(actor).fill(this.selector, this.input, this.options);
            } else if (this.locator != null) {
                BrowseTheWeb.as(actor).fill(this.locator, this.input, this.options);
            }
        }
        return null;
    }

    /**
     * Finds the specified selector and fill it with the specified input string.
     *
     * @param selector the selector.
     * @param input the input.
     */
    public static Fill in(String selector, String input) {
        return new Fill(selector, input);
    }

    /**
     * Finds the specified selector and fill it with the specified input string.
     *
     * @param selector the selector.
     * @param input the input.
     * @param options advanced selector lookup options.
     */
    public static Fill in(String selector, String input, SelectorOptions options) {
        return new Fill(selector, input, options);
    }

    /**
     * Finds the specified locator and fill it with the specified input string.
     *
     * @param locator the locator.
     * @param input the input.
     */
    public static Fill in(Locator locator, String input) {
        return new Fill(locator, input);
    }

    /**
     * Finds the specified selector and fill it with the specified input string.
     *
     * @param locator the locator.
     * @param input the input.
     * @param options advanced selector lookup options.
     */
    public static Fill in(Locator locator, String input, SelectorOptions options) {
        return new Fill(locator, input, options);
    }
}
