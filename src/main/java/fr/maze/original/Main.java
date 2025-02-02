package fr.maze.original;

import fr.maze.original.generators.implementations.BinaryTreeMazeGenerator;
import fr.maze.original.models.Maze;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Maze maze = new Maze(7, 7, new BinaryTreeMazeGenerator(new Random()));
        System.out.println(maze);
    }
}