package com.cellular.automaton.gameoflife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.cellular.automaton.engine.Application;
import com.cellular.automaton.engine.render.AbstractScreen;
import com.cellular.automaton.engine.render.DrawableColor;

public class GameOfLifeScreen extends AbstractScreen {

    private TextField widthField;
    private TextField heightField;
    private Label widthLabel;
    private Label heightLabel;
    private Button seedButton;
    private Button toggleButton;
    private Button clearButton;
    private Button nextButton;
    private Button resizeButton;

    public GameOfLifeScreen(Application application) {

        super(application);
        this.application = application;

        widthLabel = new Label("width", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        heightLabel = new Label("height", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = new BitmapFont();
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = DrawableColor.getColor(Color.WHITE);

        widthField = new TextField("10", textFieldStyle);
        heightField = new TextField("10", textFieldStyle);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.WHITE;
        textButtonStyle.overFontColor = Color.BLACK;
        textButtonStyle.over = DrawableColor.getColor(Color.WHITE);


        seedButton = new TextButton("Seed", textButtonStyle);
        toggleButton = new TextButton("Play", textButtonStyle);
        clearButton = new TextButton("Clear", textButtonStyle);
        nextButton = new TextButton("Next", textButtonStyle);
        resizeButton = new TextButton("Resize", textButtonStyle);

        seedButton.addListener(new ClickListener());
        toggleButton.addListener(new ClickListener());
        clearButton.addListener(new ClickListener());
        nextButton.addListener(new ClickListener());
        resizeButton.addListener(new ClickListener());


        table.add(widthLabel).expandX();
        table.add(heightLabel).expandX();
        table.row();
        table.add(widthField).width(uiViewport.getWorldWidth() / 2);
        table.add(heightField).width(uiViewport.getWorldWidth() / 2);
        table.row();
        table.add(seedButton).expandX();
        table.add(toggleButton).expandX();
        table.row();
        table.add(clearButton).expandX();
        table.add(nextButton).expandX();
        table.row();
        table.add(resizeButton).expandX();

        logic = new GameOfLifeLogic(20, 20);

    }

    public void update(float delta) {

        super.update(delta);
        timer += delta;

    }


    @Override
    public void render(float delta) {

        super.render(delta);

        update(delta);

        if (!logic.isPaused() && timer > 1 / 5f) {
            logic.iterate();
            timer = 0;
        }

        handleInput();

    }

    @Override
    public void resize(int width, int height) {

        super.resize(width, height);

    }

    @Override
    public void dispose() {

        super.dispose();

    }

    public void handleInput() {


        if (Gdx.input.justTouched()) {

            Vector2 vector2 = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            vector2 = cellViewport.unproject(vector2);

            if (vector2.x > 0 && vector2.y > 0) {
                logic.click((int) (vector2.x / (Gdx.graphics.getHeight() / logic.getBoard().getSize().x)), (int) (vector2.y / (Gdx.graphics.getHeight() / logic.getBoard().getSize().y)));
            }

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {

            logic.pause();

        }

        if (seedButton.isPressed() && Gdx.input.justTouched()) {

            logic.getBoard().seed();

        }

        if (toggleButton.isPressed() && Gdx.input.justTouched()) {

            logic.pause();

        }

        if (clearButton.isPressed() && Gdx.input.justTouched()) {

            logic.getBoard().clear();

        }

        if (nextButton.isPressed() && Gdx.input.justTouched() && logic.isPaused()) {

            logic.iterate();

        }

        if (resizeButton.isPressed() && Gdx.input.justTouched()) {

            logic = new GameOfLifeLogic(Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText()));

        }

    }

}
