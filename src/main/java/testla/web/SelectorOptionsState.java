package testla.web;

/**
 * Represents the selector options state.
 */
public enum SelectorOptionsState {
    /**
     * Element to be present in DOM
     */
    ATTACHED,
    /**
     * Element to not be present in DOM
     */
    DETACHED,
    /**
     * Element to have non-empty bounding box and no visibility:hidden.
     * That element without any content or with display:none has an empty bounding box and is not considered visible
     */
    VISIBLE,
    /**
     * Element to be either detached from DOM, or have an empty bounding box or
     */
    HIDDEN
}
