package com.cellular.automaton.engine.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marian on 20.05.2017.
 */
public class LogicThread extends Thread {

    private List<Cell> cells = new ArrayList<>();

    public LogicThread(List<Cell> cells) {

        super();
        this.cells = cells;

    }

    public void run() {

        for (Cell cell : cells) {
            cell.checkNeighbors();
        }

        for (Cell cell : cells) {
            cell.update();
        }

    }

}
