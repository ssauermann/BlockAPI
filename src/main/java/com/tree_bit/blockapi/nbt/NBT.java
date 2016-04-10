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

import com.google.common.collect.ImmutableList;

import org.jnbt.ByteArrayTag;
import org.jnbt.ByteTag;
import org.jnbt.CompoundTag;
import org.jnbt.DoubleTag;
import org.jnbt.EndTag;
import org.jnbt.FloatTag;
import org.jnbt.IntArrayTag;
import org.jnbt.IntTag;
import org.jnbt.ListTag;
import org.jnbt.LongTag;
import org.jnbt.ShortTag;
import org.jnbt.StringTag;
import org.jnbt.Tag;

import java.util.Map;

/**
 * Builder for NBT Tags
 */
public class NBT {

    private NBT() {}

    /**
     * Creates a new nbt tag builder.
     *
     * @return New nbt tag builder
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
        return new ByteArrayTag(name, value);
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
        return new ByteTag(name, value);
    }

    /**
     * Creates a compound tag.
     *
     * @param name The name
     * @param value The map with subtags
     *
     * @return New tag
     */
    public static CompoundTag Compound(final String name, final Map<String, Tag> value) {
        return new CompoundTag(name, value);
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
        return new DoubleTag(name, value);
    }

    /**
     * Creates an end tag.
     *
     * @return New tag
     */
    public static EndTag End() {
        return new EndTag();
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
        return new FloatTag(name, value);
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
        return new IntArrayTag(name, value);
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
        return new IntTag(name, value);
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
    public static <T extends Tag> ListTag List(final String name, final Class<? extends T> type, final java.util.List<T> value) {
        return new ListTag(name, type, ImmutableList.copyOf(value));
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
        return new LongTag(name, value);
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
        return new ShortTag(name, value);
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
        return new StringTag(name, value);
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
    public static <T extends Tag> ListBuilder<T> List(final String name, final Class<T> type) {
        return new ListBuilder<>(name, type);
    }
}
