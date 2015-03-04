package com.tree_bit.rcdl.blocks.entities;

import static org.junit.Assert.assertEquals;

import com.tree_bit.rcdl.blocks.entities.FormatText.Format;
import com.tree_bit.rcdl.blocks.entities.FormatText.FormatString;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("javadoc")
public class TestFormatText {

    @SuppressWarnings("static-method")
    @Test
    public void testFormatTextListOfFormatString() {
        final List<FormatString> list = new ArrayList<>();
        final FormatString fString = new FormatString("Test", Format.BLACK);
        list.add(fString);
        assertEquals(FormatText.builder().append(fString).build(), new FormatText(list));
    }

    @SuppressWarnings("static-method")
    @Test
    public void testFormatTextString() {
        assertEquals(FormatText.builder().append("Test").build(), new FormatText("Test"));
    }

    @SuppressWarnings("static-method")
    @Test
    public void testBuilder() {
        assertEquals(FormatText.builder().append(new FormatString("Test", FormatText.Format.BOLD)).append("Test2").build(), FormatText.builder()
                .append(new FormatString("Test", FormatText.Format.BOLD)).append(new FormatString("Test2")).build());
    }

    @SuppressWarnings("static-method")
    @Test
    public void testGetStringWithCodes() {
        final FormatText txt = FormatText.builder().append(new FormatString("Test", FormatText.Format.BOLD)).build();
        assertEquals(txt.getStringWithCodes(), "§lTest");
    }

}
