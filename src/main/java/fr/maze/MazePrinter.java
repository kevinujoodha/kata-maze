package fr.maze;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MazePrinter {
  public StringBuffer print(Grid maze) {
    StringBuffer sb = new StringBuffer();

    sb.append("+");
    IntStream.range(0, maze.getColumnsNumber())
            .forEach(i -> sb.append("---+"));
    sb.append("\n");

    sb.append(Arrays.stream(maze.getGrid())
            .map(this::buildRowRepresentation)
            .reduce("", (a, b) -> a + b));

    return sb;
  }

  public String buildRowRepresentation(Cell[] row) {
    return new StringBuilder().append("|")
            .append(buildRowTopRepresentation(row))
            .append("\n")
            .append("+")
            .append(buildRowBottomRepresentation(row))
            .append("\n")
            .toString();
  }

  public String buildRowBottomRepresentation(Cell[] row) {
    return Arrays.stream(row)
            .map(cell -> (cell.isLinkedNeighborOfDirection(Direction.SOUTH) ? "   " : "---") + "+")
            .reduce("", (a, b) -> a + b);
  }

  public String buildRowTopRepresentation(Cell[] row) {
    return Arrays.stream(row)
            .map(cell -> "   " + (cell.isLinkedNeighborOfDirection(Direction.EAST) ? " " : "|"))
            .reduce("", (a, b) -> a + b);
  }
}
