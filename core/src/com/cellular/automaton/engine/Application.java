package com.cellular.automaton.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cellular.automaton.engine.logic.Board;

public abstract class Application extends Game {

    protected SpriteBatch spriteBatch;
    protected Board board;

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

    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

}
