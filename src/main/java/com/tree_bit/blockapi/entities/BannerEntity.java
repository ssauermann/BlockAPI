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

import com.google.common.collect.ImmutableList;
import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.rcdl.blocks.dv.Color;

import org.jnbt.CompoundTag;

import java.util.List;
import java.util.stream.Collectors;

import jdk.nashorn.internal.ir.annotations.Immutable;

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
public class BannerEntity extends GenericTileEntity {

    private final Color base;
    private final List<Pattern> pattern;

    /**
     * Creates a white banner entity without patterns.
     */
    BannerEntity() {
        this(Color.White, ImmutableList.of());
    }

    /**
     * Creates a banner entity of the given color without patterns.
     *
     * @param color Base color of the banner
     */
    BannerEntity(final Color color) {
        this(color, ImmutableList.of());
    }

    /**
     * Creates a banner entity of the given color and patterns.
     *
     * @param color Base color of the banner
     * @param patterns List of applied patterns
     */
    @SuppressWarnings("null")
    // Collector returns @NonNull List<CompoundTag> but should be
    // @NonNull List<@NonNull CompoundTag> in this case.
    BannerEntity(final Color color, final List<Pattern> patterns) {
        super(NBT.Compound("").Int("Base", color.getDataValue()).add(NBT.List("Patterns", CompoundTag.class, (patterns.stream().map(p -> {
            return NBT.Compound("").Int("Color", p.getColor().getDataValue()).String("Pattern", p.getTypeCode()).build();
        }).collect(Collectors.toList())))));
        this.base = color;
        this.pattern = patterns;
    }


    /**
     * Gets the base color of this banner.
     *
     * @return base color
     */
    public Color getBase() {
        return this.base;
    }


    /**
     * Gets the pattern of this banner.
     *
     * @return list of pattern
     */
    public List<Pattern> getPattern() {
        return this.pattern;
    }


    /**
     * Starts building of a new BannerEntity.
     *
     * @param base Base color of this banner
     * @return New BannerEntity Builder
     */
    public static Builder builder(final Color base) {
        return new Builder(base);
    }


    /**
     * Builder for a BannerEntity.
     *
     * @author Sascha Sauermann
     */
    public static class Builder {

        private final Color base;
        private final Pattern.Builder pattern = new Pattern.Builder();

        /**
         * Starts building of a new BannerEntity.
         *
         * @param base Base color of this banner
         */
        public Builder(final Color base) {
            this.base = base;
        }

        /**
         * Add a pattern to this banner.
         *
         * @param color Color of the new pattern
         * @param type Type of the new pattern
         * @return Builder itself for chaining
         */
        public Builder add(final Color color, final Pattern.Type type) {
            this.pattern.add(color, type);
            return this;
        }

        /**
         * Add a pattern to this banner.
         *
         * @param color Color of the new pattern
         * @param type Type-Code of the new pattern
         * @return Builder itself for chaining
         */
        public Builder add(final Color color, final String type) {
            this.pattern.add(color, type);
            return this;
        }

        /**
         * Finishes building and creates the BannerEntity.
         *
         * @return New BannerEntity with the created design
         */
        public BannerEntity build() {
            return new BannerEntity(this.base, this.pattern.build());
        }

        /**
         * Starts building of a new BannerEntity.
         *
         * @param base Base color of this banner
         * @return new Builder
         */
        public static Builder base(final Color base) {
            return new Builder(base);
        }

    }

}
