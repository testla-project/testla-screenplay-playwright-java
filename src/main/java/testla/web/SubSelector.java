package testla.web;

import org.jetbrains.annotations.Nullable;

/**
 * Class that represents a subselector for another selector.
 */
public class SubSelector {
    public String selector;

    @Nullable
    public SelectorOptions selectorOptions;


    public SubSelector(String selector, @Nullable SelectorOptions selectorOptions) {
        this.selector = selector;
        this.selectorOptions = selectorOptions;
    }
}
