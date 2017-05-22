package com.cellular.automaton.processsimulation.dynamicrecrystallization;

import com.cellular.automaton.engine.logic.Logic;

/**
 * Created by marian on 06.05.17.
 */
public class DynamicRecrystallizationLogic extends Logic {

    double time = 0;
    double ro1 = 0;

    public DynamicRecrystallizationLogic(int x, int y) {

        super();
        board = new DynamicRecrystallizationBoard(x, y);

    }

    @Override
    public void iterate() {

        double ro2 = ro1;

        ro1 = (86710969050178.5 / 9.41268203527779) + (1 - (86710969050178.5 / 9.41268203527779) ) * Math.exp(-9.41268203527779/time);

        time += 0.001;

        double cellRo = (ro1 - ro2) / (board.getSize().x * board.getSize().y);

        super.iterate();
    }

    @Override
    public void click(int x, int y) {
        ( (DynamicRecrystallizationBoard) board).swap(x, y);
    }

}
