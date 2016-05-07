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

import com.tree_bit.blockapi.internal.Null;

import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

/**
 * NBT EndTag
 */
@Immutable(singleton = true)
public abstract class EndTag implements Tag<Null> {

    @Override
    @Derived
    public String getName() {
        return "";
    }

    @Override
    @Derived
    public Null getValue() {
        return Null._null();
    }

    @Override
    public org.jnbt.Tag unwrap() {
        return new org.jnbt.EndTag();
    }

    /**
     * Returns the default immutable singleton value of {@code EndTag}
     * 
     * @return An immutable instance of EndTag
     */
    public static EndTag of() {
        return ImmutableEndTag.of();
    }

}
