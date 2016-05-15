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

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.tree_bit.blockapi.nbt.tags.ListTag;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.Function;


/**
 * Annotates a method to add it's value to the generated {@link ListTag} via the
 * {@link NBTListData#list(String)} method when not overwritten in a subclass
 * containing this annotation.
 *
 * <p>
 * <i>The class using this annotation must implement {@link NBTListData}.</i>
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface NBTList {

    /**
     * Key of the generated tag.
     *
     * @return Key
     */
    String key();

    /**
     * Order of the generated tag in the wrapping ListTag.
     *
     * @return Order
     */
    int order() default 0;

    /**
     * Converter functions which get applied successively to the value of this
     * method. (May be empty)
     *
     * @return Converter functions
     */
    Class<? extends Function<?, ?>>[] converter() default {};

    /**
     * Should this value get calculated recursively by annotation processing?
     *
     * @return Recursive value
     */
    Recursive recursive() default Recursive.NONE;

    /**
     * Transform this value before applying the converter functions.
     * 
     * @return transformation
     */
    Transformation transformation() default Transformation.NONE;

    /**
     * If one version range matches the version set in the settings, a tag will
     * be added. Else this value will be ignored for the NBTList creation.
     *
     * @return Versions
     */
    NBTVersion[] version() default {};

    /**
     * Recursive types for annotation processing.
     */
    public enum Recursive {

        /**
         * If set the value will be calculated normally.
         */
        NONE,
        /**
         * If set, this value can be transformed to a ListTag via annotations
         * processing of it's class. So no tag type has to be set.
         */
        isNBTList,
        /**
         * If set, this value can be transformed to a CompoundTag via
         * annotations processing of it's class. So no tag type has to be set.
         */
        isNBTCompound;
    }
}

