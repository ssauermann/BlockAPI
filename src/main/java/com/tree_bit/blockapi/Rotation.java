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

import static com.google.common.base.Preconditions.checkArgument;

import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.tags.DoubleTag;
import com.tree_bit.blockapi.nbt.tags.ListTag;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

/**
 * Rotation pair of floats
 */
@Immutable(builder = false, copy = false)
public abstract class Rotation {

    /**
     * Rotation clockwise around y axis. West is 0.
     *
     * <p>
     * <i>Range:</i> [0, 360]
     *
     * @return yaw
     */
    @Parameter(order = 1)
    public abstract float yaw();

    /**
     * Declination from the horizon. Positive values look downward.
     *
     * <p>
     * <i>Range:</i> [-90, 90]
     *
     * @return yaw
     */
    @Parameter(order = 2)
    public abstract float pitch();

    /**
     * Returns the rotation as a ListTag of DoubleTags.
     *
     * @param name list name
     * @return ListTag
     */
    public final ListTag<DoubleTag> asListTag(final String name) {
        return NBT.List(name, DoubleTag.class).Double("yaw", this.yaw()).Double("pitch", this.pitch()).build();
    }

    /**
     * Construct a new immutable {@code Rotation} instance.
     *
     * @param yaw The value for the {@code yaw} attribute. <i>Range:</i> [0,
     *        360]
     * @param pitch The value for the {@code pitch} attribute. <i>Range:</i>
     *        [-90, 90]
     * @return An immutable Rotation instance
     * @throws IllegalArgumentException if yaw or pitch have illegal values.
     */
    public static Rotation of(final float yaw, final float pitch) {
        checkArgument((yaw >= 0) && (yaw <= 360));
        checkArgument((pitch >= -90) && (pitch <= 90));
        return ImmutableRotation.of(yaw, pitch);
    }

}
