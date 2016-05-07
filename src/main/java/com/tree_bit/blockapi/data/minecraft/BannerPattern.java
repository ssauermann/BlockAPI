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
package com.tree_bit.blockapi.data.minecraft;

import com.tree_bit.blockapi.data.IBannerPattern;

import java.util.Optional;

/**
 * Pattern Types
 */
public enum BannerPattern implements IBannerPattern {
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

    BannerPattern(final String code) {
        this.code = code;
    }

    @Override
    public String getDC() {
        return this.code;
    }


    @Override
    public Optional<BannerPattern> byDC(final String dc) {
        for (final BannerPattern e : values()) {
            if (e.getDC().equals(dc)) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }
}
