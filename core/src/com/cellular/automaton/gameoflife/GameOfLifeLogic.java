package com.cellular.automaton.gameoflife;


import com.cellular.automaton.engine.Logic;

public class GameOfLifeLogic extends Logic{

    public GameOfLifeLogic(int x, int y) {

        super(x,y);
        board = new Board(x,y);

    }

    @Override
    public void pause() {

        if(isPaused) isPaused = false;
        else isPaused = true;

    }

    @Override
    public void iterate() {

        board.setGrid(new int[board.getSize().x][board.getSize().y]);

        for (int i = 1; i < board.getSize().x - 1; i++) {
            for (int j = 1; j < board.getSize().y - 1; j++) {
                board.getGrid()[i][j] = checkNeighbors(i,j);
            }
        }

        board.getHistory().getStepList().add( board.getGrid() );

    }

    @Override
    public int checkNeighbors(int x, int y) {

        int alive = 0;
        int currentCell = board.getHistory().getStepList().get( board.getHistory().getStepList().size() - 1 )[x][y];

        for (int i = x - 1; i <= x + 1; i++) {

            for (int j = y - 1; j <= y + 1; j++) {

                if(i == x && j == y) continue;
                if(board.getHistory().getStepList().get(board.getHistory().getStepList().size() - 1)[i][j] == 1) alive++;

            }

        }

        if( (alive == 3 && currentCell == 0) || ( (alive == 2 || alive == 3) && currentCell == 1) ) return 1;
        else return 0;

    }

}
