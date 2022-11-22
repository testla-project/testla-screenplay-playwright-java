package testla.web.abilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.DragToOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.HoverOptions;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.assertions.LocatorAssertions.IsDisabledOptions;
import com.microsoft.playwright.assertions.LocatorAssertions.IsEnabledOptions;
import com.microsoft.playwright.assertions.LocatorAssertions.IsHiddenOptions;
import com.microsoft.playwright.assertions.LocatorAssertions.IsVisibleOptions;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.KeyboardModifier;
import com.microsoft.playwright.options.LoadState;
import testla.screenplay.ability.Ability;
import testla.screenplay.actor.IActor;
import testla.web.Modes;
import testla.web.SelectorOptions;
import testla.web.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


/**
 * This class represents the actor's ability to use a Browser.
 */
public class BrowseTheWeb extends Ability {

    private final Page page;
    private static final Utils utils = new Utils();
    private static BrowseTheWeb instance;

    private BrowseTheWeb(Page page) {
        super();
        this.page = page;
    }

    /**
     * Initialize this Ability by passing an already existing Playwright Page object.
     *
     * @param page the Playwright Page that will be used to browse.
     */
    public static BrowseTheWeb using(Page page) {
        BrowseTheWeb.instance = new BrowseTheWeb(page);
        return BrowseTheWeb.instance;
    }

    /**
     * Use this Ability as an Actor.
     *
     * @param actor the actor.
     */
    public static BrowseTheWeb as(IActor actor) {
        return (BrowseTheWeb) actor.withAbilityTo(BrowseTheWeb.instance);
    }

    /**
     * Get the page object
     *
     * @returns the page object
     */
    public Page getPage() {
        return this.page;
    }

    /**
     * Use the page to navigate to the specified URL.
     *
     * @param url the url to access.
     */
    public Response navigate(String url) {
        return this.page.navigate(url);
    }

    /**
     * Wait for the specified loading state.
     *
     * @param status the status to wait for. Allowed: "load" | "domcontentloaded" | "networkidle".
     */
    public void waitForLoadState(LoadState status) {
        this.page.waitForLoadState(status);
    }

    /**
     * Press the specified key(s) on the keyboard.
     *
     * @param input the key(s). multiple keys can be pressed by concatenating with "+"
     */
    public void press(String input) {
        this.page.keyboard().press(input);
    }

    /**
     * Get all cookies of the current browser context. If no URLs are specified, this method returns all cookies.
     */
    public List<Cookie> getCookies() {
        return this.page.context().cookies();
    }

    /**
     * Get the cookies for the specified URL of the current browser context.
     */
    public List<Cookie> getCookies(String url) {
        return this.page.context().cookies(url);
    }

    /**
     * Get the cookies for the specified URLs of the current browser context.
     */
    public List<Cookie> getCookies(List<String> urls) {
        return this.page.context().cookies(urls);
    }

    /**
     * Adds cookies into this browser context. All pages within this context will have these cookies installed. Cookies can be obtained via BrowseTheWeb.getCookies([urls]).
     */
    public void addCookies(List<Cookie> cookies) {
        this.page.context().addCookies(cookies);
    }

    /**
     * Clear the browser context cookies.
     */
    public void clearCookies() {
        this.page.context().clearCookies();
    }

    /**
     * Get a local storage item.
     *
     * @param key the key that specifies the item.
     */
    public Object getLocalStorageItem(String key) {
        return this.page.evaluate("(key) => {"
            + "const value = localStorage.getItem(key);"
            + "if (value) {"
            + "return Promise.resolve(JSON.parse(value));"
            + "}"
            + "return Promise.resolve(undefined);"
            + "}", key);
    }

    /**
     * Set a local storage item identified by the given key + value, creating a new key/value pair if none existed for key previously.
     *
     * @param key the key that specifies the item.
     * @param value the value to set.
     */
    public void setLocalStorageItem(String key, Object value) {
        Map<String, Object> args = new HashMap<>();
        args.put("key", key);
        args.put("value", value);
        this.page.evaluate("({ key, value }) => {"
            + "localStorage.setItem(key, JSON.stringify(value));"
            + "return Promise.resolve()"
            + "}", args);
    }

    /**
     * Delete a local storage item, if a key/value pair with the given key exists.
     *
     * @param key the key that specifies the item.
     */
    public void removeLocalStorageItem(String key) {
        this.page.evaluate("(key) => {"
            + "localStorage.removeItem(key);"
            + "return Promise.resolve();"
            + "}", key);
    }

    /**
     * Get a session storage item.
     *
     * @param key the key that specifies the item.
     */
    public Object getSessionStorageItem(String key) {
        return this.page.evaluate("(key) => {"
            + "const value = sessionStorage.getItem(key);"
            + "if (value) {"
            + "return Promise.resolve(JSON.parse(value));"
            + "}"
            + "return Promise.resolve(undefined);"
            + "}", key);
    }

    /**
     * Set a session storage item identified by the given key + value, creating a new key/value pair if none existed for key previously.
     *
     * @param key the key that specifies the item.
     * @param value the value to set.
     */
    public void setSessionStorageItem(String key, Object value) {
        Map<String, Object> args = new HashMap<>();
        args.put("key", key);
        args.put("value", value);
        this.page.evaluate("({ key, value }) => {"
            + "sessionStorage.setItem(key, JSON.stringify(value));"
            + "return Promise.resolve()"
            + "}", args);
    }

    /**
     * Delete a session storage item, if a key/value pair with the given key exists.
     *
     * @param key the key that specifies the item.
     */
    public void removeSessionStorageItem(String key) {
        this.page.evaluate("(key) => {"
            + "sessionStorage.removeItem(key);"
            + "return Promise.resolve();"
            + "}", key);
    }

    /**
     * Check the specified checkbox.
     *
     * @param selector the selector of the checkbox.
     */
    public void checkBox(String selector) {
        this.page.check(selector);
    }

    /**
     * Check the specified checkbox.
     *
     * @param selector the selector of the checkbox.
     * @param options advanced selector lookup options.
     */
    public void checkBox(String selector, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options)
                .check();
    }

    /**
     * Use the page mouse to hover over the specified element.
     *
     * @param selector the selector of the element to hover over.
     */
    public void hover(String selector) {
        this.page.hover(selector);
    }

    /**
     * Use the page mouse to hover over the specified element.
     *
     * @param selector the selector of the element to hover over.
     * @param modifiers modifier keys to press. Ensures that only these modifiers are pressed during the operation.
     */
    public void hover(String selector, List<KeyboardModifier> modifiers) {
        this.page.hover(selector, new HoverOptions()
                .setModifiers(modifiers));
    }

    /**
     * Use the page mouse to hover over the specified element.
     *
     * @param selector the selector of the element to hover over.
     * @param options advanced selector lookup options.
     */
    public void hover(String selector, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options)
                .hover();
    }

    /**
     * Use the page mouse to hover over the specified element.
     *
     * @param selector the selector of the element to hover over.
     * @param options advanced selector lookup options.
     * @param modifiers modifier keys to press. Ensures that only these modifiers are pressed during the operation.
     */
    public void hover(String selector, SelectorOptions options, List<KeyboardModifier> modifiers) {
        utils.recursiveLocatorLookup(this.page, selector, options)
                .hover(new Locator.HoverOptions()
                        .setModifiers(modifiers));
    }

    /**
     * Wait until the element of the specified selector exists.
     *
     * @param selector the selector of the element.
     */
    public void waitForSelector(String selector) {
        this.page.waitForSelector(selector);
    }

    /**
     * Wait until the element of the specified selector exists.
     *
     * @param selector the selector of the element.
     * @param options advanced selector lookup options.
     */
    public void waitForSelector(String selector, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options);
    }

    /**
     * Drag the specified source element to the specified target element and drop it.
     *
     * @param sourceSelector the selector of the source element.
     * @param targetSelector the selector of the target element.
     */
    public void dragAndDrop(String sourceSelector, String targetSelector) {
        this.page.dragAndDrop(sourceSelector, targetSelector);
    }

    /**
     * Drag the specified source element to the specified target element and drop it.
     *
     * @param sourceSelector the selector of the source element.
     * @param targetSelector the selector of the target element.
     * @param sourceOptions advanced selector lookup options for the source selector.
     * @param targetOptions advanced selector lookup options for the target selector.
     */
    public void dragAndDrop(String sourceSelector, String targetSelector, SelectorOptions sourceOptions, SelectorOptions targetOptions) {
        Locator target = utils.recursiveLocatorLookup(this.page, targetSelector, targetOptions);
        utils.recursiveLocatorLookup(this.page, sourceSelector, sourceOptions)
                .dragTo(target, new DragToOptions()
                        .setTargetPosition(0.0, 0.0));
    }

    /**
     * Fill the element specified by the selector with the given input.
     *
     * @param selector the selector of the source element.
     * @param input the input to fill the element with.
     */
    public void fill(String selector, String input) {
        this.page.fill(selector, input);
    }

    /**
     * Fill the element specified by the selector with the given input.
     *
     * @param selector the selector of the source element.
     * @param input the input to fill the element with.
     * @param options advanced selector lookup options.
     */
    public void fill(String selector, String input, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options).fill(input);
    }

    /**
     * Type the given input into the element specified by the selector.
     *
     * @param selector the selector of the source element.
     * @param input the input to type into the element.
     */
    public void type(String selector, String input) {
        this.page.type(selector, input);
    }

    /**
     * Type the given input into the element specified by the selector.
     *
     * @param selector the selector of the source element.
     * @param input the input to type into the element.
     * @param options advanced selector lookup options.
     */
    public void type(String selector, String input, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options).type(input);
    }

    /**
     * Click the element specified by the selector.
     *
     * @param selector the selector of the element to click.
     */
    public void click(String selector) {
        this.page.click(selector);
    }

    /**
     * Click the element specified by the selector.
     *
     * @param selector the selector of the element to click.
     * @param options advanced selector lookup options.
     */
    public void click(String selector, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options).click();
    }

    /**
     * Double-click the element specified by the selector.
     *
     * @param selector the selector of the element to double click.
     */
    public void dblclick(String selector) {
        this.page.dblclick(selector);
    }

    /**
     * Double-click the element specified by the selector.
     *
     * @param selector the selector of the element to double click.
     * @param options advanced selector lookup options.
     */
    public void dblclick(String selector, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options).dblclick();
    }

    /**
     * Validate if a locator on the page is visible or hidden.
     *
     * @param mode the expected property of the selector that needs to be checked. either 'visible' or 'hidden'.
     * @param selector the locator to search for.
     * @returns true if the element is visible/hidden as expected, false if the timeout was reached.
     */
    public boolean checkVisibilityState(String selector, Modes mode) {
        switch (mode) {
            case VISIBLE    -> assertThat(this.page.locator(selector)).isVisible();
            case HIDDEN     -> assertThat(this.page.locator(selector)).isHidden();
            default         -> throw new RuntimeException("Wrong mode for checkVisibilityState(): " + mode +
                                    " Please use VISIBLE or HIDDEN.");
        }
        return true;
    }

    /**
     * Validate if a locator on the page is visible or hidden.
     *
     * @param mode the expected property of the selector that needs to be checked. either 'visible' or 'hidden'.
     * @param selector the locator to search for.
     * @param options advanced selector lookup options.
     * @returns true if the element is visible/hidden as expected, false if the timeout was reached.
     */
    public boolean checkVisibilityState(String selector, Modes mode, SelectorOptions options) {
        switch (mode) {
            case VISIBLE    -> assertThat(utils.recursiveLocatorLookup(this.page, selector, options))
                                .isVisible(new IsVisibleOptions().setTimeout(options.timeout == null ? 0.0 : options.timeout));
            case HIDDEN     -> assertThat(utils.recursiveLocatorLookup(this.page, selector, options))
                                .isHidden(new IsHiddenOptions().setTimeout(options.timeout == null ? 0.0 : options.timeout));
            default         -> throw new RuntimeException("Wrong mode for checkVisibilityState(): " + mode +
                                    " Please use VISIBLE or HIDDEN.");
        }
        return true;
    }

    /**
     * Validate if a locator on the page is enabled or disabled.
     *
     * @param selector the locator to search for.
     * @param mode the expected property of the selector that needs to be checked. either 'enabled' or 'disabled'.
     * @returns true if the element is enabled/disabled as expected, false if the timeout was reached.
     */
    public boolean checkEnabledState(String selector, Modes mode) {
        switch (mode) {
            case VISIBLE    -> assertThat(this.page.locator(selector)).isEnabled();
            case HIDDEN     -> assertThat(this.page.locator(selector)).isDisabled();
            default         -> throw new RuntimeException("Wrong mode for checkEnabledState(): " + mode +
                                    " Please use ENABLED or DISABLED.");
        }
        return true;
    }

    /**
     * Validate if a locator on the page is enabled or disabled.
     *
     * @param selector the locator to search for.
     * @param mode the expected property of the selector that needs to be checked. either 'enabled' or 'disabled'.
     * @param options advanced selector lookup options.
     * @returns true if the element is enabled/disabled as expected, false if the timeout was reached.
     */
    public boolean checkEnabledState(String selector, Modes mode, SelectorOptions options) {
        switch (mode) {
            case ENABLED    -> assertThat(utils.recursiveLocatorLookup(this.page, selector, options))
                                .isEnabled(new IsEnabledOptions().setTimeout(options.timeout == null ? 0.0 : options.timeout));
            case DISABLED   -> assertThat(utils.recursiveLocatorLookup(this.page, selector, options))
                                .isDisabled(new IsDisabledOptions().setTimeout(options.timeout == null ? 0.0 : options.timeout));
            default         -> throw new RuntimeException("Wrong mode for checkEnabledState(): " + mode +
                                    " Please use ENABLED or DISABLED.");
        }
        return true;
    }
}
