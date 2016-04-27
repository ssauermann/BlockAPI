package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.IDataValueEnum;

import org.eclipse.jdt.annotation.NonNull;



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

    /**
     * Data of a generic block.
     */
    enum Data implements IDataValueEnum {
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

    private GenericBlockData() {
        super(Data.NONE);
    }

    private GenericBlockData(final @NonNull IDataValueEnum[] values) {
        super(validateDV(values, Data.class));
    }

    /**
     * Returns an instance of the data of a generic block with no special
     * properties.
     *
     * @return Instance of generic data
     */
    public static GenericBlockData getInstance() {
        return BlockDataFactory.getDefaultInstance(GenericBlockData.class);
    }

}
