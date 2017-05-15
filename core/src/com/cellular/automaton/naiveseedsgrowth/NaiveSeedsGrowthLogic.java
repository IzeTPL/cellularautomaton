package com.cellular.automaton.naiveseedsgrowth;

import com.cellular.automaton.engine.logic.Logic;
import com.cellular.automaton.engine.logic.boudarycondition.PeriodicBoudaryCondition;
import com.cellular.automaton.engine.logic.neighbourhood.MooreNeighbourHood;

/**
 * Created by marian on 06.05.17.
 */
public class NaiveSeedsGrowthLogic extends Logic {

    public NaiveSeedsGrowthLogic(int x, int y) {

        super();
        board = new NaiveSeedsGrowthBoard(x, y);
        board.setNeighbourhood(new MooreNeighbourHood(), new PeriodicBoudaryCondition());

    }

    @Override
    public void click(int x, int y) {

    }

}
