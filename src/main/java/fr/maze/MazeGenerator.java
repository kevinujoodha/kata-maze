package fr.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
  private int rows;
  private int columns;
  private RGrid rgrid;

  public StringBuffer generateMaze() {
    rows = 7;
    columns = 7;
    rgrid = new RGrid();

    Cell[][] grid = new Cell[rows][columns];

    //initialize the maze
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        grid[row][column] = new Cell(row, column);

        rgrid.addCell(new RCell(row, column));
      }
    }

    // THIS IS USELESS
    setCellsNeighbors(grid);

    //compute the maze : BinaryTree algorithm used here
    for (Cell[] gridRow : grid) {
      for (Cell cell : gridRow) {
        List<Cell> neighbors = new ArrayList<Cell>();
        if (cell.getNorth() != null) {
          neighbors.add(cell.getNorth());
        }
        if (cell.getEast() != null) {
          neighbors.add(cell.getEast());
        }

        Cell neighborCell = null;
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

  protected void setCellsNeighbors(Cell[][] grid) {
    for (Cell[] gridRow : grid) {
      for (Cell cell : gridRow) {
        int row = cell.getRow();
        int column = cell.getColumn();

        cell.setNorth(getGridCell(row - 1, column, grid, rows, columns));
        cell.setSouth(getGridCell(row + 1, column, grid, rows, columns));
        cell.setWest(getGridCell(row, column - 1, grid, rows, columns));
        cell.setEast(getGridCell(row, column + 1, grid, rows, columns));
      }
    }
  }

  protected int generateRandomIndex(List<Cell> neighbors) {
    Random r = new Random();
    return r.ints(1, 0, neighbors.size()).findFirst().getAsInt();
  }

  private static Cell getGridCell(int row, int column, Cell[][] grid, int rows, int columns) {
    Cell resultCell = null;
    if ((row >= 0 && row < rows) &&
            (column >= 0 && (column < columns))) {
      resultCell = grid[row][column];
    }
    return resultCell;
  }
}