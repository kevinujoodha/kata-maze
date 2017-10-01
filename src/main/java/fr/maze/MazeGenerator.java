package fr.maze;

public class MazeGenerator {
  public StringBuffer generateMaze(int rowNumber, int columnNumber) {
    Grid grid = new Grid(rowNumber, columnNumber);
    grid.computeMaze();

    MazePrinter printer = new MazePrinter();
    return printer.print(grid);
  }
}