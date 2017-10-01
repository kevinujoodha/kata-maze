package fr.maze;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MazeGenerator {
  private Grid grid;

  public StringBuffer generateMaze() {
    grid = new Grid(7, 7);

    //compute the maze : BinaryTree algorithm used here
    grid.computeMaze();


    //Display the maze
    StringBuffer sb = new StringBuffer();

    sb.append("+");
    IntStream.range(0, grid.getColumnsNumber())
            .forEach(i -> sb.append("---+"));
    sb.append("\n");

    sb.append(Arrays.stream(grid.getGrid())
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