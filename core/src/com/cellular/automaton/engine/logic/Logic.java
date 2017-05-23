package com.cellular.automaton.engine.logic;

public abstract class Logic {

    protected Board board;
    protected boolean isPaused;

    public Logic() {
        isPaused = true;
    }


    public void pause() {

        isPaused = !isPaused;

    }

    public void iterate() {

        //long startTime = System.currentTimeMillis();
        int threadsNum = Runtime.getRuntime().availableProcessors() * Runtime.getRuntime().availableProcessors();
        int start = 0;
        int end = 0;
        Thread[] threads = new LogicThread[threadsNum];

        for (int i = 0; i < threadsNum; i++) {

            if(board.size.x*board.size.y % threadsNum == 0) {

                end += board.size.x*board.size.y / threadsNum;

            } else {

                end += (board.size.x*board.size.y / threadsNum) + 1;

            }

            int warunek = (end - start) * (i + 1);

            if(warunek > board.size.x*board.size.y) {
                end -= (warunek  - board.size.x * board.size.y);
            }

            threads[i] = new LogicThread(board.cells.subList(start,end));
            threads[i].setName(Integer.toString(i));
            threads[i].start();

            start = end;

        }

        for (int i = 0; i < threadsNum; i++) {

            try {
                threads[i].join();
            } catch (InterruptedException e) {}

        }

        long endTime = System.currentTimeMillis();

        //System.out.println((endTime - startTime));


    }

    public boolean isPaused() {

        return isPaused;

    }

    public abstract void click(int x, int y);

    public Board getBoard() {
        return board;
    }
}
