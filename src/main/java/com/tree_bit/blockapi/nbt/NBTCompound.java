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

import com.tree_bit.blockapi.nbt.tags.ByteArrayTag;
import com.tree_bit.blockapi.nbt.tags.ByteTag;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;
import com.tree_bit.blockapi.nbt.tags.DoubleTag;
import com.tree_bit.blockapi.nbt.tags.EndTag;
import com.tree_bit.blockapi.nbt.tags.FloatTag;
import com.tree_bit.blockapi.nbt.tags.IntArrayTag;
import com.tree_bit.blockapi.nbt.tags.IntTag;
import com.tree_bit.blockapi.nbt.tags.ListTag;
import com.tree_bit.blockapi.nbt.tags.LongTag;
import com.tree_bit.blockapi.nbt.tags.ShortTag;
import com.tree_bit.blockapi.nbt.tags.StringTag;
import com.tree_bit.blockapi.nbt.tags.Tag;

import java.lang.annotation.Documented;
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
public @interface NBTCompound {

    /**
     * Key of the generated tag.
     *
     * @return Key
     */
    String key();

    /**
     * Converter functions which get applied successively to the value of this
     * method. (May be empty)
     *
     * @return Converter functions
     */
    Class<? extends Function<?, ?>>[] converter() default {};

    /**
     * Type of the wrapping tag for this value. Instead of a wrapping type a
     * recursive calculation method can be set.
     *
     * @return Tag type or recursive method
     */
    Tags tag();

    /**
     * Transform this value before applying the converter functions.
     *
     * @return transformation
     */
    Transformation transformation() default Transformation.NONE;

    /**
     * If one version range matches the version set in the settings, a tag will
     * be added. Else this value will be ignored for the NBTCompound creation.
     *
     * @return Versions
     */
    NBTVersion[] version() default {};

    /**
     * Tag type for annotation processing.
     */
    public enum Tags {

        /** ByteArrayTag */
        ByteArray(ByteArrayTag.class),
        /** ByteTag */
        Byte(ByteTag.class),
        /** CompoundTag */
        Compound(CompoundTag.class),
        /** DoubleTag */
        Double(DoubleTag.class),
        /** EndTag */
        End(EndTag.class),
        /** FloatTag */
        Float(FloatTag.class),
        /** IntArrayTag */
        IntArray(IntArrayTag.class),
        /** IntTag. */
        Int(IntTag.class),
        /** ListTag */
        List(ListTag.class),
        /** LongTag */
        Long(LongTag.class),
        /** ShortTag */
        Short(ShortTag.class),
        /** StringTag */
        String(StringTag.class),
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

        private Class<? extends Tag<?>> clazz;

        @SuppressWarnings("unchecked")
        private Tags(@SuppressWarnings("rawtypes") final Class<? extends Tag> clazz) {
            this.clazz = (Class<? extends Tag<?>>) clazz;
        }

        private Tags() {
            this(Tag.class);
        }

        /**
         * Get's the tag class.
         *
         * @return Tag class
         */
        public Class<? extends Tag<?>> getTagClass() {
            return this.clazz;
        }
    }

}
