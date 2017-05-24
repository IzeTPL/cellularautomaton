package com.cellular.automaton.processsimulation.montecarlo;

import com.badlogic.gdx.graphics.Color;
import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.State;
import com.cellular.automaton.processsimulation.naiveseedsgrowth.NaiveSeedsGrowthCell;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * Created by marian on 06.05.17.
 */
public class MonteCarloCell extends Cell {

    protected Integer seedID;
    protected Integer nextSeedID;
    protected static HashMap<Integer, Color> seedList;

    public MonteCarloCell(NaiveSeedsGrowthCell cell) {

        super(cell.getPosition().x, cell.getPosition().y);

        this.currentState = cell.getCurrentState();
        this.nextState = cell.getNextState();
        this.position = cell.getPosition();
        this.color = cell.getColor();
        this.nextColor = cell.getNextColor();
        this.seedID = cell.getSeedID();
        this.nextSeedID = cell.getNextSeedID();
        seedList = NaiveSeedsGrowthCell.getSeedList();

    }

    @Override
    public boolean checkNeighbors() {

        int energy = 0;
        int newEnergy = 0;
        Random random = new Random();
        int[] neighborsID = new int[neighbors.size()];

        for (int i = 0; i < neighbors.size(); i++) {

            neighborsID[i] = ( (MonteCarloCell) neighbors.get(i) ).seedID;

        }

        int seedID = neighborsID[random.nextInt( neighbors.size() ) ];

        for (Cell cell:neighbors) {

            MonteCarloCell monteCarloCell = (MonteCarloCell) cell;

            if(!Objects.equals(this.seedID, ((MonteCarloCell) cell).getSeedID())) {
                energy++;
            }

            if(!Objects.equals(seedID, ((MonteCarloCell) cell).getSeedID())) {
                newEnergy++;
            }



        }

        if(newEnergy - energy <= 0) {

            nextSeedID = seedID;
            nextColor = seedList.get(seedID);
            return true;

        }



        return false;

    }

    public boolean toRemove() {

        int sameID = 0;

        for (Cell cell: neighbors) {

            if( ( (MonteCarloCell) cell).seedID == seedID ) sameID++;

        }

        return sameID == neighbors.size();

    }

    @Override
    public void update() {

        color = nextColor;
        seedID = nextSeedID;

        super.update();

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
