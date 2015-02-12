package com.tree_bit.com.rcdl.blocks;

/**
 * Method a block orientation enum has to implement.
 *
 * This includes rotating, mirroring and getting the next orientation (cyclic).
 *
 * @author Sascha Sauermann
 */
interface IOrientationEnum {

  /**
   * Rotates the block n times (clockwise). The new orientation is independant of the degree and
   * only dependant of the number of possible fix rotations. Use negative numbers for
   * counterclockwise rotations.
   *
   * @param n <b>int</b> number of rotations
   * @return <b>IOrientation</b> returns the new orientation after the rotations
   */
  public IOrientationEnum rotate(int n);

  /**
   * Mirrors the block at the given axis.
   *
   * @param xAxis <b>boolean</b> mirror at x (true) or z (false) axis.
   * @return <b>IOrientation</b> returns the new orientation after the mirroring
   */
  public IOrientationEnum mirror(boolean xAxis);

  /**
   * Returns the next orientation. This should follow an reasonable order.
   *
   * @param i <b>int</b> index
   *
   * @return <b>IOrientation</b> returns the next orientation.
   */
  public IOrientationEnum next(int i);
}
