package fr.maze;

public class Main
{
  public static void main(String[] args) {
    MazeGenerator mazeGenerator = new MazeGenerator(7, 7);
    System.out.println(mazeGenerator.generateMaze().toString());
  }
}
