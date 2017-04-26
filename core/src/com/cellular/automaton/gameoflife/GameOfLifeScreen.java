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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.cellular.automaton.AbstractApplication;
import com.cellular.automaton.engine.AbstractScreen;
import com.cellular.automaton.engine.DrawableColor;

public class GameOfLifeScreen extends AbstractScreen{

    private TextField widthField;
    private TextField heightField;
    private Label widthLabel;
    private Label heightLabel;
    private Button seedButton;
    private Button toggleButton;
    private Button clearButton;
    private Button nextButton;

    public GameOfLifeScreen(AbstractApplication abstractApplication) {

        super(abstractApplication);
        this.abstractApplication = abstractApplication;

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

        seedButton.addListener(new ClickListener());
        toggleButton.addListener(new ClickListener());
        clearButton.addListener(new ClickListener());
        nextButton.addListener(new ClickListener());

        table.add(widthLabel).expandX();
        table.add(heightLabel).expandX();
        table.row();
        table.add(widthField).width(100);
        table.add(heightField).width(100);
        table.row();
        table.add(seedButton).expandX();
        table.add(toggleButton).expandX();
        table.row();
        table.add(clearButton).expandX();
        table.add(nextButton).expandX();

        logic = new GameOfLifeLogic(100,100);

    }

    public void update(float delta) {

        super.update(delta);
        timer+=delta;

    }


    @Override
    public void render(float delta) {

        super.render(delta);

        update(delta);

        if(!logic.isPaused && timer > 1/5f) {
            logic.iterate();
            timer = 0;
        }

        handleInput();

        logic.board.draw(shapeRenderer, cellCamera);

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

        if(Gdx.input.isKeyJustPressed(Input.Keys.S) ) {

            logic.board.seed();

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.C) ) {

            logic.board.clear();

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.I) ) {

            logic.iterate();

        }

        if(Gdx.input.justTouched()) {

            Vector2 vector2 = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            vector2 = cellViewport.unproject(vector2);

            if(vector2.x > 0 && vector2.y > 0) {
                logic.board.swap( (int) (vector2.x / (Gdx.graphics.getHeight()/(logic.board.getSize().x-2) ) ), (int) (vector2.y / (Gdx.graphics.getHeight()/(logic.board.getSize().y-2) ) ) );
            }

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {

            logic.pause();

        }

        if(seedButton.isPressed() && Gdx.input.justTouched()) {

            logic.board.seed();
            logic = new GameOfLifeLogic(Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText() ) );

        }

        if(toggleButton.isPressed() && Gdx.input.justTouched()) {

            logic.pause();

        }

        if(clearButton.isPressed() && Gdx.input.justTouched()) {

            logic.board.clear();

        }

        if(nextButton.isPressed() && Gdx.input.justTouched()) {

            logic.iterate();

        }

    }

}
