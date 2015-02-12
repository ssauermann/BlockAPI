package com.tree_bit.rcdl.blocks;

/**
 * An Object of this class contains all Information about Redstone
 *
 * @author Alexander
 *
 */
public class Redstone extends Blocks {

  public Redstone(int power) {
    super(55, power);
  }

  /**
   * Standard Constructor which sets the power to 0
   */
  public Redstone() {
    super(55, 0);
  }

}
