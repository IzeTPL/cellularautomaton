package com.cellular.automaton.engine.logic;

import com.cellular.automaton.engine.logic.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marian on 20.05.2017.
 */
public class LogicThread extends Thread {

    private List< List<Cell> > cells = new ArrayList<>();

    public LogicThread(List< List<Cell> > cells) {

        super();
        this.cells = cells;

    }

    public void run() {

        for (List<Cell> cellsRow: cells) {
            for (Cell cell : cellsRow) {
                cell.checkNeighbors();
            }
        }

        for (List<Cell> cellsRow: cells) {
            for (Cell cell : cellsRow) {
                cell.update();
            }
        }

    }

}
