package testla.web.actions;

import com.microsoft.playwright.options.KeyboardModifier;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

import java.util.List;

/**
 * Action Class. Hover over an element specified by a selector string.
 */
public class Hover extends Action {
    private final String selector;
    private final SelectorOptions options;
    private final List<KeyboardModifier> modifiers;

    private Hover(String selector) {
        this.selector = selector;
        this.options = null;
        this.modifiers = null;
    }

    private Hover(String selector, SelectorOptions options) {
        this.selector = selector;
        this.options = options;
        this.modifiers = null;
    }

    private Hover(String selector, List<KeyboardModifier> modifiers) {
        this.selector = selector;
        this.options = null;
        this.modifiers = modifiers;
    }

    private Hover(String selector, SelectorOptions options, List<KeyboardModifier> modifiers) {
        this.selector = selector;
        this.options = options;
        this.modifiers = modifiers;
    }

    /**
     * find the specified selector and hover over it.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        if(this.options != null) {
            // both options and modifiers are set
            if(this.modifiers != null) {
                BrowseTheWeb.as(actor).hover(this.selector, this.options, this.modifiers);
                // only options is set
            } else {
                BrowseTheWeb.as(actor).hover(this.selector, this.options);
            }
            // only modifiers is set
        } else if(this.modifiers != null) {
            BrowseTheWeb.as(actor).hover(this.selector, this.modifiers);
            // nothing is set
        } else {
            BrowseTheWeb.as(actor).hover(selector);
        }
        return null;
    }

    /**
     * Specify which selector should be hovered over
     *
     * @param selector The selector that should be hovered over.
     */
    public static Hover over(String selector) {
        return new Hover(selector);
    }

    /**
     * Specify which selector should be hovered over
     *
     * @param selector The selector that should be hovered over.
     * @param options advanced selector lookup options.
     */
    public static Hover over(String selector, SelectorOptions options) {
        return new Hover(selector, options);
    }

    /**
     * Specify which selector should be hovered over
     *
     * @param selector The selector that should be hovered over.
     * @param modifiers modifier keys to press. Ensures that only these modifiers are pressed during the operation.
     */
    public static Hover over(String selector, List<KeyboardModifier> modifiers) {
        return new Hover(selector, modifiers);
    }

    /**
     * Specify which selector should be hovered over
     *
     * @param selector The selector that should be hovered over.
     * @param options advanced selector lookup options
     * @param modifiers Modifier keys to press. Ensures that only these modifiers are pressed during the operation.
     */
    public static Hover over(String selector, SelectorOptions options, List<KeyboardModifier> modifiers) {
        return new Hover(selector, options, modifiers);
    }
}
