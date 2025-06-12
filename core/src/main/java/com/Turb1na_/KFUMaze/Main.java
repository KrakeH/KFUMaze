package com.Turb1na_.KFUMaze;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import com.Turb1na_.KFUMaze.States.GameStateManager;
import com.Turb1na_.KFUMaze.States.StartState;

public class Main extends ApplicationAdapter {
    public static int WIDTH = 0;
    public static int HEIGHT = 0;
    public static Vector2 SIZECHANGE;
    private GameStateManager gsm;
    SpriteBatch batch;
    Texture img;

    @Override
    public void create() {
        HEIGHT=Gdx.graphics.getHeight();
        WIDTH=Gdx.graphics.getWidth();
        SIZECHANGE=new Vector2( Main.WIDTH/1080f,Main.HEIGHT/1920f);
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        gsm.push(new StartState(gsm,1,1));
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
