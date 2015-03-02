package com.tree_bit.rcdl.blocks;

import java.util.HashSet;
import java.util.Set;



/**
 * Data values of a generic block.
 *
 * <p>
 * Allowed axes for rotation (multiple of 90 degree) are:
 * <ul>
 * <li>x</li>
 * <li>y</li>
 * <li>z</li>
 * </ul>
 *
 * <p>
 * Allowed plains for mirroring are:
 * <ul>
 * <li>x-y</li>
 * <li>z-y</li>
 * <li>x-z</li>
 * </ul>
 */
public final class GenericBlockData extends BlockData {

    private static final GenericBlockData INSTANCE = new GenericBlockData();

    enum Data implements IDataValueEnum {
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

    private GenericBlockData() {
        super(Data.NONE);
    }

    /**
     * Returns an instance of the data of a generic block with no special
     * properties.
     *
     * @return Instance of generic data
     */
    public static GenericBlockData getInstance() {
        return INSTANCE;
    }

    /**
     * Returns all data instances of 'Generic Block Data'.
     *
     * @return Set of all instances
     */
    static Set<GenericBlockData> getInstances() {
        final HashSet<GenericBlockData> set = new HashSet<>();
        set.add(INSTANCE);
        return set;
    }
}
