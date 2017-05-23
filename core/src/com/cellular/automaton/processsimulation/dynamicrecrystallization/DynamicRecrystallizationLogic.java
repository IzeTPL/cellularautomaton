package com.cellular.automaton.processsimulation.dynamicrecrystallization;

import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.Logic;

/**
 * Created by marian on 06.05.17.
 */
public class DynamicRecrystallizationLogic extends Logic {

    double time = 0;

    public DynamicRecrystallizationLogic(int x, int y) {

        super();
        board = new DynamicRecrystallizationBoard(x, y);

    }

    @Override
    public void iterate() {

        double ro1 = DynamicRecrystallizationCell.globalRo;

        DynamicRecrystallizationCell.globalRo = (86710969050178.5 / 9.41268203527779) + (1 - (86710969050178.5 / 9.41268203527779) ) * Math.exp(-9.41268203527779*time);

        time += 0.001;

        DynamicRecrystallizationCell.roPerCell = (DynamicRecrystallizationCell.globalRo - ro1) / (board.getSize().x * board.getSize().y);

        super.iterate();

        for (Cell cell : board.getCells()) {

            DynamicRecrystallizationCell dynamicRecrystallizationCell = (DynamicRecrystallizationCell) cell;
            dynamicRecrystallizationCell.addRest(DynamicRecrystallizationCell.rest/1000);

        }

    }

    @Override
    public void click(int x, int y) {
        ( (DynamicRecrystallizationBoard) board).swap(x, y);
    }

}
