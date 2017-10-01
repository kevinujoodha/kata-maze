package fr.maze.domain.legacy;

import java.util.HashMap;

public class CellLegacy {
  private int row, column;
  private HashMap<CellLegacy, Boolean> neighbors = new HashMap<CellLegacy, Boolean>();
  private CellLegacy north, south, east, west;

  public CellLegacy(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public HashMap<CellLegacy, Boolean> getNeighbors() {
    return neighbors;
  }

  public void setNeighbors(HashMap<CellLegacy, Boolean> neighbors) {
    this.neighbors = neighbors;
  }

  public CellLegacy getNorth() {
    return north;
  }

  public void setNorth(CellLegacy north) {
    this.north = north;
  }

  public CellLegacy getSouth() {
    return south;
  }

  public void setSouth(CellLegacy south) {
    this.south = south;
  }

  public CellLegacy getEast() {
    return east;
  }

  public void setEast(CellLegacy east) {
    this.east = east;
  }

  public CellLegacy getWest() {
    return west;
  }

  public void setWest(CellLegacy west) {
    this.west = west;
  }
}
