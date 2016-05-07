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
package com.tree_bit.blockapi.entities;

import com.google.common.collect.Lists;
import com.tree_bit.blockapi.data.IColor;
import com.tree_bit.blockapi.data.minecraft.Color;
import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import java.util.List;

/**
 * Tile entity of a banner
 *
 * <p>
 * Used by:
 * <ul>
 * <li>{@link Banner}</li>
 * </ul>
 *
 * @author Sascha Sauermann
 */
@Immutable
public abstract class _BannerEntity implements TileEntity {

    /**
     * Base color of the banner
     *
     * @return base color
     */
    @Parameter(order = 1)
    @Default
    @SuppressWarnings("static-method")
    public IColor base() {
        return Color.White;
    }

    /**
     * List of applied patterns
     *
     * @return pattern list
     */
    @Parameter(order = 2)
    public abstract List<Pattern> pattern();


    @Override
    @Derived
    public CompoundTag compound() {
        return NBT.Compound(this.id()).Int("Base", this.base().getDV())
                .List("Patterns", CompoundTag.class, Lists.transform(this.pattern(), Pattern::compound)).build();
    }

    @Override
    public String id() {
        return "Banner";
    }

}
