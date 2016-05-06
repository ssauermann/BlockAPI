package com.tree_bit.blockapi.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.tree_bit.blockapi.nbt.NBT;
import com.tree_bit.blockapi.nbt.tags.CompoundTag;
import com.tree_bit.blockapi.nbt.tags.IntTag;
import com.tree_bit.blockapi.nbt.tags.StringTag;
import com.tree_bit.blockapi.nbt.tags.Tag;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings({"javadoc"})
public class TestTileEntity {


    private static Tag<?> string = StringTag.of("Text", "Hello World");
    private static Tag<?> stringInteger = IntTag.of("Text", 42);
    private static Tag<?> integer = IntTag.of("Number", 42);

    private CompoundTag t = GenericTileEntity.builder("empty").build();
    private CompoundTag t2 = GenericTileEntity.builder("empty").build();

    @Before
    public void setUp() throws Exception {
        this.t = GenericTileEntity.builder("Test").add(string).add(integer).build();
        this.t2 = GenericTileEntity.builder("Test").add(stringInteger).add(string).add(integer).build();
    }

    @Test
    public void testTileEntity() {
        assertEquals(this.t, new GenericTileEntity(NBT.Compound("Test").add(string).add(integer)));
    }

    @SuppressWarnings("static-method")
    @Test
    public void testBuilder() {
        assertEquals(NBT.Compound("Test"), GenericTileEntity.builder("Test"));
    }

    @Test
    public void testGetTags() {
        assertTrue(this.t2.getValue().values().contains(string));
        assertTrue(this.t2.getValue().values().contains(integer));
        assertFalse(this.t2.getValue().values().contains(stringInteger));
    }
}
