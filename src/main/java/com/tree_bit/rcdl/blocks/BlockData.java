package com.tree_bit.rcdl.blocks;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;

import java.util.Map;
import java.util.Set;

/**
 * Representing a data value object of a block.
 *
 * <p>
 * Child classes have to implement the singleton pattern and have a static
 * method 'public static getInstance()' without parameters which returns a
 * default Data object. Additional getInstance(params) methods may be added.
 * </p>
 * <p>
 * Additionally a method 'static getInstances()' without parameters has to
 * provide all instances of this data object as a Set.
 * </p>
 */
abstract class BlockData {


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
     * @return Data with new orientation
     */
    public abstract BlockData rotate(final Axis axis, final int degree);

    /**
     * Mirrors the block at the given plain.
     * <p>
     * To create a plain you should use {@link Axis#plain(Axis, Axis)}.
     * </p>
     * <p>
     * Plains may be excluded for some blocks and throw an
     * {@link UnsupportedOperationException}.
     * </p>
     *
     * @param plain Mirror at the given plain
     * @return Data with new orientation
     */
    public abstract BlockData mirror(final Set<Axis> plain);

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

        final int count = degree / step;

        // if (degree < 0) {
        // count--;
        // } else {
        // count++;
        // }
        return count;
    }

    /**
     * Returns the data of this block. This method returns a map, mapping the
     * class of a subtype of IDataValueEnum to a specific value of the same (!)
     * type/class.
     *
     * @return Map containing all Data of the block
     */
    public abstract Map<Class<? extends IDataValueEnum>, IDataValueEnum> getData();

    /**
     * Returns the combined data value for this block data.
     *
     * @return Combined data value
     */
    int getDataValue() {
        int sum = 0;
        for (final IDataValueEnum data : this.getData().values()) {
            sum += data.getDataValue();
        }
        return sum;
    }

    @Override
    @SuppressWarnings("null")
    public String toString() {
        return Objects.toStringHelper(this).add("Combined", this.getDataValue())
                .add("Data", Joiner.on(',').skipNulls().join(this.getData().values().toArray())).toString();
    }
}
