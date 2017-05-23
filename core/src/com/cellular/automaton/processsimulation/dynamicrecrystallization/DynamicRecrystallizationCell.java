package com.cellular.automaton.processsimulation.dynamicrecrystallization;

import com.badlogic.gdx.graphics.Color;
import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.State;
import com.cellular.automaton.processsimulation.naiveseedsgrowth.NaiveSeedsGrowthBoard;
import com.cellular.automaton.processsimulation.naiveseedsgrowth.NaiveSeedsGrowthCell;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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

    public DynamicRecrystallizationCell(int x, int y, int size) {

        super(x, y);
        roCritical = (86710969050178.5 / 9.41268203527779) + (1.0 - (86710969050178.5 / 9.41268203527779) ) * Math.exp(-9.41268203527779*0.065) / (double)size;
        nextColor = new Color(Color.GOLD);
        ro = 0;
        seedID = 0;
        color = new Color(Color.BLACK);
        seedList = new HashMap<>();

    }

    @Override
    public boolean checkNeighbors() {

        boolean done = false;

        if(currentState == State.ALIVE) {

            int sameID = 0;
            boolean recrystallized = false;
            double maxDensity = 0;

            for (Cell cell : neighbors) {

                DynamicRecrystallizationCell dynamicRecrystallizationCell = (DynamicRecrystallizationCell) cell;

                if(Objects.equals(dynamicRecrystallizationCell.seedID, seedID)) {

                    sameID++;

                }

                if(dynamicRecrystallizationCell.currentState == State.RECRYSTALLIZED) {

                    recrystallized = true;

                }

                if(dynamicRecrystallizationCell.ro > maxDensity) {

                    maxDensity = dynamicRecrystallizationCell.ro;

                }

                if(sameID == neighbors.size()) {

                    ro += 0.2 * roPerCell;
                    rest += 0.8 * roPerCell;

                } else {

                    ro += 0.8 * roPerCell;
                    rest += 0.2 * roPerCell;

                }

            }

            if(recrystallized && maxDensity < ro) {

                ro = globalRo;

            }

            if(ro > roCritical && neighbors.size() != sameID) {

                Random random = new Random();
                Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
                while((color.r + color.g + color.a) < 0.5f) {
                    color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
                }

                    nextState = State.RECRYSTALLIZED;
                    nextColor = color;
                    ro = globalRo;

                }

            }

        return done;

    }

    public boolean addRest(double rest) {

        boolean done = false;

        if(currentState == State.ALIVE) {

            int sameID = 0;

            for (Cell cell : neighbors) {

                DynamicRecrystallizationCell dynamicRecrystallizationCell = (DynamicRecrystallizationCell) cell;

                if(Objects.equals(dynamicRecrystallizationCell.seedID, seedID)) {

                    sameID++;

                }

                if(sameID != neighbors.size()) {

                    ro += rest;

                }

            }

        }

        return done;

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
            nextSeedID = ++DynamicRecrystallizationBoard.newID;
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
