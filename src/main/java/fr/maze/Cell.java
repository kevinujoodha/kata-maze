package fr.maze;

public class Cell
{
  private int row, column;
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