package com.cellular.automaton.gameoflife;

import com.cellular.automaton.engine.logic.Board;
import com.cellular.automaton.engine.logic.Point;

/**
 * Created by marian on 05.05.17.
 */
public class GameOfLifeBoard extends Board {

    public GameOfLifeBoard(int x, int y) {

        super();

        size = new Point(x, y);

        for (int i = 0; i < size.x; i++) {

            for (int j = 0; j < size.y; j++) {

                cells.add(new GameOfLifeCell(i, j));

            }

        }

    }

    public void swap(int x, int y) {

        ((GameOfLifeCell) cells.get(x * size.x + y)).swap();

    }

}
