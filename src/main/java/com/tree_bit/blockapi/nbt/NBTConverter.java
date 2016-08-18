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
package com.tree_bit.blockapi.nbt;

import com.tree_bit.blockapi.nbt.tags.CompoundTag;
import com.tree_bit.blockapi.nbt.tags.FloatTag;
import com.tree_bit.blockapi.nbt.tags.StringTag;

import java.util.function.Function;

public final class NBTConverter {

    private NBTConverter() {}

    public static class OfString implements Function<String, StringTag> {

        @Override
        public StringTag apply(final String t) {
            return NBT.String("", t);
        }

    }

    public static class OfFloat implements Function<Float, FloatTag> {

        @Override
        public FloatTag apply(final Float t) {
            return NBT.Float("", t);
        }

    }

    public static class OfCompound implements Function<NBTCompoundData, CompoundTag> {

        @Override
        public CompoundTag apply(final NBTCompoundData t) {
            return t.compound();
        }

    }
}