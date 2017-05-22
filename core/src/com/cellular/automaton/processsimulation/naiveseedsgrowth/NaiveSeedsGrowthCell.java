package com.cellular.automaton.processsimulation.naiveseedsgrowth;

import com.badlogic.gdx.graphics.Color;
import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by marian on 06.05.17.
 */
public class NaiveSeedsGrowthCell extends Cell {

    protected Integer seedID;
    protected Integer nextSeedID;
    protected static HashMap<Integer, Color> seedList;

    public NaiveSeedsGrowthCell(int x, int y) {

        super(x, y);
        seedID = 0;
        color = new Color(Color.BLACK);
        nextColor = new Color(Color.BLACK);
        seedList = new HashMap<>();


    }

    @Override
    public boolean checkNeighbors() {

        if(currentState == State.EMPTY) {

            HashMap<Integer, Integer> neighborSeeds = new HashMap<>();

            for (Cell cell : neighbors) {

                if(neighborSeeds.containsKey(((NaiveSeedsGrowthCell) cell).getSeedID())) {

                    neighborSeeds.replace(((NaiveSeedsGrowthCell) cell).getSeedID(), neighborSeeds.get(((NaiveSeedsGrowthCell) cell).getSeedID()), neighborSeeds.get(((NaiveSeedsGrowthCell) cell).getSeedID()) + 1);

                } else {

                    neighborSeeds.put(((NaiveSeedsGrowthCell) cell).getSeedID(), 1);

                }

            }

            int max = -1;

            for (Map.Entry<Integer, Integer> entry : neighborSeeds.entrySet())
            {
                if(entry.getValue() > max && !entry.getKey().equals(0) ) {

                    max = entry.getValue();
                    nextSeedID = entry.getKey();

                }

            }

            if(max != -1) {

                nextColor = seedList.get(nextSeedID);
                nextState = State.ALIVE;

            }

            return true;

        }

        return false;

    }

    @Override
    public void update() {

        if(currentState == State.EMPTY) {

            color = Color.BLACK;
            seedID = 0;

        }
        if( (currentState == State.EMPTY && nextState == State.ALIVE) || (nextState == State.EMPTY && currentState == State.ALIVE) ) {

            color = nextColor;
            seedID = nextSeedID;

        }

        super.update();

    }

    public void swap() {

        Random random = new Random();

        if(currentState == State.ALIVE) {
            nextState = State.EMPTY;
            nextColor = Color.BLACK;
            nextSeedID = 0;
        } else {
            nextState = State.ALIVE;
            nextColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
            nextSeedID = ++NaiveSeedsGrowthBoard.newID;
        }

        update();

    }

    public Integer getSeedID() {
        return seedID;
    }

    public void setSeedID(Integer seedID) {
        this.seedID = seedID;
    }

    public Integer getNextSeedID() {
        return nextSeedID;
    }

    public void setNextSeedID(Integer nextSeedID) {
        this.nextSeedID = nextSeedID;
    }
}
