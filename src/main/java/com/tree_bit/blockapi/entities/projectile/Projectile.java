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

import com.tree_bit.blockapi.Coordinates;
import com.tree_bit.blockapi.entities.Entity;
import com.tree_bit.blockapi.nbt.NBTCompound;
import com.tree_bit.blockapi.nbt.NBTCompound.Tags;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

/**
 * Representing a projectile entity.
 */
// TODO Remove
@Immutable
public interface Projectile extends Entity {

    @Override
    @Parameter(order = 0)
    @NBTCompound(key = "pos", tag = Tags.isNBTList)
    Coordinates pos();

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
    @Parameter
    @NBTCompound(key = "inTile", tag = Tags.String)
    String inTile();

    // TODO Remove
    @Override
    default String id() {
        return "Name";
    }


    // TODO Remove
    /**
     * Construct a new immutable {@code Projectile} instance.
     *
     * @param pos The value for the {@code pos} attribute
     * @param xTile The value for the {@code xTile} attribute
     * @param yTile The value for the {@code yTile} attribute
     * @param zTile The value for the {@code zTile} attribute
     * @param inTile The value for the {@code inTile} attribute
     * @return An immutable Projectile instance
     */
    public static ImmutableProjectile of(final Coordinates pos, final short xTile, final short yTile, final short zTile, final String inTile) {
        return ImmutableProjectile.of(pos, xTile, yTile, zTile, inTile);
    }


    public interface Builder<T extends Builder<T>> {

    }
}
