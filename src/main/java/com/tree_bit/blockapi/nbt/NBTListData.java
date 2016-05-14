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

import com.tree_bit.blockapi.nbt.tags.ListTag;
import com.tree_bit.blockapi.nbt.tags.Tag;

/**
 * A class which represents a NBT list tag and can be represented as one.
 *
 * @param <T> Type of list tag
 */
public interface NBTListData<T extends Tag<?>> extends NBTData {

    /**
     * Get's the inner class type.
     *
     * @return Tag class
     */
    Class<T> tagClass();

    /**
     * Get's the list tag representing this object.
     *
     * <p>
     * <i>The default implementation uses annotation processing for
     * {@link NBTList} annotations to create the {@link ListTag}.</i>
     *
     * @param name Name of the list tag.
     *
     * @return List tag
     */
    default ListTag<T> list(final String name) {
        return AnnotationProcessor.getList(name, this);
    }

    /**
     * Get's the list tag representing this object with an empty String as name.
     *
     * <p>
     * <i>The default implementation uses annotation processing for
     * {@link NBTList} annotations to create the {@link ListTag}.</i>
     *
     * @return List tag
     */
    default ListTag<T> list() {
        return list("");
    }


}
