package com.cellular.automaton.engine.logic.boudarycondition;

import com.cellular.automaton.engine.logic.Point;

/**
 * Created by marian on 26.04.17.
 */
public abstract class BoundaryCondition {

    public abstract Point getPosition(Point position, Point size);

    public abstract boolean skip(Point position, Point size);

}
