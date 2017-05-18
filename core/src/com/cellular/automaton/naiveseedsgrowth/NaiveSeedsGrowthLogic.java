package com.cellular.automaton.naiveseedsgrowth;

import com.cellular.automaton.engine.logic.Logic;

/**
 * Created by marian on 06.05.17.
 */
public class NaiveSeedsGrowthLogic extends Logic {

    public NaiveSeedsGrowthLogic(int x, int y) {

        super();
        board = new NaiveSeedsGrowthBoard(x, y);

    }

    @Override
    public void click(int x, int y) {
        ( (NaiveSeedsGrowthBoard) board).swap(x, y);
    }

}
