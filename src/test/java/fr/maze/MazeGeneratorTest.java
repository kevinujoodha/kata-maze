package fr.maze;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class MazeGeneratorTest {
  @Test
  public void should_always_give_same_output() throws Exception {
    MazeGenerator mazeGenerator = new TestableMazeGenerator();

    for (int i = 0; i < 1000; ++i) {
      StringBuffer mazeOne = mazeGenerator.generateMaze();
      StringBuffer mazeTwo = mazeGenerator.generateMaze();
      Assertions.assertThat(mazeOne.toString()).isEqualTo(mazeTwo.toString());
    }
  }

  @Test
  public void should_always_act_the_same() throws Exception {
    MazeGenerator mazeGenerator = new TestableMazeGenerator();
    MazeGeneratorLegacy mazeGeneratorLegacy = new TestableMazeGeneratorLegacy();

    for (int i = 0; i < 1000; ++i) {
      StringBuffer maze = mazeGenerator.generateMaze();
      StringBuffer mazeLegacy = mazeGeneratorLegacy.generateMaze();
      Assertions.assertThat(maze.toString()).isEqualTo(mazeLegacy.toString());
    }
  }

  private class TestableMazeGenerator extends MazeGenerator {
    @Override
    protected int generateRandomIndex(int maxRandom) {
      return maxRandom - 1;
    }
  }

  private class TestableMazeGeneratorLegacy extends MazeGeneratorLegacy {
    @Override
    protected int generateRandomIndex(List<CellLegacy> neighbors) {
      return neighbors.size() - 1;
    }
  }
}
