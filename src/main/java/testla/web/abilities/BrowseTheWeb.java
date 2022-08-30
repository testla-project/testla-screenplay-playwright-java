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
import testla.web.SelectorOptions;
import testla.web.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
/**
 * TODO: Add Description
 *
 * @author Patrick Döring
 */
public class BrowseTheWeb extends Ability {

    private final Page page;
    private static final Utils utils = new Utils();
    private static BrowseTheWeb instance;

    private BrowseTheWeb(Page page) {
        super();
        this.page = page;
    }

    public static BrowseTheWeb using(Page page) {
        BrowseTheWeb.instance = new BrowseTheWeb(page);
        return BrowseTheWeb.instance;
    }

    public static BrowseTheWeb as(IActor actor) {
        return (BrowseTheWeb) actor.withAbilityTo(BrowseTheWeb.instance);
    }

    public Page getPage() {
        return this.page;
    }

    public Response navigate(String url) {
        return this.page.navigate(url);
    }

    public void waitForLoadState(LoadState status) {
        this.page.waitForLoadState(status);
    }

    public void press(String input) {
        this.page.keyboard().press(input);
    }

    public List<Cookie> getCookies() {
        return this.page.context().cookies();
    }

    public List<Cookie> getCookies(String url) {
        return this.page.context().cookies(url);
    }

    public List<Cookie> getCookies(List<String> urls) {
        return this.page.context().cookies(urls);
    }

    public void addCookies(List<Cookie> cookies) {
        this.page.context().addCookies(cookies);
    }

    public void clearCookies() {
        this.page.context().clearCookies();
    }

    public Object getLocalStorageItem(String key) {
        return this.page.evaluate("(key) => {"
                + "const value = localStorage.getItem(key);"
                + "if (value) {"
                + "return Promise.resolve(JSON.parse(value));"
                + "}"
                + "return Promise.reject();"
                + "}", key);
    }

    public void setLocalStorageItem(String key, Object value) {
        Map<String, Object> args = new HashMap<>();
        args.put("key", key);
        args.put("value", value);
        this.page.evaluate("({ key, value }) => {"
                + "localStorage.setItem(key, JSON.stringify(value));"
                + "return Promise.resolve()"
                + "}", args);
    }

    public void removeLocalStorageItem(String key) {
        this.page.evaluate("(key) => {"
                + "localStorage.removeItem(key);"
                + "return Promise.resolve();"
                + "}", key);
    }

    public Object getSessionStorageItem(String key) {
        return this.page.evaluate("(key) => {"
                + "const value = sessionStorage.getItem(key);"
                + "if (value) {"
                + "return Promise.resolve(JSON.parse(value));"
                + "}"
                + "return Promise.reject();"
                + "}", key);
    }

    public void setSessionStorageItem(String key, Object value) {
        Map<String, Object> args = new HashMap<>();
        args.put("key", key);
        args.put("value", value);
        this.page.evaluate("({ key, value }) => {"
                + "sessionStorage.setItem(key, JSON.stringify(value));"
                + "return Promise.resolve()"
                + "}", args);
    }

    public void removeSessionStorageItem(String key) {
        this.page.evaluate("(key) => {"
                + "sessionStorage.removeItem(key);"
                + "return Promise.resolve();"
                + "}", key);
    }


    public void checkBox(String selector) {
        this.page.check(selector);
    }

    public void checkBox(String selector, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options)
                .check();
    }

    public void hover(String selector) {
        this.page.hover(selector);
    }

    public void hover(String selector, List<KeyboardModifier> modifiers) {
        this.page.hover(selector, new HoverOptions()
                .setModifiers(modifiers));
    }

    public void hover(String selector, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options)
                .hover();
    }

    public void hover(String selector, SelectorOptions options, List<KeyboardModifier> modifiers) {
        utils.recursiveLocatorLookup(this.page, selector, options)
                .hover(new Locator.HoverOptions()
                        .setModifiers(modifiers));
    }

    public void waitForSelector(String selector) {
        this.page.waitForSelector(selector);
    }

    public void waitForSelector(String selector, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options);
    }

    public void dragAndDrop(String sourceSelector, String targetSelector) {
        this.page.dragAndDrop(sourceSelector, targetSelector);
    }

    public void dragAndDrop(String sourceSelector, String targetSelector, SelectorOptions sourceOptions, SelectorOptions targetOptions) {
        Locator target = utils.recursiveLocatorLookup(this.page, targetSelector, targetOptions);
        utils.recursiveLocatorLookup(this.page, sourceSelector, sourceOptions)
                .dragTo(target, new DragToOptions()
                        .setTargetPosition(0.0, 0.0));
    }

    public void fill(String selector, String input) {
        this.page.fill(selector, input);
    }

    public void fill(String selector, String input, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options).fill(input);
    }

    public void type(String selector, String input) {
        this.page.type(selector, input);
    }

    public void type(String selector, String input, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options).type(input);
    }

    public void click(String selector) {
        this.page.click(selector);
    }

    public void click(String selector, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options).click();
    }

    public void dblclick(String selector) {
        this.page.dblclick(selector);
    }

    public void dblclick(String selector, SelectorOptions options) {
        utils.recursiveLocatorLookup(this.page, selector, options).dblclick();
    }

    // ToDo: Use Enum instead of string mode
    public boolean checkVisibilityState(String selector, String mode) {
        if (mode.equals("visible")) {
            assertThat(this.page.locator(selector)).isVisible();
        } else if (mode.equals("hidden")) {
            assertThat(this.page.locator(selector)).isHidden();
        } else {
            throw new RuntimeException("Unknown mode: " + mode);
        }

        return true;
    }

    // ToDo: Use Enum instead of string mode
    public boolean checkVisibilityState(String selector, String mode, SelectorOptions options) {
        if (mode.equals("visible")) {
            assertThat(utils.recursiveLocatorLookup(this.page, selector, options))
                    .isVisible(new IsVisibleOptions().setTimeout(options.timeout == null ? 0.0 : options.timeout));
        } else if (mode.equals("hidden")) {
            assertThat(utils.recursiveLocatorLookup(this.page, selector, options))
                    .isHidden(new IsHiddenOptions().setTimeout(options.timeout == null ? 0.0 : options.timeout));
        } else {
            throw new RuntimeException("Unknown mode: " + mode);
        }

        return true;
    }

    // ToDo: Use Enum instead of string mode
    public boolean checkEnabledState(String selector, String mode) {
        if (mode.equals("enabled")) {
            assertThat(this.page.locator(selector)).isEnabled();
        } else if (mode.equals("disabled")) {
            assertThat(this.page.locator(selector)).isDisabled();
        } else {
            throw new RuntimeException("Unknown mode: " + mode);
        }

        return true;
    }

    // ToDo: Use Enum instead of string mode
    public boolean checkEnabledState(String selector, String mode, SelectorOptions options) {
        if (mode.equals("enabled")) {
            assertThat(utils.recursiveLocatorLookup(this.page, selector, options))
                    .isEnabled(new IsEnabledOptions().setTimeout(options.timeout == null ? 0.0 : options.timeout));
        } else if (mode.equals("disabled")) {
            assertThat(utils.recursiveLocatorLookup(this.page, selector, options))
                    .isDisabled(new IsDisabledOptions().setTimeout(options.timeout == null ? 0.0 : options.timeout));
        } else {
            throw new RuntimeException("Unknown mode: " + mode);
        }

        return true;
    }
}
