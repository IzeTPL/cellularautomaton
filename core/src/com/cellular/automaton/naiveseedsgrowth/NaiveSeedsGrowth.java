package com.cellular.automaton.naiveseedsgrowth;

import com.cellular.automaton.engine.Application;
import sun.net.www.ApplicationLaunchException;

/**
 * Created by marian on 05.05.17.
 */
public class NaiveSeedsGrowth extends Application {

    @Override
    public void create() {

        super.create();
        setScreen(new NaiveSeedsGrowthScreen(this) );

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
