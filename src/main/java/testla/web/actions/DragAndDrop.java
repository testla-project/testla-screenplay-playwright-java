package testla.web.actions;

import com.microsoft.playwright.Locator;
import testla.screenplay.action.Action;
import testla.screenplay.actor.IActor;
import testla.web.SelectorOptions;
import testla.web.abilities.BrowseTheWeb;


/**
 * Action Class. DragAndDrop an element specified by a selector string and drop it on an element specified by another
 * selector string.
 */
public class DragAndDrop extends Action {

    private final String sourceSelector;
    private final String targetSelector;
    private final Locator sourceLocator;
    private final Locator targetLocator;
    private final SelectorOptions sourceOptions;
    private final SelectorOptions targetOptions;

    private DragAndDrop(String sourceSelector, String targetSelector) {
        this.sourceSelector = sourceSelector;
        this.targetSelector = targetSelector;
        this.sourceLocator = null;
        this.targetLocator = null;
        this.sourceOptions = null;
        this.targetOptions = null;
    }

    private DragAndDrop(String sourceSelector, String targetSelector, SelectorOptions sourceOptions, SelectorOptions targetOptions) {
        this.sourceSelector = sourceSelector;
        this.targetSelector = targetSelector;
        this.sourceLocator = null;
        this.targetLocator = null;
        this.sourceOptions = sourceOptions;
        this.targetOptions = targetOptions;
    }

    private DragAndDrop(Locator sourceLocator, Locator targetLocator) {
        this.sourceSelector = null;
        this.targetSelector = null;
        this.sourceLocator = sourceLocator;
        this.targetLocator = targetLocator;
        this.sourceOptions = null;
        this.targetOptions = null;
    }

    private DragAndDrop(Locator sourceLocator, Locator targetLocator, SelectorOptions sourceOptions, SelectorOptions targetOptions) {
        this.sourceSelector = null;
        this.targetSelector = null;
        this.sourceLocator = sourceLocator;
        this.targetLocator = targetLocator;
        this.sourceOptions = sourceOptions;
        this.targetOptions = targetOptions;
    }

    /**
     * Drag the specified selector and drop it on the target.
     *
     * @param actor the actor.
     */
    @Override
    public Object performAs(IActor actor) {
        if (this.sourceOptions == null && this.targetOptions == null) {
            if (this.sourceSelector != null) { // only need to check one selector for null as both source and target are set at the same time
                BrowseTheWeb.as(actor).dragAndDrop(this.sourceSelector, this.targetSelector);
            } else if (this.sourceLocator != null) {
                BrowseTheWeb.as(actor).dragAndDrop(this.sourceLocator, this.targetLocator);
            }
        } else {
            if (this.sourceSelector != null) { // only need to check one selector for null as both source and target are set at the same time
                BrowseTheWeb.as(actor).dragAndDrop(this.sourceSelector, this.targetSelector, this.sourceOptions, this.targetOptions);
            } else if (this.sourceLocator != null) {
                BrowseTheWeb.as(actor).dragAndDrop(this.sourceLocator, this.targetLocator, this.sourceOptions, this.targetOptions);
            }
        }
        return null;
    }

    /**
     * Drag the specified source element to the specified target element and drop it.
     *
     * @param sourceSelector the selector of the source element.
     * @param targetSelector the selector of the target element.
     */
    public static DragAndDrop execute(String sourceSelector, String targetSelector) {
        return new DragAndDrop(sourceSelector, targetSelector);
    }

    /**
     * Drag the specified source element to the specified target element and drop it.
     *
     * @param sourceLocator the locator of the source element.
     * @param targetLocator the locator of the target element.
     */
    public static DragAndDrop execute(Locator sourceLocator, Locator targetLocator) {
        return new DragAndDrop(sourceLocator, targetLocator);
    }

    /**
     * Drag the specified source element to the specified target element and drop it.
     *
     * @param sourceSelector the selector of the source element.
     * @param targetSelector the selector of the target element.
     * @param sourceOptions advanced selector lookup options for the source selector.
     * @param targetOptions advanced selector lookup options for the source selector.
     */
    public static DragAndDrop execute(String sourceSelector, String targetSelector, SelectorOptions sourceOptions, SelectorOptions targetOptions) {
        return new DragAndDrop(sourceSelector, targetSelector, sourceOptions, targetOptions);
    }

    /**
     * Drag the specified source element to the specified target element and drop it.
     *
     * @param sourceLocator the locator of the source element.
     * @param targetLocator the locator of the target element.
     * @param sourceOptions advanced selector lookup options for the source selector.
     * @param targetOptions advanced selector lookup options for the source selector.
     */
    public static DragAndDrop execute(Locator sourceLocator, Locator targetLocator, SelectorOptions sourceOptions, SelectorOptions targetOptions) {
        return new DragAndDrop(sourceLocator, targetLocator, sourceOptions, targetOptions);
    }
}
