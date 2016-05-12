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
import com.tree_bit.blockapi.nbt.NBT;

import org.immutables.value.Value.Parameter;

import java.util.List;

/**
 * Represents an NBT tag
 *
 * @author Sascha Sauermann
 * @param <T> Content of this tag
 */
@ValueType(Object.class)
public interface Tag<T> {

    /**
     * Gets the name of this tag.
     *
     * @return The name of this tag
     */
    @Parameter(order = 1)
    String getName();

    /**
     * Gets the value of this tag.
     *
     * @return The value of this tag
     */
    @Parameter(order = 2)
    T getValue();

    /**
     * Unwraps this tag to the JNBT framework default tag.
     * <p>
     * <b>Do not use this method outside of the BlockAPI framework. It may
     * change its signature.</b>
     *
     *
     * @return unwrapped tag
     */
    org.jnbt.Tag unwrap();

    /**
     * Wrap a JNBT tag to use.
     * <p>
     * <b>Do not use this method outside of the BlockAPI framework. It may
     * change its signature.</b>
     *
     * @param tag JNBT tag to wrap
     * @param clazz Class of the type parameter this tag should be casted to.
     *
     * @return wrapped tag
     * @throws ClassCastException if given class is incompatible with the given
     *         tag
     */
    public static <X extends Tag<?>> X wrap(final org.jnbt.Tag tag, final Class<X> clazz) {
        final Tag<?> tt = wrap(tag);
        if (clazz.isInstance(tt)) {
            return clazz.cast(tt);
        }
        throw new ClassCastException("class is an incompatible type:" + clazz + "<-> " + tt.getValue().getClass());
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
            return ByteTag.of(tag.getName(), (Byte) tag.getValue());
        }

        else if (org.jnbt.CompoundTag.class.isInstance(tag)) {
            final org.jnbt.CompoundTag tt = (org.jnbt.CompoundTag) tag;
            return CompoundTag.of(tag.getName(), checkNotNull(Maps.transformValues(tt.getValue(), x -> Tag.wrap(x))));
        }

        else if (org.jnbt.DoubleTag.class.isInstance(tag)) {
            return DoubleTag.of(tag.getName(), (Double) tag.getValue());
        }

        else if (org.jnbt.EndTag.class.isInstance(tag)) {
            return EndTag.of();
        }

        else if (org.jnbt.FloatTag.class.isInstance(tag)) {
            return FloatTag.of(tag.getName(), (Float) tag.getValue());
        }

        else if (org.jnbt.IntArrayTag.class.isInstance(tag)) {
            return IntArrayTag.of(tag.getName(), (int[]) tag.getValue());
        }

        else if (org.jnbt.IntTag.class.isInstance(tag)) {
            return IntTag.of(tag.getName(), (Integer) tag.getValue());
        }

        else if (org.jnbt.ListTag.class.isInstance(tag)) {
            final org.jnbt.ListTag tt = ((org.jnbt.ListTag) tag);
            final List<Tag<?>> ll = Lists.transform(tt.getValue(), x -> Tag.wrap(x));
            return NBT.List(tag.getName(), Tag.class).addAll(checkNotNull(ll)).build();
        }

        else if (org.jnbt.LongTag.class.isInstance(tag)) {
            return LongTag.of(tag.getName(), (Long) tag.getValue());
        }

        else if (org.jnbt.ShortTag.class.isInstance(tag)) {
            return ShortTag.of(tag.getName(), (Short) tag.getValue());
        }

        else if (org.jnbt.StringTag.class.isInstance(tag)) {
            return StringTag.of(tag.getName(), (String) tag.getValue());
        }

        throw new IllegalArgumentException("Invalid tag instance");

    }

}
