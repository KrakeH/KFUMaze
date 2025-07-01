package com.Turb1na_.KFUMaze.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.Turb1na_.KFUMaze.Main;

public class InfoState implements Screen {
    final Main game;
    private int level;
    private boolean isShowed=false;
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
    public void regenerate(int level){
        this.level=level-1;
    }
    public InfoState(final Main game,Stage stage) {
        this.game=game;
        this.stage=stage;

        nextButton = createImageButton(game.tm.nextBtn, 16 * 15, 66 * 15);
        nextButton.setPosition(Main.WIDTH / 2 - nextButton.getWidth() / 2, Main.SIZECHANGE.y * 4 * 15);

        ///-------------------------
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sm.SoundBtn.play(game.sm.SoundVolume);

                game.setScreen(game.getMenuState());
                game.sm.MenuMusicPlay();
                game.getMenuState().update();
                game.getMenuState().show();
                game.getInfoState().hide();
            }
        });
        ///-------------------------

        stage.addActor(nextButton);
    }


    @Override
    public void show() {
        nextButton.setVisible(true);
        isShowed=true;
    }

    @Override
    public void render(float delta) {
        if(isShowed) {
            game.camera.update();
            game.sb.setProjectionMatrix(game.camera.combined);

            game.sb.begin();
            game.sb.draw(game.tm.info[level], 0, 0, Main.WIDTH, Main.HEIGHT);
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
        isShowed=false;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
