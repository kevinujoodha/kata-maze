package fr.maze.domain.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeGeneratorLegacy {
  private int rows;
  private int columns;

  public StringBuffer generateMaze() {
    rows = 7;
    columns = 7;

    CellLegacy[][] grid = new CellLegacy[rows][columns];

    //initialize the maze
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        grid[row][column] = new CellLegacy(row, column);
      }
    }

    for (CellLegacy[] gridRow : grid) {
      for (CellLegacy cell : gridRow) {
        int row = cell.getRow();
        int column = cell.getColumn();

        cell.setNorth(getGridCell(row - 1, column, grid, rows, columns));
        cell.setSouth(getGridCell(row + 1, column, grid, rows, columns));
        cell.setWest(getGridCell(row, column - 1, grid, rows, columns));
        cell.setEast(getGridCell(row, column + 1, grid, rows, columns));
      }
    }

    //compute the maze : BinaryTree algorithm used here
    for (CellLegacy[] gridRow : grid) {
      for (CellLegacy cell : gridRow) {
        List<CellLegacy> neighbors = new ArrayList<CellLegacy>();
        if (cell.getNorth() != null) {
          neighbors.add(cell.getNorth());
        }
        if (cell.getEast() != null) {
          neighbors.add(cell.getEast());
        }

        CellLegacy neighborCell = null;
        if (neighbors.size() > 0) {
          int randomIndex = generateRandomIndex(neighbors);
          neighborCell = neighbors.get(randomIndex);
        }

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

    for (CellLegacy[] row : grid) {
      StringBuffer top = new StringBuffer();
      top.append("|");
      StringBuffer bottom = new StringBuffer();
      bottom.append("+");

      for (CellLegacy cell : row) {
        cell = (cell == null ? new CellLegacy(-1, -1) : cell);

        boolean islinked = (cell.getNeighbors().get(cell.getEast()) != null ?
                cell.getNeighbors().get(cell.getEast()).booleanValue() : false);

        String eastBoundary = (islinked ? " " : "|");
        top.append("   ").append(eastBoundary);

        islinked = (cell.getNeighbors().get(cell.getSouth()) != null ?
                cell.getNeighbors().get(cell.getSouth()).booleanValue() : false);

        String southBoundary = (islinked ? "   " : "---");
        bottom.append(southBoundary).append("+");
      }

      sb.append(top).append("\n");
      sb.append(bottom).append("\n");
    }
    return sb;
  }

  protected int generateRandomIndex(List<CellLegacy> neighbors) {
    Random r = new Random();
    return r.ints(1, 0, neighbors.size()).findFirst().getAsInt();
  }

  private static CellLegacy getGridCell(int row, int column, CellLegacy[][] grid, int rows, int columns) {
    CellLegacy resultCell = null;
    if ((row >= 0 && row < rows) &&
            (column >= 0 && (column < columns))) {
      resultCell = grid[row][column];
    }
    return resultCell;
  }
}
