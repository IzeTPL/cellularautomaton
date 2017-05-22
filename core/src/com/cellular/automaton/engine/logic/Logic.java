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

            if(board.size.x % threadsNum == 0) {

                end += board.size.x / threadsNum;

            } else {

                end += (board.size.x / threadsNum) + 1;

            }

            int warunek = (end - start) * (i + 1);

            if(warunek > board.size.x) {
                end -= (warunek  - board.size.x);
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

        //long endTime = System.currentTimeMillis();

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
