package fr.maze.domain;

import java.util.Arrays;
import java.util.stream.IntStream;

class MazePrinter {
  private final Grid maze;

  MazePrinter(Grid maze) {
    this.maze = maze;
  }

  StringBuffer print() {
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

  private String buildRowRepresentation(Cell[] row) {
    return "|" +
            buildRowTopRepresentation(row) +
            "\n" +
            "+" +
            buildRowBottomRepresentation(row) +
            "\n";
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
