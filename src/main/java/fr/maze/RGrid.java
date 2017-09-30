package fr.maze;

import java.util.ArrayList;
import java.util.List;

public class RGrid {
  private List<RCell> cells;

  public RGrid() {
    this.cells = new ArrayList<>();
  }

  public void addCell(RCell cell) {
    this.cells.add(cell);
  }
}
