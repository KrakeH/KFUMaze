package com.Turb1na_.KFUMaze.States;

import com.Turb1na_.KFUMaze.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AdState implements Screen {
    final Main game;
    private int level;
    private boolean isShowed = false;
    private float Timer = 0;
    private Image way;
    private Image progressBar;
    private Stage stage;
    private ImageButton nextButton;

    private ImageButton createImageButton(Texture buttonTexture, float Height, float Width) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(Height * Main.SIZECHANGE.y);
        style.imageUp.setMinWidth(Width * Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        return button;
    }

    public void regenerate(int level) {
        this.level = level;
    }

    public AdState(final Main game, Stage stage) {
        this.game = game;
        this.stage = stage;

        nextButton = createImageButton(game.tm.adBtn, 16 * 15, 66 * 15);
        nextButton.setPosition(Main.WIDTH / 2 - nextButton.getWidth() / 2, Main.SIZECHANGE.y * 4 * 15);

        ///-------------------------
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sm.SoundBtn.play(game.sm.SoundVolume);

                game.getPlayState().regenerate(level + 1);
                game.setScreen(game.getPlayState());
                game.getPlayState().show();
                game.getMenuState().hide();
            }
        });
        ///-------------------------

        way = new Image(game.tm.way);
        progressBar = new Image(game.tm.progressBar);

        way.setSize(72 * 15 * Main.SIZECHANGE.x, 16 * 15 * Main.SIZECHANGE.y);
        way.setPosition(Main.WIDTH / 2 - way.getWidth() / 2, Main.SIZECHANGE.y * 4 * 15);

        progressBar.setSize(66 / 62f * 74 / 2 * 15 * Main.SIZECHANGE.x, 8 * 15 * Main.SIZECHANGE.y);
        progressBar.setPosition(Main.WIDTH / 2 - 3*progressBar.getWidth() / 2 , Main.SIZECHANGE.y * 4 * 15 + way.getHeight() / 4);
        stage.addActor(progressBar);
        stage.addActor(way);
        stage.addActor(nextButton);
    }

    @Override
    public void show() {
        progressBar.setPosition(Main.WIDTH / 2 - 3*progressBar.getWidth() / 2 , Main.SIZECHANGE.y * 4 * 15 + way.getHeight() / 4);
        progressBar.setVisible(true);
        isShowed = true;
        way.setVisible(true);
        Timer = 0;
    }

    @Override
    public void render(float delta) {
        if (isShowed) {
            if (Timer != -1) {
                progressBar.setPosition(Main.WIDTH / 2 - 3*progressBar.getWidth() / 2 +progressBar.getWidth()*Timer/5f, Main.SIZECHANGE.y * 4 * 15 + way.getHeight() / 4);
                Timer += delta;
                if (Timer > 5) {
                    Timer = -1;
                    game.hearth += 1;
                    game.prefs.putLong("Hearth", game.hearth);
                    game.prefs.flush();
                    nextButton.setVisible(true);
                }
            }
            game.camera.update();
            game.sb.setProjectionMatrix(game.camera.combined);

            game.sb.begin();
            game.sb.draw(game.tm.Ad, 0, 0, Main.WIDTH, Main.HEIGHT);
            game.sb.end();

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        nextButton.setVisible(false);
        isShowed = false;
        way.setVisible(false);
        progressBar.setVisible(false);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
