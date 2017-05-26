package com.cellular.automaton.processsimulation;

import com.badlogic.gdx.graphics.Color;
import com.cellular.automaton.engine.logic.Cell;

import java.util.HashMap;

/**
 * Created by Marian on 26.05.2017.
 */
public class SimulationCell extends Cell {

    protected static HashMap<Integer, Color> seedList;
    protected Integer seedID;
    protected Integer nextSeedID;

    public SimulationCell(int x, int y) {
        super(x, y);
    }

}
