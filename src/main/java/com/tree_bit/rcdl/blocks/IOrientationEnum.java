package com.tree_bit.rcdl.blocks;

import java.util.Set;

/**
 * Methods an orientation enum has to implement.
 *
 * This includes rotating, mirroring and getting the next orientation (cyclic).
 */
interface IOrientationEnum extends IDataValueEnum {

    /**
     * Rotates the orientation n times (clockwise). The new orientation is
     * independent of the degree and only dependent of the number of possible
     * fix rotations. Use negative numbers for counterclockwise rotations.
     * (Clockwise {@literal -> } Axis viewed from +infinity to -infinity/zero)
     *
     * <p>
     * Axes may be excluded for some types of orientation and throw an
     * {@link UnsupportedOperationException}.
     * </p>
     *
     * @param axis Axis of rotation
     * @param n Number of rotations
     * @return The new orientation after the rotations
     *
     * @throws UnsupportedOperationException if the axis is not supported
     */
    IOrientationEnum rotate(Axis axis, int n);

    /**
     * Rotates the orientation x degree (clockwise). (Clockwise {@literal -> }
     * Axis viewed from +infinity to -infinity/zero)
     *
     * <p>
     * Axes may be excluded for some types of orientation and throw an
     * {@link UnsupportedOperationException}.
     * </p>
     *
     * @param axis Axis of rotation
     * @param degree Degree for rotation
     * @return The new orientation after the rotations
     *
     * @throws UnsupportedOperationException if the axis is not supported
     * @throws IllegalArgumentException if the given degree are no multiple of
     *         the allowed step
     */
    default IOrientationEnum rotateDegree(final Axis axis, final int degree) {
        return rotate(axis, IOrientationEnum.toCount(degree, getStep()));
    }

    /**
     * Mirrors the orientation at the given plain.
     * <p>
     * Plains may be excluded for some types of orientation and throw an
     * {@link UnsupportedOperationException}.
     * </p>
     *
     * @param plain Mirror at the given plain
     * @return The new orientation after the mirroring
     * @throws UnsupportedOperationException if the axes are not supported.
     */
    IOrientationEnum mirror(Set<Axis> plain);

    /**
     * Returns the next orientation n steps after the current one. This
     * operation is cyclic.
     *
     * <p>
     * <b>Example:</b> <br>
     * next(3) == next(1).next(1).next(1)
     * </p>
     *
     * <p>
     * An implementation of this method should follow a reasonable order.
     * </p>
     *
     * @param n Number of steps to the desired orientation (may be negative)
     *
     * @return The next orientation
     */
    IOrientationEnum next(int n);

    /**
     * Gets the degrees of one rotation step.
     *
     * @return Degree
     */
    int getStep();

    /**
     * Calculates the number of single turn steps from the given degrees and the
     * minimum rotation step in degree.
     *
     * @param degree Degree of the rotation
     * @param step Degree for one step
     * @return Number of single rotation steps.
     *
     * @throws IllegalArgumentException if the given degree are no multiple of
     *         the step.
     */
    static int toCount(final int degree, final int step) {
        if ((degree % step) != 0) {
            throw new IllegalArgumentException("Rotation is only allowed for multiples of " + step + " degree");
        }

        final int count = degree / step;

        return count;
    }
}
