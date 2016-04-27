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
package com.tree_bit.blockapi.id.minecraft;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.tree_bit.blockapi.id.IHorseVariant;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Mapping base color and pattern to variant id.
 *
 * Use {@link HorseVariant#of} to create one with a given base color and
 * pattern.
 *
 * @author Sascha Sauermann
 */
public class HorseVariant implements IHorseVariant {

    /**
     * Horse color
     *
     * @author Sascha Sauermann
     */
    public enum Color {
        /** White */
        WHITE(0),
        /** Creamy */
        CREAMY(1),
        /** Chestnut */
        CHESTNUT(2),
        /** Brown */
        BROWN(3),
        /** Black */
        BLACK(4),
        /** Gray */
        GRAY(5),
        /** Dark brown */
        DARK_BROWN(6);

        private int id;

        Color(final int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).addValue(super.toString()).add("id", this.id).toString();
        }

    }

    /**
     * Horse pattern
     *
     * @author Sascha Sauermann
     */
    public enum Pattern {
        /** No pattern */
        NONE(0),
        /** White */
        WHITE(256),
        /** White field */
        WHITE_FIELD(512),
        /** White dots */
        WHITE_DOTS(768),
        /** Black dots */
        BLACK_DOTS(1024);

        private int id;

        Pattern(final int id) {
            this.id = id;
        }

        @Override
        public @NonNull String toString() {
            return MoreObjects.toStringHelper(this).addValue(super.toString()).add("id", this.id).toString();
        }

    }

    private final Color base;
    private final Pattern pattern;

    /**
     * Creates a horse variant with the given base color and pattern.
     *
     * @param base Base color
     * @param pattern Pattern
     */
    public HorseVariant(final Color base, final Pattern pattern) {
        this.base = base;
        this.pattern = pattern;
    }

    /**
     * Creates a horse variant with the given base color and pattern.
     *
     * @param base Base color
     * @param pattern Pattern
     * @return new horse variant
     */
    public static HorseVariant of(final Color base, final Pattern pattern) {
        return new HorseVariant(base, pattern);
    }

    @Override
    public int getID() {
        return this.base.id + this.pattern.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.base, this.pattern);
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof HorseVariant)) {
            return false;
        }
        final HorseVariant other = (HorseVariant) obj;
        return Objects.equal(this.base, other.base) && Objects.equal(this.pattern, other.pattern);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(this.base).addValue(this.pattern).add("id", this.getID()).toString();
    }

}
