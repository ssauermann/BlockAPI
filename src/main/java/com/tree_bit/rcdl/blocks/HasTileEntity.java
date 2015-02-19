package com.tree_bit.rcdl.blocks;

/**
 * Interface indicating that a block needs a extra TileEntity to fully define
 * its state and data.
 */
interface HasTileEntity {

    /**
     * Returns the TileEntity of this block.
     * 
     * @return TileEntity
     */
    public TileEntity getTileEntity();

}
