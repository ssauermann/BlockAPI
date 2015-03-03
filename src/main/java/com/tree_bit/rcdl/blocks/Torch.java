package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.IDataValueEnum;
import com.tree_bit.rcdl.blocks.dv.IOrientationEnum;

import com.google.common.math.IntMath;

import java.util.Arrays;
import java.util.Set;

import javafx.geometry.Orientation;

import javax.annotation.concurrent.Immutable;

/**
 * Data values of a 'Torch' block.
 *
 * <p>
 * Data enum: {@link Orientation}
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
public final class Torch extends BlockData {

    /**
     * Orientations of a Torch
     */
    public enum TorchOrientation implements IOrientationEnum {

        /** The Facing east. */
        East(1),
        /** The Facing south. */
        South(3),
        /** The Facing west. */
        West(2),
        /** The Facing north. */
        North(4),
        /** The Facing up. */
        Up(5);

        private final int value;

        private TorchOrientation(final int value) {
            this.value = value;
        }

        @Override
        public int getDataValue() {
            return this.value;
        }

        @Override
        public TorchOrientation rotate(final Axis axis, final int n) {
            if (axis != Axis.Y) {
                throw new UnsupportedOperationException("Can't rotate at this axis: " + axis);
            }
            if (this.value == Up.value) {
                return Up;
            }
            return this.next(n + ((n + this.ordinal()) / 4));
        }

        @Override
        public TorchOrientation mirror(final Set<Axis> plain) {
            Axis.checkPlain(plain);
            if ((this == Up) && plain.contains(Axis.Y)) {
                return Up;
            } else if (plain.contains(Axis.Y) && plain.contains(Axis.X)) {
                if (this.next(0) == South) {
                    return North;
                } else if (this.next(0) == North) {
                    return South;
                }
            } else if (plain.contains(Axis.Y) && plain.contains(Axis.Z)) {
                if (this.next(0) == East) {
                    return West;
                } else if (this.next(0) == West) {
                    return East;
                }
            } else {
                throw new UnsupportedOperationException("Can't mirror at this plain: " + Arrays.toString(plain.toArray(new Axis[] {})));
            }
            return this;
        }

        @Override
        public TorchOrientation next(final int i) {
            final TorchOrientation temp = values()[IntMath.mod((this.ordinal() + i), 5)];
            if (temp != null) {
                return temp;
            }
            throw new NullPointerException();
        }

        @Override
        public int getStep() {
            return 90;
        }

    }

    static {
        @SuppressWarnings("null")
        final Class<Torch> clazz = Torch.class;

        for (final TorchOrientation orientation : TorchOrientation.values()) {
            if (orientation == null) {
                throw new NullPointerException();
            }
            BlockDataFactory.register(clazz, new Torch(orientation));
        }

        BlockDataFactory.registerDefault(clazz, new Torch(TorchOrientation.Up));
    }

    private Torch(final TorchOrientation data) {
        super(data);
    }

    @SuppressWarnings("null")
    private Torch(final IDataValueEnum[] values) {
        super(validateDV(values, TorchOrientation.class));
    }

    /**
     * Returns an instance of the Torch data with the given orientation.
     *
     * @param orientation Torch orientation
     * @return Instance of Torch data
     */
    @SuppressWarnings("null")
    public static Torch getInstance(final TorchOrientation orientation) {
        return BlockDataFactory.getInstance(Torch.class, orientation);
    }

    /**
     * Returns an instance of the Torch data with a default orientation (UP).
     *
     * @return Instance of Torch data
     */
    @SuppressWarnings("null")
    public static Torch getInstance() {
        return BlockDataFactory.getDefaultInstance(Torch.class);
    }

}
