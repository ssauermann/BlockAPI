package com.tree_bit.rcdl.blocks;

import java.util.Set;

/**
 * Methods an orientation enum has to implement.
 *
 * This includes rotating, mirroring and getting the next orientation (cyclic).
 */
interface IOrientationEnum {

    /**
     * Rotates the orientation n times (clockwise). The new orientation is
     * independant of the degree and only dependant of the number of possible
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
     */
    IOrientationEnum rotate(Axis axis, int n);

    /**
     * Mirrors the orientation at the given plain.
     * <p>
     * Plains may be excluded for some types of orientation and throw an
     * {@link UnsupportedOperationException}.
     * </p>
     *
     * @param plain Mirror at the given plain
     * @return The new orientation after the mirroring
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
}
