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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tree_bit.blockapi.internal.BlockApiStyle;
import com.tree_bit.blockapi.internal.Null;

import org.eclipse.jdt.annotation.NonNull;
import org.immutables.value.Value;
import org.jnbt.IntArrayTag;
import org.jnbt.IntTag;

import java.util.List;
import java.util.Map;

/**
 * Represents an NBT tag
 *
 * @author Sascha Sauermann
 * @param <T> Content of this tag
 */
public interface Tag<@NonNull T> {

    /**
     * Gets the name of this tag.
     *
     * @return The name of this tag
     */
    @Value.Parameter(order = 1)
    public String getName();

    /**
     * Gets the value of this tag.
     *
     * @return The value of this tag
     */
    @Value.Parameter(order = 2)
    public @NonNull T getValue();

    /**
     * Unwraps this tag to the JNBT framework default tag.
     * <p>
     * <b>Do not use this method outside of the BlockAPI framework. It may
     * change its signature.</b>
     *
     *
     * @return unwrapped tag
     */
    public org.jnbt.Tag unwrap();

    /**
     * Wrap a JNBT tag to use.
     * <p>
     * <b>Do not use this method outside of the BlockAPI framework. It may
     * change its signature.</b>
     *
     * @param clazz Class of the type parameter this tag should be casted to.
     *
     * @param tag JNBT tag to wrap
     * @return wrapped tag
     * @throws ClassCastException if given class is incompatible with the given
     *         tag
     */
    // cast is checked at runtime via the return value of the getValue method
    @SuppressWarnings("unchecked")
    public static <@NonNull T, X extends org.jnbt.Tag> Tag<T> wrap(final Class<T> clazz, final X tag) {
        final Tag<Object> tt = wrap(tag);
        if (clazz.isInstance(tt.getValue())) {
            return (@NonNull Tag<T>) tt;
        }
        throw new ClassCastException("clazz is an incompatible type:" + clazz + " <-> " + tt.getValue().getClass());
    }

    /**
     * Wrap a JNBT tag to use.
     * <p>
     * <b>Do not use this method outside of the BlockAPI framework. It may
     * change its signature.</b>
     *
     * @param tag JNBT tag to wrap
     * @return wrapped tag
     */
    public static <X extends org.jnbt.Tag> Tag<Object> wrap(final X tag) {
        // TODO switch to existing classes
        return new Tag<Object>() {

            @Override
            public @NonNull String getName() {
                return tag.getName();
            }

            @Override
            public @NonNull Object getValue() {
                return tag.getValue();
            }

            @Override
            public org.jnbt.Tag unwrap() {
                return tag;
            }


        };
    }

    /**
     * ByteArray Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _ByteArrayTag implements Tag<byte[]> {
        // Will be generated

        @Override
        public org.jnbt.ByteArrayTag unwrap() {
            return new org.jnbt.ByteArrayTag(this.getName(), this.getValue());
        }
    }

    /**
     * Byte Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _ByteTag implements Tag<Byte> {
        // Will be generated

        @Override
        public org.jnbt.ByteTag unwrap() {
            return new org.jnbt.ByteTag(this.getName(), this.getValue());
        }
    }

    /**
     * Compound Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _CompoundTag implements Tag<Map<String, ? extends Tag<?>>> {
        // Will be generated

        @Override
        public org.jnbt.CompoundTag unwrap() {
            return new org.jnbt.CompoundTag(this.getName(), Maps.transformValues(this.getValue(), Tag::unwrap));
        }
    }

    /**
     * Double Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _DoubleTag implements Tag<Double> {
        // Will be generated

        @Override
        public org.jnbt.DoubleTag unwrap() {
            return new org.jnbt.DoubleTag(this.getName(), this.getValue());
        }
    }

    /**
     * End Tag
     */
    public static abstract class _EndTag implements Tag<Null> {
        // This one can't be automatic generated

        @Override
        public org.jnbt.EndTag unwrap() {
            return new org.jnbt.EndTag();
        }

    }

    /**
     * Float Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _FloatTag implements Tag<Float> {
        // Will be generated

        @Override
        public org.jnbt.FloatTag unwrap() {
            return new org.jnbt.FloatTag(this.getName(), this.getValue());
        }
    }

    /**
     * IntArray Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _IntArrayTag implements Tag<int[]> {
        // Will be generated

        @Override
        public IntArrayTag unwrap() {
            return new org.jnbt.IntArrayTag(this.getName(), this.getValue());
        }
    }

    /**
     * Int Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _IntTag implements Tag<Integer> {
        // Will be generated

        @Override
        public IntTag unwrap() {
            return new org.jnbt.IntTag(this.getName(), this.getValue());
        }
    }

    /**
     * List Tag
     *
     * @param <T> Content tag type
     */
    public static abstract class _ListTag<T extends Tag<?>> implements Tag<List<T>> {
        // This one can't be automatic generated

        private final Class<? extends org.jnbt.Tag> clazz;

        /**
         * Setting the class for unwrapping of this _ListTag
         *
         * @param clazz Content tag class
         */
        protected _ListTag(final Class<? extends T> clazz) {
            Class<? extends org.jnbt.Tag> cl;
            if (_ByteArrayTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.ByteArrayTag.class;
            }

            else if (_ByteTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.ByteTag.class;
            }

            else if (_CompoundTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.CompoundTag.class;
            }

            else if (_DoubleTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.DoubleTag.class;
            }

            else if (_EndTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.EndTag.class;
            }

            else if (_FloatTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.FloatTag.class;
            }

            else if (_IntArrayTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.IntArrayTag.class;
            }

            else if (_IntTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.IntTag.class;
            }

            else if (_ListTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.ListTag.class;
            }

            else if (_LongTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.LongTag.class;
            }

            else if (_ShortTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.ShortTag.class;
            }

            else if (_StringTag.class.isAssignableFrom(clazz)) {
                cl = org.jnbt.StringTag.class;
            }

            else {
                cl = org.jnbt.Tag.class;
            }
            this.clazz = cl;
        }

        @Override
        public org.jnbt.ListTag unwrap() {
            return new org.jnbt.ListTag(this.getName(), this.clazz, Lists.transform(this.getValue(), Tag::unwrap));
        }
    }

    /**
     * Long Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _LongTag implements Tag<Long> {
        // Will be generated

        @Override
        public org.jnbt.LongTag unwrap() {
            return new org.jnbt.LongTag(this.getName(), this.getValue());
        }
    }

    /**
     * Short Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _ShortTag implements Tag<Short> {
        // Will be generated

        @Override
        public org.jnbt.ShortTag unwrap() {
            return new org.jnbt.ShortTag(this.getName(), this.getValue());
        }
    }

    /**
     * String Tag
     */
    @Value.Immutable
    @Wrapped
    @BlockApiStyle
    public static abstract class _StringTag implements Tag<String> {
        // Will be generated

        @Override
        public org.jnbt.StringTag unwrap() {
            return new org.jnbt.StringTag(this.getName(), this.getValue());
        }
    }


}
