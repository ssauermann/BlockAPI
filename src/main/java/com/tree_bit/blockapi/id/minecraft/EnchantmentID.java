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
import com.tree_bit.blockapi.id.IEnchantmentID;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Enum of all Minecraft enchantment effects
 *
 * @author Sascha Sauermann
 */
public enum EnchantmentID implements IEnchantmentID {
    /** Protection */
    PROTECTION(0, "protection"),
    /** Fire Protection */
    FIRE_PROTECTION(1, "fire_protection"),
    /** Feather Falling */
    FEATHER_FALLING(2, "feather_falling"),
    /** Blast Protection */
    BLAST_PROTECTION(3, "blast_protection"),
    /** Projectile Protection */
    PROJECTILE_PROTECTION(4, "projectile_protection"),
    /** Respiration */
    RESPIRATION(5, "respiration"),
    /** Aqua Affinity */
    AQUA_AFFINITY(6, "aqua_affinity"),
    /** Thorns */
    THORNS(7, "thorns"),
    /** Depth Strider */
    DEPTH_STRIDER(8, "depth_strider"),
    /** Frost Walker */
    FROST_WALKER(9, "frost_walker"),
    /** Sharpness */
    SHARPNESS(16, "sharpness"),
    /** Smite */
    SMITE(17, "smite"),
    /** Bane of Arthropods */
    BANE_OF_ARTHROPODS(18, "bane_of_arthropods"),
    /** Knockback */
    KNOCKBACK(19, "knockback"),
    /** Fire Aspect */
    FIRE_ASPECT(20, "fire_aspect"),
    /** Looting */
    LOOTING(21, "looting"),
    /** Efficiency */
    EFFICIENCY(32, "efficiency"),
    /** Silk Touch */
    SILK_TOUCH(33, "silk_touch"),
    /** Unbreaking */
    UNBREAKING(34, "unbreaking"),
    /** Fortune */
    FORTUNE(35, "fortune"),
    /** Power */
    POWER(48, "power"),
    /** Punch */
    PUNCH(49, "punch"),
    /** Flame */
    FLAME(50, "flame"),
    /** Infinity */
    INFINITY(51, "infinity"),
    /** Luck of the Sea */
    LUCK_OF_THE_SEA(61, "luck_of_the_sea"),
    /** Lure */
    LURE(62, "lure"),
    /** Mending */
    MENDING(70, "mending");


    private final int id;
    private String ename;

    EnchantmentID(final int id, final String name) {
        this.id = id;
        this.ename = name;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.ename;
    }

    @Override
    public @NonNull String toString() {
        return MoreObjects.toStringHelper(this).addValue(super.toString()).add("id", this.id).toString();
    }
}
