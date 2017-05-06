package com.cellular.automaton.engine.logic;

import com.cellular.automaton.engine.logic.Board;

import java.util.List;

public abstract class Logic {

    protected Board board;
    protected boolean isPaused;

    public Logic() {
        isPaused = true;
    }


    public void pause() {

        isPaused = !isPaused;

    }

    public void iterate() {

        for (List<Cell> cellsRow: board.cells) {
            for (Cell cell : cellsRow) {
                cell.checkNeighbors();
            }
        }

        for (List<Cell> cellsRow: board.cells) {
            for (Cell cell : cellsRow) {
                cell.update();
            }
        }

    }

    public boolean isPaused() {

        return isPaused;

    }

    public abstract void click(int x, int y);

    public Board getBoard() {
        return board;
    }
}
