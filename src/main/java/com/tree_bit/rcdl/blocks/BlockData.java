package com.tree_bit.rcdl.blocks;

import java.util.Set;

/**
 * Representing a data value object of a block. Child classes should implement
 * the singleton pattern.
 */
public abstract class BlockData {


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
    public abstract void rotate(final Axis axis, final int degree);

    /**
     * Mirrors the block at the given plain.
     * <p>
     * Plains may be excluded for some blocks and throw an
     * {@link UnsupportedOperationException}.
     * </p>
     *
     * @param plain Mirror at the given plain
     */
    public abstract void mirror(final Set<Axis> plain);

    /**
     * Calculates the number of single turn steps from the given degrees and the
     * minimum roration step in degree.
     * <p>
     * An {@link IllegalArgumentException} is thrown if the given degree are no
     * multiple of the step.
     * </p>
     *
     * @param degree Degree of the rotation
     * @param step Degree for one step
     * @return Number of single rotation steps.
     */
    static int toCount(final int degree, final int step) {
        if ((degree % step) != 0) {
            throw new IllegalArgumentException("Rotation is only allowed for multiples of " + step + " degree");
        }

        int count = degree / step;

        if (degree < 0) {
            count--;
        } else {
            count++;
        }
        return count;
    }

    /**
     * Returns the data of this block.
     *
     * @return Data
     */
    abstract IDataValueEnum getData();

}
