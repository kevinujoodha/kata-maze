package fr.maze.domain;

class Cell {
  private int row, column;
  private Cell linkedNeighbor;

  Cell(int row, int column) {
    this.row = row;
    this.column = column;
  }

  int getRow() {
    return row;
  }

  int getColumn() {
    return column;
  }

  void setLinkedNeighbor(Cell neighborCell) {
    this.linkedNeighbor = neighborCell;
  }

  boolean isLinkedNeighborOfDirection(Direction direction) {
    return linkedNeighbor != null && row + direction.verticalShift == linkedNeighbor.row && column + direction.horizontalShift == linkedNeighbor.column;
  }
}