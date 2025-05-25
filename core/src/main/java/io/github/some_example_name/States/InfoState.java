package io.github.some_example_name.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.some_example_name.Main;

public class InfoState extends State{
    private Stage stage;
    private Texture[] info={new Texture("Info/info1.png")};
    private Sound SoundBtn=Gdx.audio.newSound(Gdx.files.internal("Audio/ButtonSound.wav"));
    private OrthographicCamera camera;
    private ImageButton nextButton;

    private ImageButton createImageButton(Texture buttonTexture, float Height, float Width) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(Height * Main.SIZECHANGE.y);
        style.imageUp.setMinWidth(Width * Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        return button;
    }

    public InfoState(GameStateManager gsm,boolean[][] stars) {
        super(gsm);

        camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
        camera.setToOrtho(false);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        nextButton = createImageButton(new Texture("Buttons/nextBtn.png"), 16*15, 66 * 15);
        nextButton.setPosition(Main.WIDTH/2-nextButton.getWidth()/2,Main.SIZECHANGE.y*4*15);

        ///-------------------------
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundBtn.play();
                gsm.set(new MenuState(gsm, stars));
            }
        });
        ///-------------------------

        stage.addActor(nextButton);
    }

    @Override
    public void handleInpute() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        sb.draw(info[0],0,0,Main.WIDTH,Main.HEIGHT);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
