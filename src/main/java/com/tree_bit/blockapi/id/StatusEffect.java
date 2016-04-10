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

/**
 * Enum of all Minecraft status effects used by beacons, potions, etc.
 *
 * @author Sascha Sauermann
 */
public enum StatusEffect implements IStatusEffect {

    /** None */
    NONE(0),
    /** Speed */
    SPEED(1),
    /** Slowness */
    SLOWNESS(2),
    /** Haste */
    HASTE(3),
    /** Mining Fatigue */
    MINING_FATIGUE(4),
    /** Strength */
    STRENGTH(5),
    /** Instant Health */
    INSTANT_HEALTH(6),
    /** Instant Damage */
    INSTANT_DAMAGE(7),
    /** Jump Boost */
    JUMP_BOOST(8),
    /** Nausea */
    NAUSEA(9),
    /** Regeneration */
    REGENERATION(10),
    /** Resistance */
    RESISTANCE(11),
    /** Fire Resistance */
    FIRE_RESISTANCE(12),
    /** Water Breathing */
    WATER_BREATHING(13),
    /** Invisibility */
    INVISIBILITY(14),
    /** Blindness */
    BLINDNESS(15),
    /** Night Vision */
    NIGHT_VISION(16),
    /** Hunger */
    HUNGER(17),
    /** Weakness */
    WEAKNESS(18),
    /** Poison */
    POISON(19),
    /** Wither */
    WITHER(20),
    /** Health Boost */
    HEALTH_BOOST(21),
    /** Absorption */
    ABSORPTION(22),
    /** Saturation */
    SATURATION(23),
    /** Glowing */
    GLOWING(24),
    /** Levitation */
    LEVITATION(25),
    /** Luck */
    LUCK(26),
    /** Bad Luck */
    BAD_LUCK(27);

    private final int id;

    StatusEffect(final int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }

}
