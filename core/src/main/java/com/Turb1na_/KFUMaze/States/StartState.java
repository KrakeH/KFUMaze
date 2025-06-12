package com.Turb1na_.KFUMaze.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.Turb1na_.KFUMaze.Main;
import com.Turb1na_.KFUMaze.Sprites.Button;

public class StartState extends State {

    private Texture background;
    Preferences prefs = Gdx.app.getPreferences("Game");
    private Button play;
    private Music SoundBtn = Gdx.audio.newMusic(Gdx.files.internal("Audio/ButtonSound.wav"));
    private boolean[][] Save = {
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false}};

    public StartState(GameStateManager gsm, float MusicVolume, float SoundVolume) {
        super(gsm, MusicVolume, SoundVolume);
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        background = new Texture("StartBackground.png");

        if (!prefs.contains("00")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    prefs.putBoolean("" + i + j, false);
                }
            }
            prefs.flush();
        } else {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    try {
                        Save[i][j] = prefs.getBoolean("" + i + j);
                    }catch (Exception e){
                        Save[i][j]=false;
                        prefs.putBoolean("" + i + j, false);
                        prefs.flush();
                    }
                }
            }
        }
        if (!prefs.contains("Music")) {
            prefs.putFloat("Music", 1);
            prefs.flush();
        }
        this.MusicVolume= prefs.getFloat("Music");
        if (!prefs.contains("Sound")) {
            prefs.putFloat("Sound", 1);
            prefs.flush();
        }
        this.SoundVolume= prefs.getFloat("Sound");

        play = new Button(1080 / 2 - 205, 1920 / 2 - 65, 410, 130, new Texture("Buttons/playbtn.png"));


        /// -----------Music------------
        SoundBtn.setVolume(SoundVolume);
        /// -----------------------------
    }

    @Override
    public void handleInpute() {
        if (play.onClick()) {
            SoundBtn.setVolume(SoundVolume);
            SoundBtn.play();
            gsm.set(new MenuState(gsm, MusicVolume, SoundVolume, Save));
        }
    }

    @Override
    public void update(float dt) {
        handleInpute();
        //System.out.println(Gdx.input.isTouched());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
        play.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        play.dispose();
        SoundBtn.dispose();
    }
}
