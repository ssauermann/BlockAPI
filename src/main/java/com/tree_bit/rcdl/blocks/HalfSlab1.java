package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.IDataValueEnum;
import com.tree_bit.rcdl.blocks.dv.SlabPosition;
import com.tree_bit.rcdl.blocks.dv.SlabType1;

import javax.annotation.concurrent.Immutable;

/**
 * Data class for Stone Half Slabs. (Type 1)
 *
 * <p>
 * Allowed axes for rotation (multiple of 90/180 degree) are:
 * <ul>
 * <li>x (180°)</li>
 * <li>y (90°)</li>
 * <li>z (180°)</li>
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
@Immutable
public final class HalfSlab1 extends BlockData {

    private HalfSlab1(final SlabType1 type, final SlabPosition position) {
        super(type, position);
    }

    private HalfSlab1() {
        super(SlabType1.STONE, SlabPosition.DOWN);
    }

    private HalfSlab1(final IDataValueEnum[] values) {
        super(validateDV(values, SlabType1.class, SlabPosition.class));
    }

    /**
     * Returns an instance of the 'HalfSlab1' data with the given type and
     * position.
     *
     * @param type Type
     * @param position Position
     * @return Instance of a HalfSlab (Type 1)
     */
    @SuppressWarnings("null")
    public static HalfSlab1 getInstance(final SlabType1 type, final SlabPosition position) {
        return BlockDataFactory.getInstance(HalfSlab1.class, type, position);
    }

    /**
     * Returns an instance of the 'HalfSlab1' data with a default type (Stone)
     * and position (Down).
     *
     * @return Instance of a HalfSlab (Type 1)
     */
    @SuppressWarnings("null")
    public static HalfSlab1 getInstance() {
        return BlockDataFactory.getDefaultInstance(HalfSlab1.class);
    }
}
