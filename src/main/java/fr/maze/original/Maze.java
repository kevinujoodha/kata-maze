package fr.maze.original;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Maze {
    private final int rows;
    private final int columns;
    private final Random random;
    private Cell[][] grid;

    public Maze(int rows, int columns, Random random) {
        this.rows = rows;
        this.columns = columns;
        this.random = random;
        initGrid();
        generateMaze();
    }

    private void initGrid() {
        grid = new Cell[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                grid[row][column] = new Cell(row, column);
            }
        }

        for (Cell[] gridRow : grid) {
            for (Cell cell : gridRow) {
                int row = cell.getRow();
                int column = cell.getColumn();

                cell.setNorth(getGridCell(row - 1, column));
                cell.setSouth(getGridCell(row + 1, column));
                cell.setWest(getGridCell(row, column - 1));
                cell.setEast(getGridCell(row, column + 1));
            }
        }
    }

    private void generateMaze() {
        for (Cell[] gridRow : grid) {
            for (Cell cell : gridRow) {
                List<Cell> neighbors = Stream.of(cell.getNorth(), cell.getEast()).filter(Objects::nonNull).collect(Collectors.toList());
                if (!neighbors.isEmpty()) {
                    int randomIndex = random.ints(1, 0, neighbors.size()).findFirst().orElse(0);
                    Cell neighborCell = neighbors.get(randomIndex);
                    if (neighborCell != null) {
                        cell.addNeighbors(neighborCell);
                        neighborCell.addNeighbors(cell);
                    }
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int i = 0; i < columns; i++) {
            sb.append("---+");
        }
        sb.append("\n");

        for (Cell[] row : grid) {
            StringBuilder top = new StringBuilder("|");
            StringBuilder bottom = new StringBuilder("+");

            for (Cell cell : row) {
                top.append("   ").append(cell.isNeighbor(cell.getEast()) ? " " : "|");
                bottom.append(cell.isNeighbor(cell.getSouth()) ? "   " : "---").append("+");
            }

            sb.append(top).append("\n");
            sb.append(bottom).append("\n");
        }
        return sb.toString();
    }

    private Cell getGridCell(int row, int column) {
        return (row >= 0 && row < rows) && (column >= 0 && (column < columns)) ? grid[row][column] : null;
    }
}
