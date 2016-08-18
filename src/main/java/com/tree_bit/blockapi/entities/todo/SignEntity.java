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
package com.tree_bit.blockapi.entities.todo;

import com.tree_bit.blockapi.entities.blockentity.BlockEntity;
import com.tree_bit.blockapi.internal.BlockApiStyle;
import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;
import com.tree_bit.rcdl.blocks.HangingSign;
import com.tree_bit.rcdl.blocks.StandingSign;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;


/**
 * Tile entity of a sign block.
 *
 * <p>
 * Used by:
 * <ul>
 * <li>{@link HangingSign}</li>
 * <li>{@link StandingSign}</li>
 * </ul>
 *
 * @author Sascha Sauermann
 */
@Immutable
@BlockApiStyle
@SuppressWarnings("all")
public abstract class SignEntity implements BlockEntity {

    /**
     * First row of text.
     *
     * @return text
     */
    @Parameter(order = 1)
    @Default
    @SuppressWarnings("static-method")
    public String text1() {
        return "";
    }

    /**
     * Second row of text.
     *
     * @return text
     */
    @Parameter(order = 2)
    @Default
    @SuppressWarnings("static-method")
    public String text2() {
        return "";
    }

    /**
     * Third row of text.
     *
     * @return text
     */
    @Parameter(order = 3)
    @Default
    @SuppressWarnings("static-method")
    public String text3() {
        return "";
    }

    /**
     * Fourth row of text.
     *
     * @return text
     */
    @Parameter(order = 4)
    @Default
    @SuppressWarnings("static-method")
    public String text4() {
        return "";
    }

    // TODO: extra class instead compoundTag?
    /**
     * Information identifying scoreboard parameters to modify relative to the
     * last command run.
     *
     * @return command stats
     */
    @Default
    @SuppressWarnings("static-method")
    public CompoundTag commandStats() {
        return NBT.Compound("CommandStats").build();
    }

    @Override
    @Derived
    public CompoundTag compound() {
        return NBT.Compound(this.id()).add(this.commandStats()).String("Text1", this.text1()).String("Text2", this.text2())
                .String("Text3", this.text3()).String("Text4", this.text4()).build();
    }

    @Override
    public String id() {
        return "Sign";
    }

    // TODO Remove
    public static SignEntity empty() {
        // return SignEntity.builder().build();
        return new SignEntity() {};
    }



}
