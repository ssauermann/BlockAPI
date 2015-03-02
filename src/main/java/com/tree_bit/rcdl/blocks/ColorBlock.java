package com.tree_bit.rcdl.blocks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.concurrent.Immutable;

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

    private static Map<Color, ColorBlock> instances = new HashMap<>();

    static {
        for (final Color color : Color.values()) {
            if (color == null) {
                throw new NullPointerException();
            }
            instances.put(color, new ColorBlock(color));
        }
    }

    private ColorBlock(final Color color) {
        super(color);
    }

    /**
     * Returns an instance of the 'ColorBlock' data with a default color
     * (White).
     *
     * @return Instance of a ColorBlock
     */
    public static ColorBlock getInstance() {
        return instances.get(Color.White);
    }

    /**
     * Returns an instance of the 'ColorBlock' data with a given color.
     *
     * @param color Color
     * @return Instance of a ColorBlock
     */
    public static ColorBlock getInstance(final Color color) {
        return instances.get(color);
    }

    /**
     * Returns all data instances of 'ColorBlock'.
     *
     * @return Set of all instances
     */
    static Set<ColorBlock> getInstances() {
        return new HashSet<>(instances.values());
    }

}
