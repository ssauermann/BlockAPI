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
package com.tree_bit.blockapi.entities.projectile;

import com.tree_bit.blockapi.Coordinates;
import com.tree_bit.blockapi.data.IDataValue;
import com.tree_bit.blockapi.entities.BooleanHelper;
import com.tree_bit.blockapi.nbt.NBTCompound;
import com.tree_bit.blockapi.nbt.NBTCompound.Tags;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import java.util.Optional;
import java.util.function.Function;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Representing an arrow entity.
 */
// TODO Spectral Arrow
@Immutable
public abstract class Arrow implements Projectile {

    @Override
    public String id() {
        return "Arrow";
    }

    @Override
    @Parameter(order = 0)
    public abstract Coordinates pos();

    @Override
    @Parameter
    public abstract short xTile();


    @Override
    @Parameter
    public abstract short yTile();


    @Override
    @Parameter
    public abstract short zTile();

    /**
     * The "shake" when arrows hit a block.
     *
     * @return Shake
     */
    @Default
    @NBTCompound(key = "shake", tag = Tags.Byte)
    @SuppressWarnings("static-method")
    public byte shake() {
        return 0;
    }

    /**
     * Metadata of tile arrow is in.
     *
     * @return Metadata
     */
    @Default
    // TODO check if this works
    @NBTCompound(key = "inData", tag = Tags.Byte, converter = IDataValue.ToInt.class)
    @SuppressWarnings("static-method")
    public IDataValue inData() {
        return IDataValue.none();
    }

    /**
     * 0 = cannot be picked up by players. 1 = can be picked up by players in
     * survival or creative. 2 = can only be picked up by players in creative.
     *
     * @return Pickup
     */
    @NBTCompound(key = "pickup", tag = Tags.Byte, converter = Pickup.ToByte.class)
    public abstract Optional<Pickup> pickup();

    /**
     * 1 or 0 (true/false) - If pickup is not used, and this is true, the arrow
     * can be picked up by players.
     *
     * @return Player pickup
     */
    @Default
    @NBTCompound(key = "player", tag = Tags.Byte, converter = BooleanHelper.ToByte.class)
    @SuppressWarnings("static-method")
    public boolean player() {
        return true;
    }

    /**
     * Increments each tick when an arrow is not moving; resets to 0 if it
     * moves. When it ticks to 1200, the arrow despawns.
     *
     * @return Life
     */
    @Default
    @NBTCompound(key = "life", tag = Tags.Short)
    @SuppressWarnings("static-method")
    public short life() {
        return 0;
    }

    /**
     * Damage dealt by the arrow, in half-hearts. May not be a whole number. 2.0
     * for normal arrows, and increased 0.5 per level of Power enchantment on
     * the firing bow. If the Power enchantment is present, an additional 0.5 is
     * added on (so Power I gives a bonus of 1.0, while Power II gives 1.5).
     *
     * @return Damage
     */
    @Default
    @NBTCompound(key = "damage", tag = Tags.Double)
    @SuppressWarnings("static-method")
    public double damage() {
        return 2.0;
    }

    /**
     * 1 or 0 (true/false) - If the Projectile is in the ground or hit the
     * ground already (For arrow pickup; you cannot pickup arrows in the air)
     *
     * @return In Ground
     */
    @Default
    @NBTCompound(key = "inGround", tag = Tags.Byte, converter = BooleanHelper.ToByte.class)
    @SuppressWarnings("static-method")
    public boolean inGround() {
        return false;
    }

    /**
     * Construct a new immutable {@code Arrow} instance.
     * 
     * @param pos The value for the {@code pos} attribute
     * @param xTile The value for the {@code xTile} attribute
     * @param yTile The value for the {@code yTile} attribute
     * @param zTile The value for the {@code zTile} attribute
     * @return An immutable Arrow instance
     */
    public static Arrow of(final Coordinates pos, final short xTile, final short yTile, final short zTile) {
        return ImmutableArrow.of(pos, xTile, yTile, zTile);
    }

    /**
     * Defines pickup behavior for an arrow.
     */
    public enum Pickup {
        /** Cannot be picked up by players. */
        DISABLED(0),
        /** Can be picked up by players in survival or creative. */
        ACTIVE(1),
        /** Can be picked up by players in creative only. */
        CREATIVE(2);

        private Pickup(final int id) {
            this.id = (byte) id;
        }

        private final byte id;

        private static class ToByte implements Function<Pickup, Byte> {

            @Override
            public Byte apply(final Pickup t) {
                return t.id;
            }

        }
    }

    /**
     * Builds instances of type {@link Arrow Arrow}. Initialize attributes and
     * then invoke the {@link #build()} method to create an immutable instance.
     * <p>
     * <em>{@code Builder} is not thread-safe and generally should not be stored
     * in a field or collection, but instead used immediately to create
     * instances.</em>
     */
    @NotThreadSafe
    public interface Builder extends Projectile.Builder<Builder> {

        /**
         * Fill a builder with attribute values from the provided {@code Arrow}
         * instance. Regular attribute values will be replaced with those from
         * the given instance. Absent optional values will not replace present
         * values.
         *
         * @param instance The instance from which to copy values
         * @return {@code this} builder for use in a chained invocation
         */
        Builder from(Arrow instance);

        /**
         * Initializes the value for the {@link Arrow#pos() pos} attribute.
         *
         * @param pos The value for pos
         * @return {@code this} builder for use in a chained invocation
         */
        @Override
        Builder pos(Coordinates pos);

        /**
         * Initializes the value for the {@link Arrow#shake() shake} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Arrow#shake() shake}.</em>
         *
         * @param shake The value for shake
         * @return {@code this} builder for use in a chained invocation
         */
        Builder shake(byte shake);

        /**
         * Initializes the value for the {@link Arrow#inData() inData}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Arrow#inData() inData}.</em>
         *
         * @param inData The value for inData
         * @return {@code this} builder for use in a chained invocation
         */
        Builder inData(IDataValue inData);

        /**
         * Initializes the optional value {@link Arrow#pickup() pickup} to
         * pickup.
         *
         * @param pickup The value for pickup
         * @return {@code this} builder for chained invocation
         */
        Builder pickup(Arrow.Pickup pickup);

        /**
         * Initializes the optional value {@link Arrow#pickup() pickup} to
         * pickup.
         *
         * @param pickup The value for pickup
         * @return {@code this} builder for use in a chained invocation
         */
        Builder pickup(Optional<Arrow.Pickup> pickup);

        /**
         * Initializes the value for the {@link Arrow#player() player}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Arrow#player() player}.</em>
         *
         * @param player The value for player
         * @return {@code this} builder for use in a chained invocation
         */
        Builder player(boolean player);

        /**
         * Initializes the value for the {@link Arrow#life() life} attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Arrow#life() life}.</em>
         *
         * @param life The value for life
         * @return {@code this} builder for use in a chained invocation
         */
        Builder life(short life);

        /**
         * Initializes the value for the {@link Arrow#damage() damage}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Arrow#damage() damage}.</em>
         *
         * @param damage The value for damage
         * @return {@code this} builder for use in a chained invocation
         */
        Builder damage(double damage);

        /**
         * Initializes the value for the {@link Arrow#inGround() inGround}
         * attribute.
         * <p>
         * <em>If not set, this attribute will have a default value as returned
         * by the initializer of {@link Arrow#inGround() inGround}.</em>
         *
         * @param inGround The value for inGround
         * @return {@code this} builder for use in a chained invocation
         */
        Builder inGround(boolean inGround);

        /**
         * Builds a new {@link Arrow Arrow}.
         *
         * @return An immutable instance of Arrow
         * @throws java.lang.IllegalStateException if any required attributes
         *         are missing
         */
        @Override
        Arrow build();
    }

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Arrow#pos() pos} attribute. A shallow reference equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param pos A new value for pos
     * @return A modified copy of the {@code this} object
     */
    @Override
    public abstract Arrow withPos(Coordinates pos);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Arrow#shake() shake} attribute. A value equality check is used to
     * prevent copying of the same value by returning {@code this}.
     *
     * @param shake A new value for shake
     * @return A modified copy of the {@code this} object
     */

    public abstract Arrow withShake(byte shake);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Arrow#inData() inData} attribute. A shallow reference equality
     * check is used to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param inData A new value for inData
     * @return A modified copy of the {@code this} object
     */

    public abstract Arrow withInData(IDataValue inData);

    /**
     * Copy the current immutable object by setting a <i>present</i> value for
     * the optional {@link Arrow#pickup() pickup} attribute.
     *
     * @param value The value for pickup
     * @return A modified copy of {@code this} object
     */

    public abstract Arrow withPickup(Arrow.Pickup value);

    /**
     * Copy the current immutable object by setting an optional value for the
     * {@link Arrow#pickup() pickup} attribute. An equality check is used on
     * inner nullable value to prevent copying of the same value by returning
     * {@code this}.
     *
     * @param optional A value for pickup
     * @return A modified copy of {@code this} object
     */

    public abstract Arrow withPickup(Optional<Arrow.Pickup> optional);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Arrow#player() player} attribute. A value equality check is used
     * to prevent copying of the same value by returning {@code this}.
     *
     * @param player A new value for player
     * @return A modified copy of the {@code this} object
     */

    public abstract Arrow withPlayer(boolean player);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Arrow#life() life} attribute. A value equality check is used to
     * prevent copying of the same value by returning {@code this}.
     *
     * @param life A new value for life
     * @return A modified copy of the {@code this} object
     */

    public abstract Arrow withLife(short life);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Arrow#damage() damage} attribute. A value strict bits equality
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param damage A new value for damage
     * @return A modified copy of the {@code this} object
     */

    public abstract Arrow withDamage(double damage);

    /**
     * Copy the current immutable object by setting a value for the
     * {@link Arrow#inGround() inGround} attribute. A value equality check is
     * used to prevent copying of the same value by returning {@code this}.
     *
     * @param inGround A new value for inGround
     * @return A modified copy of the {@code this} object
     */

    public abstract Arrow withInGround(boolean inGround);


}
