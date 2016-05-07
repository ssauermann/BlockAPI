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

import com.google.common.collect.Lists;

import java.util.List;

/**
 * NBT ListTag
 *
 * @param <T> Content type
 */
public abstract class ListTag<T extends Tag<?>> implements Tag<List<T>> {

    private final Class<? extends org.jnbt.Tag> clazz;

    /**
     * Setting the class for unwrapping of this ListTag.
     *
     * @param clazz Content type class
     */
    protected ListTag(final Class<T> clazz) {
        Class<? extends org.jnbt.Tag> cl;

        if (ByteArrayTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.ByteArrayTag.class;
        }

        else if (ByteTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.ByteTag.class;
        }

        else if (CompoundTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.CompoundTag.class;
        }

        else if (DoubleTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.DoubleTag.class;
        }

        else if (EndTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.EndTag.class;
        }

        else if (FloatTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.FloatTag.class;
        }

        else if (IntArrayTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.IntArrayTag.class;
        }

        else if (IntTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.IntTag.class;
        }

        else if (ListTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.ListTag.class;
        }

        else if (LongTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.LongTag.class;
        }

        else if (ShortTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.ShortTag.class;
        }

        else if (StringTag.class.isAssignableFrom(clazz)) {
            cl = org.jnbt.StringTag.class;
        }

        else {
            cl = org.jnbt.Tag.class;
        }

        this.clazz = cl;
    }

    @Override
    public org.jnbt.ListTag unwrap() {
        return new org.jnbt.ListTag(this.getName(), this.clazz, Lists.transform(this.getValue(), Tag::unwrap));
    }

    /**
     * Construct a new immutable {@code ListTag} instance.
     *
     * @param name The value for the {@code name} attribute
     * @param clazz Class of list content
     * @param value The value for the {@code value} attribute
     * @return An immutable ListTag instance
     */
    public static <T extends Tag<?>> ListTag<T> of(final String name, final Class<T> clazz, final List<? extends T> value) {
        return ListTag.of(name, clazz, value);
    }
}
