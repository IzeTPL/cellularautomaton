package com.cellular.automaton.gameoflife;

import java.util.ArrayList;

/**
 * Created by marian on 20.04.17.
 */
public class History {

    private ArrayList<int[][]> stepList;

    public History() {

        stepList = new ArrayList<>();

    }

    public ArrayList<int[][]> getStepList() {
        return stepList;
    }

    public void setStepList(ArrayList<int[][]> stepList) {
        this.stepList = stepList;
    }

}
