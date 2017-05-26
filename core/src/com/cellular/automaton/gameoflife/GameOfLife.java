package com.cellular.automaton.gameoflife;

import com.cellular.automaton.engine.Application;

public class GameOfLife extends Application {

    @Override
    public void create() {

        super.create();
        setScreen(new GameOfLifeScreen(this));

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

        super.dispose();

    }

}
