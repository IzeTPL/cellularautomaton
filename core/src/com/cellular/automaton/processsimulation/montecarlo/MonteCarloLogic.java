package com.cellular.automaton.processsimulation.montecarlo;

import com.cellular.automaton.engine.logic.Board;
import com.cellular.automaton.engine.logic.Logic;
import com.cellular.automaton.engine.logic.MonteCarloLogicThread;

/**
 * Created by marian on 06.05.17.
 */
public class MonteCarloLogic extends Logic {

    public MonteCarloLogic(Logic logic) {

        super();
        board = new MonteCarloBoard(logic.getBoard());

    }

    @Override
    public Thread createThread(Board board, int start, int end) {
        return new MonteCarloLogicThread(board.getCells().subList(start, end));
    }

    @Override
    public void click(int x, int y) {
        ((MonteCarloBoard) board).swap(x, y);
    }

}
