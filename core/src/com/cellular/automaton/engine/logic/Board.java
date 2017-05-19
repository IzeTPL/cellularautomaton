package com.cellular.automaton.engine.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cellular.automaton.engine.logic.boudarycondition.BoundaryCondition;
import com.cellular.automaton.engine.logic.boudarycondition.FixedBoundaryCondition;
import com.cellular.automaton.engine.logic.boudarycondition.PeriodicBoudaryCondition;
import com.cellular.automaton.engine.logic.neighbourhood.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by marian on 20.04.17.
 */
public abstract class Board {

    protected Point size;
    protected List< List<Cell> > cells;
    protected List<BoundaryCondition> boundaryConditions;
    protected List<Neighborhood> neighborhoods;

    public Board() {

        cells = new ArrayList<>();
        boundaryConditions = new ArrayList<>();
        neighborhoods = new ArrayList<>();

        boundaryConditions.add(new FixedBoundaryCondition());
        boundaryConditions.add(new PeriodicBoudaryCondition());

        neighborhoods.add(new MooreNeighbourHood());
        neighborhoods.add(new VonNewmanNeighbourhood());
        neighborhoods.add(new PentagonalRandomNeighbourhood());
        neighborhoods.add(new HexagonalRandomNeighbourhood());
        neighborhoods.add(new HexagonalLeftNeighbourhood());
        neighborhoods.add(new HexagonalRightNeighbourhood());

    }

    public void seed() {

        Random random = new Random();
        for (List<Cell> cellsRow : cells) {

            for (Cell cell : cellsRow) {

                randomize(cell, random);
                cell.update();

            }

        }

    }

    public void clear() {

        for (List<Cell> cellsRow : cells) {

            for ( Cell cell : cellsRow) {
                 cell.setNextState(State.EMPTY);
                 cell.update();
            }

        }

    }

    public void draw(ShapeRenderer shapeRenderer, Camera camera) {

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (List<Cell> cellsRow: cells) {

            for (Cell cell : cellsRow) {

                shapeRenderer.setColor(cell.getColor());
                shapeRenderer.rect(cell.getPosition().x * Gdx.graphics.getHeight() / size.x,Gdx.graphics.getHeight() - cell.getPosition().y * Gdx.graphics.getHeight() / size.y - Gdx.graphics.getHeight() / size.y,Gdx.graphics.getHeight() / size.x, Gdx.graphics.getHeight() / size.y );
            }

        }

        shapeRenderer.end();

    }

    public void randomize(Cell cell, Random random) {

        if (random.nextInt( (int) ( (size.x * size.y) * 0.01f ) ) == 1) {
            cell.setNextState(State.ALIVE);
        }

    }

    public void setNeighbourhood(Neighborhood neighbourhood, BoundaryCondition boundaryCondition) {

        for(List<Cell> cellsRow : cells) {

            for (Cell cell : cellsRow) {

                cell.neighbors = neighbourhood.findNeighbors(cells, cell, boundaryCondition, size);

            }

        }

    }

    public Point getSize() {
        return size;
    }

    public List<BoundaryCondition> getBoundaryConditions() {
        return boundaryConditions;
    }

    public List<Neighborhood> getNeighborhoods() {
        return neighborhoods;
    }
}
