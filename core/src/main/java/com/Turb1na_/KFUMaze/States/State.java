package com.Turb1na_.KFUMaze.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    public OrthographicCamera camera;
    public Vector3 mouse;
    public float MusicVolume;
    public float SoundVolume;
    public GameStateManager gsm;

    public State(GameStateManager gsm,float MusicVolume,float SoundVolume) {
        this.gsm = gsm;
        this.MusicVolume=MusicVolume;
        this.SoundVolume=SoundVolume;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    public abstract void handleInpute();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();
}
