package fr.maze;

import java.util.HashMap;

public class Cell
{
  private int row, column;
  private HashMap<Cell, Boolean> neighbors = new HashMap<Cell, Boolean>();
  private Cell north, south, east, west;

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

  public HashMap<Cell, Boolean> getNeighbors() {
    return neighbors;
  }

  public void setNeighbors(HashMap<Cell, Boolean> neighbors) {
    this.neighbors = neighbors;
  }

  public Cell getNorth() {
    return north;
  }

  public void setNorth(Cell north) {
    this.north = north;
  }

  public Cell getSouth() {
    return south;
  }

  public void setSouth(Cell south) {
    this.south = south;
  }

  public Cell getEast() {
    return east;
  }

  public void setEast(Cell east) {
    this.east = east;
  }

  public Cell getWest() {
    return west;
  }

  public void setWest(Cell west) {
    this.west = west;
  }
}