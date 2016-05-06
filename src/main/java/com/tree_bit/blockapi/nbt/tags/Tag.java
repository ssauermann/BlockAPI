/**
 * Copyright (c) 2016 The BlockAPI authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.tree_bit.blockapi.nbt.tags;

import com.tree_bit.blockapi.internal.BlockApiStyle;

import org.immutables.value.Value;

import java.util.List;
import java.util.Map;

/**
 * Represents an NBT tag
 *
 * @author Sascha Sauermann
 * @param <T> Content of this tag
 */
public interface Tag<T> {

    /**
     * Gets the name of this tag.
     *
     * @return The name of this tag
     */
    @Value.Parameter(order = 1)
    public String getName();

    /**
     * Gets the value of this tag.
     *
     * @return The value of this tag
     */
    @Value.Parameter(order = 2)
    public T getValue();



    /**
     * ByteArray Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _ByteArrayTag implements Tag<byte[]> {
        // Will be generated
    }

    /**
     * Byte Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _ByteTag implements Tag<Byte> {
        // Will be generated
    }

    /**
     * Compound Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _CompoundTag implements Tag<Map<String, Tag<?>>> {
        // Will be generated
    }

    /**
     * Double Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _DoubleTag implements Tag<Double> {
        // Will be generated
    }

    /**
     * Float Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _FloatTag implements Tag<Float> {
        // Will be generated
    }

    /**
     * IntArray Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _IntArrayTag implements Tag<int[]> {
        // Will be generated
    }

    /**
     * Int Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _IntTag implements Tag<Integer> {
        // Will be generated
    }

    /**
     * List Tag
     *
     * @param <T> Content tag type
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _ListTag // <T>
            implements Tag<List<Tag<?>>> { // Tag<List<Tag<T>>>
        // Will be generated
    }

    /**
     * Long Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _LongTag implements Tag<Long> {
        // Will be generated
    }

    /**
     * Short Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _ShortTag implements Tag<Short> {
        // Will be generated
    }

    /**
     * String Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _StringTag implements Tag<String> {
        // Will be generated
    }


}
