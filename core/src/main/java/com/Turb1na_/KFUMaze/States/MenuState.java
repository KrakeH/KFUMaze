package com.Turb1na_.KFUMaze.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


import com.Turb1na_.KFUMaze.Main;


public class MenuState implements Screen {
    final Main game;
    public boolean isShowed = false;
    private Stage stage;
    private BitmapFont font;
    private BitmapFont font2;
    private ScrollPane scrollPane;
    private Table container;
    private ImageButton enterBtn;
    private ImageButton homeBtn;
    private ImageButton shopBtn;
    private ImageButton cancelBtn;
    private ImageButton Black;
    private TextureRegion background;
    private Image loadingBackground;
    private Image parametrsBackground;
    private Image KStar;
    private Image FStar;
    private Image UStar;
    private Image Hearth1;
    private Image Hearth2;
    private Image Hearth3;
    private Image HearthZero1;
    private Image HearthZero2;
    private Image HearthZero3;
    private Image Coins;
    private Image Plashka;
    private TextureRegionDrawable SliderBack;
    private TextureRegionDrawable Knob;
    private Slider.SliderStyle style;
    private Slider MusicSlider;
    private Slider SoundSlider;
    private int levelTo = 0;
    private float tempSound;
    private float tempMusic;
    private static int countLevel = 20;
    private Image[] levelsNumber = new Image[10];
    private boolean levelStars[][] = {
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false}};

    private ImageButton createImageButton(int level, Texture buttonTexture) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(240);
        style.imageUp.setMinWidth(240 * Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!loadingBackground.isVisible() && !parametrsBackground.isVisible()) {
                    try {
                        if (level == 0 || (levelStars[level - 1][0] || levelStars[level - 1][1] || levelStars[level - 1][2])) {
                            cancelBtn.setPosition(Main.WIDTH / 2 - game.tm.loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 67f * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 67 * 10 * Main.SIZECHANGE.y);

                            levelTo = level;
                            if (levelStars[levelTo][0]) {
                                KStar.setVisible(true);
                            }
                            if (levelStars[levelTo][1]) {
                                FStar.setVisible(true);
                            }
                            if (levelStars[levelTo][2]) {
                                UStar.setVisible(true);
                            }
                            loadingBackground.setVisible(true);
                            cancelBtn.setVisible(true);
                            enterBtn.setVisible(true);
                            Black.setVisible(true);
                            levelsNumber[level].setVisible(true);
                            game.sm.SoundBtn.play(game.sm.SoundVolume);
                        } else {
                            game.sm.Blocked.play(game.sm.SoundVolume);
                        }
                    } catch (Exception e) {
                        game.sm.Blocked.play(game.sm.SoundVolume);
                    }
                }
            }
        });
        return button;
    }

    private ImageButton createImageButton(Texture buttonTexture, float Height, float Width) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(Height * Main.SIZECHANGE.y);
        style.imageUp.setMinWidth(Width * Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        return button;
    }

    public MenuState(final Main game, Stage stage) {
        this.game = game;
        this.stage = stage;

        SliderBack = new TextureRegionDrawable(game.tm.sliderBack);
        Knob = new TextureRegionDrawable(game.tm.knob);

        tempSound = game.prefs.getFloat("Sound");
        tempMusic = game.prefs.getFloat("Music");

        /// -----Font-----------
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (5 * 15 * Main.SIZECHANGE.y);
        parameter.color = new Color(47 / 255f, 54 / 255f, 153 / 255f, 1);
        font = generator.generateFont(parameter);
        parameter.color = Color.BLACK;
        parameter.size = (int) (4 * 15 * Main.SIZECHANGE.y);
        parameter.borderWidth = (int) 8 * Main.SIZECHANGE.y;
        parameter.borderColor = new Color(180 / 255f, 180 / 255f, 180 / 255f, 1);
        font2 = generator.generateFont(parameter);
        generator.dispose();

        SliderBack.setMinSize(48 * 15 * Main.SIZECHANGE.x, 1 * 15 * Main.SIZECHANGE.y);
        Knob.setMinSize(3 * 15 * Main.SIZECHANGE.x, 7 * 15 * Main.SIZECHANGE.y);
        style = new Slider.SliderStyle(SliderBack, Knob);
        MusicSlider = new Slider(0, 1, 0.01f, false, style);
        SoundSlider = new Slider(0, 1, 0.01f, false, style);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                levelStars[i][j] = game.prefs.getBoolean("" + i + j);
            }
        }


        container = new Table();

        container.defaults().pad(60, 90 * Main.SIZECHANGE.x, 60, 90 * Main.SIZECHANGE.x);

        container.defaults().size(240 * Main.SIZECHANGE.x, 240);


        update();

        scrollPane = new ScrollPane(container);
        scrollPane.setFillParent(true);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, false);
        scrollPane.setPosition(0,-180*Main.SIZECHANGE.y);


        loadingBackground = new Image(game.tm.loadingMenu);
        parametrsBackground = new Image(game.tm.paramBackground);
        KStar = new Image(game.tm.KStar);
        FStar = new Image(game.tm.FStar);
        UStar = new Image(game.tm.UStar);

        Hearth1=new Image(game.tm.hearth);
        Hearth2=new Image(game.tm.hearth);
        Hearth3=new Image(game.tm.hearth);
        HearthZero1=new Image(game.tm.hearthZero);
        HearthZero2=new Image(game.tm.hearthZero);
        HearthZero3=new Image(game.tm.hearthZero);

        Coins = new Image(game.tm.coinValue);
        Plashka = new Image(game.tm.plashka);

        parametrsBackground.setSize(960 * Main.SIZECHANGE.x, 540 * Main.SIZECHANGE.y);
        parametrsBackground.setPosition(Main.WIDTH / 2 - parametrsBackground.getWidth() / 2, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2);

        loadingBackground.setSize(1125 / 1.5f * Main.SIZECHANGE.x, 1125 / 1.5f * Main.SIZECHANGE.y);
        loadingBackground.setPosition(Main.WIDTH / 2 - game.tm.loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y);

        enterBtn = createImageButton(game.tm.enterBtn, 16 * 10, 67 * 10);
        cancelBtn = createImageButton(game.tm.cancelBtn, 60, 60);
        homeBtn = createImageButton(game.tm.paramBtn, 150, 150);
        shopBtn = createImageButton(game.tm.shopBtn, 150, 150);
        Black = createImageButton(game.tm.Black, 1920, 1080);

        KStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
        FStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
        UStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);

        Hearth1.setSize(150*Main.SIZECHANGE.x,150* Main.SIZECHANGE.y);
        Hearth2.setSize(150*Main.SIZECHANGE.x,150* Main.SIZECHANGE.y);
        Hearth3.setSize(150*Main.SIZECHANGE.x,150* Main.SIZECHANGE.y);

        HearthZero1.setSize(150*Main.SIZECHANGE.x,150* Main.SIZECHANGE.y);
        HearthZero2.setSize(150*Main.SIZECHANGE.x,150* Main.SIZECHANGE.y);
        HearthZero3.setSize(150*Main.SIZECHANGE.x,150* Main.SIZECHANGE.y);

        Hearth1.setPosition(Main.WIDTH/2-Hearth1.getWidth()/2-180*Main.SIZECHANGE.x,Main.HEIGHT-165*Main.SIZECHANGE.y);
        Hearth2.setPosition(Main.WIDTH/2-Hearth1.getWidth()/2,Main.HEIGHT-165*Main.SIZECHANGE.y);
        Hearth3.setPosition(Main.WIDTH/2-Hearth1.getWidth()/2+180*Main.SIZECHANGE.x,Main.HEIGHT-165*Main.SIZECHANGE.y);

        HearthZero1.setPosition(Main.WIDTH/2-Hearth1.getWidth()/2-180*Main.SIZECHANGE.x,Main.HEIGHT-165*Main.SIZECHANGE.y);
        HearthZero2.setPosition(Main.WIDTH/2-Hearth1.getWidth()/2,Main.HEIGHT-165*Main.SIZECHANGE.y);
        HearthZero3.setPosition(Main.WIDTH/2-Hearth1.getWidth()/2+180*Main.SIZECHANGE.x,Main.HEIGHT-165*Main.SIZECHANGE.y);

        Coins.setSize(120 * Main.SIZECHANGE.x, 120 * Main.SIZECHANGE.y);
        Coins.setPosition((30), Main.HEIGHT - (homeBtn.getHeight() + 0 * Main.SIZECHANGE.y));

        Plashka.setSize(1080 * Main.SIZECHANGE.x, 210 * Main.SIZECHANGE.y);
        Plashka.setPosition(0,(1920-180)*Main.SIZECHANGE.y);

        enterBtn.setPosition(Main.WIDTH / 2 - game.tm.loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4f * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 8 * 10 * Main.SIZECHANGE.y);
        homeBtn.setPosition(Main.WIDTH - (homeBtn.getWidth() + 30), Main.HEIGHT - (homeBtn.getHeight() + 30));
        shopBtn.setPosition(Main.WIDTH - (shopBtn.getWidth() + 30), Main.HEIGHT - (shopBtn.getHeight() + 60 + homeBtn.getHeight()));

        KStar.setPosition(Main.WIDTH / 2 - game.tm.loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 34 * 10 * Main.SIZECHANGE.y);
        FStar.setPosition(Main.WIDTH / 2 - game.tm.loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 25 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 34 * 10 * Main.SIZECHANGE.y);
        UStar.setPosition(Main.WIDTH / 2 - game.tm.loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 49 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 34 * 10 * Main.SIZECHANGE.y);


        MusicSlider.setBounds(Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 8 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 19 * 15 * Main.SIZECHANGE.y, 48 * 15 * Main.SIZECHANGE.x, 7 * 15 * Main.SIZECHANGE.y);
        SoundSlider.setBounds(Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 8 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 4 * 15 * Main.SIZECHANGE.y, 48 * 15 * Main.SIZECHANGE.x, 7 * 15 * Main.SIZECHANGE.y);
        MusicSlider.setVisible(false);
        SoundSlider.setVisible(false);

        MusicSlider.setValue(game.sm.MusicVolume);
        SoundSlider.setValue(game.sm.SoundVolume);

        /// ------------------
        for (int i = 0; i < 10; i++) {
            levelsNumber[i] = new Image(game.tm.levelsNumbers[i]);
            levelsNumber[i].setVisible(false);
            levelsNumber[i].setSize(520 * Main.SIZECHANGE.x, 110 * Main.SIZECHANGE.y);
            levelsNumber[i].setPosition(Main.WIDTH / 2 - game.tm.loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 12 * 10 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + Main.SIZECHANGE.y * 62 * 10);
        }
        /// -----------------

        Black.setColor(0, 0, 0, 0.5f);

        stage.addActor(scrollPane);
        stage.addActor(Plashka);
        stage.addActor(HearthZero1);
        stage.addActor(HearthZero2);
        stage.addActor(HearthZero3);
        stage.addActor(Black);
        stage.addActor(Hearth1);
        stage.addActor(Hearth2);
        stage.addActor(Hearth3);
        stage.addActor(loadingBackground);
        stage.addActor(parametrsBackground);
        stage.addActor(KStar);
        stage.addActor(FStar);
        stage.addActor(UStar);
        stage.addActor(Coins);
        stage.addActor(cancelBtn);
        stage.addActor(homeBtn);
        stage.addActor(shopBtn);
        stage.addActor(enterBtn);
        for (int i = 0; i < 10; i++) {
            stage.addActor(levelsNumber[i]);
        }

        stage.addActor(MusicSlider);
        stage.addActor(SoundSlider);

        Hearth1.setVisible(false);
        Hearth2.setVisible(false);
        Hearth3.setVisible(false);
        HearthZero1.setVisible(false);
        HearthZero2.setVisible(false);
        HearthZero3.setVisible(false);

        KStar.setVisible(false);
        FStar.setVisible(false);
        UStar.setVisible(false);
        Black.setVisible(false);
        loadingBackground.setVisible(false);
        parametrsBackground.setVisible(false);
        cancelBtn.setVisible(false);
        enterBtn.setVisible(false);
        ///-----------------------------------------
        homeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!loadingBackground.isVisible()) {
                    MusicSlider.setValue(game.sm.MusicVolume);
                    SoundSlider.setValue(game.sm.SoundVolume);
                    Black.setVisible(true);
                    parametrsBackground.setVisible(true);
                    MusicSlider.setVisible(true);
                    SoundSlider.setVisible(true);
                    cancelBtn.setPosition(Main.WIDTH / 2 + parametrsBackground.getWidth() / 2 - 15 * 5f * Main.SIZECHANGE.x, Main.HEIGHT / 2 + parametrsBackground.getHeight() / 2 - 5 * 15 * Main.SIZECHANGE.y);
                    cancelBtn.setVisible(true);
                    game.sm.SoundBtn.play(game.sm.SoundVolume);
                }
            }
        });
        Black.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Black.setVisible(false);
                KStar.setVisible(false);
                FStar.setVisible(false);
                UStar.setVisible(false);
                MusicSlider.setVisible(false);
                SoundSlider.setVisible(false);
                loadingBackground.setVisible(false);
                parametrsBackground.setVisible(false);
                cancelBtn.setVisible(false);
                enterBtn.setVisible(false);
                for (int i = 0; i < 10; i++) {
                    levelsNumber[i].setVisible(false);
                }
            }
        });

        shopBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!loadingBackground.isVisible() && !parametrsBackground.isVisible()) {
                    game.sm.SoundBtn.play(game.sm.SoundVolume);
                    game.setScreen(game.getShopState());
                    game.getMenuState().hide();
                }
            }
        });

        cancelBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Black.setVisible(false);
                KStar.setVisible(false);
                FStar.setVisible(false);
                UStar.setVisible(false);
                MusicSlider.setVisible(false);
                SoundSlider.setVisible(false);
                loadingBackground.setVisible(false);
                parametrsBackground.setVisible(false);
                cancelBtn.setVisible(false);
                enterBtn.setVisible(false);
                for (int i = 0; i < 10; i++) {
                    levelsNumber[i].setVisible(false);
                }
                game.sm.SoundBtn.play(game.sm.SoundVolume);
            }
        });

        enterBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sm.MenuMusicStop();
                game.sm.SoundBtn.play(game.sm.SoundVolume);

                if(game.hearth>0) {
                    game.getPlayState().regenerate(levelTo + 1);
                    game.setScreen(game.getPlayState());
                    game.getPlayState().show();
                    game.getMenuState().hide();
                }
                else{
                    game.setScreen(game.getAdState());
                    game.getAdState().regenerate(levelTo);
                    game.getAdState().show();
                    game.getMenuState().hide();
                }
            }
        });

        MusicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tempMusic = MusicSlider.getValue();
                game.sm.MusicVolume = MusicSlider.getValue();
                game.prefs.putFloat("Music", tempMusic);
                game.prefs.flush();
                game.sm.setVolume();
            }
        });

        SoundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tempSound = SoundSlider.getValue();
                game.sm.SoundVolume = SoundSlider.getValue();
                game.prefs.putFloat("Sound", tempSound);
                game.prefs.flush();
                game.sm.setVolume();
            }
        });

    }


    @Override
    public void show() {
        isShowed = true;
        homeBtn.setVisible(true);
        scrollPane.setVisible(true);
        shopBtn.setVisible(true);
        Coins.setVisible(true);
        Plashka.setVisible(true);

        HearthZero1.setVisible(true);
        HearthZero2.setVisible(true);
        HearthZero3.setVisible(true);

        /// ----------Music-------------
        game.sm.setVolume();
        /// -----------------------------

        /// -----------Sliders----------
        MusicSlider.setValue(game.sm.MusicVolume);
        SoundSlider.setValue(game.sm.SoundVolume);
        /// ----------------------------
    }

    @Override
    public void render(float delta) {

        if (isShowed) {
            Hearth1.setVisible(game.hearth >= 1);
            Hearth2.setVisible(game.hearth >= 2);
            Hearth3.setVisible(game.hearth >= 3);
            ///----------------------
            game.camera.update();
            game.sb.setProjectionMatrix(game.camera.combined);

            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();

            game.sb.begin();
            if (parametrsBackground.isVisible()) {
                font.draw(game.sb, String.valueOf((int) (100 * game.sm.SoundVolume)), Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 42 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 17 * 15 * Main.SIZECHANGE.y);
                font.draw(game.sb, String.valueOf((int) (100 * game.sm.MusicVolume)), Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 42 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 32 * 15 * Main.SIZECHANGE.y);
            }
            font2.draw(game.sb, String.valueOf(game.money), 36 * Main.SIZECHANGE.x + Coins.getWidth(), Main.HEIGHT - homeBtn.getHeight() + 90 * Main.SIZECHANGE.y);
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

        isShowed = false;
        homeBtn.setVisible(false);
        scrollPane.setVisible(false);
        shopBtn.setVisible(false);
        loadingBackground.setVisible(false);
        cancelBtn.setVisible(false);
        enterBtn.setVisible(false);
        Black.setVisible(false);
        KStar.setVisible(false);
        FStar.setVisible(false);
        UStar.setVisible(false);

        Hearth1.setVisible(false);
        Hearth2.setVisible(false);
        Hearth3.setVisible(false);
        HearthZero1.setVisible(false);
        HearthZero2.setVisible(false);
        HearthZero3.setVisible(false);

        for (int i = 0; i < levelsNumber.length; i++) {
            levelsNumber[i].setVisible(false);
        }
        Coins.setVisible(false);
        Plashka.setVisible(false);
    }

    @Override
    public void dispose() {
        stage.dispose();
        game.tm.loadingMenu.dispose();
    }

    public void update() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                levelStars[i][j] = game.prefs.getBoolean("" + i + j);
            }
        }
        container.clear();
        for (int i = 0; i < countLevel / 4 + 1; i++) {
            try {
                if (levelStars[i * 4][0] || levelStars[i * 4][1] || levelStars[i * 4][2])
                    container.add(createImageButton(i * 4 + 1, game.tm.levels[i * 4 + 1]));
                else
                    container.add(createImageButton(i * 4 + 1, game.tm.lock));

            } catch (Exception e) {
                container.add(createImageButton(i * 4 + 1, game.tm.lock));
            }
            try {
                if (i == 0)
                    container.add(createImageButton(i * 4, game.tm.levels[i * 4]));
                else if (levelStars[i * 4 - 1][0] || levelStars[i * 4 - 1][1] || levelStars[i * 4 - 1][2])
                    container.add(createImageButton(i * 4, game.tm.levels[i * 4]));
                else
                    container.add(createImageButton(i * 4, game.tm.lock));

            } catch (Exception e) {
                container.add(createImageButton(i * 4, game.tm.lock));
            }

            container.row();

            try {
                if (levelStars[i * 4 + 1][0] || levelStars[i * 4 + 1][1] || levelStars[i * 4 + 1][2])
                    container.add(createImageButton(i * 4 + 2, game.tm.levels[i * 4 + 2]));
                else
                    container.add(createImageButton(i * 4 + 2, game.tm.lock));
            } catch (Exception e) {
                container.add(createImageButton(i * 4 + 2, game.tm.lock));
            }
            try {
                if (levelStars[i * 4 + 2][0] || levelStars[i * 4 + 2][1] || levelStars[i * 4 + 2][2])
                    container.add(createImageButton(i * 4 + 3, game.tm.levels[i * 4 + 3]));
                else
                    container.add(createImageButton(i * 4 + 3, game.tm.lock));
            } catch (Exception e) {
                container.add(createImageButton(i * 4 + 3, game.tm.lock));
            }
            container.row();
        }

        background = new TextureRegion(game.tm.menuBackground);
        background.flip(true, false);

        container.setBackground(new TextureRegionDrawable(background));
    }
}
