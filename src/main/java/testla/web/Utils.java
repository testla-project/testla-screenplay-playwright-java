package testla.web;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import org.jetbrains.annotations.Nullable;

/**
 * Utility functions used to deal with subselectors and advanced selector options.
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

    public Locator recursiveLocatorLookup(Page page, String selector, SelectorOptions options) {
        // replace selector placeholders if present.
        if (options.replacements != null) {
            selector = replacePlaceholders(selector, options.replacements);
            System.out.println(selector);
        }

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

    // support for locator templates to replace placeholders
    private String replacePlaceholders(String selector, Object... args) {
        return String.format(selector, args);
    }
}
