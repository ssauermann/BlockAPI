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
import com.tree_bit.blockapi.nbt.tags.ListTag;
import com.tree_bit.blockapi.nbt.tags.Tag;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Builder for NBT <code>ListTag</code>
 *
 * @param <T> Type of list elements
 */
public class ListBuilder<T extends Tag<?>> extends NBTBuilder<T> {

    private final LinkedList<T> tags = new LinkedList<>();
    private final Class<T> type;

    /**
     * Creates a new <code>ListTag</code> builder.
     *
     * @param name Name of this list
     * @param type Type of list elements
     */
    ListBuilder(final String name, final Class<T> type) {
        super(name);
        this.type = type;
    }


    /**
     * Adds a new tag to this <code>ListTag</code>. An existing tag with the
     * same name is replaced.
     *
     * @param tag Tag
     * @return Builder for chaining
     *
     * @throws NullPointerException if the given tag has a null value as its
     *         name
     * @throws IllegalArgumentException if Tag type doens't match
     */
    @Override
    public ListBuilder<T> add(final T tag) {
        return (ListBuilder<T>) super.add(tag);
    }

    /**
     * Adds a new tag to this <code>ListTag</code> if it is present. An existing
     * tag with the same name is replaced.
     *
     * @param tag Tag
     * @return Builder for chaining
     *
     * @throws NullPointerException if the given tag has a null value as its
     *         name
     * @throws IllegalArgumentException if Tag type doens't match
     */
    @Override
    public ListBuilder<T> add(final Optional<? extends T> tag) {
        return (ListBuilder<T>) super.add(tag);
    }

    /**
     * Adds a new tag to this list tag if the value is present. An existing tag
     * with the same name is replaced. The added tag will be computed by
     * applying the given function to the inner value of the given optional.
     *
     * @param value optional witch may contain the value
     * @param function function to apply if value is present to transform this
     *        value into a Tag
     * @return Builder for chaining
     *
     * @throws NullPointerException if one of the given tags has a null value as
     *         its name
     */
    @Override
    public <Y> ListBuilder<T> add(final Optional<Y> value, final Function<? super Y, T> function) {
        return (ListBuilder<T>) super.add(value, function);
    }

    /**
     * Adds new tags to this <code>ListTag</code>. An existing tag with the same
     * name as one in the collection is replaced.
     *
     * @param tags Collection of tags
     * @return Builder for chaining
     *
     * @throws NullPointerException if one of the given tags has a null value as
     *         its name
     */
    @Override
    public ListBuilder<T> addAll(final Collection<? extends T> tags) {
        return (ListBuilder<T>) super.addAll(tags);
    }

    /**
     * Builds the <code>ListTag</code> and returns it.
     *
     * @return New <code>ListTag</code>
     */
    @Override
    public ListTag<T> build() {
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
    protected ListBuilder<T> addTag(final Tag<?> tag) {
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
    public ListBuilder<T> ByteArray(final String name, final Optional<byte[]> value) {
        return (ListBuilder<T>) super.ByteArray(name, value);
    }

    @Override
    public ListBuilder<T> Byte(final String name, final byte value) {
        return (ListBuilder<T>) super.Byte(name, value);
    }

    @Override
    public ListBuilder<T> Byte(final String name, final Optional<Byte> value) {
        return (ListBuilder<T>) super.Byte(name, value);
    }

    @Override
    public ListBuilder<T> Compound(final String name, final Map<String, Tag<?>> value) {
        return (ListBuilder<T>) super.Compound(name, value);
    }

    @Override
    public ListBuilder<T> Compound(final String name, final Optional<Map<String, Tag<?>>> value) {
        return (ListBuilder<T>) super.Compound(name, value);
    }

    @Override
    public ListBuilder<T> Double(final String name, final double value) {
        return (ListBuilder<T>) super.Double(name, value);
    }

    @Override
    public ListBuilder<T> Double(final String name, final Optional<Double> value) {
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
    public ListBuilder<T> Float(final String name, final Optional<Float> value) {
        return (ListBuilder<T>) super.Float(name, value);
    }

    @Override
    public ListBuilder<T> IntArray(final String name, final int[] value) {
        return (ListBuilder<T>) super.IntArray(name, value);
    }

    @Override
    public ListBuilder<T> IntArray(final String name, final Optional<int[]> value) {
        return (ListBuilder<T>) super.IntArray(name, value);
    }

    @Override
    public ListBuilder<T> Int(final String name, final int value) {
        return (ListBuilder<T>) super.Int(name, value);
    }

    @Override
    public ListBuilder<T> Int(final String name, final Optional<Integer> value) {
        return (ListBuilder<T>) super.Int(name, value);
    }

    @Override
    public <X extends Tag<?>> ListBuilder<T> List(final String name, final Class<X> type, final java.util.List<? extends X> value) {
        return (ListBuilder<T>) super.List(name, type, value);
    }

    @Override
    public <X extends Tag<?>> ListBuilder<T> List(final String name, final Class<X> type, final Optional<java.util.List<? extends X>> value) {
        return (ListBuilder<T>) super.List(name, type, value);
    }

    @Override
    public <Y, X extends Tag<?>> ListBuilder<T> List(final String name, final Class<X> type, final java.util.List<Y> value,
            final Function<? super Y, ? extends X> function) {
        return (ListBuilder<T>) super.List(name, type, value, function);
    }

    @Override
    public ListBuilder<T> Long(final String name, final long value) {
        return (ListBuilder<T>) super.Long(name, value);
    }

    @Override
    public ListBuilder<T> Long(final String name, final Optional<Long> value) {
        return (ListBuilder<T>) super.Long(name, value);
    }

    @Override
    public ListBuilder<T> Short(final String name, final short value) {
        return (ListBuilder<T>) super.Short(name, value);
    }

    @Override
    public ListBuilder<T> Short(final String name, final Optional<Short> value) {
        return (ListBuilder<T>) super.Short(name, value);
    }

    @Override
    public ListBuilder<T> String(final String name, final String value) {
        return (ListBuilder<T>) super.String(name, value);
    }

    @Override
    public ListBuilder<T> String(final String name, final Optional<String> value) {
        return (ListBuilder<T>) super.String(name, value);
    }

}
