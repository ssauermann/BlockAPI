package com.tree_bit.rcdl.blocks;

import java.util.Set;

/**
 * Represents the data values of a block.
 */
public interface IBlockData {

    /**
     * Returns the data value of this block.
     *
     * @return Data value
     */
    public int getDataValue();

    /**
     * Mirrors the block at the given plain.
     * <p>
     * Plains may be excluded for some blocks and throw an
     * {@link UnsupportedOperationException}.
     * </p>
     *
     * @param plain Mirror at the given plain
     */
    public void mirror(final Set<Axis> plain);

    /**
     * Rotates the block the given amount of degree. (Axis viewed from +infinity
     * to -infinity/zero)
     *
     * <p>
     * The given degree have to be a multiple of the allowed rotation step (e.g.
     * 30°, 90°). This is dependend on the block.
     * </p>
     * <p>
     * Axes may be excluded for some blocks and throw an
     * {@link UnsupportedOperationException}.
     * </p>
     *
     * @param axis Axis of rotation
     * @param degree Degree
     */
    public void rotate(final Axis axis, final int degree);
}
