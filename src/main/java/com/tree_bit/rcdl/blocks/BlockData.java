package com.tree_bit.rcdl.blocks;

import java.util.Set;

/**
 * Data values of a generic block.
 */
public enum BlockData implements IBlockData {

    /** No data value for this block */
    NONE;

    private final int dataValue;

    private BlockData() {
        this.dataValue = 0;
    }

    @Override
    public int getDataValue() {
        return this.dataValue;
    }

    @Override
    public void mirror(final Set<Axis> plain) {
        return;
    }

    @Override
    public void rotate(final Axis a, final int degree) {
        toCount(degree, 90);
        // No rotation for a default block
    }

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
}
