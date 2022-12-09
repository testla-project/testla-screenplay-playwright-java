package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;


/**
 * Action Class. Set the value of a Selector of type select to the given option.
 */
public class Select extends Action {

    private final String selector;
    private final String dropDownOption;
    private final SelectorOptions selectorOptions;
    private final String optionMode; // value, index, label

    // only selector and option -> will select dropdown label
    private Select(String selector, String dropDownOption) {
        this.selector = selector;
        this.dropDownOption = dropDownOption;
        this.selectorOptions = null;
        this.optionMode = "label";
    }

    // selector, option and SelectorOptions -> will select dropdown label
    private Select(String selector, String dropDownOption, SelectorOptions selectorOptions) {
        this.selector = selector;
        this.dropDownOption = dropDownOption;
        this.selectorOptions = selectorOptions;
        this.optionMode = "label";
    }

    // selector and optionMode -> will select according to optionMode
    private Select(String selector, String dropDownOption, String optionMode) {
        this.selector = selector;
        this.dropDownOption = dropDownOption;
        this.selectorOptions = null;
        this.optionMode = optionMode;
    }

    // selector, SelectorOptions and playwright SelectOption() -> will select according to playwright SelectOption()
    private Select(String selector, String dropDownOption, SelectorOptions selectorOptions, String optionMode) {
        this.selector = selector;
        this.dropDownOption = dropDownOption;
        this.selectorOptions = selectorOptions;
        this.optionMode = optionMode;
    }


    /**
     * Set the value of a Selector of type select to the given option.
     *
     * @param selector the string representing the (select) selector.
     * @param dropDownOption the label of the option.
     * @return new Select instance.
     */
    public static Select option(String selector, String dropDownOption) {
        return new Select(selector, dropDownOption);
    }

    /**
     * Set the value of a Selector of type select to the given option.
     *
     * @param selector the string representing the (select) selector.
     * @param dropDownOption the label of the option.
     * @param selectorOptions advanced selector lookup options.
     * @return new Select instance.
     */
    public static Select option(String selector, String dropDownOption, SelectorOptions selectorOptions) {
        return new Select(selector, dropDownOption, selectorOptions);
    }

    /**
     * Set the value of a Selector of type select to the given option.
     *
     * @param selector the string representing the (select) selector.
     * @param dropDownOption optionLabel the label/value/index of the option.
     * @param optionMode determines how the dropDownOption is looked after. Supported: "value", "label", "index".
     * @return new Select instance.
     */
    public static Select option(String selector, String dropDownOption, String optionMode) {
        return new Select(selector, dropDownOption, optionMode);
    }

    /**
     * Set the value of a Selector of type select to the given option.
     *
     * @param selector the string representing the (select) selector.
     * @param dropDownOption optionLabel the label/value/index of the option.
     * @param selectorOptions advanced selector lookup options.
     * @param optionMode determines how the dropDownOption is looked after. Supported: "value", "label", "index".
     * @return new Select instance.
     */
    public static Select option(String selector, String dropDownOption, SelectorOptions selectorOptions, String optionMode) {
        return new Select(selector, dropDownOption, selectorOptions, optionMode);
    }

    /**
     * Set the value of a Selector of type select to the given option.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        if (this.selectorOptions == null) {
            return BrowseTheWeb.as(actor).selectOption(this.selector, this.dropDownOption, this.optionMode);
        } else {
            return BrowseTheWeb.as(actor).selectOption(this.selector, this.dropDownOption, this.selectorOptions, this.optionMode);
        }
    }

}


