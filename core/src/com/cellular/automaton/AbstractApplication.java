package com.cellular.automaton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AbstractApplication extends Game {

    protected SpriteBatch spriteBatch;

    @Override
    public void create () {

        spriteBatch = new SpriteBatch();

    }

    @Override
    public void render () {

        super.render();

    }

    @Override
    public void dispose() {

        super.dispose();
        spriteBatch.dispose();

    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

}
