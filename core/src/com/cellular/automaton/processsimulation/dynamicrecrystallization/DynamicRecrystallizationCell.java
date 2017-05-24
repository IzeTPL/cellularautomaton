package com.cellular.automaton.processsimulation.dynamicrecrystallization;

import com.badlogic.gdx.graphics.Color;
import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.State;
import com.cellular.automaton.processsimulation.naiveseedsgrowth.NaiveSeedsGrowthBoard;
import com.cellular.automaton.processsimulation.naiveseedsgrowth.NaiveSeedsGrowthCell;

import java.util.*;

/**
 * Created by marian on 06.05.17.
 */
public class DynamicRecrystallizationCell extends Cell {

    protected Integer seedID;
    protected Integer nextSeedID;
    protected static HashMap<Integer, Color> seedList;
    private double ro;
    private static double roCritical;
    public static double roPerCell;
    public static double globalRo;
    public static double rest;

    public DynamicRecrystallizationCell(NaiveSeedsGrowthCell cell, int size) {

        super(cell.getPosition().x, cell.getPosition().y);

        this.currentState = cell.getCurrentState();
        this.nextState = cell.getNextState();
        this.position = cell.getPosition();
        this.color = cell.getColor();
        this.nextColor = cell.getNextColor();
        this.seedID = cell.getSeedID();
        this.nextSeedID = cell.getNextSeedID();
        seedList = NaiveSeedsGrowthCell.getSeedList();

        ro = 0;
        roCritical = (86710969050178.5 / 9.41268203527779) + (1.0 - (86710969050178.5 / 9.41268203527779) ) * Math.exp(-9.41268203527779*0.065) / (double)size;

    }

    @Override
    public boolean checkNeighbors() {

        if(currentState == State.ALIVE) {

            HashMap<Color, Integer> neighborSeeds = new HashMap<>();

            int sameID = 0;
            Color neighborColor = new Color(Color.GOLD);
            boolean recrystallized = false;
            double maxDensity = 0;

            for (Cell cell : neighbors) {

                DynamicRecrystallizationCell dynamicRecrystallizationCell = (DynamicRecrystallizationCell) cell;

                if(Objects.equals(dynamicRecrystallizationCell.seedID, seedID)) {

                    if(neighborSeeds.containsKey(((DynamicRecrystallizationCell) cell).getSeedID())) {

                        neighborSeeds.replace(((DynamicRecrystallizationCell) cell).getColor(), neighborSeeds.get(((DynamicRecrystallizationCell) cell).getColor()), neighborSeeds.get(((DynamicRecrystallizationCell) cell).getColor()) + 1);

                    } else {

                        neighborSeeds.put(((DynamicRecrystallizationCell) cell).getColor(), 1);

                    }

                    sameID++;
                    maxDensity += dynamicRecrystallizationCell.ro;

                }

                int max = -1;

                for (Map.Entry<Color, Integer> entry : neighborSeeds.entrySet())
                {
                    if(entry.getValue() > max && entry.getKey().equals(seedID) ) {

                        max = entry.getValue();
                        neighborColor = entry.getKey();

                    }

                }

                if(dynamicRecrystallizationCell.currentState == State.RECRYSTALLIZED && Objects.equals(seedID, dynamicRecrystallizationCell.seedID)) {

                    recrystallized = true;
                    neighborColor = dynamicRecrystallizationCell.getColor();

                }

                if(sameID == neighbors.size()) {

                    ro += 0.2 * roPerCell;
                    rest += 0.8 * roPerCell;

                } else {

                    ro += 0.8 * roPerCell;
                    rest += 0.2 * roPerCell;

                }

            }

            if(recrystallized && maxDensity > ro) {

                nextState = State.RECRYSTALLIZED;
                nextColor = neighborColor;
                ro = globalRo;

                return true;

            }

            if(ro > roCritical && neighbors.size() != sameID) {

                Random random = new Random();
                Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
                while((color.r + color.g + color.a) < 0.5f && color == this.color) {
                    color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
                }

                    nextState = State.RECRYSTALLIZED;
                    nextColor = color;
                    ro = globalRo;

                }

                return true;

            }

        return false;

    }

    public boolean addRest(double rest) {

        boolean done = false;

        //if(currentState == State.ALIVE) {

            int sameID = 0;

            for (Cell cell : neighbors) {

                DynamicRecrystallizationCell dynamicRecrystallizationCell = (DynamicRecrystallizationCell) cell;

                if(Objects.equals(dynamicRecrystallizationCell.seedID, seedID)) {

                    sameID++;

                }

                if(sameID != neighbors.size()) {

                    Random random = new Random();
                    if(random.nextInt(16) == 1)
                        ro += rest;

                }

            }

        //}

        return done;

    }

    @Override
    public void update() {

        if( (currentState == State.ALIVE && nextState == State.RECRYSTALLIZED) ) {

            color = nextColor;

        }

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
