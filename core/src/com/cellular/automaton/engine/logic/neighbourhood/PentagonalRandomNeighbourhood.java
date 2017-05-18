package com.cellular.automaton.engine.logic.neighbourhood;

import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.Point;
import com.cellular.automaton.engine.logic.boudarycondition.BoundaryCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by marian on 06.05.17.
 */
public class PentagonalRandomNeighbourhood extends Neighborhood {

    @Override
    public List<Cell> findNeighbors(List<List<Cell>> cells, Cell currentCell, BoundaryCondition boundaryCondition, Point size) {
        List<Cell> neighbors = new ArrayList<>();
        Random random = new Random();
        boolean[] skip = new boolean[8];
        int two = 0;
        int iterator = -1;
        while(two < 3) {

            int index = random.nextInt(8);
            if(!skip[index]) {
                skip[index] = true;
                two++;
            }

        }

        for (int i = currentCell.getPosition().x - 1; i <= currentCell.getPosition().x + 1; i++) {
            for (int j = currentCell.getPosition().y - 1; j <= currentCell.getPosition().y + 1; j++) {


                Point position = new Point(i, j);
                position = boundaryCondition.getPosition(position, size);

                if (!(currentCell.getPosition().x == position.x && currentCell.getPosition().y == position.y)) iterator++;

                if( (currentCell.getPosition().x == position.x && currentCell.getPosition().y == position.y) || skip[iterator] || boundaryCondition.skip(position, size) ) {
                    continue;
                }

                neighbors.add(cells.get(position.x).get(position.y));

            }
        }

        return neighbors;
    }

}
