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
import com.tree_bit.blockapi.id.IBiomeID;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Mapping of biome names to id's.
 *
 * @author Sascha Sauermann
 */
public enum BiomeID implements IBiomeID {
    /** Ocean */
    OCEAN(0),
    /** Plains */
    PLAINS(1),
    /** Desert */
    DESERT(2),
    /** Extreme Hills */
    EXTREME_HILLS(3),
    /** Forest */
    FOREST(4),
    /** Taiga */
    TAIGA(5),
    /** Swampland */
    SWAMPLAND(6),
    /** River */
    RIVER(7),
    /** Hell */
    HELL(8),
    /** The End */
    THE_END(9),
    /** FrozenOcean */
    FROZENOCEAN(10),
    /** FrozenRiver */
    FROZENRIVER(11),
    /** Ice Plains */
    ICE_PLAINS(12),
    /** Ice Mountains */
    ICE_MOUNTAINS(13),
    /** MushroomIsland */
    MUSHROOMISLAND(14),
    /** MushroomIslandShore */
    MUSHROOMISLANDSHORE(15),
    /** Beach */
    BEACH(16),
    /** DesertHills */
    DESERTHILLS(17),
    /** ForestHills */
    FORESTHILLS(18),
    /** TaigaHills */
    TAIGAHILLS(19),
    /** Extreme Hills Edge */
    EXTREME_HILLS_EDGE(20),
    /** Jungle */
    JUNGLE(21),
    /** JungleHills */
    JUNGLEHILLS(22),
    /** JungleEdge */
    JUNGLEEDGE(23),
    /** Deep Ocean */
    DEEP_OCEAN(24),
    /** Stone Beach */
    STONE_BEACH(25),
    /** Cold Beach */
    COLD_BEACH(26),
    /** Birch Forest */
    BIRCH_FOREST(27),
    /** Birch Forest Hills */
    BIRCH_FOREST_HILLS(28),
    /** Roofed Forest */
    ROOFED_FOREST(29),
    /** Cold Taiga */
    COLD_TAIGA(30),
    /** Cold Taiga Hills */
    COLD_TAIGA_HILLS(31),
    /** Mega Taiga */
    MEGA_TAIGA(32),
    /** Mega Taiga Hills */
    MEGA_TAIGA_HILLS(33),
    /** Extreme Hills+ */
    EXTREME_HILLS_PLUS(34),
    /** Savanna */
    SAVANNA(35),
    /** Savanna Plateau */
    SAVANNA_PLATEAU(36),
    /** Mesa */
    MESA(37),
    /** Mesa Plateau F */
    MESA_PLATEAU_F(38),
    /** Mesa Plateau */
    MESA_PLATEAU(39),
    ///////////////////////////
    /** The Void */
    THE_VOID(127),
    //////////////////////////
    /** Plains M */
    PLAINS_M(128),
    /** Sunflower Plains */
    SUNFLOWER_PLAINS(129),
    /** Desert M */
    DESERT_M(130),
    /** Extreme Hills M */
    EXTREME_HILLS_M(131),
    /** Flower Forest */
    FLOWER_FOREST(132),
    /** Taiga M */
    TAIGA_M(133),
    /** Swampland M */
    SWAMPLAND_M(134),
    /** Ice Plains Spikes */
    ICE_PLAINS_SPIKES(140),
    /** Jungle M */
    JUNGLE_M(149),
    /** JungleEdge M */
    JUNGLE_EDGE_M(151),
    /** Birch Forest M */
    BIRCH_FOREST_M(155),
    /** Birch Forest Hills M */
    BIRCH_FOREST_HILLS_M(156),
    /** Roofed Forest M */
    ROOFED_FOREST_M(157),
    /** Cold Taiga M */
    COLD_TAIGA_M(158),
    /** Mega Spruce Taiga */
    MEGA_SPRUCE_TAIGA(160),
    /** Redwood Taiga Hills M */
    REDWOOD_TAIGA_HILLS_M(161),
    /** Extreme Hills+ M */
    EXTREME_HILLS_PLUS_M(162),
    /** Savanna M */
    SAVANNA_M(163),
    /** Savanna Plateau M */
    SAVANNA_PLATEAU_M(164),
    /** Mesa (Bryce) */
    MESA_BRYCE(165),
    /** Mesa Plateau F M */
    MESA_PLATEAU_F_M(166),
    /** Mesa Plateau M */
    MESA_PLATEAU_M(167);

    private int id;

    BiomeID(final int id) {
        this.id = id;
    }

    /**
     * Returns the biome id.
     *
     * @return Biome id
     */
    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public @NonNull String toString() {
        return MoreObjects.toStringHelper(this).addValue(super.toString()).add("id", this.id).toString();
    }
}
