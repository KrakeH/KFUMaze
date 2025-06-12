package com.Turb1na_.KFUMaze.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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


public class MenuState extends State {
    private Stage stage;
    private BitmapFont font;
    private ScrollPane scrollPane;
    private Table container;
    private ImageButton enterBtn;
    private ImageButton homeBtn;
    private ImageButton cancelBtn;

    private TextureRegion background;
    private Texture loadingMenu;
    private Image loadingBackground;
    private Image parametrsBackground;
    private Image KStar;
    private Image FStar;
    private Image UStar;
    private Music MenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/MenuMusic.mp3"));
    private Music Blocked = Gdx.audio.newMusic(Gdx.files.internal("Audio/blocked.mp3"));
    private Music SoundBtn = Gdx.audio.newMusic(Gdx.files.internal("Audio/ButtonSound.wav"));
    private Preferences prefs = Gdx.app.getPreferences("Game");
    private TextureRegionDrawable SliderBack=new TextureRegionDrawable(new Texture("SliderBack.png"));
    private TextureRegionDrawable Knob=new TextureRegionDrawable(new Texture("Knob.png"));
    private Slider.SliderStyle style;
    private Slider MusicSlider;
    private Slider SoundSlider;

    private int levelTo = 0;
    private float tempSound;
    private float tempMusic;
    private static int countLevel = 20;
    private boolean levelStars[][] = {
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
        style.imageUp.setMinWidth(240*Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!loadingBackground.isVisible() && !parametrsBackground.isVisible()) {
                    try {
                        if (level == 0 || (levelStars[level - 1][0] || levelStars[level - 1][1] || levelStars[level - 1][2])) {
                            cancelBtn.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 68f * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 60 * 10 * Main.SIZECHANGE.y);

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
                            SoundBtn.play();
                        } else {
                            Blocked.play();
                        }
                    } catch (Exception e) {
                        Blocked.play();
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

    public MenuState(GameStateManager gsm,float MusicVolume,float SoundVolume, boolean[][] stars) {
        super(gsm, MusicVolume, SoundVolume);
        tempSound=SoundVolume;
        tempMusic=MusicVolume;
        MenuMusic.setVolume(MusicVolume);
        Blocked.setVolume(SoundVolume);
        SoundBtn.setVolume(SoundVolume);

        /// -----Font-----------

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (5*15*Main.SIZECHANGE.y);
        parameter.color=new Color(47/255f,54/255f,153/255f,1);
        font = generator.generateFont(parameter);
        generator.dispose();
        /// ------------------

        SliderBack.setMinSize(48 * 15 *Main.SIZECHANGE.x, 1 * 15*Main.SIZECHANGE.y );
        Knob.setMinSize(3 * 15 *Main.SIZECHANGE.x, 7 * 15*Main.SIZECHANGE.y );
        style = new Slider.SliderStyle(SliderBack, Knob);
        MusicSlider = new Slider(0, 1, 0.01f, false, style);
        SoundSlider = new Slider(0, 1, 0.01f, false, style);
        MusicSlider.setValue(MusicVolume);
        SoundSlider.setValue(SoundVolume);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                prefs.putBoolean("" + i + j, stars[i][j]);
            }
        }
        prefs.flush();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                levelStars[i][j] = prefs.getBoolean("" + i + j);
            }
        }

        MenuMusic.setLooping(true);
        MenuMusic.play();
        for (int i = 0; i < levelStars.length; i++) {
            for (int k = 0; k < 3; k++) {
                System.out.print(levelStars[i][k] + " ");
            }
            System.out.println();
        }
        camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
        camera.setToOrtho(false);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        container = new Table();

        container.defaults().pad(60, 90 * Main.SIZECHANGE.x, 60, 90 * Main.SIZECHANGE.x);

        container.defaults().size(240 * Main.SIZECHANGE.x, 240 );


        for (int i = 0; i < countLevel / 4 + 1; i++) {
            try {
                if (levelStars[i * 4][0] || levelStars[i * 4][1] || levelStars[i * 4][2])
                    container.add(createImageButton(i * 4 + 1, new Texture("levels/" + (i * 4 + 2) + ".png")));
                else
                    container.add(createImageButton(i * 4 + 1, new Texture("levels/lock.png")));

            } catch (Exception e) {
                container.add(createImageButton(i * 4 + 1, new Texture("levels/lock.png")));
            }
            try {
                if (i == 0)
                    container.add(createImageButton(i * 4, new Texture("levels/" + (i * 4 + 1) + ".png")));
                else if (levelStars[i * 4 - 1][0] || levelStars[i * 4 - 1][1] || levelStars[i * 4 - 1][2])
                    container.add(createImageButton(i * 4, new Texture("levels/" + (i * 4 + 1) + ".png")));
                else
                    container.add(createImageButton(i * 4, new Texture("levels/lock.png")));

            } catch (Exception e) {
                container.add(createImageButton(i * 4, new Texture("levels/lock.png")));
            }
            container.row();

            try {
                if (levelStars[i * 4 + 1][0] || levelStars[i * 4 + 1][1] || levelStars[i * 4 + 1][2])
                    container.add(createImageButton(i * 4 + 2, new Texture("levels/" + (i * 4 + 3) + ".png")));
                else
                    container.add(createImageButton(i * 4 + 2, new Texture("levels/lock.png")));
            } catch (Exception e) {
                container.add(createImageButton(i * 4 + 2, new Texture("levels/lock.png")));
            }
            try {
                if (levelStars[i * 4 + 2][0] || levelStars[i * 4 + 2][1] || levelStars[i * 4 + 2][2])
                    container.add(createImageButton(i * 4 + 3, new Texture("levels/" + (i * 4 + 4) + ".png")));
                else
                    container.add(createImageButton(i * 4 + 3, new Texture("levels/lock.png")));
            } catch (Exception e) {
                container.add(createImageButton(i * 4 + 3, new Texture("levels/lock.png")));
            }
            container.row();
        }

        background = new TextureRegion(new Texture("menuBackground.png"));
        background.flip(true, false);

        container.setBackground(new TextureRegionDrawable(background));

        scrollPane = new ScrollPane(container);
        scrollPane.setFillParent(true);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, false);


        loadingMenu = new Texture("loadingMenu.png");
        loadingBackground = new Image(loadingMenu);
        parametrsBackground = new Image(new Texture("paramBackground.png"));
        KStar = new Image(new Texture("Sprites/KFU/K.png"));
        FStar = new Image(new Texture("Sprites/KFU/F.png"));
        UStar = new Image(new Texture("Sprites/KFU/U.png"));

        parametrsBackground.setSize(960 * Main.SIZECHANGE.x, 540 * Main.SIZECHANGE.y);
        parametrsBackground.setPosition(Main.WIDTH / 2 - parametrsBackground.getWidth() / 2, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2);

        loadingBackground.setSize(1125 / 1.5f * Main.SIZECHANGE.x, 1020 / 1.5f * Main.SIZECHANGE.y);
        loadingBackground.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y);

        enterBtn = createImageButton(new Texture("Buttons/enterBtn.png"), 16 * 10, 67 * 10);
        cancelBtn = createImageButton(new Texture("Buttons/cancelBtn.png"), 60, 60);
        homeBtn = createImageButton(new Texture("Buttons/paramBtn.png"), 150, 150);

        KStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
        FStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
        UStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);

        enterBtn.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4f * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 8 * 10 * Main.SIZECHANGE.y);
        homeBtn.setPosition(Main.WIDTH - (homeBtn.getWidth() + 30), Main.HEIGHT - (homeBtn.getHeight() + 30));

        KStar.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 34 * 10 * Main.SIZECHANGE.y);
        FStar.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 25 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 34 * 10 * Main.SIZECHANGE.y);
        UStar.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 49 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 34 * 10 * Main.SIZECHANGE.y);


        MusicSlider.setBounds(Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 8 * 15*Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 19 * 15*Main.SIZECHANGE.y, 48 * 15 *Main.SIZECHANGE.x, 7 * 15*Main.SIZECHANGE.y );
        SoundSlider.setBounds(Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 8 * 15*Main.SIZECHANGE.x , Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 4 * 15*Main.SIZECHANGE.y , 48 * 15 *Main.SIZECHANGE.x, 7 * 15*Main.SIZECHANGE.y );
        MusicSlider.setVisible(false);
        SoundSlider.setVisible(false);


        stage.addActor(scrollPane);
        stage.addActor(loadingBackground);
        stage.addActor(parametrsBackground);
        stage.addActor(KStar);
        stage.addActor(FStar);
        stage.addActor(UStar);
        stage.addActor(cancelBtn);
        stage.addActor(homeBtn);
        stage.addActor(enterBtn);


        stage.addActor(MusicSlider);
        stage.addActor(SoundSlider);

        KStar.setVisible(false);
        FStar.setVisible(false);
        UStar.setVisible(false);
        loadingBackground.setVisible(false);
        parametrsBackground.setVisible(false);
        cancelBtn.setVisible(false);
        enterBtn.setVisible(false);
        ///-----------------------------------------
        homeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!loadingBackground.isVisible()) {
                    parametrsBackground.setVisible(true);
                    MusicSlider.setVisible(true);
                    SoundSlider.setVisible(true);
                    cancelBtn.setPosition(Main.WIDTH / 2 + parametrsBackground.getWidth() / 2 - 15 * 5f * Main.SIZECHANGE.x, Main.HEIGHT / 2 + parametrsBackground.getHeight() / 2 - 5 * 15 * Main.SIZECHANGE.y);
                    cancelBtn.setVisible(true);
                    SoundBtn.play();
                }
            }
        });

        cancelBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                KStar.setVisible(false);
                FStar.setVisible(false);
                UStar.setVisible(false);
                MusicSlider.setVisible(false);
                SoundSlider.setVisible(false);
                loadingBackground.setVisible(false);
                parametrsBackground.setVisible(false);
                cancelBtn.setVisible(false);
                enterBtn.setVisible(false);
                SoundBtn.play();
            }
        });

        enterBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MenuMusic.stop();
                SoundBtn.play();

                gsm.set(new PlayState(gsm, prefs.getFloat("Music"), prefs.getFloat("Sound"), levelTo + 1, levelStars));
            }
        });

        MusicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tempMusic=MusicSlider.getValue();
                prefs.putFloat("Music",tempMusic);
                prefs.flush();
            }
        });

        SoundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tempSound=SoundSlider.getValue();
                prefs.putFloat("Sound",tempSound);
                prefs.flush();
            }
        });

    }

    @Override
    public void handleInpute() {
    }

    @Override
    public void update(float dt) {
        handleInpute();
        if(SoundVolume!=tempSound) {

            SoundVolume = tempSound;
            Blocked.setVolume(SoundVolume);
            SoundBtn.setVolume(SoundVolume);
        }

        if(MusicVolume!=tempMusic) {
            MusicVolume = tempMusic;
            MenuMusic.setVolume(MusicVolume);

        }
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        sb.setProjectionMatrix(camera.combined);



        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        sb.begin();
        if(parametrsBackground.isVisible()) {
            font.draw(sb, String.valueOf((int) (100 * SoundVolume)), Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 42 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 17 * 15 * Main.SIZECHANGE.y);
            font.draw(sb, String.valueOf((int) (100 * MusicVolume)), Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 42 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 32 * 15 * Main.SIZECHANGE.y);
        }
        sb.end();

    }

    @Override
    public void dispose() {
        stage.dispose();
        MenuMusic.dispose();
        loadingMenu.dispose();
        SoundBtn.dispose();
    }
}
