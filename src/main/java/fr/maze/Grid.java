package fr.maze;

import java.util.Arrays;
import java.util.List;
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

  protected void computeMaze(Cell[][] grid) {
    this.grid = grid;

    Arrays.stream(grid)
            .flatMap(Arrays::stream)
            .forEach(cell -> {
              List<Cell> potentialLinkedNeighbors = findNeighbors(grid, cell, Direction.NORTH, Direction.EAST);
              if (potentialLinkedNeighbors.size() > 0) {
                int randomIndex = Grid.generateRandomIndex(potentialLinkedNeighbors.size());

                Cell neighborCell = potentialLinkedNeighbors.get(randomIndex);
                cell.setLinkedNeighbor(neighborCell);
                neighborCell.setLinkedNeighbor(cell);
              }
            });
  }

  public List<Cell> findNeighbors(Cell[][] grid, Cell cell, Direction... directions) {
    return Arrays.stream(directions)
            .filter(direction -> neighborIsInGrid(cell, direction))
            .map(direction -> getCellNeighbor(cell, direction))
            .collect(Collectors.toList());
  }

  public boolean neighborIsInGrid(Cell cell, Direction direction) {
    return cell.getRow() + direction.verticalShift >= 0
            && cell.getRow() + direction.verticalShift < rows
            && cell.getColumn() + direction.horizontalShift >= 0
            && cell.getColumn() + direction.horizontalShift < columns;
  }

  public Cell getCellNeighbor(Cell cell, Direction direction) {
    return grid[cell.getRow() + direction.verticalShift][cell.getColumn() + direction.horizontalShift];
  }

  public static int generateRandomIndex(int maxRandom) {
//    Random r = new Random();
//    return r.ints(1, 0, maxRandom).findFirst().getAsInt();
    return maxRandom - 1;
  }
}