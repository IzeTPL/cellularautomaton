package com.cellular.automaton.gameoflife;

import com.cellular.automaton.engine.logic.Board;
import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marian on 05.05.17.
 */
public class GameOfLifeBoard extends Board {

    public GameOfLifeBoard(int x, int y) {

            super();

            size = new Point(x, y);

            for (int i = 0; i < size.x; i++) {

                List<Cell> cellsRow = new ArrayList<>();

                for (int j = 0; j < size.y; j++) {

                    cellsRow.add(new GameOfLifeCell(i, j));

                }

                cells.add(cellsRow);

            }

    }

    public void swap(int x, int y) {

        ( (GameOfLifeCell) cells.get(x).get(y) ).swap();

    }

}
