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

import javax.annotation.concurrent.NotThreadSafe;


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

    /**
     * Entity builder
     *
     * @param <T> Recursive type for builder extension
     */
    @NotThreadSafe
    public interface Builder<T extends Builder<T>> {

        /**
         * Fill a builder with attribute values from the provided
         * {@code com.tree_bit.blockapi.entities.Entity} instance.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        T from(Entity instance);

        /**
         * Initializes the value for the {@link Entity#pos() pos} attribute.
         *
         * @param pos The value for pos
         * @return {@code this} builder for use in a chained invocation
         */
        T pos(Coordinates pos);

        /**
         * Initializes the value for the {@link Entity#motion() motion}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Entity#motion() motion}.</em>
         *
         * @param motion The value for motion
         * @return {@code this} builder for use in a chained invocation
         */
        T motion(Motion motion);

        /**
         * Initializes the value for the {@link Entity#rotation() rotation}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Entity#rotation() rotation}.</em>
         *
         * @param rotation The value for rotation
         * @return {@code this} builder for use in a chained invocation
         */
        T rotation(Rotation rotation);

        /**
         * Initializes the value for the {@link Entity#fallDistance()
         * fallDistance} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Entity#fallDistance()
         * fallDistance}.</em>
         *
         * @param fallDistance The value for fallDistance
         * @return {@code this} builder for use in a chained invocation
         */
        T fallDistance(float fallDistance);

        /**
         * Initializes the value for the {@link Entity#fire() fire} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Entity#fire() fire}.</em>
         *
         * @param fire The value for fire
         * @return {@code this} builder for use in a chained invocation
         */
        T fire(short fire);

        /**
         * Initializes the value for the {@link Entity#air() air} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Entity#air() air}.</em>
         *
         * @param air The value for air
         * @return {@code this} builder for use in a chained invocation
         */
        T air(short air);

        /**
         * Initializes the value for the {@link Entity#onGround() onGround}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Entity#onGround() onGround}.</em>
         *
         * @param onGround The value for onGround
         * @return {@code this} builder for use in a chained invocation
         */
        T onGround(boolean onGround);

        /**
         * Initializes the optional value {@link Entity#dimension() dimension}
         * to dimension.
         *
         * @param dimension The value for dimension
         * @return {@code this} builder for chained invocation
         */
        T dimension(IDimensionID dimension);

        /**
         * Initializes the optional value {@link Entity#dimension() dimension}
         * to dimension.
         *
         * @param dimension The value for dimension
         * @return {@code this} builder for use in a chained invocation
         */
        T dimension(Optional<IDimensionID> dimension);

        /**
         * Initializes the optional value {@link Entity#uuidMost() uuidMost} to
         * uuidMost.
         *
         * @param uuidMost The value for uuidMost
         * @return {@code this} builder for chained invocation
         */
        T uuidMost(long uuidMost);

        /**
         * Initializes the optional value {@link Entity#uuidMost() uuidMost} to
         * uuidMost.
         *
         * @param uuidMost The value for uuidMost
         * @return {@code this} builder for use in a chained invocation
         */
        T uuidMost(Optional<Long> uuidMost);

        /**
         * Initializes the optional value {@link Entity#uuidLeast() uuidLeast}
         * to uuidLeast.
         *
         * @param uuidLeast The value for uuidLeast
         * @return {@code this} builder for chained invocation
         */
        T uuidLeast(long uuidLeast);

        /**
         * Initializes the optional value {@link Entity#uuidLeast() uuidLeast}
         * to uuidLeast.
         *
         * @param uuidLeast The value for uuidLeast
         * @return {@code this} builder for use in a chained invocation
         */
        T uuidLeast(Optional<Long> uuidLeast);

        /**
         * Initializes the optional value {@link Entity#uuid() uuid} to uuid.
         *
         * @param uuid The value for uuid
         * @return {@code this} builder for chained invocation
         */
        T uuid(String uuid);

        /**
         * Initializes the optional value {@link Entity#uuid() uuid} to uuid.
         *
         * @param uuid The value for uuid
         * @return {@code this} builder for use in a chained invocation
         */
        T uuid(Optional<String> uuid);

        /**
         * Initializes the optional value {@link Entity#customName() customName}
         * to customName.
         *
         * @param customName The value for customName
         * @return {@code this} builder for chained invocation
         */
        T customName(String customName);

        /**
         * Initializes the optional value {@link Entity#customName() customName}
         * to customName.
         *
         * @param customName The value for customName
         * @return {@code this} builder for use in a chained invocation
         */
        T customName(Optional<String> customName);

        /**
         * Initializes the optional value {@link Entity#riding() riding} to
         * riding.
         *
         * @param riding The value for riding
         * @return {@code this} builder for chained invocation
         */
        T riding(Entity riding);

        /**
         * Initializes the optional value {@link Entity#riding() riding} to
         * riding.
         *
         * @param riding The value for riding
         * @return {@code this} builder for use in a chained invocation
         */
        T riding(Optional<Entity> riding);

        /**
         * Adds one element to {@link Entity#passengers() passengers} list.
         *
         * @param element A passengers element
         * @return {@code this} builder for use in a chained invocation
         */
        T addPassenger(Entity element);

        /**
         * Adds elements to {@link Entity#passengers() passengers} list.
         *
         * @param elements An array of passengers elements
         * @return {@code this} builder for use in a chained invocation
         */
        T addPassenger(Entity... elements);

        /**
         * Sets or replaces all elements for {@link Entity#passengers()
         * passengers} list.
         *
         * @param elements An iterable of passengers elements
         * @return {@code this} builder for use in a chained invocation
         */
        T passengers(Iterable<? extends Entity> elements);

        /**
         * Adds elements to {@link Entity#passengers() passengers} list.
         *
         * @param elements An iterable of passengers elements
         * @return {@code this} builder for use in a chained invocation
         */
        T addAllPassengers(Iterable<? extends Entity> elements);

        /**
         * Adds one element to {@link Entity#tags() tags} list.
         *
         * @param element A tags element
         * @return {@code this} builder for use in a chained invocation
         */
        T addTag(String element);

        /**
         * Adds elements to {@link Entity#tags() tags} list.
         *
         * @param elements An array of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        T addTag(String... elements);

        /**
         * Sets or replaces all elements for {@link Entity#tags() tags} list.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        T tags(Iterable<String> elements);

        /**
         * Adds elements to {@link Entity#tags() tags} list.
         *
         * @param elements An iterable of tags elements
         * @return {@code this} builder for use in a chained invocation
         */
        T addAllTags(Iterable<String> elements);

        /**
         * Initializes the optional value {@link Entity#commandStats()
         * commandStats} to commandStats.
         *
         * @param commandStats The value for commandStats
         * @return {@code this} builder for chained invocation
         */
        T commandStats(CommandStats commandStats);

        /**
         * Initializes the optional value {@link Entity#commandStats()
         * commandStats} to commandStats.
         *
         * @param commandStats The value for commandStats
         * @return {@code this} builder for use in a chained invocation
         */
        T commandStats(Optional<CommandStats> commandStats);


        /**
         * Builds a new {@link Entity Entity}.
         *
         * @return An immutable instance of Entity
         * @throws java.lang.IllegalStateException if any required attributes
         *         are missing
         */
        Entity build();

    }

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Entity#pos() pos} attribute. A shallow reference equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param pos A new value for pos
     * @return A modified copy of the {@code this} object
     */
    Entity withPos(Coordinates pos);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Entity#air() air} attribute. A value equality check is used to
     * prevent copying of the same value by returning {@code this}.
     *
     * @param air A new value for air
     * @return A modified copy of the {@code this} object
     */
    Entity withAir(final short air);

    /**
     * Copy the current immutable object by setting a <i>present</i> value for
     * the optional {@link Entity#commandStats() commandStats} attribute.
     *
     * @param value The value for commandStats
     * @return A modified copy of {@code this} object
     */
    Entity withCommandStats(final CommandStats value);

    /**
     * Copy the current immutable object by setting an optional value for the
     * {@link Entity#commandStats() commandStats} attribute. A shallow reference
     * equality check is used on unboxed optional value to prevent copying of
     * the same value by returning {@code this}.
     *
     * @param optional A value for commandStats
     * @return A modified copy of {@code this} object
     */
    Entity withCommandStats(Optional<CommandStats> optional);

    /**
     * Copy the current immutable object by setting a <i>present</i> value for
     * the optional {@link Entity#customName() customName} attribute.
     *
     * @param value The value for customName
     * @return A modified copy of {@code this} object
     */
    Entity withCustomName(final String value);

    /**
     * Copy the current immutable object by setting an optional value for the
     * {@link Entity#customName() customName} attribute. An equality check is
     * used on inner nullable value to prevent copying of the same value by
     * returning {@code this}.
     *
     * @param optional A value for customName
     * @return A modified copy of {@code this} object
     */
    Entity withCustomName(Optional<String> optional);

    /**
     * Copy the current immutable object by setting a <i>present</i> value for
     * the optional {@link Entity#dimension() dimension} attribute.
     *
     * @param value The value for dimension
     * @return A modified copy of {@code this} object
     */
    Entity withDimension(final IDimensionID value);

    /**
     * Copy the current immutable object by setting an optional value for the
     * {@link Entity#dimension() dimension} attribute. A shallow reference
     * equality check is used on unboxed optional value to prevent copying of
     * the same value by returning {@code this}.
     *
     * @param optional A value for dimension
     * @return A modified copy of {@code this} object
     */
    Entity withDimension(Optional<IDimensionID> optional);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Entity#fallDistance() fallDistance} attribute. A value strict bits
     * equality used to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param fallDistance A new value for fallDistance
     * @return A modified copy of the {@code this} object
     */
    Entity withFallDistance(final float fallDistance);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Entity#fire() fire} attribute. A value equality check is used to
     * prevent copying of the same value by returning {@code this}.
     *
     * @param fire A new value for fire
     * @return A modified copy of the {@code this} object
     */
    Entity withFire(final short fire);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Entity#motion() motion} attribute. A shallow reference equality
     * check is used to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param motion A new value for motion
     * @return A modified copy of the {@code this} object
     */
    Entity withMotion(Motion motion);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Entity#onGround() onGround} attribute. A value equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param onGround A new value for onGround
     * @return A modified copy of the {@code this} object
     */
    Entity withOnGround(final boolean onGround);

    /**
     * Copy the current immutable object with elements that replace the content
     * of {@link Entity#passengers() passengers}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    Entity withPassengers(final Entity... elements);

    /**
     * Copy the current immutable object with elements that replace the content
     * of {@link Entity#passengers() passengers}. A shallow reference equality
     * check is used to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param elements An iterable of passengers elements to set
     * @return A modified copy of {@code this} object
     */
    Entity withPassengers(final Iterable<? extends Entity> elements);

    /**
     * Copy the current immutable object by setting a <i>present</i> value for
     * the optional {@link Entity#riding() riding} attribute.
     *
     * @param value The value for riding
     * @return A modified copy of {@code this} object
     */
    @Deprecated
    Entity withRiding(final Entity value);

    /**
     * Copy the current immutable object by setting an optional value for the
     * {@link Entity#riding() riding} attribute. A shallow reference equality
     * check is used on unboxed optional value to prevent copying of the same
     * value by returning {@code this}.
     *
     * @param optional A value for riding
     * @return A modified copy of {@code this} object
     */
    @Deprecated
    Entity withRiding(Optional<Entity> optional);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Entity#rotation() rotation} attribute. A shallow reference
     * equality check is used to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param rotation A new value for rotation
     * @return A modified copy of the {@code this} object
     */
    Entity withRotation(Rotation rotation);

    /**
     * Copy the current immutable object with elements that replace the content
     * of {@link Entity#tags() tags}.
     *
     * @param elements The elements to set
     * @return A modified copy of {@code this} object
     */
    Entity withTags(final String... elements);

    /**
     * Copy the current immutable object with elements that replace the content
     * of {@link Entity#tags() tags}. A shallow reference equality check is used
     * to prevent copying of the same value by returning {@code this}.
     *
     * @param elements An iterable of tags elements to set
     * @return A modified copy of {@code this} object
     */
    Entity withTags(final Iterable<String> elements);

    /**
     * Copy the current immutable object by setting a <i>present</i> value for
     * the optional {@link Entity#uuid() uuid} attribute.
     *
     * @param value The value for uuid
     * @return A modified copy of {@code this} object
     */
    @Deprecated
    Entity withUuid(final String value);

    /**
     * Copy the current immutable object by setting an optional value for the
     * {@link Entity#uuid() uuid} attribute. An equality check is used on inner
     * nullable value to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param optional A value for uuid
     * @return A modified copy of {@code this} object
     */
    @Deprecated
    Entity withUuid(Optional<String> optional);

    /**
     * Copy the current immutable object by setting a <i>present</i> value for
     * the optional {@link Entity#uuidLeast() uuidLeast} attribute.
     *
     * @param value The value for uuidLeast
     * @return A modified copy of {@code this} object
     */
    Entity withUuidLeast(final long value);

    /**
     * Copy the current immutable object by setting an optional value for the
     * {@link Entity#uuidLeast() uuidLeast} attribute. An equality check is used
     * on inner nullable value to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param optional A value for uuidLeast
     * @return A modified copy of {@code this} object
     */
    Entity withUuidLeast(Optional<Long> optional);

    /**
     * Copy the current immutable object by setting a <i>present</i> value for
     * the optional {@link Entity#uuidMost() uuidMost} attribute.
     *
     * @param value The value for uuidMost
     * @return A modified copy of {@code this} object
     */
    Entity withUuidMost(long value);

    /**
     * Copy the current immutable object by setting an optional value for the
     * {@link Entity#uuidMost() uuidMost} attribute. An equality check is used
     * on inner nullable value to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param optional A value for uuidMost
     * @return A modified copy of {@code this} object
     */
    Entity withUuidMost(Optional<Long> optional);

}
