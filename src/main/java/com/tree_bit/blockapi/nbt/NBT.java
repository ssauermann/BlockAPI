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
package com.tree_bit.blockapi.nbt;

import com.tree_bit.blockapi.nbt.tags.ByteArrayTag;
import com.tree_bit.blockapi.nbt.tags.ByteTag;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;
import com.tree_bit.blockapi.nbt.tags.DoubleTag;
import com.tree_bit.blockapi.nbt.tags.EndTag;
import com.tree_bit.blockapi.nbt.tags.FloatTag;
import com.tree_bit.blockapi.nbt.tags.IntArrayTag;
import com.tree_bit.blockapi.nbt.tags.IntTag;
import com.tree_bit.blockapi.nbt.tags.ListTag;
import com.tree_bit.blockapi.nbt.tags.LongTag;
import com.tree_bit.blockapi.nbt.tags.ShortTag;
import com.tree_bit.blockapi.nbt.tags.StringTag;
import com.tree_bit.blockapi.nbt.tags.Tag;

import java.util.Map;

/**
 * Builder for NBT Tags
 */
public class NBT {

    private NBT() {}

    /**
     * Creates a new NBT tag builder.
     *
     * @return New NBT tag builder
     */
    public static CompoundBuilder begin() {
        return Compound("");
    }

    /**
     * Creates a byte array tag.
     *
     * @param name The name
     * @param value The value array
     *
     * @return New tag
     */
    public static ByteArrayTag ByteArray(final String name, final byte[] value) {
        return ByteArrayTag.of(name, value);
    }

    /**
     * Creates a byte tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return New tag
     */
    public static ByteTag Byte(final String name, final byte value) {
        return ByteTag.of(name, value);
    }

    /**
     * Creates a compound tag.
     *
     * @param name The name
     * @param value The map with subtags
     *
     * @return New tag
     */
    public static CompoundTag Compound(final String name, final Map<String, ? extends Tag<?>> value) {
        return CompoundTag.of(name, value);
    }

    /**
     * Creates a double tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return New tag
     */
    public static DoubleTag Double(final String name, final double value) {
        return DoubleTag.of(name, value);
    }

    /**
     * Creates an end tag.
     *
     * @return New tag
     */
    public static EndTag End() {
        return EndTag.of();
    }

    /**
     * Creates a float tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return New tag
     */
    public static FloatTag Float(final String name, final float value) {
        return FloatTag.of(name, value);
    }

    /**
     * Creates an int array tag.
     *
     * @param name The name
     * @param value The value array
     *
     * @return New tag
     */
    public static IntArrayTag IntArray(final String name, final int[] value) {
        return IntArrayTag.of(name, value);
    }

    /**
     * Creates an int tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return New tag
     */
    public static IntTag Int(final String name, final int value) {
        return IntTag.of(name, value);
    }

    /**
     * Creates a list tag.
     *
     * @param name The name
     * @param type The type of item in the list
     * @param value The list
     *
     * @return New tag
     */
    public static <T extends Tag<?>> ListTag<T> List(final String name, final Class<T> type, final java.util.List<? extends T> value) {
        return ListTag.of(name, type, value);
    }

    /**
     * Creates a long tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return New tag
     */
    public static LongTag Long(final String name, final long value) {
        return LongTag.of(name, value);
    }

    /**
     * Creates a short tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return New tag
     */
    public static ShortTag Short(final String name, final short value) {
        return ShortTag.of(name, value);
    }

    /**
     * Creates a string tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return New tag
     */
    public static StringTag String(final String name, final String value) {
        return StringTag.of(name, value);
    }

    /////////////////// BUILDER/////////////////////////////////////////
    /**
     * Creates a compound tag builder.
     *
     * @param name The name
     *
     * @return New tag builder
     */
    public static CompoundBuilder Compound(final String name) {
        return new CompoundBuilder(name);
    }

    /**
     * Creates a list tag builder.
     *
     * @param name The name
     * @param type Type of the list
     *
     * @return New tag builder
     */
    public static <T extends Tag<?>> ListBuilder<T> List(final String name, final Class<T> type) {
        return new ListBuilder<>(name, type);
    }
}
