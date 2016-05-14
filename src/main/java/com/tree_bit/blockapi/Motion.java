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
 * Motion triplet of doubles
 */
@Immutable(builder = false, copy = false)
public abstract class Motion implements NBTListData<DoubleTag> {

    @Override
    public Class<DoubleTag> tagClass() {
        return DoubleTag.class;
    }

    /**
     * dX velocity in meters per tick
     *
     * @return dX
     */
    @Parameter(order = 1)
    @NBTList(key = "dX", order = 0)
    public abstract double dX();

    /**
     * dY velocity in meters per tick
     *
     * @return dY
     */
    @Parameter(order = 2)
    @NBTList(key = "dY", order = 1)
    public abstract double dY();

    /**
     * dZ velocity in meters per tick
     *
     * @return dZ
     */
    @Parameter(order = 3)
    @NBTList(key = "dZ", order = 2)
    public abstract double dZ();

    /**
     * Construct a new immutable {@code Motion} instance.
     *
     * @param dX The value for the {@code dX} attribute
     * @param dY The value for the {@code dY} attribute
     * @param dZ The value for the {@code dZ} attribute
     * @return An immutable Motion instance
     */
    public static Motion of(final double dX, final double dY, final double dZ) {
        return ImmutableMotion.of(dX, dY, dZ);
    }

}
