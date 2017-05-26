package com.cellular.automaton.gameoflife;

import com.badlogic.gdx.graphics.Color;
import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.State;

/**
 * Created by marian on 30.04.17.
 */
public class GameOfLifeCell extends Cell {

    public GameOfLifeCell(int x, int y) {
        super(x, y);
        update();
    }

    @Override
    public boolean checkNeighbors() {

        int aliveCellsCount = 0;

        for (Cell cell : neighbors) {
            if (cell.getCurrentState() == State.ALIVE) aliveCellsCount++;
        }

        if (((aliveCellsCount == 3 || aliveCellsCount == 2) && currentState == State.ALIVE) || (aliveCellsCount == 3 && currentState == State.EMPTY))
            nextState = State.ALIVE;
        else
            nextState = State.EMPTY;

        return false;

    }

    @Override
    public void update() {
        super.update();

        if (currentState == State.ALIVE) color = Color.WHITE;
        else color = Color.BLACK;

    }

    public void swap() {

        if (currentState == State.ALIVE) setNextState(State.EMPTY);
        else setNextState(State.ALIVE);
        update();

    }

}
