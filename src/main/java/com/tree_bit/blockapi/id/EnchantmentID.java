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
package com.tree_bit.blockapi.id;

import com.google.common.base.MoreObjects;

/**
 * Enum of all Minecraft enchantment effects
 *
 * @author Sascha Sauermann
 */
public enum EnchantmentID implements IEnchantmentID {
    /** Protection */
    PROTECTION(0),
    /** Fire Protection */
    FIRE_PROTECTION(1),
    /** Feather Falling */
    FEATHER_FALLING(2),
    /** Blast Protection */
    BLAST_PROTECTION(3),
    /** Projectile Protection */
    PROJECTILE_PROTECTION(4),
    /** Respiration */
    RESPIRATION(5),
    /** Aqua Affinity */
    AQUA_AFFINITY(6),
    /** Thorns */
    THORNS(7),
    /** Depth Strider */
    DEPTH_STRIDER(8),
    /** Frost Walker */
    FROST_WALKER(9),
    /** Sharpness */
    SHARPNESS(16),
    /** Smite */
    SMITE(17),
    /** Bane of Arthropods */
    BANE_OF_ARTHROPODS(18),
    /** Knockback */
    KNOCKBACK(19),
    /** Fire Aspect */
    FIRE_ASPECT(20),
    /** Looting */
    LOOTING(21),
    /** Efficiency */
    EFFICIENCY(32),
    /** Silk Touch */
    SILK_TOUCH(33),
    /** Unbreaking */
    UNBREAKING(34),
    /** Fortune */
    FORTUNE(35),
    /** Power */
    POWER(48),
    /** Punch */
    PUNCH(49),
    /** Flame */
    FLAME(50),
    /** Infinity */
    INFINITY(51),
    /** Luck of the Sea */
    LUCK_OF_THE_SEA(61),
    /** Lure */
    LURE(62),
    /** Mending */
    MENDING(70);


    private final int id;

    EnchantmentID(final int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(super.toString()).add("id", this.id).toString();
    }
}
