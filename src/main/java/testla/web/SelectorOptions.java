package testla.web;

import org.jetbrains.annotations.Nullable;

/**
 * Class that holds elements to help with advanced selector options.
 */
public class SelectorOptions {
    @Nullable
    public String hasText;
    @Nullable
    public Double timeout;
    @Nullable
    public SubSelector subSelector;
    @Nullable
    public SelectorOptionsState state;
    @Nullable
    Object[] replacements;

    public SelectorOptions() {
        // empty constructor instead of constructor with many arguments. Maybe it's better to handle optional arguments
        // like Playwright.
    }

    /**
     * Advanced selector option - hasText
     *
     * @param hasText expected text of the locator
     * @return this instance
     */
    public SelectorOptions setHasText(String hasText) {
        this.hasText = hasText;
        return this;
    }

    /**
     * Advanced selector option - timeout
     *
     * @param timeout timeout to interact with locator
     * @return this instance
     */
    public SelectorOptions setTimeout(Double timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Advanced selector option - subSelector
     *
     * @param subSelector the selector under a selector
     * @return this instance
     */
    public SelectorOptions setSubSelector(SubSelector subSelector) {
        this.subSelector = subSelector;
        return this;
    }

    /**
     * Advanced selector option - state
     * @param state the state of the selector
     * @return this instance
     */
    public SelectorOptions setSelectorOptionsState(SelectorOptionsState state) {
        this.state = state;
        return this;
    }

    /**
     * Advanced selector option - replacements
     * @param replacements the replacements on the selector
     * @return this instance
     */
    public SelectorOptions setReplacements(Object... replacements) {
        this.replacements = replacements;
        return this;
    }
}
