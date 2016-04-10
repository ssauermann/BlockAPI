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

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableList;

import org.eclipse.jdt.annotation.Nullable;
import org.jnbt.ListTag;
import org.jnbt.Tag;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**
 * Builder for NBT list Tags
 *
 * @param <T> Type of list elements
 */
public class ListBuilder<T extends Tag> extends NBTBuilder<T> {

    private final LinkedList<T> tags = new LinkedList<>();
    private final Class<T> type;

    /**
     * Creates a new list tag builder.
     *
     * @param name Name of this list
     * @param type Type of list elements
     */
    ListBuilder(final String name, final Class<T> type) {
        super(name);
        this.type = type;
    }


    /**
     * Adds a new tag to this list tag. An existing tag with the same name is
     * replaced.
     *
     * @param tag Tag
     * @return Builder for chaining
     *
     * @throws NullPointerException if the given tag has a null value as its
     *         name
     */
    @Override
    public ListBuilder<T> add(final T tag) {
        this.tags.add(tag);
        return this;
    }

    /**
     * Adds new tags to this list tag. An existing tag with the same name as one
     * in the collection is replaced.
     *
     * @param tags Collection of tags
     * @return Builder for chaining
     *
     * @throws NullPointerException if one of the given tags has a null value as
     *         its name
     */
    @Override
    public ListBuilder<T> addAll(final Collection<? extends T> tags) {
        for (final T tag : tags) {
            this.add(tag);
        }
        return this;
    }

    /**
     * Builds the listTag and returns it.
     *
     * @return New listTag
     */
    @Override
    public ListTag build() {
        return NBT.List(this.getName(), this.type, this.tags);
    }

    /**
     * Returns the current tags as an immutable list.
     *
     * @return Tag set
     */
    @Override
    public ImmutableList<T> getTags() {
        return ImmutableList.copyOf(this.tags);
    }

    /**
     * Add a tag to the collection and return self.
     *
     * @param tag Tag to add
     * @return this
     */
    @Override
    protected ListBuilder<T> addTag(final Tag tag) {
        checkNotNull(tag.getName());
        if (this.type.isInstance(tag)) {
            this.tags.add(this.type.cast(tag));
        } else {
            throw new IllegalArgumentException(tag + "has not the type: " + this.type);
        }
        return this;
    }

    /////////////////////////////////////////////////////

    @Override
    public ListBuilder<T> ByteArray(final String name, final byte[] value) {
        return (ListBuilder<T>) super.ByteArray(name, value);
    }

    @Override
    public ListBuilder<T> Byte(final String name, final byte value) {
        return (ListBuilder<T>) super.Byte(name, value);
    }

    @Override
    public ListBuilder<T> Compound(final String name, final Map<String, Tag> value) {
        return (ListBuilder<T>) super.Compound(name, value);
    }

    @Override
    public ListBuilder<T> Double(final String name, final double value) {
        return (ListBuilder<T>) super.Double(name, value);
    }

    @Override
    public ListBuilder<T> End() {
        return (ListBuilder<T>) super.End();
    }


    @Override
    public ListBuilder<T> Float(final String name, final float value) {
        return (ListBuilder<T>) super.Float(name, value);
    }

    @Override
    public ListBuilder<T> IntArray(final String name, final int[] value) {
        return (ListBuilder<T>) super.IntArray(name, value);
    }

    @Override
    public ListBuilder<T> Int(final String name, final int value) {
        return (ListBuilder<T>) super.Int(name, value);
    }

    @Override
    public ListBuilder<T> List(final String name, final Class<? extends T> type, final java.util.List<T> value) {
        return (ListBuilder<T>) super.List(name, type, value);
    }


    @Override
    public ListBuilder<T> Long(final String name, final long value) {
        return (ListBuilder<T>) super.Long(name, value);
    }


    @Override
    public ListBuilder<T> Short(final String name, final short value) {
        return (ListBuilder<T>) super.Short(name, value);
    }

    @Override
    public ListBuilder<T> String(final String name, final @Nullable String value) {
        return (ListBuilder<T>) super.String(name, value);
    }

}
