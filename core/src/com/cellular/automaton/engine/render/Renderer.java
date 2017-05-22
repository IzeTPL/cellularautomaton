package com.cellular.automaton.engine.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cellular.automaton.engine.logic.Cell;
import com.cellular.automaton.engine.logic.Point;
import com.cellular.automaton.engine.logic.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marian on 20.05.2017.
 */
public class Renderer extends Thread {

    List<List<Cell>> cells = new ArrayList<>();
    ShapeRenderer shapeRenderer;
    Camera camera;
    Point size;
    float width;
    float height;

    public Renderer(List< List<Cell> > cells, Camera camera, Point size, float width, float height) {

        super();
        this.cells = cells;
        this.camera = camera;
        this.size = size;
        this.width = width;
        this.height = height;
        shapeRenderer = new ShapeRenderer();

    }

    public void run() {

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (List<Cell> cellsRow: cells) {

            for (Cell cell : cellsRow) {

                if(cell.getCurrentState() == com.cellular.automaton.engine.logic.State.ALIVE || cell.getColor().equals(cell.getNextColor() ) )  {
                    shapeRenderer.setColor(cell.getColor());
                    shapeRenderer.rect(cell.getPosition().x * Gdx.graphics.getHeight() / size.x, Gdx.graphics.getHeight() - cell.getPosition().y * Gdx.graphics.getHeight() / size.y - Gdx.graphics.getHeight() / size.y, width, height);
                }

            }

        }

        shapeRenderer.end();

    }

}
