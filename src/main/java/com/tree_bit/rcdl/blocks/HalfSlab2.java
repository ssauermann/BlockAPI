package com.tree_bit.rcdl.blocks;

import com.tree_bit.rcdl.blocks.dv.IDataValueEnum;
import com.tree_bit.rcdl.blocks.dv.SlabPosition;
import com.tree_bit.rcdl.blocks.dv.SlabType2;

import org.eclipse.jdt.annotation.NonNull;

import jdk.nashorn.internal.ir.annotations.Immutable;


/**
 * Data class for Stone Half Slabs. (Type 2)
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
public final class HalfSlab2 extends BlockData {

    private HalfSlab2(final SlabType2 type, final SlabPosition position) {
        super(type, position);
    }

    private HalfSlab2() {
        super(SlabType2.RED_SANDSTONE, SlabPosition.DOWN);
    }

    private HalfSlab2(final @NonNull IDataValueEnum[] values) {
        super(validateDV(values, SlabType2.class, SlabPosition.class));
    }

    /**
     * Returns an instance of the 'HalfSlab2' data with the given type and
     * position.
     *
     * @param type Type
     * @param position Position
     * @return Instance of a HalfSlab (Type 2)
     */
    public static HalfSlab2 getInstance(final SlabType2 type, final SlabPosition position) {
        return BlockDataFactory.getInstance(HalfSlab2.class, type, position);
    }

    /**
     * Returns an instance of the 'HalfSlab2' data with a default type (Stone)
     * and position (Down).
     *
     * @return Instance of a HalfSlab (Type 2)
     */
    public static HalfSlab2 getInstance() {
        return BlockDataFactory.getDefaultInstance(HalfSlab2.class);
    }

}
