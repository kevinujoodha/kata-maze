package fr.maze.domain;

public class MazeGenerator {
  public StringBuffer generateMaze(int rowNumber, int columnNumber) {
    Grid grid = new Grid(rowNumber, columnNumber);
    grid.computeMaze();

    MazePrinter printer = new MazePrinter(grid);
    return printer.print();
  }
}