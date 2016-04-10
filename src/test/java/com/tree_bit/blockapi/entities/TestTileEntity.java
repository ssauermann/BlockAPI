package com.tree_bit.blockapi.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.tree_bit.blockapi.entities.TileEntity;
import com.tree_bit.blockapi.entities.TileEntity.Builder;

import org.jnbt.IntTag;
import org.jnbt.StringTag;
import org.jnbt.Tag;
import org.junit.Before;
import org.junit.Test;


@SuppressWarnings({"javadoc"})
public class TestTileEntity {


    private static Tag string = new StringTag("Text", "Hello World");
    private static Tag stringInteger = new IntTag("Text", 42);
    private static Tag integer = new IntTag("Number", 42);

    private TileEntity t = TileEntity.builder("empty").build();
    private TileEntity t2 = TileEntity.builder("empty").build();

    @Before
    public void setUp() throws Exception {
        this.t = TileEntity.builder("Test").add(string).add(integer).build();
        this.t2 = TileEntity.builder("Test").add(stringInteger).add(string).add(integer).build();
    }

    @Test
    public void testTileEntity() {
        assertEquals(this.t, new TileEntity(new Builder("Test").add(string).add(integer)));
    }

    @SuppressWarnings("static-method")
    @Test
    public void testBuilder() {
        assertEquals(new TileEntity.Builder("Test"), TileEntity.builder("Test"));
    }

    @Test
    public void testGetTags() {
        assertTrue(this.t2.getTags().contains(string));
        assertTrue(this.t2.getTags().contains(integer));
        assertFalse(this.t2.getTags().contains(stringInteger));
    }
}
