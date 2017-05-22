package com.cellular.automaton.processsimulation.dynamicrecrystallization;

import com.badlogic.gdx.graphics.Color;
import com.cellular.automaton.engine.logic.Board;
import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.Point;
import com.cellular.automaton.engine.logic.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by marian on 06.05.17.
 */
public class DynamicRecrystallizationBoard extends Board {

    protected List<Color> colors;

    public DynamicRecrystallizationBoard(int x, int y) {

        super();

        size = new Point(x, y);

        for (int i = 0; i < size.x; i++) {

            List<Cell> cellsRow = new ArrayList<>();

            for (int j = 0; j < size.y; j++) {

                cellsRow.add(new DynamicRecrystallizationCell(i, j));

            }

            cells.add(cellsRow);

        }

    }

    @Override
    public void clear() {

        for (List<Cell> cellsRow : cells) {

            for ( Cell cell : cellsRow) {
                cell.setNextState(State.EMPTY);
                cell.setNextColor(Color.BLACK);
                cell.update();
            }

        }

    }

    @Override
    public void randomize(Cell cell, Random random) {

        super.randomize(cell, random);

        boolean test = cell.getColor().equals(Color.BLACK);

        if(cell.getNextState() == State.ALIVE && test) {
            Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
            while((color.r + color.g + color.a) < 0.5f) {
                color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
            }
            cell.setColor(color);
            cell.setCurrentState(State.ALIVE);
        }

    }

    public void swap(int x, int y) {

        ( (DynamicRecrystallizationCell) cells.get(x).get(y) ).swap();

    }

    public void seed(int distance) {

        Random random = new Random();

        int distanceY = 0;


        for (List<Cell> cellsRow : cells) {

            int distanceX = 0;
            if(distanceY == distance + 1) distanceY = 0;
            for (Cell cell : cellsRow) {

                if(distanceX == distance + 1) distanceX = 0;
                if (distanceX == distance && distanceY == distance) {
                    cell.setNextState(State.ALIVE);
                    cell.update();
                    if(cell.getCurrentState() == State.ALIVE ) {
                        cell.setColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1));
                    }
                }
                distanceX++;

            }
            distanceY++;

        }

    }

    public void radiusSeed(int radius) {

        Random random = new Random();
        Point position;
        for (List<Cell> cellsRow : cells) {

            for (Cell cell : cellsRow) {

                boolean inside = false;

                for(int x=cell.getPosition().x-radius; x<cell.getPosition().x+radius; x++) {

                    int yspan = (int) Math.round(radius*Math.sin(Math.acos((cell.getPosition().x-x)/radius) ) );

                    for(int y=cell.getPosition().y-yspan; y<cell.getPosition().y+yspan; y++) {

                        position = new Point(x,y);
                        if(position.x < 0) position.x = size.x - 1;
                        if(position.y < 0) position.y = size.y - 1;
                        if(position.x >= size.x) position.x = 0;
                        if(position.y >= size.y) position.y = 0;
                        if(cells.get(position.x).get(position.y).getCurrentState() == State.ALIVE) inside = true;
                        if(inside) break;

                    }

                    if(inside) break;

                }

                if(!inside) {
                    randomize(cell, random);
                }

            }

        }

    }

}
