package fr.maze.original.generators.implementations;

import fr.maze.original.generators.MazeGenerator;
import fr.maze.original.models.Cell;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryTreeMazeGenerator extends MazeGenerator {
    public BinaryTreeMazeGenerator(Random random) {
        super(random);
    }

    @Override
    public void generateMaze(Cell[][] grid, int rows, int columns) {
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
}
