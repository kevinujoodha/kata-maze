package fr.maze.original.generators;

import fr.maze.original.models.Cell;

import java.util.Random;

public abstract class MazeGenerator {
    protected final Random random;

    public MazeGenerator(Random random) {
        this.random = random;
    }

    public abstract void generateMaze(Cell[][] grid, int rows, int columns);
}
