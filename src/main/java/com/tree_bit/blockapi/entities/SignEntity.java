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

import com.tree_bit.blockapi.nbt.CompoundBuilder;
import com.tree_bit.rcdl.blocks.HangingSign;
import com.tree_bit.rcdl.blocks.StandingSign;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.jnbt.StringTag;

import jdk.nashorn.internal.ir.annotations.Immutable;

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
public class SignEntity extends GenericTileEntity {

    /**
     * Creates a Sign TileEntity with no text.
     */
    public SignEntity() {
        this(new @Nullable FormatText[0]);
    }

    /**
     * Creates a Sign TileEntity with no text.
     *
     * @return Empty sign entity
     */
    public static SignEntity empty() {
        return new SignEntity();
    }

    /**
     * Creates a Sign TileEntity from a FormatText array.
     *
     * @param text Array containing text for each line of the sign. (max 4)
     *
     * @throws IllegalArgumentException if text[] has {@literal length>4}
     */
    public SignEntity(final @Nullable FormatText[] text) {
        super(createCompoundBuilder(text));
    }

    /**
     * Creates a Sign TileEntity from a String array.
     *
     * @param text Array containing text for each line of the sign. (max 4)
     *
     * @throws IllegalArgumentException if text[] has {@literal length>4}
     */
    public SignEntity(final @Nullable String[] text) {
        super(createCompoundBuilder(toFText(text)));
    }

    private static @NonNull FormatText[] toFText(final @Nullable String[] text) {
        final @NonNull FormatText[] fText = new @NonNull FormatText[text.length];
        for (int i = 0; i < text.length; i++) {
            final String tmp = text[i];
            fText[i] = new FormatText((tmp != null) ? tmp : "");
        }
        return fText;
    }

    private static CompoundBuilder createCompoundBuilder(final FormatText[] text) {
        if (text.length > 4) {
            throw new IllegalArgumentException("Sign can't have more than 4 lines of text! Given: " + text.length);
        }
        final CompoundBuilder b = GenericTileEntity.builder("Sign");
        for (int i = 0; i < 4; i++) {
            String s = "";
            if (i < text.length) {
                final FormatText tmp = text[i];
                if (tmp != null) {
                    s = tmp.getStringWithCodes();
                }
            }
            b.add(new StringTag("Text" + (i + 1), s));
        }
        return b;
    }

}
