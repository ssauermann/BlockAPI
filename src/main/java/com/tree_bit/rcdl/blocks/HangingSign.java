package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.IOrientationEnum;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import org.jnbt.StringTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Data values of a 'Hanging Sign' block.
 *
 * <p>
 * Data enum: {@link SignOrientation}, {@link FormatText}
 *
 * <p>
 * Allowed axes for rotation (multiple of 90 degree) are:
 * <ul>
 * <li>y</li>
 * </ul>
 *
 * <p>
 * Allowed plains for mirroring are: *
 * <ul>
 * <li>x-y</li>
 * <li>z-y</li>
 * </ul>
 * *Note: This mirroring does not include the text.
 */
public class HangingSign extends BlockData {

    @SuppressWarnings("null")
    private static Table<SignOrientation, TileEntity, HangingSign> instances = HashBasedTable.create();

    private HangingSign(final SignOrientation orientation, final TileEntity entity) {
        super(entity, orientation);
    }

    /**
     * Creates a Sign TileEntity from a FormatText array.
     *
     * @param text Array containing text for each line of the sign. (max 4)
     * @return TileEntity
     *
     * @throws IllegalArgumentException if text[] has {@literal length>4}
     */
    static TileEntity createEntity(final FormatText[] text) {
        if (text.length > 4) {
            throw new IllegalArgumentException("Sign can't have more than 4 lines of text! Given: " + text.length);
        }
        final TileEntity.Builder b = new TileEntity.Builder("Sign");
        for (int i = 0; i < 4; i++) {
            final String s = (i < text.length) ? text[i].getStringWithCodes() : "";
            b.add(new StringTag("Text" + (i + 1), s));
        }
        return b.build();
    }

    /**
     * Returns an instance of the 'HangingSign' data with a default orientation
     * (North) and no text.
     *
     * @return Instance of a HangingSign
     */
    public static HangingSign getInstance() {
        @SuppressWarnings("null")
        final TileEntity e = createEntity(new FormatText[0]);

        return HangingSign.getOrCreate(SignOrientation.North, e);

    }

    /**
     * Returns an instance of the 'HangingSign' data with the given orientation
     * and text.
     *
     * @param orientation Orientation
     * @param text Array of text containing a maximum of 4 entries (one per each
     *        line)
     * @return Instance of a HangingSign
     *
     * @throws IllegalArgumentException if text has {@literal length>4}
     */
    public static HangingSign getInstance(final SignOrientation orientation, final FormatText[] text) {
        final TileEntity e = createEntity(text);
        return getOrCreate(orientation, e);
    }

    /**
     * Returns all data instances of 'HangingSign'.
     *
     * @return Set of all instances
     */
    static Set<HangingSign> getInstances() {
        return new HashSet<>(instances.values());
    }

    @SuppressWarnings({"null", "unused"})
    // THIS IS NOT DEAD CODE!!!! instance can be null
    private static HangingSign getOrCreate(final SignOrientation orientation, final TileEntity e) {
        HangingSign instance = instances.get(orientation, e);
        // No dead code
        if (instance == null) {
            synchronized (HangingSign.class) {
                instance = instances.get(orientation, e);
                if (instance == null) {
                    instance = new HangingSign(orientation, e);
                }
                instances.put(orientation, e, instance);
            }
        }
        return instance;
    }

    /**
     * Enum of the four directions (North, East, South, West), mapping those to
     * their block data value.
     */
    public enum SignOrientation implements IOrientationEnum {
        /** North */
        North(2),
        /** East */
        East(5),
        /** South */
        South(3),
        /** West */
        West(4);

        private int value;

        private SignOrientation(final int value) {
            this.value = value;
        }

        @Override
        public SignOrientation rotate(final Axis axis, final int n) {
            if (axis == Axis.Y) {
                return this.next(n);
            }
            throw new UnsupportedOperationException("Can't rotate at this axis: " + axis);
        }

        @Override
        public SignOrientation mirror(final Set<Axis> plain) {
            Axis.checkPlain(plain);
            if (plain.contains(Axis.Y) && plain.contains(Axis.X)) {
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
        public SignOrientation next(final int i) {
            final SignOrientation temp = values()[(this.ordinal() + i) % values().length];
            if (temp != null) {
                return temp;
            }
            throw new NullPointerException();
        }

        @Override
        public int getDataValue() {
            return this.value;
        }

        @Override
        public int getStep() {
            return 30;
        }
    }

}
