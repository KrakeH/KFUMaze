package com.Turb1na_.KFUMaze.States;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.Turb1na_.KFUMaze.Main;
import com.Turb1na_.KFUMaze.Sprites.Button;

public class StartState implements Screen {
    final  Main game;
    private boolean isShowed=false;
    private Button play;

    public StartState(final Main game){
        this.game=game;
        play = new Button(1080 / 2 - 205, 1920 / 2 - 65, 410, 130, game.tm.playBtn);
    }

    @Override
    public void show() {
        isShowed=true;
    }

    @Override
    public void render(float delta) {
        if(isShowed){
            if (play.onClick()) {
                game.sm.SoundBtn.play(game.sm.SoundVolume);

                game.setScreen(game.getMenuState());
                game.getMenuState().show();
                game.getStartState().hide();
                game.sm.MenuMusicPlay();
            }

            game.sb.setProjectionMatrix(game.camera.combined);
            game.sb.begin();
            game.sb.draw(game.tm.startBackground, 0, 0, Main.WIDTH, Main.HEIGHT);
            play.draw(game.sb);
            game.sb.end();
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
        isShowed=false;
    }

    @Override
    public void dispose() {
        play.dispose();
    }
}
