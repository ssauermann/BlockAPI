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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;


/**
 * Text containing multiple Strings with formatting.
 *
 * @author Sascha Sauermann
 */
// @Immutable
@SuppressWarnings("all")
public final class FormatText {

    private final ImmutableList<@NonNull FormatString> strings;

    /**
     * Creates a new FormatText with the given FormatStrings as content.
     *
     * @param strings FormatString list
     */
    public FormatText(final List<@NonNull FormatString> strings) {
        this.strings = ImmutableList.copyOf(strings);
    }

    /**
     * Creates a new FormatText with no format.
     *
     * @param string String
     */
    public FormatText(final String string) {
        this(ImmutableList.of(new FormatString(string)));
    }

    /**
     * Creates a new FormatText Builder.
     *
     * @return Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a formatted Text containing all formatting codes preceded by '§'.
     * Unnecessary format codes are removed.
     * {@literal ('§rHello §a§rWorld' --> 'Hello World'}
     *
     * @return Formatted Text
     */
    public String getStringWithCodes() {
        final StringBuilder b = new StringBuilder();
        boolean formatSet = false;
        for (final FormatString formatString : this.strings) {

            final String s = formatString.getString();
            final Format f = formatString.getFormat();

            // Do not add empty Strings
            if (s.length() > 0) {
                if ((f == Format.NONE) || (f == Format.RESET)) {
                    // If there was a format set --> reset it
                    if (formatSet) {
                        b.append('§');
                        b.append(f.getChar());
                    }
                } else {
                    b.append('§');
                    b.append(f.getChar());
                    formatSet = true;
                }
                b.append(s);
            }
        }

        return "" + b.toString();
    }

    /**
     * Builder for FormatText.
     *
     * @author Sascha Sauermann
     */
    public static class Builder {

        private final List<@NonNull FormatString> strings;

        /**
         * Creates a new FormatText Builder.
         */
        public Builder() {
            this.strings = new LinkedList<>();
        }

        /**
         * Creates a new FormatText builder with the given list of
         * FormatStrings.
         *
         * @param strings FormatString list
         */
        public Builder(final List<@NonNull FormatString> strings) {
            // No defensive copy, cause there are no invariants.
            this.strings = strings;
        }

        /**
         * Adds a FormatString to the end of this FormatText.
         *
         * @param string FormatString to add
         * @return Builder for chaining
         */
        public Builder append(final FormatString string) {
            this.strings.add(string);
            return this;
        }

        /**
         * Adds a unformatted String to the end of this FormatText.
         *
         * @param string String to add
         * @return Builder for chaining
         */
        public Builder append(final String string) {
            this.strings.add(new FormatString(string));
            return this;
        }

        /**
         * Finishes building and returns an instance of FormatText.
         *
         * @return FormatText instance
         */
        public FormatText build() {
            return new FormatText(this.strings);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).addValue(this.strings).toString();
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.strings);
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
            return Objects.equal(this.strings, other.strings);
        }


    }

    /**
     * A FormatString represents a single String with a given format.
     *
     * @author Sascha Sauermann
     */
    // @Immutable
    public static class FormatString {

        private final String string;
        private final Format format;

        /**
         * Creates a new FormatString with the given format.
         *
         * @param string String
         * @param format Format
         */
        public FormatString(final String string, final Format format) {
            this.string = string;
            this.format = format;
        }

        /**
         * Creates a new FormatString with no format.
         *
         * @param string String
         */
        public FormatString(final String string) {
            this(string, Format.NONE);
        }

        /**
         * Returns the String.
         *
         * @return String
         */
        public String getString() {
            return this.string;
        }

        /**
         * Returns the format of this String.
         *
         * @return Format
         */
        public Format getFormat() {
            return this.format;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.string, this.format);
        }

        @Override
        public boolean equals(@Nullable final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof FormatString)) {
                return false;
            }
            final FormatString other = (FormatString) obj;
            return Objects.equal(this.string, other.string) && Objects.equal(this.format, other.format);
        }


        @Override
        public @NonNull String toString() {
            return MoreObjects.toStringHelper(this).add("String", this.string).add("Format", this.format).toString();
        }
    }

    /**
     * Minecraft text formatting codes.
     *
     * @author Sascha Sauermann
     */
    public enum Format {
        /** Color: Black */
        BLACK('0'),
        /** Color: Dark Blue */
        DARK_BLUE('1'),
        /** Color: Dark Green */
        DARK_GREEN('2'),
        /** Color: Dark Aqua */
        DARK_AQUA('3'),
        /** Color: Dark Red */
        DARK_RED('4'),
        /** Color: Dark Purple */
        DARK_PURPLE('5'),
        /** Color: Gold */
        GOLD('6'),
        /** Color: Gray */
        GRAY('7'),
        /** Color: Dark Gray */
        DARK_GRAY('8'),
        /** Color: Blue */
        BLUE('9'),
        /** Color: Green */
        GREEN('a'),
        /** Color: Aqua */
        AQUA('b'),
        /** Color: Red */
        RED('c'),
        /** Color: Light Purple */
        LIGHT_PURPLE('d'),
        /** Color: Yellow */
        YELLOW('e'),
        /** Color: White */
        WHITE('f'),
        /** Obfuscated - Random changing characters */
        OBFUSCATED('k'),
        /** Bold */
        BOLD('l'),
        /** Strikethrough */
        STRIKETHROUGH('m'),
        /** Underline */
        UNDERLINE('n'),
        /** Italic */
        ITALIC('o'),
        /**
         * No formatting (Resets previous format) - Unnecessary resets will be
         * removed. Same as {@link #NONE}
         */
        RESET('r'),
        /**
         * No formatting (Resets previous format) - Unnecessary resets will be
         * removed. Same as {@link #RESET}
         */
        NONE('r');

        private final char c;

        private Format(final char c) {
            this.c = c;
        }

        /**
         * Returns the character of this format.
         *
         * @return Character
         */
        public char getChar() {
            return this.c;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.strings);
    }

    @Override
    public boolean equals(@Nullable final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FormatText)) {
            return false;
        }
        final FormatText other = (FormatText) obj;
        return Objects.equal(this.strings, other.strings);
    }



    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(this.strings).toString();
    }
}
