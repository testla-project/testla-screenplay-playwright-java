package testla.web;

import org.jetbrains.annotations.Nullable;

/**
 * Class that represents a SubSelector for another selector.
 */
public class SubSelector {

    /**
     * The selector
     */
    public String selector;

    /**
     * The selector options
     */
    @Nullable
    public SelectorOptions selectorOptions;

    /**
     * Constructor for the SubSelector
     *
     * @param selector - the selector for the SubSelector
     * @param selectorOptions - the selector options for the SubSelector
     */
    public SubSelector(String selector, @Nullable SelectorOptions selectorOptions) {
        this.selector = selector;
        this.selectorOptions = selectorOptions;
    }
}
