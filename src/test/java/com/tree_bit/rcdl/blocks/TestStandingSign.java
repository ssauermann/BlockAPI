package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertTrue;

import com.tree_bit.blockapi.entities.SignEntity;
import com.tree_bit.rcdl.blocks.dv.Orientation16;

import org.junit.Test;


@SuppressWarnings("javadoc")
public class TestStandingSign {


    StandingSign someInstance = StandingSign.getInstance();
    StandingSign fixedInstance = StandingSign.getInstance(Orientation16.N);
    StandingSign entityInstance = StandingSign.getInstance(Orientation16.N, SignEntity.empty());
    StandingSign entityInstanceT = StandingSign.getInstance(Orientation16.N, new SignEntity(new String[] {"Test"}));

    @Test
    public void testGetInstance() {
        assertTrue(this.someInstance == StandingSign.getInstance());
        assertTrue((this.someInstance == this.fixedInstance) && (this.fixedInstance == this.entityInstance));
    }

    @Test
    public void testGetInstanceSignOrientation() {
        assertTrue(this.fixedInstance == StandingSign.getInstance(Orientation16.N));
    }

    @Test
    public void testGetInstanceSignOrientationSignEntity() {
        assertTrue(this.entityInstanceT == StandingSign.getInstance(Orientation16.N, new SignEntity(new String[] {"Test"})));
    }

}
