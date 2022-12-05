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

    public SelectorOptions setHasText(String hasText) {
        this.hasText = hasText;
        return this;
    }

    public SelectorOptions setTimeout(Double timeout) {
        this.timeout = timeout;
        return this;
    }

    public SelectorOptions setSubSelector(SubSelector subSelector) {
        this.subSelector = subSelector;
        return this;
    }

    public SelectorOptions setSelectorOptionsState(SelectorOptionsState state) {
        this.state = state;
        return this;
    }

    public SelectorOptions setReplacements(Object... replacements) {
        this.replacements = replacements;
        return this;
    }
}
