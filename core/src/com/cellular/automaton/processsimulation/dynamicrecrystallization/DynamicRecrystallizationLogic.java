package com.cellular.automaton.processsimulation.dynamicrecrystallization;

import com.cellular.automaton.engine.logic.Logic;

import java.util.Random;

/**
 * Created by marian on 06.05.17.
 */
public class DynamicRecrystallizationLogic extends Logic {

    double time;

    public DynamicRecrystallizationLogic(Logic logic) {

        super();
        board = new DynamicRecrystallizationBoard(logic.getBoard());
        time = 0;

    }

    @Override
    public void iterate() {

        double ro1 = DynamicRecrystallizationCell.globalRo;

        DynamicRecrystallizationCell.globalRo = (86710969050178.5 / 9.41268203527779) + (1 - (86710969050178.5 / 9.41268203527779)) * Math.exp(-9.41268203527779 * time);

        time += 0.001;

        DynamicRecrystallizationCell.roPerCell = (DynamicRecrystallizationCell.globalRo - ro1) / (board.getSize().x * board.getSize().y);

        super.iterate();

        Random random = new Random();

        double rest = DynamicRecrystallizationCell.rest/100;

        while (DynamicRecrystallizationCell.rest > 0) {

            DynamicRecrystallizationCell dynamicRecrystallizationCell = (DynamicRecrystallizationCell) board.getCells().get(random.nextInt(board.getCells().size()));
            dynamicRecrystallizationCell.addRest(rest);

        }

    }

    @Override
    public void click(int x, int y) {
        ((DynamicRecrystallizationBoard) board).swap(x, y);
    }

}
