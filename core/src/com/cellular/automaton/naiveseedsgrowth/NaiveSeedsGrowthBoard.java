package com.cellular.automaton.naiveseedsgrowth;

import com.badlogic.gdx.graphics.Color;
import com.cellular.automaton.engine.logic.Board;
import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.Point;
import com.cellular.automaton.gameoflife.GameOfLifeCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marian on 06.05.17.
 */
public class NaiveSeedsGrowthBoard extends Board {

    protected List<Color> colors;

    public NaiveSeedsGrowthBoard(int x, int y) {

        super();

        size = new Point(x, y);

        for (int i = 0; i < size.x; i++) {

            List<Cell> cellsRow = new ArrayList<>();

            for (int j = 0; j < size.y; j++) {

                cellsRow.add(new NaiveSeedsGrowthCell(i, j));

            }

            cells.add(cellsRow);

        }

    }

}
