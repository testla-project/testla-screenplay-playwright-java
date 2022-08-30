package testla.web.questions;

import testla.screenplay.actor.IActor;
import testla.screenplay.question.Question;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

public class Element extends Question<Boolean> {

    private final String checkMode;
    private String mode;
    private String selector;
    private SelectorOptions options;

    public Element(String checkMode) {
        this.checkMode = checkMode;
    }
    public static Element toBe() {
        return new Element("toBe");
    }

    public static Element notToBe() {
        return new Element("notToBe");
    }

    public Element visible(String selector) {
        this.mode = "visible";
        this.selector = selector;
        this.options = null;
        return this;
    }

    public Element visible(String selector, SelectorOptions options) {
        this.mode = "visible";
        this.selector = selector;
        this.options = options;
        return this;
    }

    public Element enabled(String selector) {
        this.mode = "enabled";
        this.selector = selector;
        this.options = null;
        return this;
    }

    public Element enabled(String selector, SelectorOptions options) {
        this.mode = "enabled";
        this.selector = selector;
        this.options = options;
        return this;
    }

    @Override
    public Boolean answeredBy(IActor actor) {
        switch (this.mode) {
            case "visible" -> {
                if (options == null) {
                    return BrowseTheWeb.as(actor).checkVisibilityState(this.selector, this.checkMode.equals("toBe") ? "visible" : "hidden");
                } else {
                    return BrowseTheWeb.as(actor).checkVisibilityState(this.selector, this.checkMode.equals("toBe") ? "visible" : "hidden", this.options);
                }
            }
            case "enabled" -> {
                if (options == null) {
                    return BrowseTheWeb.as(actor).checkEnabledState(this.selector, this.checkMode.equals("toBe") ? "enabled" : "disabled");
                } else {
                    return BrowseTheWeb.as(actor).checkVisibilityState(this.selector, this.checkMode.equals("toBe") ? "enabled" : "disabled", this.options);
                }
            }
            default -> throw new RuntimeException("Unknown mode: Element.answeredBy()!");
        }
    }
}
