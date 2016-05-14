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

import static org.junit.Assert.assertEquals;

import com.tree_bit.blockapi.Coordinates;
import com.tree_bit.blockapi.id.minecraft.BlockID;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;
import com.tree_bit.blockapi.nbt.tags.ListTag;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 */
public class TestProjectile {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testCompound() {
        final Projectile p = Projectile.of(Coordinates.of(5, 9, 3), (short) 1, (short) 2, (short) 3, BlockID.STONE.getAlphabeticalID());
        final CompoundTag c = p.compound();

        System.out.println(c);

        assertEquals((short) 1, c.getValue().get("xTile").getValue());
        assertEquals((short) 2, c.getValue().get("yTile").getValue());
        assertEquals((short) 3, c.getValue().get("zTile").getValue());
        assertEquals(BlockID.STONE.getAlphabeticalID(), c.getValue().get("inTile").getValue());
        final ListTag<?> coordinates = (ListTag<?>) c.getValue().get("pos");
        assertEquals(5., coordinates.getValue().get(0).getValue());
        assertEquals(9., coordinates.getValue().get(1).getValue());
        assertEquals(3., coordinates.getValue().get(2).getValue());

    }

}
