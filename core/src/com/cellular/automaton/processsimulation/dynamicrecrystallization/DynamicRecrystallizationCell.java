package com.cellular.automaton.processsimulation.dynamicrecrystallization;

import com.badlogic.gdx.graphics.Color;
import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by marian on 06.05.17.
 */
public class DynamicRecrystallizationCell extends Cell {

    double roC;

    public DynamicRecrystallizationCell(int x, int y) {

        super(x, y);
        nextColor = new Color(Color.GOLD);

    }

    @Override
    public boolean checkNeighbors() {

        boolean done = false;

        if(currentState == State.ALIVE) {

            HashMap<Color, Integer> colors = new HashMap<>();

            for (Cell cell : neighbors) {

                if(colors.containsKey(cell.getColor())) {

                    colors.replace(cell.getColor(), colors.get(cell.getColor()), colors.get(cell.getColor()) + 1);

                } else {

                    colors.put(cell.getColor(), 1);

                }

            }

            int max = -1;
            Color color = new Color(0.5f,0.5f,0.5f,1);

            for (Map.Entry<Color, Integer> entry : colors.entrySet())
            {
                if(entry.getValue() > max && !entry.getKey().equals(Color.BLACK) ) {

                    max = entry.getValue();
                    color = entry.getKey();

                }

            }

            if(max != -1) {

                nextColor = color;
                nextState = State.ALIVE;

            }
            
            done = true;

        }

        return done;

    }

    @Override
    public void update() {

        if(currentState == State.EMPTY) color = Color.BLACK;
        if( (currentState == State.EMPTY && nextState == State.ALIVE) || (nextState == State.EMPTY && currentState == State.ALIVE)) color = nextColor;

        super.update();

    }

    public void swap() {

        Random random = new Random();

        if(currentState == State.ALIVE) {
            setNextState(State.EMPTY);
            nextColor = Color.BLACK;
        } else {
            setNextState(State.ALIVE);
            nextColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
        }

        update();

    }

}
