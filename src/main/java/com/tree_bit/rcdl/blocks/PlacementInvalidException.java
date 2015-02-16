package com.tree_bit.rcdl.blocks;

/**
 * Exception thrown when a block placement after an edit would be invalid.
 */
public class PlacementInvalidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a PlacementInvalidException with the given reason.
     *
     * @param e Reason
     */
    public PlacementInvalidException(final Exception e) {
        super(e);
    }

}
