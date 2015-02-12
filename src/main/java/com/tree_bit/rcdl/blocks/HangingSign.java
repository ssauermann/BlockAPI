package com.tree_bit.rcdl.blocks;


/**
 * A Sign which is placed on the side of a block (not on Top, if you need to place a sign on top of
 * a block use StandingSign instead)
 *
 * @author Alexander
 * @author Sascha Sauermann
 *
 */
public class HangingSign extends Blocks {

  private Orientation orientation;

  /**
   * Defines which direction the front side of the sign faces
   *
   * @param orientation (North, West etc.)
   */
  public HangingSign(Orientation orientation, String[] text) {
    super(68, orientation.getDataValue());
    if (text.length > 4)
      throw new IllegalArgumentException(Messages.getString("StandingSign.IllegalTextArray")); //$NON-NLS-1$
    this.text = text;
    this.orientation = orientation;
  }

  public enum Orientation implements IDataValueEnum, IOrientationEnum {
    North(2), East(5), South(3), West(4);

    private int value;

    private Orientation(int value) {
      this.value = value;
    }

    @Override
    public Orientation rotate(int n) {
      return next(1);
    }

    @Override
    public Orientation mirror(boolean xAxis) {
      if (xAxis) {
        if (next(0) == South)
          return North;
        else if (next(0) == North)
          return South;
      } else {
        if (next(0) == East)
          return West;
        else if (next(0) == West)
          return East;
      }
      return next(0);
    }

    @Override
    public Orientation next(int i) {
      Orientation temp = values()[(ordinal() + i) % values().length];
      if (temp != null)
        return temp;
      throw new IllegalStateException();
    }

    @Override
    public int getDataValue() {
      return this.value;
    }
  }

  @Override
  public void rotateCount(int n) {
    setOrientation(this.orientation.rotate(n));
  }

  public void setOrientation(Orientation orientation) {
    this.orientation = orientation;
  }

  public Orientation getOrientation() {
    return this.orientation;
  }
}
