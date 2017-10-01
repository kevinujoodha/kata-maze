package fr.maze;

public class Cell
{
  private int row, column;
  private Cell north;
  private Cell south;
  private Cell east;
  private Cell linkedNeighbor;

  public Cell(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public void setNorth(Cell north) {
    this.north = north;
  }

  public void setSouth(Cell south) {
    this.south = south;
  }

  public void setEast(Cell east) {
    this.east = east;
  }

  public void setLinkedNeighbor(Cell neighborCell) {
    this.linkedNeighbor = neighborCell;
  }

  public boolean isLinkedNeighborOfDirection(Direction direction) {
    if (linkedNeighbor == null)
      return false;

    if (row + direction.verticalShift == linkedNeighbor.row && column + direction.horizontalShift == linkedNeighbor.column)
      return true;

    return false;
  }
}