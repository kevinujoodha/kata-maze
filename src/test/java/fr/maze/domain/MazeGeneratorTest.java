package fr.maze.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class MazeGeneratorTest {
  @Test
  public void should_always_give_same_output() throws Exception {
    MazeGenerator mazeGenerator = new MazeGenerator();

    for (int i = 0; i < 1000; ++i) {
      StringBuffer mazeOne = mazeGenerator.generateMaze(7, 7);
      StringBuffer mazeTwo = mazeGenerator.generateMaze(7, 7);
      Assertions.assertThat(mazeOne.toString()).isEqualTo(mazeTwo.toString());
    }
  }

  @Test
  public void should_always_act_the_same() throws Exception {
    MazeGenerator mazeGenerator = new MazeGenerator();
    MazeGeneratorLegacy mazeGeneratorLegacy = new TestableMazeGeneratorLegacy();

    for (int i = 0; i < 1000; ++i) {
      StringBuffer maze = mazeGenerator.generateMaze(7, 7);
      StringBuffer mazeLegacy = mazeGeneratorLegacy.generateMaze();
      Assertions.assertThat(maze.toString()).isEqualTo(mazeLegacy.toString());
    }
  }

  private class TestableMazeGeneratorLegacy extends MazeGeneratorLegacy {
    @Override
    protected int generateRandomIndex(List<CellLegacy> neighbors) {
      return neighbors.size() - 1;
    }
  }
}
