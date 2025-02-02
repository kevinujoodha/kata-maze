package fr.maze.original.models;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private final int row;
    private final int column;
    private final Set<Cell> neighbors = new HashSet<>();
    private Cell north, south, east, west;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void addNeighbors(Cell cell) {
        neighbors.add(cell);
    }

    public boolean isNeighbor(Cell cell) {
        return neighbors.contains(cell);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
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
