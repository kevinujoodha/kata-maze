package fr.maze;

public class MazeGenerator {
  public StringBuffer generateMaze() {
    Grid grid = new Grid(7, 7);
    grid.computeMaze();

    MazePrinter printer = new MazePrinter();
    return printer.print(grid);
  }
}