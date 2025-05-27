package io.github.some_example_name.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Timer;

import io.github.some_example_name.Main;
import io.github.some_example_name.Sprites.Button;

public class StartState extends State {

    private Texture background;
    Preferences prefs=Gdx.app.getPreferences("Game");
    private Button play;
    private Music SoundBtn=Gdx.audio.newMusic(Gdx.files.internal("Audio/ButtonSound.wav"));
    private boolean[][] Save= {
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false}};

    public StartState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        background = new Texture("background.png");

        if(!prefs.contains("00")){
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 3; j++) {
                    prefs.putBoolean(""+i+j,false);
                }
            }
            prefs.flush();
        }
        else{
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 3; j++) {
                    Save[i][j]=prefs.getBoolean(""+i+j);
                }
            }
        }

        play = new Button(1080 / 2 - 205, 1920 / 2 - 65, 410, 130, new Texture("Buttons/playbtn.png"));
    }

    @Override
    public void handleInpute() {
        if (Gdx.input.justTouched()) {
            SoundBtn.play();
            gsm.set(new MenuState(gsm, Save));
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
