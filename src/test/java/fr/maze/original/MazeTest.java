package fr.maze.original;

import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MazeTest {

    @Test
    public void should_generate_a_correct_maze_when_random_index_is_always_zero() {
        Random randomMock = mock(Random.class);
        when(randomMock.ints(anyLong(), anyInt(), anyInt()))
                .thenAnswer(invocationOnMock -> IntStream.of(0));

        assertThat((new Maze(7, 7, randomMock)).toString()).isEqualTo(
                "+---+---+---+---+---+---+---+\n" +
                        "|                           |\n" +
                        "+   +   +   +   +   +   +   +\n" +
                        "|   |   |   |   |   |   |   |\n" +
                        "+   +   +   +   +   +   +   +\n" +
                        "|   |   |   |   |   |   |   |\n" +
                        "+   +   +   +   +   +   +   +\n" +
                        "|   |   |   |   |   |   |   |\n" +
                        "+   +   +   +   +   +   +   +\n" +
                        "|   |   |   |   |   |   |   |\n" +
                        "+   +   +   +   +   +   +   +\n" +
                        "|   |   |   |   |   |   |   |\n" +
                        "+   +   +   +   +   +   +   +\n" +
                        "|   |   |   |   |   |   |   |\n" +
                        "+---+---+---+---+---+---+---+\n");
    }

    @Test
    public void should_generate_a_correct_maze_when_random_index_is_always_the_max_index() {
        Random randomMock = mock(Random.class);
        when(randomMock.ints(1, 0, 1))
                .thenAnswer(invocationOnMock -> IntStream.of(0));
        when(randomMock.ints(1, 0, 2))
                .thenAnswer(invocationOnMock -> IntStream.of(1));

        assertThat((new Maze(7, 7, randomMock)).toString()).isEqualTo(
                "+---+---+---+---+---+---+---+\n" +
                        "|                           |\n" +
                        "+---+---+---+---+---+---+   +\n" +
                        "|                           |\n" +
                        "+---+---+---+---+---+---+   +\n" +
                        "|                           |\n" +
                        "+---+---+---+---+---+---+   +\n" +
                        "|                           |\n" +
                        "+---+---+---+---+---+---+   +\n" +
                        "|                           |\n" +
                        "+---+---+---+---+---+---+   +\n" +
                        "|                           |\n" +
                        "+---+---+---+---+---+---+   +\n" +
                        "|                           |\n" +
                        "+---+---+---+---+---+---+---+\n");
    }

    @Test
    public void should_generate_random_mazes_with_corrects_borders_and_separators() {
        for (int i = 0; i < 10; i++) {
            checkMazeSeparators((new Maze(7, 7, new Random())).toString());
        }
    }

    private static void checkMazeSeparators(String maze) {
        int rows = 7;
        int columns = 7;

        int rowLength = columns * 4 + 2; // 4 chars + last right char + \n
        int colLength = rows * 2 + 1; // 2 chars + last bottom char
        int totalLength = rowLength * colLength;

        String expectedTopBottomBorder = generateTopBottomBorder(columns);

        assertThat(maze.length()).isEqualTo(totalLength);
        assertThat(maze).startsWith(expectedTopBottomBorder);
        assertThat(maze).endsWith(expectedTopBottomBorder);

        for (int i = 0; i < rows; i++) {
            int firstLineStartIndex = i * 2 * rowLength;
            int firstLineEndIndex = firstLineStartIndex + rowLength;
            int secondLineEndIndex = firstLineEndIndex + rowLength;
            String firstLine = maze.substring(firstLineStartIndex, firstLineEndIndex);
            String secondLine = maze.substring(firstLineEndIndex, secondLineEndIndex);

            for (int j = 0; j <= columns; j++) {
                assertThat(String.valueOf(firstLine.charAt(j * 4))).isEqualTo("+");
            }

            assertThat(secondLine).startsWith("|");
            assertThat(secondLine).endsWith("|\n");
        }
    }

    private static String generateTopBottomBorder(int columns) {
        StringBuilder topBottomBorder = new StringBuilder("+");
        for (int i = 0; i < columns; i++) {
            topBottomBorder.append("---+");
        }
        topBottomBorder.append("\n");
        return topBottomBorder.toString();
    }
}
