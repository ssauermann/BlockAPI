package com.tree_bit.rcdl.blocks;


public class PlacementInvalidException extends Exception {

    public PlacementInvalidException(final UnsupportedOperationException e) {
        super(e);
    }

}
