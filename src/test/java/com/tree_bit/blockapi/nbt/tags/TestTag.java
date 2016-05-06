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
package com.tree_bit.blockapi.nbt.tags;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableList;
import com.tree_bit.blockapi.internal.Null;

import org.eclipse.jdt.annotation.NonNull;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;


/**
 *
 */
@SuppressWarnings({"javadoc", "null", "static-method"})
public class TestTag {


    private static Map<String, List<org.jnbt.Tag>> tags = new HashMap<>();

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        tags.put("bytearray", ImmutableList.of(new org.jnbt.ByteArrayTag("byte[]", new byte[] {0, 20, -40})));
        tags.put("byte", ImmutableList.of(new org.jnbt.ByteTag("byte", (byte) 14)));
        // Just using plain, primitive types
        tags.put("compound", ImmutableList.of(new org.jnbt.CompoundTag("compound", new HashMap<String, org.jnbt.Tag>() {

            {
                this.put("0", new org.jnbt.IntTag("int", 12));
                this.put("1", new org.jnbt.ByteTag("byte", (byte) -645));
                this.put("2", new org.jnbt.StringTag("string", "20"));
            }
        })));
        tags.put("double", ImmutableList.of(new org.jnbt.DoubleTag("double", 12.02)));
        tags.put("end", ImmutableList.of(new org.jnbt.EndTag()));
        tags.put("float", ImmutableList.of(new org.jnbt.FloatTag("float", 888.1231f)));
        tags.put("intarray", ImmutableList.of(new org.jnbt.IntArrayTag("int[]", new int[] {0, 1231, -1234})));
        tags.put("int", ImmutableList.of(new org.jnbt.IntTag("int", 15)));
        // Just using plain, primitive types
        tags.put("list", ImmutableList.of(new org.jnbt.ListTag("int list", org.jnbt.IntTag.class, new LinkedList<org.jnbt.Tag>() {

            {
                this.add(new org.jnbt.IntTag("int 1", 12));
                this.add(new org.jnbt.IntTag("int 2", -645));
            }
        }), new org.jnbt.ListTag("string list", org.jnbt.StringTag.class, new LinkedList<org.jnbt.Tag>() {

            {
                this.add(new org.jnbt.StringTag("string 1", "foo"));
                this.add(new org.jnbt.StringTag("string 2", "bar"));
            }
        })));
        tags.put("mixedlist", ImmutableList.of(new org.jnbt.ListTag("mixed list", org.jnbt.ByteTag.class, new LinkedList<org.jnbt.Tag>() {

            {
                this.add(new org.jnbt.IntTag("int 1", 25));
                this.add(new org.jnbt.ByteTag("byte 2", (byte) 25));
                this.add(new org.jnbt.StringTag("string 3", "Apple"));
            }
        })));
        tags.put("long", ImmutableList.of(new org.jnbt.LongTag("long", 816318L)));
        tags.put("short", ImmutableList.of((new org.jnbt.ShortTag("short", (short) 2))));
        tags.put("string", ImmutableList.of(new org.jnbt.StringTag("string", "My String")));

    }


    @Test
    public void testUnwrap() {
        assertEquals(new org.jnbt.IntTag("int", 12), IntTag.of("int", 12).unwrap());
        assertEquals(new org.jnbt.IntArrayTag("intarray", new int[] {1, 2, 3}), IntArrayTag.of("intarray", new int[] {1, 2, 3}).unwrap());
        assertEquals(new org.jnbt.ListTag("int list", org.jnbt.IntTag.class, new LinkedList<org.jnbt.Tag>() {

            {
                this.add(new org.jnbt.IntTag("foo", 12));
                this.add(new org.jnbt.IntTag("bar", -151));
            }
        }), ListTag.of("int list", IntTag.class, new LinkedList<IntTag>() {

            {
                this.add(IntTag.of("foo", 12));
                this.add(IntTag.of("bar", -151));
            }
        }).unwrap());

        assertEquals(new org.jnbt.CompoundTag("compound", new HashMap<String, org.jnbt.Tag>() {

            {
                this.put("hello", new org.jnbt.IntTag("foo", 12));
                this.put("world", new org.jnbt.StringTag("bar", "-151"));
            }
        }), CompoundTag.of("compound", new HashMap<String, Tag<?>>() {

            {
                this.put("hello", IntTag.of("foo", 12));
                this.put("world", StringTag.of("bar", "-151"));
            }
        }).unwrap());
    }


    @Test
    public void testWrapClassOfXTag() {

        final ByteTag bt = Tag.wrap(tags.get("byte").get(0), ByteTag.class);
        assertEquals(bt.getName(), tags.get("byte").get(0).getName());
        assertEquals(bt.getValue(), tags.get("byte").get(0).getValue());


        final CompoundTag ct = Tag.wrap(tags.get("compound").get(0), CompoundTag.class);
        assertEquals(ct.getName(), tags.get("compound").get(0).getName());
        final Map<String, org.jnbt.Tag> _tagl = ((org.jnbt.CompoundTag) tags.get("compound").get(0)).getValue();
        final Map<String, ? extends Tag<?>> _wtagl = ct.getValue();

        assertEquals(_tagl.size(), _wtagl.size());
        for (int i = 0; i < _tagl.size(); i++) {
            assertEquals(_tagl.get("" + i).getName(), _wtagl.get("" + i).getName());
            assertEquals(_tagl.get("" + i).getValue(), _wtagl.get("" + i).getValue());
        }

        final org.jnbt.Tag tag = tags.get("list").get(0);

        final ListTag<?> wtag = Tag.wrap(tag, ListTag.class);
        assertEquals(tag.getName(), wtag.getName());
        @SuppressWarnings("unchecked")
        final List<org.jnbt.Tag> tagl = (List<org.jnbt.Tag>) tag.getValue();
        @SuppressWarnings("unchecked")
        final List<IntTag> wtagl = (List<@NonNull IntTag>) wtag.getValue();

        assertEquals(tagl.size(), wtagl.size());

        Class<?> typeOfList = null;
        if (wtagl.size() > 0) {
            typeOfList = wtagl.get(0).getClass();
        }

        for (int i = 0; i < tagl.size(); i++) {
            assertEquals(tagl.get(i).getName(), wtagl.get(i).getName());
            assertEquals(tagl.get(i).getValue(), wtagl.get(i).getValue());
            assertEquals(typeOfList, wtagl.get(i).getClass());
        }



    }

    @Test(expected = ClassCastException.class)
    public void testWrapClassOfXTag_Exception() {
        Tag.wrap(tags.get("byte").get(0), IntTag.class);
    }

    @Test
    public void testWrapTag_Compound() {

        @SuppressWarnings("unchecked")
        final Consumer<org.jnbt.Tag> test = tag -> {
            final Tag<?> wtag = Tag.wrap(tag);
            assertEquals(tag.getName(), wtag.getName());
            final Map<String, org.jnbt.Tag> tagl = (Map<String, org.jnbt.Tag>) tag.getValue();
            final Map<String, Tag<?>> wtagl = (Map<String, Tag<?>>) wtag.getValue();

            assertEquals(tagl.size(), wtagl.size());
            for (int i = 0; i < tagl.size(); i++) {
                assertEquals(tagl.get("" + i).getName(), wtagl.get("" + i).getName());
                assertEquals(tagl.get("" + i).getValue(), wtagl.get("" + i).getValue());
            }

        };


        tags.get("compound").forEach(test);

    }

    @SuppressWarnings("unchecked")
    private final Consumer<org.jnbt.Tag> test = tag -> {
        final Tag<?> wtag = Tag.wrap(tag);
        assertEquals(tag.getName(), wtag.getName());
        final List<org.jnbt.Tag> tagl = (List<org.jnbt.Tag>) tag.getValue();
        final List<Tag<?>> wtagl = (List<Tag<?>>) wtag.getValue();

        assertEquals(tagl.size(), wtagl.size());

        Class<?> typeOfList = null;
        if (wtagl.size() > 0) {
            typeOfList = wtagl.get(0).getClass();
        }

        for (int i = 0; i < tagl.size(); i++) {
            assertEquals(tagl.get(i).getName(), wtagl.get(i).getName());
            assertEquals(tagl.get(i).getValue(), wtagl.get(i).getValue());
            assertEquals(typeOfList, wtagl.get(i).getClass());
        }

    };

    @Test
    public void testWrapTag_List() {

        tags.get("list").forEach(this.test);
    }

    // Should this be an exception?
    @Ignore
    @Test(expected = ClassCastException.class)
    public void testWrapTag_MixedList() {

        tags.get("mixedlist").forEach(this.test);
    }

    @Test
    public void testWrapTag_End() {

        final Consumer<org.jnbt.Tag> test = tag -> {
            final Tag<?> wtag = Tag.wrap(tag);
            assertEquals(tag.getName(), wtag.getName());
            assertEquals(Null._null(), wtag.getValue());
        };


        tags.get("end").forEach(test);

    }

    @Test
    public void testWrapTag_Array() {

        final Consumer<org.jnbt.Tag> testByte = tag -> {
            final Tag<?> wtag = Tag.wrap(tag);
            assertEquals(tag.getName(), wtag.getName());
            assertArrayEquals((byte[]) tag.getValue(), (byte[]) wtag.getValue());
        };
        final Consumer<org.jnbt.Tag> testInt = tag -> {
            final Tag<?> wtag = Tag.wrap(tag);
            assertEquals(tag.getName(), wtag.getName());
            assertArrayEquals((int[]) tag.getValue(), (int[]) wtag.getValue());
        };


        tags.get("bytearray").forEach(testByte);
        tags.get("intarray").forEach(testInt);
    }

    @Test
    public void testWrapTag_Primitives() {

        final Consumer<org.jnbt.Tag> test = tag -> {
            final Tag<?> wtag = Tag.wrap(tag);
            assertEquals(tag.getName(), wtag.getName());
            assertEquals(tag.getValue(), wtag.getValue());

        };


        tags.get("string").forEach(test);
        tags.get("byte").forEach(test);
        tags.get("int").forEach(test);
        tags.get("long").forEach(test);
        tags.get("short").forEach(test);
        tags.get("float").forEach(test);
        tags.get("double").forEach(test);
    }

}
