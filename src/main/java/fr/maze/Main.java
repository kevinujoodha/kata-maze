package fr.maze;

public class Main
{
  public static void main(String[] args) {
    MazeGenerator mazeGenerator = new MazeGenerator();
    System.out.println(mazeGenerator.generateMaze().toString());
  }
}
