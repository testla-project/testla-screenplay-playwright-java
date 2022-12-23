package testla.web.actions;

import com.microsoft.playwright.Locator;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Click on an element specified by a selector string.
 */
public class Click extends Action {

    private final String selector;
    private final Locator locator;
    private final SelectorOptions options;

    private Click(String selector) {
        this.selector = selector;
        this.locator = null;
        this.options = null;
    }

    private Click(String selector, SelectorOptions options) {
        this.selector = selector;
        this.locator = null;
        this.options = options;
    }

    private Click(Locator locator) {
        this.selector = null;
        this.locator = locator;
        this.options = null;
    }

    private Click(Locator locator, SelectorOptions options) {
        this.selector = null;
        this.locator = locator;
        this.options = options;
    }

    /**
     * Click on the specified element.
     *
     * @param selector the string representing the selector.
     */
    public static Click on(String selector) {
        return new Click(selector);
    }

    /**
     * Click on the specified element.
     *
     * @param selector the string representing the selector.
     * @param options advanced selector lookup options.
     */
    public static Click on(String selector, SelectorOptions options) {
        return new Click(selector, options);
    }

    /**
     * Click on the specified element.
     *
     * @param locator the existing Playwright locator.
     */
    public static Click on(Locator locator) {
        return new Click(locator);
    }

    /**
     * Click on the specified element.
     *
     * @param locator the existing Playwright locator.
     * @param options advanced selector lookup options.
     */
    public static Click on(Locator locator, SelectorOptions options) {
        return new Click(locator, options);
    }

    /**
     * Find the specified selector and click on it.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        if (this.options == null) {
            if (this.selector != null) {
                BrowseTheWeb.as(actor).click(this.selector);
            } else if (this.locator != null) {
                BrowseTheWeb.as(actor).click(this.locator);
            }
        } else {
            if (this.selector != null) {
                BrowseTheWeb.as(actor).click(this.selector, this.options);
            } else if (this.locator != null) {
                BrowseTheWeb.as(actor).click(this.locator, this.options);
            }
        }
        return null;
    }
}
