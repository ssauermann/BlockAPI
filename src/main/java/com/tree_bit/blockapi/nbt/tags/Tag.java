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

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tree_bit.blockapi.internal.BlockApiStyle;
import com.tree_bit.blockapi.internal.Null;
import com.tree_bit.blockapi.nbt.NBT;

import org.eclipse.jdt.annotation.NonNull;
import org.immutables.value.Value;

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
    public static <@NonNull T, X extends Tag<T>> X wrap(final Class<X> clazz, final org.jnbt.Tag tag) {
        final Tag<?> tt = wrap(tag);
        if (clazz.isInstance(tt)) {
            return clazz.cast(tt);
        }
        throw new ClassCastException("class is an incompatible type:" + clazz + " <-> " + tt.getValue().getClass());
    }

    /**
     * Wrap a JNBT tag to use.
     * <p>
     * <b>Do not use this method outside of the BlockAPI framework. It may
     * change its signature.</b>
     *
     * <p>
     * <code>ListTag&lt;? extends Tag&gt;</code> will be returned if parameter
     * is a JNBT <code>ListTag</code>
     *
     * @param tag JNBT tag to wrap
     * @return wrapped tag
     */
    public static Tag<?> wrap(final org.jnbt.Tag tag) {

        if (org.jnbt.ByteArrayTag.class.isInstance(tag)) {
            return ByteArrayTag.of(tag.getName(), (byte[]) tag.getValue());
        }

        else if (org.jnbt.ByteTag.class.isInstance(tag)) {
            return ByteTag.of(tag.getName(), (byte) tag.getValue());
        }

        else if (org.jnbt.CompoundTag.class.isInstance(tag)) {
            final org.jnbt.CompoundTag tt = (org.jnbt.CompoundTag) tag;
            return CompoundTag.of(tag.getName(), checkNotNull(Maps.transformValues(tt.getValue(), x -> Tag.wrap(x))));
        }

        else if (org.jnbt.DoubleTag.class.isInstance(tag)) {
            return DoubleTag.of(tag.getName(), (double) tag.getValue());
        }

        else if (org.jnbt.EndTag.class.isInstance(tag)) {
            return EndTag.of();
        }

        else if (org.jnbt.FloatTag.class.isInstance(tag)) {
            return FloatTag.of(tag.getName(), (float) tag.getValue());
        }

        else if (org.jnbt.IntArrayTag.class.isInstance(tag)) {
            return IntArrayTag.of(tag.getName(), (int[]) tag.getValue());
        }

        else if (org.jnbt.IntTag.class.isInstance(tag)) {
            return (IntTag) (Tag<?>) IntTag.of(tag.getName(), (int) tag.getValue());
        }

        else if (org.jnbt.ListTag.class.isInstance(tag)) {
            final org.jnbt.ListTag tt = ((org.jnbt.ListTag) tag);
            final List<Tag<?>> ll = Lists.transform(tt.getValue(), x -> Tag.wrap(x));
            return NBT.List(tag.getName(), Tag.class).addAll(checkNotNull(ll)).build();
        }

        else if (org.jnbt.LongTag.class.isInstance(tag)) {
            return LongTag.of(tag.getName(), (long) tag.getValue());
        }

        else if (org.jnbt.ShortTag.class.isInstance(tag)) {
            return ShortTag.of(tag.getName(), (short) tag.getValue());
        }

        else if (org.jnbt.StringTag.class.isInstance(tag)) {
            return StringTag.of(tag.getName(), (String) tag.getValue());
        }

        throw new IllegalArgumentException("Invalid tag instance");

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
        public org.jnbt.IntArrayTag unwrap() {
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
        public org.jnbt.IntTag unwrap() {
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
