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

import com.tree_bit.blockapi.Coordinates;
import com.tree_bit.blockapi.Motion;
import com.tree_bit.blockapi.Rotation;
import com.tree_bit.blockapi.id.IDimensionID;
import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.NBTData;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;
import com.tree_bit.blockapi.nbt.tags.StringTag;
import com.tree_bit.blockapi.nbt.tags.Tag;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Parameter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


/**
 * Tags common to all entities.
 */
public interface Entity extends NBTData {

    /**
     * Entity ID. This tag does not exist for the Player entity.
     * <p>
     * <b>Must be overridden manually</b>
     *
     * @return entity id
     */
    String id();

    /**
     * Coordinates describing the current X,Y,Z position of the entity.
     *
     * @return position
     */
    @Parameter(order = 0)
    Coordinates pos();

    /**
     * Motion describing the current dX,dY,dZ velocity of the entity in meters
     * per tick.
     *
     * @return motion
     */
    @Default
    default Motion motion() {
        return Motion.of(0, 0, 0);
    }

    /**
     * Rotation object representing rotation in degrees.
     *
     * @return rotation
     */
    @Default
    default Rotation rotation() {
        return Rotation.of(0, 0);
    }

    /**
     * Distance the entity has fallen. Larger values cause more damage when the
     * entity lands.
     *
     * @return fall distance
     */
    @Default
    default float fallDistance() {
        return 0f;
    }

    /**
     * Number of ticks until the fire is put out. Negative values reflect how
     * long the entity can stand in fire before burning. Default -20 when not on
     * fire.
     *
     * @return remaining fire time
     */
    @Default
    default short fire() {
        return -20;
    }

    /**
     * How much air the entity has, in ticks. Fills to a maximum of 300 in air,
     * giving 15 seconds submerged before the entity starts to drown, and a
     * total of up to 35 seconds before the entity dies (if it has 20 health).
     * Decreases while underwater. If 0 while underwater, the entity loses 1
     * health per second.
     *
     * @return remaining air time
     */
    @Default
    default short air() {
        return 0;
    }

    /**
     * 1 or 0 (true/false) - true if the entity is touching the ground.
     *
     * @return on ground boolean
     */
    @Default
    default boolean onGround() {
        return false;
    }

    /**
     * Only known to be used in &lt;player&gt;.dat to store the players last
     * known location along with Pos. All other entities are only saved in the
     * region files for the dimension they are in.
     *
     * @return dimension object
     */
    Optional<IDimensionID> dimension();

    /**
     * 1 or 0 (true/false) - true if the entity should not take damage. This
     * applies to living and nonliving entities alike: mobs will not take damage
     * from any source (including potion effects), and cannot be moved by
     * fishing rods, attacks, explosions, or projectiles, and objects such as
     * vehicles and item frames cannot be destroyed unless their supports are
     * removed. Note that these entities can be damaged by players in Creative
     * mode.
     *
     * @return invulnerable boolean
     */
    default boolean invulnerable() {
        return false;
    }

    /**
     * The number of ticks before which the entity may be teleported back
     * through a nether portal. Initially starts at 300 ticks (15 seconds) after
     * teleportation and counts down to 0.
     *
     * @return portal cooldown time
     */
    default int portalCooldown() {
        return 0;
    }

    /**
     * The most significant bits of this entity's Universally Unique IDentifier.
     * This is joined with UUIDLeast to form this entity's unique ID.
     *
     * @return UUID most
     */
    Optional<Long> uuidMost();

    /**
     * The least significant bits of this entity's Universally Unique
     * IDentifier. This is joined with UUIDMost to form this entity's unique ID.
     *
     * @return UUID least
     */
    Optional<Long> uuidLeast();

    /**
     * The Universally Unique IDentifier of this entity. Converts a hexadecimal
     * UUID (for example: 069a79f4-44e9-4726-a5be-fca90e38aaf5) into the
     * UUIDLeast and UUIDMost tags. Will not apply new UUIDLeast and UUIDMost
     * tags if both of these tags are already present. The "UUID" tag is removed
     * once the entity is loaded.
     *
     * @deprecated since 1.9
     * @return UUID
     */
    @Deprecated
    Optional<String> uuid();

    /**
     * The custom name of this entity. Appears in player death messages and
     * villager trading interfaces, as well as above the entity when your cursor
     * is over it. May not exist, or may exist and be empty.
     *
     * @return custom name
     */
    Optional<String> customName();

    /**
     * 1 or 0 (true/false) - if true, and this entity has a custom name, it will
     * always appear above them, whether or not the cursor is pointing at it. If
     * the entity hasn't a custom name, a default name will be shown. May not
     * exist.
     *
     * @return custom name visible boolean
     */
    default boolean customNameVisible() {
        return false;
    }

    /**
     * 1 or 0 (true/false) - if true, this entity will not make sound. May not
     * exist.
     *
     * @return silent boolean
     */
    default boolean silent() {
        return false;
    }

    /**
     * The data of the entity being ridden. Note that if an entity is being
     * ridden, the topmost entity in the stack has the Pos tag, and the
     * coordinates specify the location of the bottommost entity. Also note that
     * the bottommost entity controls movement, while the topmost entity
     * determines spawning conditions when created by a mob spawner.
     *
     * @deprecated since 1.9
     * @return riding entity
     */
    @Deprecated
    Optional<Entity> riding();

    /**
     * The data of the entity riding. Note that both entities control movement
     * and the topmost entity controls spawning conditions when created by a mob
     * spawner.
     *
     * @return passenger list
     */
    List<Entity> passengers();

    /**
     * 1 or 0 (true/false) - true if the entity is glowing.
     *
     * @return glowing boolean
     */
    default boolean glowing() {
        return false;
    }

    /**
     * List of custom string data.
     *
     * @return tag list
     */
    List<String> tags();

    /**
     * Information identifying scoreboard parameters to modify relative to the
     * last command run.
     *
     * @return command stats
     */
    Optional<CommandStats> commandStats();


    /**
     * Returns a collection of tags for this entity. This can be used to create
     * a CompoundTag for a subclass of entity.
     * <p>
     * <b>Do not use outside of classes that implement this interface.</b>
     *
     * @return tag collection
     */
    // TODO What to do with the deprecated values? Optional version switch? Only
    // support newest version?
    default Collection<? extends Tag<?>> _entityTags() {
        return NBT.begin() //
                .String("id", id()) //
                .add(pos().asListTag("Pos")) //
                .add(motion().asListTag("Motion")) //
                .add(rotation().asListTag("Rotation")) //
                .Float("FallDistance", fallDistance()) //
                .Short("Fire", fire()) //
                .Short("Air", air()) //
                .Byte("OnGround", BooleanHelper.toByte(onGround())) //
                .add(dimension(), x -> NBT.Int("Dimension", x.getID())) //
                .Byte("Invulnerable", BooleanHelper.toByte(invulnerable())) //
                .Int("PortalCooldown", portalCooldown()) //
                .Long("UUIDMost", uuidMost()) //
                .Long("UUIDLeast", uuidLeast()) //
                .String("UUID", uuid()) //
                .String("CustomName", customName()) //
                .Byte("CustomNameVisible", BooleanHelper.toByte(customNameVisible())) //
                .Byte("Silent", BooleanHelper.toByte(silent())).add(riding(), Entity::compound) //
                .List("Passengers", CompoundTag.class, passengers(), Entity::compound) //
                .Byte("Glowing", BooleanHelper.toByte(glowing())) //
                .List("Tags", StringTag.class, tags(), x -> StringTag.of("", x)) //
                .build().getValue().values();
    }

    public interface Builder {
        // TODO



        Entity build();
    }


}
