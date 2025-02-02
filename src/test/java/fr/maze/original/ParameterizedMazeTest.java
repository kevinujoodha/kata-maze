package fr.maze.original;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class ParameterizedMazeTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1),
                        "+---+---+---+---+---+---+---+\n" +
                                "|                           |\n" +
                                "+---+   +---+---+   +   +   +\n" +
                                "|       |           |   |   |\n" +
                                "+   +---+   +---+---+---+   +\n" +
                                "|   |       |               |\n" +
                                "+   +---+---+---+---+   +   +\n" +
                                "|   |                   |   |\n" +
                                "+   +   +---+   +   +   +   +\n" +
                                "|   |   |       |   |   |   |\n" +
                                "+---+---+   +   +   +---+   +\n" +
                                "|           |   |   |       |\n" +
                                "+---+---+   +---+   +---+   +\n" +
                                "|           |       |       |\n" +
                                "+---+---+---+---+---+---+---+\n",
                },
                {Arrays.asList(1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0),
                        "+---+---+---+---+---+---+---+\n" +
                                "|                           |\n" +
                                "+---+   +   +---+   +---+   +\n" +
                                "|       |   |       |       |\n" +
                                "+   +   +---+---+---+---+   +\n" +
                                "|   |   |                   |\n" +
                                "+   +---+---+   +   +   +   +\n" +
                                "|   |           |   |   |   |\n" +
                                "+   +---+   +---+   +---+   +\n" +
                                "|   |       |       |       |\n" +
                                "+   +   +---+   +---+---+   +\n" +
                                "|   |   |       |           |\n" +
                                "+   +   +   +   +---+   +   +\n" +
                                "|   |   |   |   |       |   |\n" +
                                "+---+---+---+---+---+---+---+\n",
                },
                {Arrays.asList(1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1),
                        "+---+---+---+---+---+---+---+\n" +
                                "|                           |\n" +
                                "+---+   +   +   +---+---+   +\n" +
                                "|       |   |   |           |\n" +
                                "+   +---+---+---+---+---+   +\n" +
                                "|   |                       |\n" +
                                "+---+---+   +   +   +   +   +\n" +
                                "|           |   |   |   |   |\n" +
                                "+---+---+   +   +   +---+   +\n" +
                                "|           |   |   |       |\n" +
                                "+---+---+   +   +   +---+   +\n" +
                                "|           |   |   |       |\n" +
                                "+---+---+---+---+   +---+   +\n" +
                                "|                   |       |\n" +
                                "+---+---+---+---+---+---+---+\n",
                },
                {Arrays.asList(1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0),
                        "+---+---+---+---+---+---+---+\n" +
                                "|                           |\n" +
                                "+---+---+---+   +   +---+   +\n" +
                                "|               |   |       |\n" +
                                "+   +---+---+   +   +---+   +\n" +
                                "|   |           |   |       |\n" +
                                "+---+---+---+---+   +   +   +\n" +
                                "|                   |   |   |\n" +
                                "+   +   +   +---+   +---+   +\n" +
                                "|   |   |   |       |       |\n" +
                                "+   +---+   +---+   +---+   +\n" +
                                "|   |       |       |       |\n" +
                                "+---+   +---+   +   +   +   +\n" +
                                "|       |       |   |   |   |\n" +
                                "+---+---+---+---+---+---+---+\n",
                },
                {Arrays.asList(0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0),
                        "+---+---+---+---+---+---+---+\n" +
                                "|                           |\n" +
                                "+   +---+---+   +   +   +   +\n" +
                                "|   |           |   |   |   |\n" +
                                "+---+   +   +---+   +---+   +\n" +
                                "|       |   |       |       |\n" +
                                "+---+   +---+   +   +---+   +\n" +
                                "|       |       |   |       |\n" +
                                "+   +   +---+---+   +---+   +\n" +
                                "|   |   |           |       |\n" +
                                "+---+   +---+---+   +---+   +\n" +
                                "|       |           |       |\n" +
                                "+   +---+---+---+---+   +   +\n" +
                                "|   |                   |   |\n" +
                                "+---+---+---+---+---+---+---+\n",
                },
        });
    }

    private final List<Integer> randomIndexes;
    private final String expectedMaze;

    public ParameterizedMazeTest(List<Integer> randomIndexes, String expectedMaze) {
        this.expectedMaze = expectedMaze;
        this.randomIndexes = randomIndexes;
    }

    @Test
    public void should_generate_a_correct_random_maze() {
        Random randomMock = mock(Random.class);
        when(randomMock.ints(1, 0, 1))
                .thenAnswer(invocationOnMock -> IntStream.of(0));

        when(randomMock.ints(1, 0, 2))
                .thenReturn(IntStream.of(randomIndexes.get(0)), randomIndexes.stream().skip(1).map(IntStream::of).toArray(IntStream[]::new));

        assertThat((new Maze(7, 7, randomMock)).toString()).isEqualTo(expectedMaze);
    }
}
