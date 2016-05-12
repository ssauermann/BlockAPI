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
 * NBT StringTag
 */
@Immutable
@ValueType(String.class)
public abstract class StringTag implements Tag<String> {

    @Override
    public org.jnbt.StringTag unwrap() {
        return new org.jnbt.StringTag(this.getName(), this.getValue());
    }

    /**
     * Construct a new immutable {@code StringTag} instance.
     *
     * @param name The value for the {@code name} attribute
     * @param value The value for the {@code value} attribute
     * @return An immutable StringTag instance
     */
    public static StringTag of(final String name, final String value) {
        return ImmutableStringTag.of(name, value);
    }
}
