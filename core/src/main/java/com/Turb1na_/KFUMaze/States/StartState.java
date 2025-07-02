package com.Turb1na_.KFUMaze.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.Turb1na_.KFUMaze.Main;

public class StartState implements Screen {
    final  Main game;
    private boolean isShowed=false;

    public StartState(final Main game){
        this.game=game;
    }

    @Override
    public void show() {
        isShowed=true;
    }

    @Override
    public void render(float delta) {
        if(isShowed){
            if(Gdx.input.justTouched()){
                game.sm.SoundBtn.play(game.sm.SoundVolume);

                game.setScreen(game.getMenuState());
                game.getMenuState().show();
                game.getMenuState().update();
                game.getStartState().hide();
                game.sm.MenuMusicPlay();
            }

            game.sb.setProjectionMatrix(game.camera.combined);
            game.sb.begin();
            game.sb.draw(game.tm.startBackground, 0, 0, Main.WIDTH, Main.HEIGHT);
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
    }
}
