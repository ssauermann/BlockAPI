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
package com.tree_bit.blockapi;

import com.tree_bit.blockapi.nbt.NBTList;
import com.tree_bit.blockapi.nbt.NBTListData;
import com.tree_bit.blockapi.nbt.tags.DoubleTag;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

/**
 * Coordinate triplet of doubles
 */
@Immutable(builder = false, copy = false)
public abstract class Coordinates implements NBTListData<DoubleTag> {

    @Override
    public Class<DoubleTag> tagClass() {
        return DoubleTag.class;
    }

    /**
     * x-Coordinate
     *
     * @return x
     */
    @Parameter(order = 1)
    @NBTList(key = "X", order = 0)
    public abstract double x();

    /**
     * y-Coordinate
     *
     * @return y
     */
    @Parameter(order = 2)
    @NBTList(key = "Y", order = 1)
    public abstract double y();

    /**
     * z-Coordinate
     *
     * @return z
     */
    @Parameter(order = 3)
    @NBTList(key = "Z", order = 2)
    public abstract double z();

    /**
     * Construct a new immutable {@code Coordinates} instance.
     *
     * @param x The value for the {@code x} attribute
     * @param y The value for the {@code y} attribute
     * @param z The value for the {@code z} attribute
     * @return An immutable Coordinates instance
     */
    public static Coordinates of(final double x, final double y, final double z) {
        return ImmutableCoordinates.of(x, y, z);
    }

}
