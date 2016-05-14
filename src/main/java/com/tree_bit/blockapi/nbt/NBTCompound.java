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

import com.tree_bit.blockapi.nbt.tags.CompoundTag;
import com.tree_bit.blockapi.nbt.tags.Tag;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.Function;

/**
 * Annotates a method to add it's value to the generated {@link CompoundTag} via
 * the {@link NBTCompoundData#compound(String)} method when not overwritten in a
 * subclass containing this annotation.
 *
 * <p>
 * <i>The class using this annotation must implement
 * {@link NBTCompoundData}.</i>
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
@Inherited
public @interface NBTCompound {

    /**
     * Key of the generated tag.
     *
     * @return Key
     */
    String key();

    /**
     * Type of the wrapping tag for this value.
     * <p>
     * <b>This or "isNBT___" has to be set.</b>
     *
     * <p>
     * <i>The interface "Tag" is no valid substitution for this parameter and
     * will result in an exception when processed.</i>
     *
     * @return Tag class
     */
    @SuppressWarnings("rawtypes")
    Class<? extends Tag> tag() default Tag.class;

    /**
     * Converter functions which get applied successively to the value of this
     * method. (May be empty)
     *
     * @return Converter functions
     */
    Class<? extends Function<?, ?>>[] converter() default {};

    /**
     * If set to true, this value can be transformed to a ListTag via
     * annotations processing of it's class. So no tag type has to be set. (It
     * will be ignored)
     * <p>
     * <b>If "isNBTCompound" is also set to true, the result of the annotation
     * processing is undefined.</b>
     *
     * @return Can this value be transformed to a ListTag
     */
    boolean isNBTList() default false;

    /**
     * If set to true, this value can be transformed to a CompoundTag via
     * annotations processing of it's class. So no tag type has to be set. (It
     * will be ignored)
     * <p>
     * <b>If "isNBTList" is also set to true , the result of the annotation
     * processing is undefined.</b>
     *
     * @return Can this value be transformed to a CompoundTag
     */
    boolean isNBTCompound() default false;
}
