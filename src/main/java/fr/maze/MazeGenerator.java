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
    rgrid.computeMaze(grid);


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


}