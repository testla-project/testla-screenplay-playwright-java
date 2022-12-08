package testla.web;

import org.jetbrains.annotations.Nullable;

/**
 * Class that holds elements to help with advanced selector options.
 */
public class SelectorOptions {

    /**
     * Selector option hasText
     */
    @Nullable
    public String hasText;

    /**
     * Selector option timeout
     */
    @Nullable
    public Double timeout;

    /**
     * Selector option subSelector
     */
    @Nullable
    public SubSelector subSelector;

    /**
     * Selector option state
     */
    @Nullable
    public SelectorOptionsState state;

    /**
     * Selector option replacements
     */
    @Nullable
    Object[] replacements;

    /**
     * Empty constructor instead of constructor with many argument.
     * Maybe it's better to handle optional arguments like playwright.
     */
    public SelectorOptions() {
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
