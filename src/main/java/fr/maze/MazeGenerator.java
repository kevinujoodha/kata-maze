package fr.maze;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    IntStream.range(0, columns)
            .forEach(i -> sb.append("---+"));
    sb.append("\n");

    sb.append(Arrays.stream(grid)
            .map(this::buildRowRepresentation)
            .reduce("", (a, b) -> a + b));

    return sb;
  }

  private String buildRowRepresentation(Cell[] row) {
    return new StringBuilder().append("|")
            .append(buildRowTopRepresentation(row))
            .append("\n")
            .append("+")
            .append(buildRowBottomRepresentation(row))
            .append("\n")
            .toString();
  }

  private String buildRowBottomRepresentation(Cell[] row) {
    return Arrays.stream(row)
            .map(cell -> (cell.isLinkedNeighborOfDirection(Direction.SOUTH) ? "   " : "---") + "+")
            .reduce("", (a, b) -> a + b);
  }

  private String buildRowTopRepresentation(Cell[] row) {
    return Arrays.stream(row)
            .map(cell -> "   " + (cell.isLinkedNeighborOfDirection(Direction.EAST) ? " " : "|"))
            .reduce("", (a, b) -> a + b);
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