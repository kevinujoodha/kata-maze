package fr.maze;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {
  private int rows;
  private int columns;
  private Cell[][] grid;

  public Grid(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;

    grid = new Cell[rows][columns];
    IntStream.range(0, rows)
            .forEach(rowIndex -> {
              IntStream.range(0, columns)
                      .forEach(columnIndex -> grid[rowIndex][columnIndex] = new Cell(rowIndex, columnIndex));
            });
  }

  public Cell[][] getGrid() {
    return grid;
  }


  public boolean neighborIsInGrid(Cell cell, Direction direction) {
    return cell.getRow() + direction.verticalShift >= 0
            && cell.getRow() + direction.verticalShift < rows
            && cell.getColumn() + direction.horizontalShift >= 0
            && cell.getColumn() + direction.horizontalShift < columns;
  }

  public Cell getCellNeighbor(Cell[][] grid, Cell cell, Direction direction) {
    return grid[cell.getRow() + direction.verticalShift][cell.getColumn() + direction.horizontalShift];
  }

  protected int generateRandomIndex(int maxRandom) {
    Random r = new Random();
    return r.ints(1, 0, maxRandom).findFirst().getAsInt();
  }
}