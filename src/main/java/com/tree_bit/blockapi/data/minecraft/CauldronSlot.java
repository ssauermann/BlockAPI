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
package com.tree_bit.blockapi.data.minecraft;

import com.tree_bit.blockapi.data.ISlot;

import java.util.Optional;

/**
 * Slots of a brewing stand.
 */
public enum CauldronSlot implements ISlot {
    /** Left potion slot */
    Left(0),
    /** Middle potion slot */
    Middle(1),
    /** Right potion slot */
    Right(2),
    /** Ingredient slot */
    Ingredient(3),
    /** Fuel slot */
    Fuel(4);

    private int slot;

    CauldronSlot(final int slot) {
        this.slot = slot;
    }

    @Override
    public int getDV() {
        return this.slot;
    }

    @Override
    public Optional<CauldronSlot> byDV(final int dv) {
        for (final CauldronSlot e : values()) {
            if (e.getDV() == this.slot) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }
}
