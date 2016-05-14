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

/**
 * A class which represents a NBT compound tag and can be represented as one.
 */
public interface NBTCompoundData extends NBTData {

    /**
     * Get's the compound tag representing this object with an empty string as
     *
     * <p>
     * <i>The default implementation uses annotation processing for
     * {@link NBTCompound} annotations to create the {@link CompoundTag}.</i>
     * name.
     *
     * @return Compound tag
     */
    default CompoundTag compound() {
        return compound("");
    }

    /**
     * Get's the compound tag representing this object.
     *
     * <p>
     * <i>The default implementation uses annotation processing for
     * {@link NBTCompound} annotations to create the {@link CompoundTag}.</i>
     *
     * @param name Tag name
     *
     * @return Compound tag
     */
    default CompoundTag compound(final String name) {
        return AnnotationProcessor.getCompound(name, this);
    }
}
