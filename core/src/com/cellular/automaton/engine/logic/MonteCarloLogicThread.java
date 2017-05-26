package com.cellular.automaton.engine.logic;

import com.cellular.automaton.processsimulation.montecarlo.MonteCarloCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marian on 20.05.2017.
 */
public class MonteCarloLogicThread extends Thread {

    private List<Cell> cells = new ArrayList<>();

    public MonteCarloLogicThread(List<Cell> cells) {

        super();
        this.cells = cells;

    }

    public void run() {

        List<Cell> cells = new ArrayList<>(this.cells);

        cells.removeIf(cell -> ((MonteCarloCell) cell).toRemove());

        Random random = new Random();

        while (cells.size() > 0) {

            int index = random.nextInt(cells.size());

            cells.get(index).checkNeighbors();
            cells.remove(index);

        }

        for (Cell cell : this.cells) {
            cell.update();
        }

    }

}
