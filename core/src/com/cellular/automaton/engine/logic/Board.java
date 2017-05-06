package com.cellular.automaton.engine.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cellular.automaton.engine.logic.boudarycondition.BoundaryCondition;
import com.cellular.automaton.engine.logic.neighbourhood.Neighborhood;

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

    }

    public void seed() {

        Random random = new Random();
        for (List<Cell> cellsRow : cells) {

            for (Cell cell : cellsRow) {

                if (random.nextInt(2) == 1) {
                    cell.setNextState(State.ALIVE);
                    cell.update();
                }

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
                shapeRenderer.rect(cell.getPosition().x * Gdx.graphics.getHeight() / size.x, Gdx.graphics.getHeight() - cell.getPosition().y * Gdx.graphics.getHeight() / size.y - Gdx.graphics.getHeight() / size.y, Gdx.graphics.getHeight() / size.x, Gdx.graphics.getHeight() / size.y);

            }
        }

        shapeRenderer.end();

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
}
