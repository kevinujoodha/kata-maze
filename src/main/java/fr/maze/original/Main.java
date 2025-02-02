package fr.maze.original;

import java.util.Random;

public class Main {

  public static void main(String[] args) {
    Maze maze = new Maze(7, 7, new Random());
    System.out.println(maze);
  }
}