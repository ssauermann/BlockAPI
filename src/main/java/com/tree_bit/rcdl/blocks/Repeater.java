package com.tree_bit.rcdl.blocks;

import com.tree_bit.blockapi.data.IDataValue;
import com.tree_bit.rcdl.blocks.dv.OrientationNESW;

import org.eclipse.jdt.annotation.NonNull;

import jdk.nashorn.internal.ir.annotations.Immutable;


/**
 * Data values of a 'Repeater' block.
 *
 * <p>
 * Data enum: {@link OrientationNESW}, {@link Delay}
 *
 * <p>
 * Allowed axes for rotation (multiple of 90 degree) are:
 * <ul>
 * <li>y</li>
 * </ul>
 *
 * <p>
 * Allowed plains for mirroring are:
 * <ul>
 * <li>x-y</li>
 * <li>z-y</li>
 * </ul>
 */
@Immutable
public final class Repeater extends BlockData {

    /**
     * Representing the delay of a 'Repeater' block.
     */
    public enum Delay implements IDataValue {
        /** 1-Tick Delay */
        D1(0),
        /** 2-Tick Delay */
        D2(4),
        /** 3-Tick Delay */
        D3(8),
        /** 4-Tick Delay */
        D4(12);

        private int value;

        private Delay(final int value) {
            this.value = value;
        }

        @Override
        public int getDataValue() {
            return this.value;
        }
    }

    private Repeater(final OrientationNESW orientation, final Delay delay) {
        super(orientation, delay);
    }

    private Repeater() {
        super(OrientationNESW.North, Delay.D1);
    }

    private Repeater(final @NonNull IDataValue[] values) {
        super(validateDV(values, OrientationNESW.class, Delay.class));
    }

    /**
     * Returns an instance of the 'Repeater' data with the given orientation and
     * delay.
     *
     * @param orientation Orientation
     * @param delay Delay
     * @return Instance of a repeater
     */
    public static Repeater getInstance(final OrientationNESW orientation, final Delay delay) {
        return BlockDataFactory.getInstance(Repeater.class, orientation, delay);
    }

    /**
     * Returns an instance of the 'Repeater' data with a default orientation
     * (North) and default delay (1 Tick).
     *
     * @return Instance of a repeater
     */
    public static Repeater getInstance() {
        return BlockDataFactory.getDefaultInstance(Repeater.class);
    }

}
