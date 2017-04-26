package com.cellular.automaton;

import com.cellular.automaton.gameoflife.GameOfLifeScreen;

public class GameOfLife extends AbstractApplication {

	@Override
	public void create() {

		super.create();
		setScreen(new GameOfLifeScreen(this) );

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
