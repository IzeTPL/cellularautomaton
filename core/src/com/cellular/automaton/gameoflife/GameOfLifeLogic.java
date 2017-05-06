package com.cellular.automaton.gameoflife;


import com.cellular.automaton.engine.logic.Logic;
import com.cellular.automaton.engine.logic.boudarycondition.PeriodicBoudaryCondition;
import com.cellular.automaton.engine.logic.neighbourhood.MooreNeighbourHood;

public class GameOfLifeLogic extends Logic{

    public GameOfLifeLogic(int x, int y) {

        super();
        board = new GameOfLifeBoard(x, y);
        board.setNeighbourhood(new MooreNeighbourHood(), new PeriodicBoudaryCondition());

    }

    @Override
    public void iterate() {

        super.iterate();

    }

    @Override
    public void click(int x, int y) {
        ( (GameOfLifeBoard) board).swap(x, y);
    }

}
