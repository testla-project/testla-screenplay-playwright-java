package testla.web.questions;

import testla.screenplay.actor.IActor;
import testla.screenplay.question.Question;
import testla.web.Modes;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

/**
 * Question Class. Get a specified state for a selector like visible or enabled.
 */
public class Element extends Question<Boolean> {

    private final String checkMode;
    private Modes mode;
    private String selector;
    private SelectorOptions options;

    private Element(String checkMode) {
        this.checkMode = checkMode;
    }

    /**
     * make the Question check for the positive.
     */
    public static Element toBe() {
        return new Element("toBe");
    }

    /**
     * make the Question check for the negative.
     */
    public static Element notToBe() {
        return new Element("notToBe");
    }

    /**
     * Verifies if an element is visible.
     *
     * @param selector the selector
     */
    public Element visible(String selector) {
        this.mode = Modes.VISIBLE;
        this.selector = selector;
        this.options = null;
        return this;
    }

    /**
     * Verifies if an element is visible.
     *
     * @param selector the selector
     * @param options advanced selector lookup options.
     */
    public Element visible(String selector, SelectorOptions options) {
        this.mode = Modes.VISIBLE;
        this.selector = selector;
        this.options = options;
        return this;
    }

    /**
     * Verifies if an element is enabled.
     *
     * @param selector the selector
     */
    public Element enabled(String selector) {
        this.mode = Modes.ENABLED;
        this.selector = selector;
        this.options = null;
        return this;
    }

    /**
     * Verifies if an element is enabled.
     *
     * @param selector the selector
     * @param options advanced selector lookup options.
     */
    public Element enabled(String selector, SelectorOptions options) {
        this.mode = Modes.ENABLED;
        this.selector = selector;
        this.options = options;
        return this;
    }

    @Override
    public Boolean answeredBy(IActor actor) {
        switch (this.mode) {
            case VISIBLE -> {
                if (options == null) {
                    return BrowseTheWeb.as(actor).checkVisibilityState(this.selector, this.checkMode.equals("toBe") ? Modes.VISIBLE : Modes.HIDDEN);
                } else {
                    return BrowseTheWeb.as(actor).checkVisibilityState(this.selector, this.checkMode.equals("toBe") ? Modes.VISIBLE : Modes.HIDDEN, this.options);
                }
            }
            case ENABLED -> {
                if (options == null) {
                    return BrowseTheWeb.as(actor).checkEnabledState(this.selector, this.checkMode.equals("toBe") ? Modes.ENABLED : Modes.DISABLED);
                } else {
                    return BrowseTheWeb.as(actor).checkVisibilityState(this.selector, this.checkMode.equals("toBe") ? Modes.ENABLED : Modes.DISABLED, this.options);
                }
            }
            default -> throw new RuntimeException("Unknown mode for Element.answeredBy(): " + mode);
        }
    }
}
