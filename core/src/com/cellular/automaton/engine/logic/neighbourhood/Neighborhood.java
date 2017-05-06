package com.cellular.automaton.engine.logic.neighbourhood;

import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.Point;
import com.cellular.automaton.engine.logic.boudarycondition.BoundaryCondition;

import java.util.List;

/**
 * Created by marian on 27.04.17.
 */
public abstract class Neighborhood {

    public abstract List<Cell> findNeighbors(List< List<Cell> > cells, Cell currentCell, BoundaryCondition boundaryCondition, Point size);

}
