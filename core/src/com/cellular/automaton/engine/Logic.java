package com.cellular.automaton.engine;

import com.cellular.automaton.gameoflife.Board;

public abstract class Logic {

    public Board board;
    public boolean isPaused;

    public Logic(int x, int y) {
        isPaused = true;
    }


    public void pause() {



    }

    public void iterate() {

    }

    public abstract int checkNeighbors(int x, int y);

}
