package com.cellular.automaton.processsimulation;

import com.cellular.automaton.engine.Application;

/**
 * Created by marian on 05.05.17.
 */
public class Simulation extends Application {

    @Override
    public void create() {

        super.create();
        setScreen(new SimulationScreen(this));

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
