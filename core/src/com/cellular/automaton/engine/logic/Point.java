package com.cellular.automaton.engine.logic;

/**
 * Created by marian on 20.04.17.
 */
public class Point {

    public int x;
    public int y;
    public int z;

    public Point(int x) {

        this.x = x;
        this.y = 1;
        this.z = 1;

    }

    public Point(int x, int y) {

        this.x = x;
        this.y = y;
        this.z = 1;

    }

    public Point(int x, int y, int z) {

        this.x = x;
        this.y = y;
        this.z = z;

    }

}
