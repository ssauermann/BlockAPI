package com.tree_bit.rcdl.blocks;

import static com.google.common.base.Preconditions.checkNotNull;

import org.eclipse.jdt.annotation.NonNull;

import java.util.EnumSet;
import java.util.Set;

/**
 * Axes of the three dimensional World.
 *
 * <ul>
 * <li>x-Axis (longitude - 'east-west')</li>
 * <li>y-Axis (elevation - 'height')</li>
 * <li>z-Axis (latitude - 'south-north'</li>
 * </ul>
 *
 */
public enum Axis {
    /** x-Axis (longitude - 'east-west') */
    X,
    /** y-Axis (elevation - 'height') */
    Y,
    /** z-Axis (latitude - 'south-north' */
    Z;


    /**
     * Creates an EnumSet with the two given axes representing a plain in the
     * coordinate system.
     *
     * @param a First axis
     * @param b Second axis (mustn't be identical with the first one)
     * @return Plain spanned by the two given axes.
     */
    @NonNull
    public static EnumSet<Axis> plain(final Axis a, final Axis b) {
        if (a == b) {
            throw new IllegalArgumentException("The two axes mustn't be identical: " + a);
        }
        return checkNotNull(EnumSet.of(a, b));
    }

    /**
     * Checks a plain for validity.
     *
     * @param plain Plain to check
     * @return Given plain
     */
    @NonNull
    public static Set<Axis> checkPlain(final Set<Axis> plain) {
        if (plain.size() != 2) {
            throw new IllegalArgumentException("A plain has to have exactly two distinct axes. Not: " + plain.size());
        }
        return plain;
    }
}
