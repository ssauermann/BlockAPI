package com.tree_bit.rcdl.blocks;

import com.tree_bit.blockapi.data.IDataValue;
import com.tree_bit.blockapi.data.minecraft.Color;

import org.eclipse.jdt.annotation.NonNull;

import jdk.nashorn.internal.ir.annotations.Immutable;


/**
 * Data values of a colored block.
 *
 * <p>
 * Data enum: {@link Color}
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
@Immutable
public final class ColorBlock extends BlockData {

    private ColorBlock(final Color color) {
        super(color);
    }

    private ColorBlock() {
        super(Color.White);
    }

    private ColorBlock(final @NonNull IDataValue[] values) {
        super(validateDV(values, Color.class));
    }

    /**
     * Returns an instance of the 'ColorBlock' data with a default color
     * (White).
     *
     * @return Instance of a ColorBlock
     */
    public static ColorBlock getInstance() {
        return BlockDataFactory.getDefaultInstance(ColorBlock.class);
    }

    /**
     * Returns an instance of the 'ColorBlock' data with a given color.
     *
     * @param color Color
     * @return Instance of a ColorBlock
     */
    public static ColorBlock getInstance(final Color color) {
        return BlockDataFactory.getInstance(ColorBlock.class, color);
    }

}
