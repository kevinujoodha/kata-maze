package fr.maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MazeGenerator {
  private int rows;
  private int columns;

  public StringBuffer generateMaze() {
    rows = 7;
    columns = 7;


    Cell[][] grid = new Cell[rows][columns];

    //initialize the maze
    // TODO: To delete
    initializeMaze(grid);

    //compute the maze : BinaryTree algorithm used here
    for (Cell[] gridRow : grid) {
      for (Cell cell : gridRow) {
        List<Cell> potentialLinkedNeighbors = findNeighbors(grid, cell, Direction.NORTH, Direction.EAST);

        Cell neighborCell = null;
        if (potentialLinkedNeighbors.size() > 0) {
          int randomIndex = generateRandomIndex(potentialLinkedNeighbors.size());
          neighborCell = potentialLinkedNeighbors.get(randomIndex);
        }

        // INSERT A LINKED CELL

        if (neighborCell != null) {
          cell.getNeighbors().put(neighborCell, true);
          neighborCell.getNeighbors().put(cell, true);
        }

      }
    }

    //Display the maze
    StringBuffer sb = new StringBuffer();

    sb.append("+");
    for (int colCount = 0; colCount < columns; colCount++) {
      sb.append("---+");
    }
    sb.append("\n");

    for (Cell[] row : grid) {
      StringBuffer top = new StringBuffer();
      top.append("|");
      StringBuffer bottom = new StringBuffer();
      bottom.append("+");

      for (Cell cell : row) {
        cell = (cell == null ? new Cell(-1, -1) : cell);

        boolean islinked = (cell.getNeighbors().get(cell.getEast()) != null && cell.getNeighbors().get(cell.getEast()));

        String eastBoundary = (islinked ? " " : "|");
        top.append("   ").append(eastBoundary);

        islinked = (cell.getNeighbors().get(cell.getSouth()) != null && cell.getNeighbors().get(cell.getSouth()));

        String southBoundary = (islinked ? "   " : "---");
        bottom.append(southBoundary).append("+");
      }

      sb.append(top).append("\n");
      sb.append(bottom).append("\n");
    }
    return sb;
  }


  private List<Cell> findNeighbors(Cell[][] grid, Cell cell, Direction... directions) {
    return Arrays.stream(directions)
            .filter(direction -> neighborIsInGrid(cell, direction))
            .map(direction -> getCellNeighbor(grid, cell, direction))
            .collect(Collectors.toList());
  }

  private boolean neighborIsInGrid(Cell cell, Direction direction) {
    return cell.getRow() + direction.verticalShift >= 0
            && cell.getRow() + direction.verticalShift < rows
            && cell.getColumn() + direction.horizontalShift >= 0
            && cell.getColumn() + direction.horizontalShift < columns;
  }

  private Cell getCellNeighbor(Cell[][] grid, Cell cell, Direction direction) {
    return grid[cell.getRow() + direction.verticalShift][cell.getColumn() + direction.horizontalShift];
  }


  private void initializeMaze(Cell[][] grid) {
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        grid[row][column] = new Cell(row, column);
      }
    }

    for (Cell[] gridRow : grid) {
      for (Cell cell : gridRow) {
        int row = cell.getRow();
        int column = cell.getColumn();

        cell.setNorth(getGridCell(row - 1, column, grid));
        cell.setSouth(getGridCell(row + 1, column, grid));
        cell.setEast(getGridCell(row, column + 1, grid));
      }
    }
  }

  private Cell getGridCell(int row, int column, Cell[][] grid) {
    Cell resultCell = null;
    if ((row >= 0 && row < rows) && (column >= 0 && (column < columns))) {
      resultCell = grid[row][column];
    }
    return resultCell;
  }

  protected int generateRandomIndex(int maxRandom) {
    Random r = new Random();
    return r.ints(1, 0, maxRandom).findFirst().getAsInt();
  }
}