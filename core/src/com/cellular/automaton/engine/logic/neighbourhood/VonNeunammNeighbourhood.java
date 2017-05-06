package com.cellular.automaton.engine.logic.neighbourhood;

import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.Point;
import com.cellular.automaton.engine.logic.boudarycondition.BoundaryCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marian on 05.05.17.
 */
public class VonNeunammNeighbourhood extends Neighborhood {
    @Override
    public List<Cell> findNeighbors(List<List<Cell>> cells, Cell currentCell, BoundaryCondition boundaryCondition, Point size) {
        List<Cell> neighbors = new ArrayList<>();

        for (int i = currentCell.getPosition().x - 1; i <= currentCell.getPosition().x + 1; i++) {
            for (int j = currentCell.getPosition().y - 1; j <= currentCell.getPosition().y + 1; j++) {

                Point position = new Point(i, j);
                position = boundaryCondition.getPosition(position, size);

                if( (currentCell.getPosition().x == i || currentCell.getPosition().y == j) || boundaryCondition.skip(position, size) ) {
                    continue;
                }

                neighbors.add(cells.get(position.x).get(position.y));

            }
        }

        return neighbors;
    }
}
