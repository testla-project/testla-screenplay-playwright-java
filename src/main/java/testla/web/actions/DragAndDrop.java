package testla.web.actions;

import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;

public class DragAndDrop extends Action {
    private final String sourceSelector;
    private final String targetSelector;
    private final SelectorOptions sourceOptions;
    private final SelectorOptions targetOptions;

    private DragAndDrop(String sourceSelector, String targetSelector) {
        this.sourceSelector = sourceSelector;
        this.targetSelector = targetSelector;
        this.sourceOptions = null;
        this.targetOptions = null;
    }

    private DragAndDrop(String sourceSelector, String targetSelector, SelectorOptions sourceOptions, SelectorOptions targetOptions) {
        this.sourceSelector = sourceSelector;
        this.targetSelector = targetSelector;
        this.sourceOptions = sourceOptions;
        this.targetOptions = targetOptions;
    }


    @Override
    public Object performAs(IActor actor) {
        if(this.sourceOptions == null) {
            BrowseTheWeb.as(actor).dragAndDrop(this.sourceSelector, this.targetSelector);
        } else {
            BrowseTheWeb.as(actor).dragAndDrop(this.sourceSelector, this.targetSelector, this.sourceOptions, this.targetOptions);
        }
        return null;
    }

    public static DragAndDrop execute(String sourceSelector, String targetSelector) {
        return new DragAndDrop(sourceSelector, targetSelector);
    }

    public static DragAndDrop execute(String sourceSelector, String targetSelector, SelectorOptions sourceOptions, SelectorOptions targetOptions) {
        return new DragAndDrop(sourceSelector, targetSelector, sourceOptions, targetOptions);
    }
}
