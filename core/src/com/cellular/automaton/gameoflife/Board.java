package com.cellular.automaton.gameoflife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

/**
 * Created by marian on 20.04.17.
 */
public class Board {

    private History history;
    private int[][] grid;
    private Size size;

    public Board(int x, int y) {

        history = new History();
        size = new Size(x+2,y+2);
        grid = new int[size.x][size.y];
        seed();

    }

    public void seed() {

        clear();

        Random random = new Random();

        for (int i = 1; i < size.x - 1; i++) {
            for (int j = 1; j < size.y - 1; j++) {
                grid[i][j] = random.nextInt(2);
            }
        }

        history.getStepList().add(grid);

    }

    public void clear() {

        for (int i = 0; i < size.x; i++) {
            for (int j = 0; j < size.y; j++) {
                grid[i][j] = 0;
            }
        }

    }

    public void draw(ShapeRenderer shapeRenderer, Camera camera) {

        for (int i = 1; i < size.x-1; i++) {
            for (int j = 1; j < size.y-1; j++) {

                if(grid[i][j] == 1) {

                    shapeRenderer.setProjectionMatrix(camera.combined);
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.setColor(1, 1, 1, 1);
                    shapeRenderer.rect((i-1)*Gdx.graphics.getHeight()/(size.x-2), Gdx.graphics.getHeight() - ((j-1)*Gdx.graphics.getHeight()/(size.y-2)) - Gdx.graphics.getHeight()/(size.y-2),Gdx.graphics.getHeight()/(size.x-2),Gdx.graphics.getHeight()/(size.y-2));
                    shapeRenderer.end();

                }

            }

        }

    }

    public void swap(int x,int y) {

        x++;
        y++;
        if(grid[x][y] == 1) grid[x][y] = 0;
        else grid[x][y] = 1;

    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
