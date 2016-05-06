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

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.tree_bit.blockapi.nbt.tags.Tag._ListTag;

import org.eclipse.jdt.annotation.Nullable;

import java.util.List;

/**
 * Immutable implementation of {@link Tag._ListTag}.
 * <p>
 * Use the static factory method to create immutable instances:
 * {@code ListTag.of()}.
 *
 * @param <T> Type of list content
 */
public final class ListTag<T extends Tag<?, ? extends org.jnbt.Tag>> extends _ListTag<T> {

    private final String name;
    private final List<T> value;

    private ListTag(final String name, final List<T> value, final Class<T> clazz) {
        super(clazz);
        this.name = Preconditions.checkNotNull(name, "name");
        this.value = ImmutableList.copyOf(value);
    }

    /**
     * @return The value of the {@code name} attribute
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return The value of the {@code value} attribute
     */
    @Override
    public List<T> getValue() {
        return this.value;
    }

    /**
     * This instance is equal to all instances of {@code ListTag} that have
     * equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(@Nullable final Object another) {
        if (this == another) {
            return true;
        }
        return (another instanceof ListTag) && this.equalTo((ListTag<?>) another);
    }

    private boolean equalTo(final ListTag<?> another) {
        return this.name.equals(another.name) && this.value.equals(another.value);
    }

    /**
     * Computes a hash code from attributes: {@code name}, {@code value}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        int h = 31;
        h = (h * 17) + this.name.hashCode();
        h = (h * 17) + this.value.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@code ListTag} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper("ListTag").omitNullValues().add("name", this.name).add("value", this.value).toString();
    }

    /**
     * Construct a new immutable {@code ListTag} instance.
     *
     * @param name The value for the {@code name} attribute
     * @param clazz Class of list content
     * @param value The value for the {@code value} attribute
     * @return An immutable ListTag instance
     */
    public static <T extends Tag<Object, org.jnbt.Tag>> ListTag<T> of(final String name, final Class<T> clazz, final List<T> value) {
        return new ListTag<>(name, value, clazz);
    }

}
