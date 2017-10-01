package fr.maze.utils;

import java.util.Random;

public class Utils {
  public static int generateRandomIndex(int maxRandom) {
//    Random r = new Random();
//    return r.ints(1, 0, maxRandom).findFirst().getAsInt();
    return maxRandom - 1;
  }
}
