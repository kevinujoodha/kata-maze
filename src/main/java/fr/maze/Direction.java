package fr.maze;

public enum Direction {
  NORTH(-1, 0),
  EAST(0, 1);

  public final int verticalShift;
  public final int horizontalShift;

  Direction(int verticalShift, int horizontalShift) {
    this.verticalShift = verticalShift;
    this.horizontalShift = horizontalShift;
  }
}
