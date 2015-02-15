package com.tree_bit.rcdl.blocks;

import java.util.Set;

/**
 * Data values of a generic block.
 *
 * <p>
 * Data enum: {@link Data}
 * </p>
 *
 * <p>
 * Allowed axes for rotation (multiple of 90 degree) are:
 * <ul>
 * <li>x</li>
 * <li>y</li>
 * <li>z</li>
 * </ul>
 * </p>
 *
 * <p>
 * Allowed plains for mirroring are:
 * <ul>
 * <li>x-y</li>
 * <li>z-y</li>
 * <li>x-z</li>
 * </ul>
 * </p>
 */
public final class GenericBlockData extends BlockData {

    /**
     * Representing the data of a generic block.
     */
    public enum Data implements IDataValueEnum {
        /** No data */
        NONE(0);

        private final int datavalue;

        private Data(final int datavalue) {
            this.datavalue = datavalue;
        }

        @Override
        public int getDataValue() {
            return this.datavalue;
        }
    }

    private final Data data;

    private static final GenericBlockData INSTANCE = new GenericBlockData(Data.NONE);

    private GenericBlockData(final Data data) {
        this.data = data;
    }

    /**
     * Returns an instance of the data of a genric block with no special
     * properties.
     *
     * @return Instance of generic data
     */
    public static GenericBlockData getInstance() {
        return INSTANCE;
    }

    @Override
    public GenericBlockData mirror(final Set<Axis> plain) {
        return this;
    }

    @Override
    public GenericBlockData rotate(final Axis axis, final int degree) {
        toCount(degree, 90);
        return this; // No rotation for a default block
    }

    @Override
    @SuppressWarnings("null")
    public Data[] getData() {
        return new Data[] {this.data};
    }
}
