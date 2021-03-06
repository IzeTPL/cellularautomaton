package com.cellular.automaton.engine.logic.boudarycondition;

import com.cellular.automaton.engine.logic.Point;

/**
 * Created by marian on 05.05.17.
 */
public class PeriodicBoudaryCondition extends BoundaryCondition {

    @Override
    public Point getPosition(Point position, Point size) {
        if (position.x < 0) position.x = size.x - 1;
        if (position.y < 0) position.y = size.y - 1;
        if (position.x >= size.x) position.x = 0;
        if (position.y >= size.y) position.y = 0;

        return position;

    }

    @Override
    public boolean skip(Point position, Point size) {
        return false;
    }
}
