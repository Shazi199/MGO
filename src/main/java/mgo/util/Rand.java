package mgo.util;

import java.util.Random;

public class Rand {
    private final static Random rand = new Random();

    public static int getInt(int from, int to) {
        return rand.nextInt(to - from + 1) + from;
    }
}
