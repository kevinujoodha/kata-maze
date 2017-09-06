package fr.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
  private int rows;
  private int columns;
  private Cell[][] grid;

  public MazeGenerator(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    this.grid = new Cell[rows][columns];
  }

  public StringBuffer generateMaze() {
    initializeMaze();


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


  protected void initializeMaze() {
    //initialize the maze
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        grid[row][column] = new Cell(row, column);
      }
    }

    setGridCellsNeighbours();
  }

  private void setGridCellsNeighbours() {
    for (Cell[] gridRow : grid) {
      for (Cell cell : gridRow) {
        int currentCellRow = cell.getRow();
        int currentCellColumn = cell.getColumn();

        cell.setNorth(getGridCell(currentCellRow - 1, currentCellColumn, grid));
        cell.setSouth(getGridCell(currentCellRow + 1, currentCellColumn, grid));
        cell.setWest(getGridCell(currentCellRow, currentCellColumn - 1, grid));
        cell.setEast(getGridCell(currentCellRow, currentCellColumn + 1, grid));
      }
    }
  }

  private Cell getGridCell(int row, int column, Cell[][] grid) {
    return isInGrid(row, column) ? grid[row][column] : null;
  }

  private boolean isInGrid(int row, int column) {
    return (row >= 0 && row < this.rows) && (column >= 0 && (column < this.columns));
  }


  protected int generateRandomIndex(List<Cell> neighbors) {
    Random r = new Random();
    return r.ints(1, 0, neighbors.size()).findFirst().getAsInt();
  }
}