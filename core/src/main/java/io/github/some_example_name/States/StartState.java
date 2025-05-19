package io.github.some_example_name.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.Main;
import io.github.some_example_name.Sprites.Button;

public class StartState extends State {

    private Texture background;
    private Button play;
    public StartState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        background = new Texture("background.png");

        play =new Button(1080/2-205,1920/2-65,410,130,new Texture("Buttons/playbtn.png"));
    }

    @Override
    public void handleInpute() {
        if (Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));
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
    }
}
