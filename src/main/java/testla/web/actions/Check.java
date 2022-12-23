package testla.web.actions;

import com.microsoft.playwright.Locator;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Check a checkbox specified by a selector string.
 */
public class Check extends Action {

    private final String selector;
    private final Locator locator;
    private final SelectorOptions options;

    private Check(String selector) {
        this.selector = selector;
        this.locator = null;
        this.options = null;
    }

    private Check(String selector, SelectorOptions options) {
        this.selector = selector;
        this.locator = null;
        this.options = options;
    }

    private Check(Locator locator) {
        this.selector = null;
        this.locator = locator;
        this.options = null;
    }

    private Check(Locator locator, SelectorOptions options) {
        this.selector = null;
        this.locator = locator;
        this.options = options;
    }

    /**
     * Specify which element should be checked.
     *
     * @param selector the string representing the selector.
     */
    public static Check element(String selector) {
        return new Check(selector);
    }

    /**
     * Specify which element should be checked.
     *
     * @param selector the string representing the selector.
     * @param options advanced selector lookup options.
     */
    public static Check element(String selector, SelectorOptions options) {
        return new Check(selector, options);
    }

    /**
     * Specify which element should be checked.
     *
     * @param locator the existing Playwright locator.
     */
    public static Check element(Locator locator) {
        return new Check(locator);
    }

    /**
     * Specify which element should be checked.
     *
     * @param locator the existing Playwright locator.
     * @param options advanced selector lookup options.
     */
    public static Check element(Locator locator, SelectorOptions options) {
        return new Check(locator, options);
    }


    /**
     * find the specified selector and click on it.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        if (this.options == null) {
            if (this.selector != null) {
                BrowseTheWeb.as(actor).checkBox(this.selector);
            } else if (this.locator != null) {
                BrowseTheWeb.as(actor).checkBox(this.locator);
            }
        } else {
            if (this.selector != null) {
                BrowseTheWeb.as(actor).checkBox(this.selector, this.options);
            } else if (this.locator != null) {
                BrowseTheWeb.as(actor).checkBox(this.locator, this.options);
            }
        }
        return null;
    }
}
