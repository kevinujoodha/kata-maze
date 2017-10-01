package fr.maze;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MazeGenerator {
  private int rows;
  private int columns;
  private Grid rgrid;

  public StringBuffer generateMaze() {
    rows = 7;
    columns = 7;

    rgrid = new Grid(rows, columns);


    Cell[][] grid = new Cell[rows][columns];


    grid = rgrid.getGrid();

    //compute the maze : BinaryTree algorithm used here
    computeMaze(grid);


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




  protected void computeMaze(Cell[][] grid) {
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
  }

  private List<Cell> findNeighbors(Cell[][] grid, Cell cell, Direction... directions) {
    return Arrays.stream(directions)
            .filter(direction -> rgrid.neighborIsInGrid(cell, direction))
            .map(direction -> rgrid.getCellNeighbor(grid, cell, direction))
            .collect(Collectors.toList());
  }

  protected int generateRandomIndex(int maxRandom) {
    Random r = new Random();
    return r.ints(1, 0, maxRandom).findFirst().getAsInt();
  }
}