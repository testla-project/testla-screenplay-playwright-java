package testla.web;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import org.jetbrains.annotations.Nullable;

/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class Utils {
    private Locator getPageLocator(Page page, String selector, @Nullable String hasText) {
        if (hasText == null) {
            return page.locator(selector);
        } else {
            return page.locator(selector, new Page.LocatorOptions().setHasText(hasText));
        }
    }

    private Locator getLocatorFromLocator(Locator locator, String selector, @Nullable String hasText) {
        if (hasText == null) {
            return locator.locator(selector);
        } else {
            return locator.locator(selector, new Locator.LocatorOptions().setHasText(hasText));
        }
    }

    private void waitForLocator(Locator locator, @Nullable Double timeout) {
        locator.waitFor(new WaitForOptions().setTimeout(timeout == null ? 0.0 : timeout));
    }

    // ToDo: Add SubSelector
    public Locator recursiveLocatorLookup(Page page, String selector, SelectorOptions options) {
        // Find first Level Locator!!!
        Locator locator = getPageLocator(page, selector, options.hasText);

        if (options.subSelector != null) {
            return subLocatorLookup(page, locator, options.timeout, options.subSelector);
        } else {
            waitForLocator(locator, options.timeout);
            return locator;
        }
    }

    private Locator subLocatorLookup(Page page, Locator locator, @Nullable Double timeout, SubSelector subSelector) {

        Locator resolvedLocator = locator;
        waitForLocator(resolvedLocator, timeout);

        if (subSelector.selectorOptions != null) {
            resolvedLocator = getLocatorFromLocator(resolvedLocator, subSelector.selector, subSelector.selectorOptions.hasText);
            waitForLocator(resolvedLocator, timeout);
            if (subSelector.selectorOptions.subSelector != null) {
                resolvedLocator = subLocatorLookup(page, resolvedLocator, subSelector.selectorOptions.timeout, subSelector.selectorOptions.subSelector);
            }
        } else {
            resolvedLocator = getLocatorFromLocator(resolvedLocator, subSelector.selector, null);
            waitForLocator(resolvedLocator, timeout);
        }
        return resolvedLocator;
    }
}
