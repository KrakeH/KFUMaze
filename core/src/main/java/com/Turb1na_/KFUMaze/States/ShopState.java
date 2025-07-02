package com.Turb1na_.KFUMaze.States;

import com.Turb1na_.KFUMaze.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class ShopState implements Screen {
    final Main game;
    private boolean isShowed=false;
    private Stage stage;
    private BitmapFont font;
    private BitmapFont font2;
    private BitmapFont font3;
    private float tempSound;
    private float tempMusic;
    private ScrollPane scrollPane;
    private Table container;
    private Slider MusicSlider;
    private Slider SoundSlider;
    private ImageButton homeBtn;
    private ImageButton paramBtn;
    private ImageButton cancelBtn;
    private Image parametrsBackground;
    private TextureRegionDrawable SliderBack;
    private TextureRegionDrawable Knob;
    private Slider.SliderStyle style;

    private List<TextButton> textButtons = new ArrayList<>();

    private String[] prices={
        "Equipped", "10", "50", "100", "500", "1000"
    };

    private Image Coins;

    private void create(){
        /// ----Music----------
        game.sm.setVolume();
        /// ----------------------

        /// -------------Sliders---------------
        MusicSlider.setValue(game.sm.MusicVolume);
        SoundSlider.setValue(game.sm.SoundVolume);
        /// ----------------------------------

        for (int i = 0; i < prices.length; i++) {
            prices[i]=game.prefs.getString("price"+i);
        }
    }

    private ImageButton createImageButton(Texture buttonTexture, float Height, float Width) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(Height * Main.SIZECHANGE.y);
        style.imageUp.setMinWidth(Width * Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        return button;
    }
    private ImageButton createImageButton(int level, Texture buttonTexture) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(240);
        style.imageUp.setMinWidth(240*Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(String.valueOf(textButtons.get(level).getText()).equals("Equip")){
                    for (int i = 0; i < textButtons.size(); i++) {
                        if(String.valueOf(textButtons.get(i).getText()).equals("Equipped"))
                            textButtons.get(i).setText("Equip");
                    }
                    textButtons.get(level).setText("Equipped");
                    game.sm.Star.stop();
                    game.sm.Star.play(game.sm.SoundVolume);
                    for (int i = 0; i < textButtons.size(); i++) {
                        if(String.valueOf(textButtons.get(i).getText()).equals("Equipped")) {
                            game.prefs.putInteger("Skin", i);
                            game.Skin=i;
                        }
                        game.prefs.putString("price"+i, String.valueOf(textButtons.get(i).getText()));
                    }
                }
                game.prefs.flush();
            }
        });
        return button;
    }

    private TextButton createTextButton(String text, BitmapFont font) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        style.up = new TextureRegionDrawable(new TextureRegion(game.tm.priceBackground));
        style.up.setMinHeight(60);
        style.up.setMinWidth(150 * Main.SIZECHANGE.x);
        TextButton button = new TextButton(text, style);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    if (game.money < Integer.parseInt(String.valueOf(button.getText())))
                        game.sm.Blocked.play(game.sm.SoundVolume);
                    else {
                        game.money -= Integer.parseInt(text);
                        game.prefs.putInteger("Coins",game.money);
                        game.sm.Star.stop();
                        game.sm.Star.play(game.sm.SoundVolume);
                        button.setText("Equip");
                        for (int i = 0; i < textButtons.size(); i++) {
                            game.prefs.putString("price"+i, String.valueOf(textButtons.get(i).getText()));
                        }
                        game.prefs.flush();
                    }
                } catch (Exception e) {
                    if(String.valueOf(button.getText()).equals("Equip")){
                        for (int i = 0; i < textButtons.size(); i++) {
                            if(String.valueOf(textButtons.get(i).getText()).equals("Equipped"))
                                textButtons.get(i).setText("Equip");
                        }
                        button.setText("Equipped");
                        game.sm.Star.stop();
                        game.sm.Star.play(game.sm.SoundVolume);
                        for (int i = 0; i < textButtons.size(); i++) {
                            if(String.valueOf(textButtons.get(i).getText()).equals("Equipped")) {
                                game.prefs.putInteger("Skin", i);
                                game.Skin=i;
                            }
                            game.prefs.putString("price"+i, String.valueOf(textButtons.get(i).getText()));
                        }
                    }
                    game.prefs.flush();
                }
            }
        });
        return button;
    }

    public ShopState(final Main game,Stage stage) {
        this.game=game;
        this.stage=stage;


        tempSound = game.prefs.getFloat("Sound");
        tempMusic = game.prefs.getFloat("Music");

        /// -----Sliders-----
        Knob = new TextureRegionDrawable(game.tm.knob);
        SliderBack = new TextureRegionDrawable(game.tm.sliderBack);
        SliderBack.setMinSize(48 * 15 * Main.SIZECHANGE.x, 1 * 15 * Main.SIZECHANGE.y);
        Knob.setMinSize(3 * 15 * Main.SIZECHANGE.x, 7 * 15 * Main.SIZECHANGE.y);
        style = new Slider.SliderStyle(SliderBack, Knob);
        MusicSlider = new Slider(0, 1, 0.01f, false, style);
        SoundSlider = new Slider(0, 1, 0.01f, false, style);
        /// -------------------

        if(!game.prefs.contains("price0")){
            for (int i = 0; i < prices.length; i++) {
                game.prefs.putString("price"+i,prices[i]);
            }
            game.prefs.flush();
        }

        create();
        /// -----Font-----------
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (40 * Main.SIZECHANGE.x);
        parameter.color = new Color(47 / 255f, 54 / 255f, 153 / 255f, 1);
        font = generator.generateFont(parameter);
        parameter.size = (int) (5*15 * Main.SIZECHANGE.y);
        parameter.color = new Color(47 / 255f, 54 / 255f, 153 / 255f, 1);
        font3=generator.generateFont(parameter);
        parameter.color = Color.BLACK;
        parameter.size = (int) (4 * 15 * Main.SIZECHANGE.y);
        parameter.borderWidth = (int) 8 * Main.SIZECHANGE.y;
        parameter.borderColor = new Color(180 / 255f, 180 / 255f, 180 / 255f, 1);
        font2 = generator.generateFont(parameter);
        generator.dispose();

        container = new Table();
        Coins = new Image(game.tm.coinValue);

        homeBtn = createImageButton(game.tm.homeBtn, 150, 150);
        paramBtn = createImageButton(game.tm.paramBtn, 150, 150);
        cancelBtn = createImageButton(game.tm.cancelBtn, 60, 60);
        homeBtn.setPosition(Main.WIDTH - (homeBtn.getWidth() + 30), Main.HEIGHT - (homeBtn.getHeight() + 60 + paramBtn.getHeight()));
        paramBtn.setPosition(Main.WIDTH - (paramBtn.getWidth() + 30), Main.HEIGHT - (paramBtn.getHeight() + 30));

        parametrsBackground = new Image(game.tm.paramBackground);
        parametrsBackground.setSize(960 * Main.SIZECHANGE.x, 540 * Main.SIZECHANGE.y);
        parametrsBackground.setPosition(Main.WIDTH / 2 - parametrsBackground.getWidth() / 2, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2);


        scrollPane = new ScrollPane(container);
        scrollPane.setFillParent(true);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, false);

        Coins.setSize(120 * Main.SIZECHANGE.x, 120 * Main.SIZECHANGE.y);
        Coins.setPosition((30), Main.HEIGHT - (150 * Main.SIZECHANGE.y));

        container.defaults().size(240 * Main.SIZECHANGE.x, 240);
        container.defaults().pad(120, 45 * Main.SIZECHANGE.x, 60, 45 * Main.SIZECHANGE.x);

        for (int i = 0; i < game.tm.skins.length; i++) {
            container.add(createImageButton(i,game.tm.skins[i]));
            if ((i + 1) % 3 == 0) {
                container.row();
                container.defaults().pad(0, 22.5f * Main.SIZECHANGE.x, 120, 22.5f * Main.SIZECHANGE.x);
                container.defaults().size(300 * Main.SIZECHANGE.x, 60);
                for (int j = i-2; j < i+1; j++) {
                    TextButton tempButton=createTextButton(prices[j], font);
                    textButtons.add(tempButton);
                    container.add(tempButton);
                }
                container.row();
                container.defaults().pad(120, 45 * Main.SIZECHANGE.x, 60, 45 * Main.SIZECHANGE.x);
                container.defaults().size(240 * Main.SIZECHANGE.x, 240);
            }
        }

        SliderBack.setMinSize(48 * 15 * Main.SIZECHANGE.x, 1 * 15 * Main.SIZECHANGE.y);
        Knob.setMinSize(3 * 15 * Main.SIZECHANGE.x, 7 * 15 * Main.SIZECHANGE.y);
        style = new Slider.SliderStyle(SliderBack, Knob);
        MusicSlider.setBounds(Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 8 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 19 * 15 * Main.SIZECHANGE.y, 48 * 15 * Main.SIZECHANGE.x, 7 * 15 * Main.SIZECHANGE.y);
        SoundSlider.setBounds(Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 8 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 4 * 15 * Main.SIZECHANGE.y, 48 * 15 * Main.SIZECHANGE.x, 7 * 15 * Main.SIZECHANGE.y);
        MusicSlider.setVisible(false);
        SoundSlider.setVisible(false);

        stage.addActor(scrollPane);
        stage.addActor(homeBtn);
        stage.addActor(paramBtn);
        stage.addActor(parametrsBackground);
        stage.addActor(Coins);
        stage.addActor(MusicSlider);
        stage.addActor(SoundSlider);
        stage.addActor(cancelBtn);

        parametrsBackground.setVisible(false);
        cancelBtn.setVisible(false);

        /// ------------------
        homeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!parametrsBackground.isVisible()) {
                    game.sm.SoundBtn.play(game.sm.SoundVolume);
                    game.setScreen(game.getMenuState());
                    game.getMenuState().show();
                    game.getShopState().hide();
                }
            }
        });

        cancelBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MusicSlider.setVisible(false);
                SoundSlider.setVisible(false);
                parametrsBackground.setVisible(false);
                cancelBtn.setVisible(false);
                game.sm.SoundBtn.play(game.sm.SoundVolume);
            }
        });

        paramBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parametrsBackground.setVisible(true);
                MusicSlider.setVisible(true);
                SoundSlider.setVisible(true);
                cancelBtn.setPosition(Main.WIDTH / 2 + parametrsBackground.getWidth() / 2 - 15 * 5f * Main.SIZECHANGE.x, Main.HEIGHT / 2 + parametrsBackground.getHeight() / 2 - 5 * 15 * Main.SIZECHANGE.y);
                cancelBtn.setVisible(true);
                game.sm.SoundBtn.play(game.sm.SoundVolume);

            }
        });

        MusicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tempMusic = MusicSlider.getValue();
                game.sm.MusicVolume=MusicSlider.getValue();
                game.prefs.putFloat("Music", tempMusic);
                game.prefs.flush();
                game.sm.setVolume();
            }
        });

        SoundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tempSound = SoundSlider.getValue();
                game.sm.SoundVolume=SoundSlider.getValue();
                game.prefs.putFloat("Sound", tempSound);
                game.prefs.flush();
                game.sm.setVolume();
            }
        });
    }


    @Override
    public void show() {
        isShowed=true;
        create();
        Coins.setVisible(true);
        homeBtn.setVisible(true);
        paramBtn.setVisible(true);
        scrollPane.setVisible(true);
    }

    @Override
    public void render(float delta) {
        /// --------------------------------
        if(isShowed) {
            ScreenUtils.clear(180 / 255f, 180 / 255f, 180 / 255f, 1);
            game.camera.update();
            game.sb.setProjectionMatrix(game.camera.combined);

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();

            game.sb.begin();
            if (parametrsBackground.isVisible()) {
                font3.draw(game.sb, String.valueOf((int) (100 * game.sm.SoundVolume)), Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 42 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 17 * 15 * Main.SIZECHANGE.y);
                font3.draw(game.sb, String.valueOf((int) (100 * game.sm.MusicVolume)), Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 42 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 32 * 15 * Main.SIZECHANGE.y);
            }
            font2.draw(game.sb, String.valueOf(game.money), 36 * Main.SIZECHANGE.x + Coins.getWidth(), Main.HEIGHT  -60 * Main.SIZECHANGE.y);
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
        homeBtn.setVisible(false);
        paramBtn.setVisible(false);
        Coins.setVisible(false);
        scrollPane.setVisible(false);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
