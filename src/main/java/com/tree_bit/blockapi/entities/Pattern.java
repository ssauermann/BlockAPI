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

import com.google.common.base.Objects;
import com.tree_bit.rcdl.blocks.dv.Color;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * One pattern of a {@link BannerEntity} with a {@link Color} and a pattern
 * {@link Type}
 *
 * @author Sascha Sauermann
 */
public final class Pattern {

    private final Color color;
    private final String pattern;

    /**
     * Pattern Types
     *
     * @author Sascha Sauermann
     */
    public enum Type {
        /** Base fess */
        BOTTOM_STRIPE("bs"),
        /** Chief fess */
        TOP_STRIPE("ts"),
        /** Pale dexter */
        LEFT_STRIPE("ls"),
        /** Pale sinister */
        RIGHT_STRIPE("rs"),
        /** Pale */
        CENTER_STRIPE_VERTICAL("cs"),
        /** Fess */
        MIDDLE_STRIPE_HORIZONTAL("ms"),
        /** Bend */
        DOWN_RIGHT_STRIPE("drs"),
        /** Bend sinister */
        DOWN_LEFT_STRIPE("dls"),
        /** Paly */
        SMALL_VERTICAL_STRIPES("ss"),
        /** Saltire */
        DIAGONAL_CROSS("cr"),
        /** Cross */
        SQUARE_CROSS("sc"),
        /** Per bend sinister */
        LEFT_OF_DIAGONAL("ld"),
        /** Per bend */
        RIGHT_OF_UPSIDEDOWN_DIAGONAL("rud"),
        /** Per bend inverted */
        LEFT_OF_UPSIDEDOWN_DIAGONAL("lud"),
        /** Per bend sinister inverted */
        RIGHT_OF_DIAGONAL("rd"),
        /** Per pale */
        VERTICAL_HALF_LEFT("vh"),
        /** Per pale inverted */
        VERTICAL_HALF_RIGHT("vhr"),
        /** Per fess */
        HORIZONTAL_HALF_TOP("hh"),
        /** Per fess inverted */
        HORIZONTAL_HALF_BOTTOM("hhb"),
        /** Base dexter canton */
        BOTTOM_LEFT_CORNER("bl"),
        /** Base sinister canton */
        BOTTOM_RIGHT_CORNER("br"),
        /** Chief dexter canton */
        TOP_LEFT_CORNER("tl"),
        /** Chief sinister canton */
        TOP_RIGHT_CORNER("tr"),
        /** Chevron */
        BOTTOM_TRIANGLE("bt"),
        /** Inverted chevron */
        TOP_TRIANGLE("tt"),
        /** Base indented */
        BOTTOM_TRIANGLE_SAWTOOTH("bts"),
        /** Chief indented */
        TOP_TRIANGLE_SAWTOOTH("tts"),
        /** Roundel */
        MIDDLE_CIRCLE("mc"),
        /** Lozenge */
        MIDDLE_RHOMBUS("mr"),
        /** Bordure */
        BORDER("bo"),
        /** Bordure indented */
        CURLY_BORDER("cbo"),
        /** Field masoned */
        BRICK("bri"),
        /** Gradient */
        GRADIENT("gra"),
        /**  */
        /** Base gradient */
        GRADIENT_UPSIDEDOWN("gru"),
        /** Creeper charge */
        CREEPER("cre"),
        /** Skull charge */
        SKULL("sku"),
        /** Flower charge */
        FLOWER("flo"),
        /** Mojang charge */
        MOJANG("moj");

        private final String code;

        Type(final String code) {
            this.code = code;
        }

        /**
         * Get the code of this pattern.
         *
         * @return Pattern Code
         */
        public String getCode() {
            return this.code;
        }
    }

    /**
     * Create a new Pattern
     *
     * @param color Color of the new pattern
     * @param type Type of the new pattern
     */
    public Pattern(final Color color, final Type type) {
        this.color = color;
        this.pattern = type.getCode();
    }

    /**
     * Create a new Pattern
     *
     * @param color Color of the new pattern
     * @param type Type-Code of the new pattern
     */
    public Pattern(final Color color, final String type) {
        this.color = color;
        this.pattern = type;
    }

    /**
     * Builder for Pattern lists
     *
     * @author Sascha Sauermann
     */
    public static class Builder {

        private final List<@NonNull Pattern> patternList;

        /**
         * Starts the build process for a pattern list.
         */
        public Builder() {
            this.patternList = new LinkedList<>();
        }

        /**
         * Add a pattern to the list.
         *
         * @param color Color of the new pattern
         * @param type Type of the new pattern
         * @return Builder itself for chaining
         */
        public Builder add(final Color color, final Type type) {
            this.patternList.add(new Pattern(color, type));
            return this;
        }

        /**
         * Add a pattern to the list.
         *
         * @param color Color of the new pattern
         * @param type Type-Code of the new pattern
         * @return Builder itself for chaining
         */
        public Builder add(final Color color, final String type) {
            this.patternList.add(new Pattern(color, type));
            return this;
        }

        /**
         * Returns the patterns as list.
         *
         * @return Pattern list
         */
        public List<@NonNull Pattern> build() {
            return this.patternList;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.patternList);
        }

        @Override
        public boolean equals(@Nullable final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Builder)) {
                return false;
            }
            final Builder other = (Builder) obj;
            return Objects.equal(this.patternList, other.patternList);
        }
    }

    /**
     * Gets the color of this pattern.
     *
     * @return Pattern color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets the type of this pattern.
     *
     * @return Pattern type
     */
    public Optional<Type> getType() {
        try {
            return Optional.of(Type.valueOf(this.pattern));
        } catch (final IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    /**
     * Gets the type-code of this pattern.
     *
     * @return Pattern type-code
     */
    public String getTypeCode() {
        return this.pattern;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.color, this.pattern);
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Pattern)) {
            return false;
        }
        final Pattern other = (Pattern) obj;
        return Objects.equal(this.color, other.color) && Objects.equal(this.pattern, other.pattern);
    }
}

