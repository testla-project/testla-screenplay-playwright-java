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
    Object[] replacements;

    public SelectorOptions(@Nullable String hasText, @Nullable Double timeout, @Nullable SubSelector subSelector,
                           @Nullable Object... replacements) {
        this.hasText = hasText;
        this.timeout = timeout;
        this.subSelector = subSelector;

        // varargs replacements can be explicitly null or not given at all -> need to check both cases
        if (replacements != null && replacements.length == 0) {
            this.replacements = null;
        } else {
            this.replacements = replacements;

        }
    }
}
