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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableCollection;

import org.eclipse.jdt.annotation.Nullable;
import org.jnbt.Tag;

import java.util.Collection;
import java.util.Map;

/**
 * Superclass of NBT tag builders.
 *
 * @param <T> Type of subtags
 */
public abstract class NBTBuilder<T extends Tag> {

    private final String name;

    /**
     * Creates a NBT builder
     *
     * @param name Name of this tag
     */
    public NBTBuilder(final String name) {
        this.name = name;
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
    public abstract NBTBuilder<T> add(final T tag);

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
    public abstract NBTBuilder<T> addAll(final Collection<T> tags);

    /**
     * Gets the name of this nbt tag.
     *
     * @return Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the current tags as an immutable collection.
     *
     * @return Tag set
     */
    public abstract ImmutableCollection<T> getTags();


    /**
     * Builds the nbt tag and returns it.
     *
     * @return New nbt tag
     */
    public abstract Tag build();

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getTags());
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof NBTBuilder)) {
            return false;
        }
        final NBTBuilder<?> other = (NBTBuilder<?>) obj;
        return Objects.equal(this.getTags(), other.getTags());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(this.getTags()).toString();
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


    /**
     * Creates a byte array tag.
     *
     * @param name The name
     * @param value The value array
     *
     * @return Builder
     */
    public NBTBuilder<T> ByteArray(final String name, final byte[] value) {
        return this.addTag(NBT.ByteArray(name, value));
    }

    /**
     * Creates a byte tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return Builder
     */
    public NBTBuilder<T> Byte(final String name, final byte value) {
        return this.addTag(NBT.Byte(name, value));
    }

    /**
     * Creates a compound tag.
     *
     * @param name The name
     * @param value The map with subtags
     *
     * @return Builder
     */
    public NBTBuilder<T> Compound(final String name, final Map<String, Tag> value) {
        return this.addTag(NBT.Compound(name, value));
    }

    /**
     * Creates a double tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return Builder
     */
    public NBTBuilder<T> Double(final String name, final double value) {
        return this.addTag(NBT.Double(name, value));
    }

    /**
     * Creates an end tag.
     *
     * @return Builder
     */
    public NBTBuilder<T> End() {
        return this.addTag(NBT.End());
    }

    /**
     * Creates a float tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return Builder
     */
    public NBTBuilder<T> Float(final String name, final float value) {
        return this.addTag(NBT.Float(name, value));
    }

    /**
     * Creates an int array tag.
     *
     * @param name The name
     * @param value The value array
     *
     * @return Builder
     */
    public NBTBuilder<T> IntArray(final String name, final int[] value) {
        return this.addTag(NBT.IntArray(name, value));
    }

    /**
     * Creates an int tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return Builder
     */
    public NBTBuilder<T> Int(final String name, final int value) {
        return this.addTag(NBT.Int(name, value));
    }

    /**
     * Creates a list tag.
     *
     * @param name The name
     * @param type The type of item in the list
     * @param value The list
     *
     * @return Builder
     */
    public NBTBuilder<T> List(final String name, final Class<T> type, final java.util.List<T> value) {
        return this.addTag(NBT.List(name, type, value));
    }

    /**
     * Creates a long tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return Builder
     */
    public NBTBuilder<T> Long(final String name, final long value) {
        return this.addTag(NBT.Long(name, value));
    }

    /**
     * Creates a short tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return Builder
     */
    public NBTBuilder<T> Short(final String name, final short value) {
        return this.addTag(NBT.Short(name, value));
    }

    /**
     * Creates a string tag.
     *
     * @param name The name
     * @param value The value
     *
     * @return Builder
     */
    public NBTBuilder<T> String(final String name, final String value) {
        return this.addTag(NBT.String(name, value));
    }

    /**
     * Add a tag to the collection and return self.
     *
     * @param tag Tag to add
     * @return this
     *
     * @throws NullPointerException if tag name is null
     * @throws IllegalArgumentException if Tag type doens't match
     */
    protected abstract NBTBuilder<T> addTag(final Tag tag);



}
