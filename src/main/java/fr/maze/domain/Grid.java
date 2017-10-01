package fr.maze.domain;

import fr.maze.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Grid {
  private int rows;
  private int columns;
  private Cell[][] grid;

  Grid(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;

    grid = new Cell[rows][columns];
    IntStream.range(0, rows)
            .forEach(rowIndex -> {
              IntStream.range(0, columns)
                      .forEach(columnIndex -> grid[rowIndex][columnIndex] = new Cell(rowIndex, columnIndex));
            });
  }

  Cell[][] getGrid() {
    return grid;
  }

  int getColumnsNumber() {
    return columns;
  }

  void computeMaze() {
    Arrays.stream(grid)
            .flatMap(Arrays::stream)
            .forEach(cell -> {
              List<Cell> potentialLinkedNeighbors = findNeighbors(cell, Direction.NORTH, Direction.EAST);
              if (potentialLinkedNeighbors.size() > 0) {
                Cell neighborCell = potentialLinkedNeighbors.get(Utils.generateRandomIndex(potentialLinkedNeighbors.size()));
                cell.setLinkedNeighbor(neighborCell);
                neighborCell.setLinkedNeighbor(cell);
              }
            });
  }

  private List<Cell> findNeighbors(Cell cell, Direction... directions) {
    return Arrays.stream(directions)
            .filter(direction -> neighborIsInGrid(cell, direction))
            .map(direction -> getCellNeighbor(cell, direction))
            .collect(Collectors.toList());
  }

  private boolean neighborIsInGrid(Cell cell, Direction direction) {
    return cell.getRow() + direction.verticalShift >= 0
            && cell.getRow() + direction.verticalShift < rows
            && cell.getColumn() + direction.horizontalShift >= 0
            && cell.getColumn() + direction.horizontalShift < columns;
  }

  private Cell getCellNeighbor(Cell cell, Direction direction) {
    return grid[cell.getRow() + direction.verticalShift][cell.getColumn() + direction.horizontalShift];
  }
}