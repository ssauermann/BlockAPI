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
import com.tree_bit.blockapi.nbt.tags.Tag._EndTag;

import org.eclipse.jdt.annotation.Nullable;

/**
 * Immutable implementation of {@link Tag._EndTag}.
 * <p>
 * Use the static factory method to create immutable instances:
 * {@code EndTag.of()}.
 *
 */
public final class EndTag extends _EndTag {

    private EndTag() {}

    /**
     * @return The value of the {@code name} attribute
     */
    @Override
    public String getName() {
        return "";
    }

    /**
     * @return The value of the {@code value} attribute
     */
    @Override
    public Null getValue() {
        return Null._null();
    }

    /**
     * This instance is equal to all instances of {@code EndTag} that have equal
     * attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code another} instance
     */
    @Override
    public boolean equals(@Nullable final Object another) {
        if (this == another) {
            return true;
        }
        return (another instanceof EndTag);
    }


    /**
     * Computes a hash code from attributes: {@code name}, {@code value}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        return 313;
    }

    /**
     * Prints the immutable value {@code EndTag} with attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "EndTag";
    }

    /**
     * Construct a new immutable {@code EndTag} instance.
     *
     * @return An immutable EndTag instance
     */
    public static EndTag of() {
        return new EndTag();
    }

}
