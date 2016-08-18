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
package com.tree_bit.blockapi.entities.projectile;

import com.tree_bit.blockapi.entities.Entity;
import com.tree_bit.blockapi.id.minecraft.BlockID;
import com.tree_bit.blockapi.nbt.NBTCompound;
import com.tree_bit.blockapi.nbt.NBTCompound.Tags;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Parameter;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Representing a projectile entity.
 */
public interface Projectile extends Entity {

    /**
     * X coordinate of the projectile's position in the chunk.
     *
     * @return x coordinate
     */
    @Parameter
    @NBTCompound(key = "xTile", tag = Tags.Short)
    short xTile();

    /**
     * Y coordinate of the projectile's position in the chunk.
     *
     * @return y coordinate
     */
    @Parameter
    @NBTCompound(key = "yTile", tag = Tags.Short)
    short yTile();

    /**
     * Z coordinate of the projectile's position in the chunk.
     *
     * @return z coordinate
     */
    @Parameter
    @NBTCompound(key = "zTile", tag = Tags.Short)
    short zTile();

    /**
     * ID of tile projectile is in.
     *
     * @return alphabetical id
     */
    @Default
    @NBTCompound(key = "inTile", tag = Tags.String)
    default String inTile() {
        return BlockID.AIR.getAlphabeticalID();
    }

    /**
     * Builds instances of type {@link Projectile Projectile}. Initialize
     * attributes and then invoke the {@link #build()} method to create an
     * immutable instance.
     * <p>
     * <em>{@code Builder} is not thread-safe and generally should not be stored
     * in a field or collection, but instead used immediately to create
     * instances.</em>
     *
     * @param <T> Recursive type for builder extension
     */
    @NotThreadSafe
    public interface Builder<T extends Builder<T>> extends Entity.Builder<Builder<T>> {

        /**
         * Fill a builder with attribute values from the provided
         * {@code Projectile} instance. Regular attribute values will be
         * replaced with those from the given instance. Absent optional values
         * will not replace present values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        Builder<T> from(Projectile instance);

        /**
         * Initializes the value for the {@link Projectile#xTile() xTile}
         * attribute.
         *
         * @param xTile The value for xTile
         * @return {@code this} builder for use in a chained invocation
         */
        Builder<T> xTile(short xTile);

        /**
         * Initializes the value for the {@link Projectile#yTile() yTile}
         * attribute.
         *
         * @param yTile The value for yTile
         * @return {@code this} builder for use in a chained invocation
         */
        Builder<T> yTile(short yTile);

        /**
         * Initializes the value for the {@link Projectile#zTile() zTile}
         * attribute.
         *
         * @param zTile The value for zTile
         * @return {@code this} builder for use in a chained invocation
         */
        Builder<T> zTile(short zTile);

        /**
         * Initializes the value for the {@link Projectile#inTile() inTile}
         * attribute.
         *
         * @param inTile The value for inTile
         * @return {@code this} builder for use in a chained invocation
         */
        Builder<T> inTile(String inTile);

        /**
         * Builds a new {@link Projectile Projectile}.
         *
         * @return An immutable instance of Projectile
         * @throws java.lang.IllegalStateException if any required attributes
         *         are missing
         */
        @Override
        Projectile build();

    }

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Projectile#xTile() xTile} attribute. A value equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param xTile A new value for xTile
     * @return A modified copy of the {@code this} object
     */
    Projectile withXTile(short xTile);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Projectile#yTile() yTile} attribute. A value equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param yTile A new value for yTile
     * @return A modified copy of the {@code this} object
     */
    Projectile withYTile(short yTile);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Projectile#zTile() zTile} attribute. A value equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param zTile A new value for zTile
     * @return A modified copy of the {@code this} object
     */
    Projectile withZTile(short zTile);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Projectile#inTile() inTile} attribute. An equals check used to
     * prevent copying of the same value by returning {@code this}.
     *
     * @param inTile A new value for inTile
     * @return A modified copy of the {@code this} object
     */
    Projectile withInTile(String inTile);

}

