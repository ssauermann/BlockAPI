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
package com.tree_bit.blockapi.entities.mobs;

import com.google.common.base.Objects;
import com.tree_bit.blockapi.Version;
import com.tree_bit.blockapi.entities.BooleanHelper;
import com.tree_bit.blockapi.entities.Entity;
import com.tree_bit.blockapi.entities.Item;
import com.tree_bit.blockapi.nbt.NBTCompound;
import com.tree_bit.blockapi.nbt.NBTCompound.Tags;
import com.tree_bit.blockapi.nbt.NBTCompoundData;
import com.tree_bit.blockapi.nbt.NBTConverter;
import com.tree_bit.blockapi.nbt.NBTVersion;
import com.tree_bit.blockapi.nbt.Transformation;
import com.tree_bit.blockapi.nbt.tags.FloatTag;

import org.immutables.value.Value.Immutable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


/**
 * Representing a mob entity.
 */
@Immutable
public interface Mob { // extends Entity {
    // TODO: Remove temp classes

    public class Attribute {

    }

    public class PotionEffect {

    }

    public class LootTable {

    }

    /**
     * Amount of health the entity has, in floating point format. A value of 1
     * is half a heart. Used when more precise health values are needed, such as
     * the health of entities being damaged by a player with the Weakness effect
     * (if the player isn't holding an item that increases their attack damage,
     * they deal 1/2 a health point, 1/4 a heart). If this tag exists, Health
     * will be ignored.
     *
     * @return HealF
     * @deprecated since 1.9
     */
    @NBTCompound(key = "HealF", tag = Tags.Float, version = @NBTVersion(max = Version.V1_8))
    @Deprecated
    float healF();

    /**
     * Amount of health the entity has. Used when whole-number health values are
     * needed, such as displaying a player's health on their HUD. If the HealF
     * tag exists, this tag will be ignored.
     *
     * @return Health (float)
     */
    @NBTCompound(key = "Health", tag = Tags.Float, version = @NBTVersion(min = Version.V1_9))
    float health();

    /**
     * Amount of health the entity has. Used when whole-number health values are
     * needed, such as displaying a player's health on their HUD. If the HealF
     * tag exists, this tag will be ignored.
     *
     * @return Health (short)
     * @deprecated since 1.9
     */
    @NBTCompound(key = "Health", tag = Tags.Short, version = @NBTVersion(max = Version.V1_8))
    @Deprecated
    short health_();

    /**
     * Amount of extra health added by Absorption effect.
     *
     * @return AbsorptionAmount
     */
    // Probably added in 1.6
    @NBTCompound(key = "AbsorptionAmount", tag = Tags.Float)
    float absorptionAmount();

    /**
     * Number of ticks the mob turns red for after being hit. 0 when not
     * recently hit.
     *
     * @return HurtTime
     */
    @NBTCompound(key = "HurtTime", tag = Tags.Short)
    short hurtTime();

    /**
     * The last time the mob was damaged, measured in the number of ticks since
     * the mob's creation. Updates to a new value whenever the mob is damaged,
     * then updates again 101 ticks later for reasons unknown. Can be changed
     * with the entitydata command, but the specified value does not affect
     * natural updates in any way, and is overwritten if the mob receives
     * damage.
     *
     * @return HurtByTimestamp
     */
    @NBTCompound(key = "HurtByTimestamp", tag = Tags.Int)
    int hurtByTimestamp();

    /**
     * Number of ticks the mob has been dead for. Controls death animations. 0
     * when alive.
     *
     * @return DeathTime
     */
    @NBTCompound(key = "DeathTime", tag = Tags.Short)
    short deathTime();

    /**
     * A list of Attributes for this mob. These are used for many purposes in
     * internal calculations, and can be considered a mob's "statistics". Valid
     * Attributes for a given mob are listed in the main article.
     *
     * @return Attributes
     */
    @NBTCompound(key = "Attributes", tag = Tags.List, transformation = Transformation.LIST, converter = NBTConverter.OfCompound.class)
    List<Attribute> attributes();

    /**
     * The list of potion effects on this mob. May not exist.
     *
     * @return ActiveEffects
     */
    @NBTCompound(key = "ActiveEffects", tag = Tags.List, transformation = Transformation.LIST, converter = NBTConverter.OfCompound.class)
    List<PotionEffect> activeEffects();

    /**
     * The list of compound tags of the equipment the mob has. Each compound tag
     * in the list is an Item without the slot tag. All 5 entries will always
     * exist, but may be empty compound tags to indicate no item.
     *
     * @return Equipment
     */
    @NBTCompound(key = "Equipment", tag = Tags.List, transformation = Transformation.LIST, converter = NBTConverter.OfCompound.class,
            version = @NBTVersion(max = Version.V1_8))
    @Deprecated
    List<Item> equipment();

    /**
     * List of float values from 0 to 1 representing the chance for a carried
     * item to drop. By default these are all 0.085, but they get set to 2 if
     * the mob picks up an item. Items with durability held by a mob that have
     * an associated DropChances greater than 1.0 will retain the defined
     * durability of the item. If the DropChances is 1.0 or lower, the
     * durability is randomized. If the "Unbreakable" tag exists on the item,
     * the durability will be assigned as defined, regardless of the DropChances
     * value.
     *
     * @return DropChances
     */
    // TODO: Create equipment class? annotate a method with multiple
    // annotations: equipment generates: Items + DropChances?
    @NBTCompound(key = "DropChances", tag = Tags.List, transformation = Transformation.LIST, converter = DropChance.Converter.class,
            version = @NBTVersion(max = Version.V1_8))
    @Deprecated
    List<DropChance> dropChances();

    @NBTCompound(key = "HandItems", tag = Tags.List, transformation = Transformation.LIST, converter = NBTConverter.OfCompound.class,
            version = @NBTVersion(min = Version.V1_9))
    List<Item> handItems();

    @NBTCompound(key = "ArmorItems", tag = Tags.List, transformation = Transformation.LIST, converter = NBTConverter.OfCompound.class,
            version = @NBTVersion(min = Version.V1_9))
    List<Item> armorItems();

    @NBTCompound(key = "HandDropChances", tag = Tags.List, transformation = Transformation.LIST, converter = DropChance.Converter.class,
            version = @NBTVersion(min = Version.V1_9))
    List<DropChance> handDropChances();

    @NBTCompound(key = "ArmorDropChances", tag = Tags.List, transformation = Transformation.LIST, converter = DropChance.Converter.class,
            version = @NBTVersion(min = Version.V1_9))
    List<DropChance> armorDropChances();

    @NBTCompound(key = "DeathLootTable", tag = Tags.isNBTCompound, transformation = Transformation.OPTIONAL,
            version = @NBTVersion(min = Version.V1_9))
    Optional<LootTable> deathLootTable();

    /**
     * Optional. Seed for generating the loot table. 0 or omitted will use a
     * random seed.
     *
     * @return DeathLootTableSeed
     */
    @NBTCompound(key = "DeathLootTableSeed", tag = Tags.Long, transformation = Transformation.OPTIONAL, version = @NBTVersion(min = Version.V1_9))
    Optional<Long> deathLootTableSeed();

    /**
     * 1 or 0 (true/false) - true if the mob can pick up loot (wear armor it
     * picks up, use weapons it picks up).
     *
     * @return CanPickUpLoot
     */
    @NBTCompound(key = "CanPickUpLoot", tag = Tags.Byte, converter = BooleanHelper.ToByte.class)
    boolean canPickUpLoot();

    /**
     * 1 or 0 (true/false) - If true, the mob's AI will be disabled. The mob
     * will not attempt to move and cannot move, to the extent of not falling
     * when normally able.
     *
     * @return NoAI
     */
    @NBTCompound(key = "NoAI", tag = Tags.Byte, converter = BooleanHelper.ToByte.class)
    boolean noAI();

    /**
     * 1 or 0 (true/false) - true if the mob must not despawn naturally.
     *
     * @return PersistenceRequired
     */
    @NBTCompound(key = "PersistenceRequired", tag = Tags.Byte, converter = BooleanHelper.ToByte.class)
    boolean persistenceRequired();

    /**
     * 1 or 0 (true/false) - true if the mob renders the main hand as being
     * left.
     *
     * @return LeftHanded
     */
    @NBTCompound(key = "LeftHanded", tag = Tags.Byte, converter = BooleanHelper.ToByte.class)
    boolean leftHanded();

    /**
     * This tag is actually not part of the NBT data of a mob, but instead used
     * when spawning it. It makes the mob instantly join the scoreboard team
     * with that name.
     *
     * @return Team
     */
    @NBTCompound(key = "Team", tag = Tags.String)
    String team();

    /**
     * 1 or 0 (true/false) - whether the mob is leashed.
     *
     * @return Leashed
     */
    @NBTCompound(key = "Leashed", tag = Tags.Byte, converter = BooleanHelper.ToByte.class)
    boolean leashed();

    /**
     * Either contains a UUID long pair, if this leash connects to another
     * entity, or an X, Y, Z int trio if this leash connects to a fencepost.
     *
     * @return Leash
     */
    @NBTCompound(key = "Leash", tag = Tags.isNBTCompound, transformation = Transformation.OPTIONAL)
    Optional<Leash> leash();

    /**
     * Unknown value.
     *
     * @return Golden apple overflow
     */
    @NBTCompound(key = "GoldenAppleOverflow", tag = Tags.Float, version = @NBTVersion(max = Version.EVERY))
    @Deprecated
    float goldenAppleOverflow();

    @Immutable
    public static abstract class Leash implements NBTCompoundData {

    }

    public static class DropChance {

        public static DropChance Never = new DropChance(-327.67f);
        public static DropChance NotWitoutLooting = new DropChance(0f);
        public static DropChance Always = new DropChance(1f);
        public static DropChance AlwaysUndamaged = new DropChance(2f);

        private final float p;

        private DropChance(final float f) {
            this.p = f;
        }

        /**
         * Create a new <code>Dropchance</code> of a float.
         *
         * @param f Drop probability
         * @return <code>Dropchance</code>
         */
        public static DropChance of(final float f) {
            return new DropChance(f);
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof DropChance) {
                return Objects.equal(this.p, ((DropChance) obj).p);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.p);
        }

        /**
         * Converter from DropChance to FloatTag
         */
        static class Converter implements Function<DropChance, FloatTag> {

            @Override
            public FloatTag apply(final DropChance t) {
                return FloatTag.of("", t.p);
            }

        }
    }

    public interface Builder<T extends Builder> extends Entity.Builder<Builder<T>> {

    }

}
