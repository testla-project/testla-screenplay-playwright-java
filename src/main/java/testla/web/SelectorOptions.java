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

    public SelectorOptions(@Nullable String hasText, @Nullable Double timeout, @Nullable SubSelector subSelector) {
        this.hasText = hasText;
        this.timeout = timeout;
        this.subSelector = subSelector;
    }
}
