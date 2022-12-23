package testla.web.actions;

import com.microsoft.playwright.Locator;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Action Class. Double-Click on an element specified by a selector string.
 */
public class DoubleClick extends Action {

    private final String selector;
    private final Locator locator;
    private final SelectorOptions options;

    private DoubleClick(String selector) {
        this.selector = selector;
        this.locator = null;
        this.options = null;
    }

    private DoubleClick(String selector, SelectorOptions options) {
        this.selector = selector;
        this.locator = null;
        this.options = options;
    }

    private DoubleClick(Locator locator) {
        this.selector = null;
        this.locator = locator;
        this.options = null;
    }

    private DoubleClick(Locator locator, SelectorOptions options) {
        this.selector = null;
        this.locator = locator;
        this.options = options;
    }

    /**
     * Double-Click the specified element.
     *
     * @param selector the string representing the selector.
     */
    public static DoubleClick on(String selector) {
        return new DoubleClick(selector);
    }

    /**
     * Double-Click the specified element.
     *
     * @param selector the string representing the selector.
     * @param options advanced selector lookup options.
     */
    public static DoubleClick on(String selector, SelectorOptions options) {
        return new DoubleClick(selector, options);
    }

    /**
     * Double-Click the specified element.
     *
     * @param locator the existing Playwright locator.
     */
    public static DoubleClick on(Locator locator) {
        return new DoubleClick(locator);
    }

    /**
     * Double-Click the specified element.
     *
     * @param locator the existing Playwright locator.
     * @param options advanced selector lookup options.
     */
    public static DoubleClick on(Locator locator, SelectorOptions options) {
        return new DoubleClick(locator, options);
    }

    /**
     * Find the specified selector and double-click it.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        if (this.options == null) {
            if (this.selector != null) {
                BrowseTheWeb.as(actor).dblclick(this.selector);
            } else if (this.locator != null) {
                BrowseTheWeb.as(actor).dblclick(this.locator);
            }
        } else {
            if (this.selector != null) {
                BrowseTheWeb.as(actor).dblclick(this.selector, this.options);
            } else if (this.locator != null) {
                BrowseTheWeb.as(actor).dblclick(this.locator, this.options);
            }
        }
        return null;
    }
}
