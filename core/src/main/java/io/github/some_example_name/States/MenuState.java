package io.github.some_example_name.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


import java.io.FileNotFoundException;

import io.github.some_example_name.Main;


public class MenuState extends State {
    private Stage stage;
    private ScrollPane scrollPane;
    private Table container;
    private TextureRegion background;
    private static int countLevel = 20;

    private ImageButton createImageButton(int level,Texture buttonTexture) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(240*Main.SIZECHANGE.y);
        style.imageUp.setMinWidth(240*Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);


        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    gsm.set(new PlayState(gsm, level + 1));
                } catch (Exception e) {

                }
            }
        });

        return button;
    }

    public MenuState(GameStateManager gsm) {
        super(gsm);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        container = new Table();
        //!!!!!!container.background(new TextureRegionDrawable(new Texture("menuBackground.png")));
        container.defaults().pad(60*Main.SIZECHANGE.y,90*Main.SIZECHANGE.x,60*Main.SIZECHANGE.y,90*Main.SIZECHANGE.x);

        container.defaults().size(240*Main.SIZECHANGE.x,240*Main.SIZECHANGE.y);


        for (int i = 0; i < countLevel/4+1; i++) {
            try{
                container.add(createImageButton(i*4+1,new Texture("levels/"+(i*4+2)+".png")));
            }catch (Exception e){
                container.add(createImageButton(i*4+1,new Texture("levels/lock.png")));
            }
            try{
                container.add(createImageButton(i*4,new Texture("levels/"+(i*4+1)+".png")));
            }catch (Exception e){
                container.add(createImageButton(i*4,new Texture("levels/lock.png")));
            }
            container.row();

            try{
                container.add(createImageButton(i*4+2,new Texture("levels/"+(i*4+3)+".png")));
            }catch (Exception e){
                container.add(createImageButton(i*4+2,new Texture("levels/lock.png")));
            }
            try{
                container.add(createImageButton(i*4+3,new Texture("levels/"+(i*4+4)+".png")));
            }catch (Exception e){
                container.add(createImageButton(i*4+3,new Texture("levels/lock.png")));
            }
            container.row();
        }

        background = new TextureRegion(new Texture("menuBackground.png"));
        background.flip(true,false);

        container.setBackground(new TextureRegionDrawable(background));


        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);



        scrollPane = new ScrollPane(container);
        scrollPane.setFillParent(true);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false,false);

        stage.addActor(scrollPane);
        //-----------------------------------------
    }

    @Override
    public void handleInpute() {
    }

    @Override
    public void update(float dt) {
        handleInpute();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        camera.update();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
