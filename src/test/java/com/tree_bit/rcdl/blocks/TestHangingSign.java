package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertTrue;

import com.tree_bit.blockapi.entities.SignEntity;
import com.tree_bit.rcdl.blocks.dv.SignOrientation;

import org.eclipse.jdt.annotation.NonNull;
import org.junit.Test;


@SuppressWarnings("javadoc")
public class TestHangingSign {


    HangingSign someInstance = HangingSign.getInstance();
    HangingSign fixedInstance = HangingSign.getInstance(SignOrientation.North);
    HangingSign entityInstance = HangingSign.getInstance(SignOrientation.North, SignEntity.empty());
    HangingSign entityInstanceT = HangingSign.getInstance(SignOrientation.North, new SignEntity(new String @NonNull [] {"Test"}));

    @Test
    public void testGetInstance() {
        assertTrue(this.someInstance == HangingSign.getInstance());
        assertTrue((this.someInstance == this.fixedInstance) && (this.fixedInstance == this.entityInstance));
    }

    @Test
    public void testGetInstanceSignOrientation() {
        assertTrue(this.fixedInstance == HangingSign.getInstance(SignOrientation.North));
    }

    @Test
    public void testGetInstanceSignOrientationSignEntity() {
        assertTrue(this.entityInstanceT == HangingSign.getInstance(SignOrientation.North, new SignEntity(new String[] {"Test"})));
    }

}
