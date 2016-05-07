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

import org.immutables.value.Value.Immutable;

/**
 * NBT IntArrayTag
 */
@TagStyle
@Immutable
public abstract class IntArrayTag implements Tag<int[]> {

    @Override
    public org.jnbt.IntArrayTag unwrap() {
        return new org.jnbt.IntArrayTag(this.getName(), this.getValue());
    }

    /**
     * Construct a new immutable {@code IntArrayTag} instance.
     * 
     * @param name The value for the {@code name} attribute
     * @param value The value for the {@code value} attribute
     * @return An immutable IntArrayTag instance
     */
    public static IntArrayTag of(final String name, final int[] value) {
        return ImmutableIntArrayTag.of(name, value);
    }
}
