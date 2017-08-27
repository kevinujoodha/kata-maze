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

  private class TestableMazeGenerator extends MazeGenerator {
    @Override
    protected int generateRandomIndex(List<Cell> neighbors) {
      return neighbors.size() - 1;
    }
  }
}
