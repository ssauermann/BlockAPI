package com.tree_bit.rcdl.blocks;

/**
 * Everything ready to generate colorful wool
 * 
 * @author Alexander
 *
 */
public class Wool extends Blocks {

	public Wool(WoolColor color) {
		super(35, color.getColorvalue());
	}

	public enum WoolColor {
		White(0), Orange(1), Magenta(2), LightBlue(3), Yellow(4), Lime(5), Pink(6), Gray(7), LightGray(
				8), Cyan(9), Purple(10), Blue(11), Brown(12), Green(13), Red(14), Black(15);

		private int value;

		private WoolColor(int value) {
			this.value = value;
		}

		private int getColorvalue() {
			return value;
		}
	}

}
