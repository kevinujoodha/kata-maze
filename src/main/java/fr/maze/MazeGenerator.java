package fr.maze;

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
    for (int row1 = 0; row1 < rows; row1++) {
      for (int column = 0; column < columns; column++) {
        grid[row1][column] = new Cell(row1, column);
      }
    }

    //compute the maze : BinaryTree algorithm used here
    Arrays.stream(grid)
            .flatMap(Arrays::stream)
            .forEach(cell -> {
              List<Cell> potentialLinkedNeighbors = findNeighbors(grid, cell, Direction.NORTH, Direction.EAST);
              if (potentialLinkedNeighbors.size() > 0) {
                int randomIndex = generateRandomIndex(potentialLinkedNeighbors.size());
                Cell neighborCell = potentialLinkedNeighbors.get(randomIndex);

                // INSERT A LINKED CELL
                cell.setLinkedNeighbor(neighborCell);
                neighborCell.setLinkedNeighbor(cell);
              }
            });


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
        boolean islinked = cell.isLinkedNeighborOfDirection(Direction.EAST);

        String eastBoundary = (islinked ? " " : "|");
        top.append("   ").append(eastBoundary);

        islinked = cell.isLinkedNeighborOfDirection(Direction.SOUTH);

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

  protected int generateRandomIndex(int maxRandom) {
    Random r = new Random();
    return r.ints(1, 0, maxRandom).findFirst().getAsInt();
  }
}