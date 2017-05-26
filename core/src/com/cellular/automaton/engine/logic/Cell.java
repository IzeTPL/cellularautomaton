package com.cellular.automaton.engine.logic;

import com.badlogic.gdx.graphics.Color;
import com.cellular.automaton.model.ICell;

import java.util.List;

/**
 * Created by marian on 26.04.17.
 */
public class Cell implements ICell {

    protected State currentState;
    protected State nextState;
    protected Point position;
    protected List<Cell> neighbors;
    protected Color color;
    protected Color nextColor;

    public Cell(int x, int y) {

        position = new Point(x, y);
        color = new Color(Color.BLACK);
        currentState = State.EMPTY;
        nextState = State.EMPTY;

    }

    @Override
    public boolean checkNeighbors() {

        return false;

    }

    @Override
    public void update() {

        currentState = nextState;

    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Color getColor() {

        return color;

    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getNextColor() {
        return nextColor;
    }

    public void setNextColor(Color nextColor) {
        this.nextColor = nextColor;
    }

    public List<Cell> getNeighbors() {
        return neighbors;
    }
}
