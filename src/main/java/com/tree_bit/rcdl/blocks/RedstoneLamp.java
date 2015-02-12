package com.tree_bit.rcdl.blocks;

/**
 * This class holds all Information which is needed to store a Redstone Lamp
 * 
 * @author Alexander
 *
 */
public class RedstoneLamp extends Blocks {

	public RedstoneLamp(Type type) {
		super(type.getMcID(), 0);
	}

	public enum Type {
		RedstoneLampOFF(123), RedstoneLampOn(124);

		private int mcID;

		private Type(int id) {
			mcID = id;
		}

		private int getMcID() {
			return mcID;
		}
	}

}
