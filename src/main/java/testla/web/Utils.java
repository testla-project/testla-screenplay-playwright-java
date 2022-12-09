package testla.web;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.jetbrains.annotations.Nullable;

/**
 * Utility functions used to deal with SubSelectors and advanced selector options.
 */
public class Utils {

    /**
     * Resolve a locator from the page, optionally with hasText.
     *
     * @param page - page object
     * @param selector - selector to search
     * @param hasText - locator should have this text
     * @return Locator - resolved locator
     */
    private Locator getPageLocator(Page page, String selector, @Nullable String hasText) {
        if (hasText == null) {
            return page.locator(selector);
        } else {
            return page.locator(selector, new Page.LocatorOptions().setHasText(hasText));
        }
    }

    /**
     * Resolve a locator from another locator, optionally with hasText.
     *
     * @param locator - base locator
     * @param selector - selector to search for
     * @param hasText - locator should have this text
     * @return Locator - resolved locator
     */
    private Locator getLocatorFromLocator(Locator locator, String selector, @Nullable String hasText) {
        if (hasText == null) {
            return locator.locator(selector);
        } else {
            return locator.locator(selector, new Locator.LocatorOptions().setHasText(hasText));
        }
    }

    /**
     * Wait until the given locator has the given state. Default state is visible if no state is specified. Timeout optional.
     *
     * @param locator - the locator
     * @param timeout - timeout can be null
     * @param state - SelectorOptionState can be null
     */
    private void waitForLocator(Locator locator, @Nullable Double timeout, @Nullable SelectorOptionsState state) {
        locator.waitFor(new WaitForOptions().setTimeout(timeout == null ? 0.0 : timeout)
                .setState(state == null ? WaitForSelectorState.VISIBLE : WaitForSelectorState.valueOf(state.toString())));
    }

    /**
     * Get a Locator from a page. Performs recursive subLocator lookups if options.subLocator is specified.
     * Supports further options timeouts, state that the Locator should have (visible, hidden, attached, detached).
     *
     * @param page - the playwright page object
     * @param selector - the selector
     * @param options - SelectorOptions
     * @return Locator - the locator found on the page
     */
    public Locator recursiveLocatorLookup(Page page, String selector, SelectorOptions options) {
        // double check if this method was somehow called with options == null.
        // if this is really the case, just resolve the locator, wait for it to be visible and return it.
        if (options == null) {
            Locator locator = getPageLocator(page, selector, null);
            waitForLocator(locator, null, null);
            return locator;
        }

        // replace selector placeholders if present.
        if (options.replacements != null) {
            selector = replacePlaceholders(selector, options.replacements);
        }

        // Find first Level Locator.
        Locator locator = getPageLocator(page, selector, options.hasText);

        // perform subLocator lookup if specified.
        if (options.subSelector != null) {
            return subLocatorLookup(page, locator, options.timeout, options.subSelector, options.state);
        } else { // if not, just wait for the state (default is VISIBLE) and return.
            waitForLocator(locator, options.timeout, options.state);
            return locator;
        }
    }

    // TODO: parameter page might be unused? In JS version too.
    /**
     * Perform the subLocator lookup. Search below the given locator
     *
     * @param page - the page object
     * @param locator - base locator
     * @param timeout - timeout can be null
     * @param subSelector - SubSelector
     * @param state - SelectorOptionsState can be null
     * @return Locator - the resolved locator
     */
    private Locator subLocatorLookup(Page page, Locator locator, @Nullable Double timeout, SubSelector subSelector,
                                     @Nullable SelectorOptionsState state) {
        Locator resolvedLocator = locator;
        waitForLocator(resolvedLocator, timeout, state);

        // if subLocator options are set: perform lookup according to options.
        if (subSelector.selectorOptions != null) {
            resolvedLocator = getLocatorFromLocator(resolvedLocator, subSelector.selector, subSelector.selectorOptions.hasText);
            waitForLocator(resolvedLocator, timeout, null);
            // if subLocator has another subLocator: recursive call.
            if (subSelector.selectorOptions.subSelector != null) {
                resolvedLocator = subLocatorLookup(page, resolvedLocator, subSelector.selectorOptions.timeout,
                        subSelector.selectorOptions.subSelector, subSelector.selectorOptions.state);
            }
        } else { // if subLocator has no options: just get the subLocator from the locator and return.
            resolvedLocator = getLocatorFromLocator(resolvedLocator, subSelector.selector, null);
            // waitForLocator(resolvedLocator, timeout);
        }
        return resolvedLocator;
    }

    /**
     * Support for locator templates to replace placeholders.
     *
     * @param selector - the selector template
     * @param args - arguments to replace placeholders
     * @return String - Replaced placeholders
     */
    private String replacePlaceholders(String selector, Object... args) {
        return String.format(selector, args);
    }
}
