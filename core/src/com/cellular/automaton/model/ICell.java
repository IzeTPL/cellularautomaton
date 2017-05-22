package com.cellular.automaton.model;

/**
 * Created by marian on 27.04.17.
 */
public interface ICell {

    boolean checkNeighbors();

    void update();

}
