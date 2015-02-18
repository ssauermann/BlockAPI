package com.tree_bit.rcdl.blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
public final class ColorBlock extends BlockData {

    private static Map<Color, ColorBlock> instances = new HashMap<>();

    static {
        for (final Color color : Color.values()) {
            if (color == null) {
                throw new NullPointerException();
            }
            instances.put(color, new ColorBlock(color));
        }
    }

    private final Color color;

    private ColorBlock(final Color color) {
        this.color = color;
    }

    @Override
    public BlockData rotate(final Axis axis, final int degree) {
        toCount(degree, 90);
        return this; // No rotation
    }

    @Override
    public BlockData mirror(final Set<Axis> plain) {
        Axis.checkPlain(plain);
        return this;
    }

    @Override
    @SuppressWarnings("null")
    public Map<Class<? extends IDataValueEnum>, IDataValueEnum> getData() {
        final Map<Class<? extends IDataValueEnum>, IDataValueEnum> map = new HashMap<>();
        map.put(Color.class, this.color);
        return map;
    }

}
