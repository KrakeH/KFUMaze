package com.Turb1na_.KFUMaze.States;

import com.Turb1na_.KFUMaze.Main;
import com.Turb1na_.KFUMaze.Sprites.KillBlock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.List;

public class ShopState extends State {
    private Stage stage;
    private BitmapFont font;
    private BitmapFont font2;
    private BitmapFont font3;

    //font.draw(this.getBatch(), "Score: 0" + myScore.getCurrent(), 600, 500);
    //this.getBatch().end();
    //stage.act(Gdx.graphics.getDeltaTime());
    //stage.draw();
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
    private Preferences prefs = Gdx.app.getPreferences("Game");
    private int money = prefs.getInteger("Coins");
    private Music MenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/MenuMusic.mp3"));
    private Music Blocked = Gdx.audio.newMusic(Gdx.files.internal("Audio/blocked.mp3"));
    private Music SoundBtn = Gdx.audio.newMusic(Gdx.files.internal("Audio/ButtonSound.wav"));
    private Sound Star= Gdx.audio.newSound(Gdx.files.internal("Audio/Star.mp3"));
    private TextureRegionDrawable SliderBack = new TextureRegionDrawable(new Texture("SliderBack.png"));
    private TextureRegionDrawable Knob = new TextureRegionDrawable(new Texture("Knob.png"));
    private Slider.SliderStyle style;

    private List<TextButton> textButtons = new ArrayList<>();

    private Texture[] images = {
        new Texture("Player/playerStop.png"),
        new Texture("Player/playerStop3.png"),
        new Texture("Player/playerStop4.png"),
        new Texture("Player/playerStop1.png"),
        new Texture("Player/playerStop2.png"),
        new Texture("Player/playerStop5.png")
    };

    private String[] prices={
        "Equipped", "10", "50", "100", "500", "1000"
    };

    private Image Coins;

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
                    Star.stop();
                    Star.play(tempSound);
                    for (int i = 0; i < textButtons.size(); i++) {
                        if(String.valueOf(textButtons.get(i).getText()).equals("Equipped"))
                            prefs.putInteger("Skin",i);
                        prefs.putString("price"+i, String.valueOf(textButtons.get(i).getText()));
                    }
                }
                prefs.flush();
            }
        });
        return button;
    }

    private TextButton createTextButton(String text, BitmapFont font) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture("priceBackground.png")));
        style.up.setMinHeight(60);
        style.up.setMinWidth(150 * Main.SIZECHANGE.x);
        TextButton button = new TextButton(text, style);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    if (money < Integer.parseInt(String.valueOf(button.getText())))
                        Blocked.play();
                    else {
                        money -= Integer.parseInt(text);
                        prefs.putInteger("Coins",money);
                        Star.stop();
                        Star.play(tempSound);
                        button.setText("Equip");
                        for (int i = 0; i < textButtons.size(); i++) {
                            prefs.putString("price"+i, String.valueOf(textButtons.get(i).getText()));
                        }
                        prefs.flush();
                    }
                } catch (Exception e) {
                    if(String.valueOf(button.getText()).equals("Equip")){
                        for (int i = 0; i < textButtons.size(); i++) {
                            if(String.valueOf(textButtons.get(i).getText()).equals("Equipped"))
                                textButtons.get(i).setText("Equip");
                        }
                        button.setText("Equipped");
                        Star.stop();
                        Star.play(tempSound);
                        for (int i = 0; i < textButtons.size(); i++) {
                            if(String.valueOf(textButtons.get(i).getText()).equals("Equipped"))
                                prefs.putInteger("Skin",i);
                            prefs.putString("price"+i, String.valueOf(textButtons.get(i).getText()));
                        }
                    }
                    prefs.flush();
                }
            }
        });
        return button;
    }

    public ShopState(GameStateManager gsm, float MusicVolume, float SoundVolume, boolean[][] stars) {
        super(gsm, MusicVolume, SoundVolume);

        SoundVolume = prefs.getFloat("Sound");
        MusicVolume = prefs.getFloat("Music");
        tempSound = prefs.getFloat("Sound");
        tempMusic = prefs.getFloat("Music");
        MenuMusic.setVolume(MusicVolume);
        Blocked.setVolume(SoundVolume);
        SoundBtn.setVolume(SoundVolume);

        MenuMusic.setLooping(true);
        MenuMusic.play();

        if(!prefs.contains("price0")){
            for (int i = 0; i < prices.length; i++) {
                prefs.putString("price"+i,prices[i]);
            }
            prefs.flush();
        }else{
            for (int i = 0; i < prices.length; i++) {
                prices[i]=prefs.getString("price"+i);
            }
        }

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
        parameter.borderWidth = (int) 4 * Main.SIZECHANGE.y;
        parameter.borderColor = new Color(180 / 255f, 180 / 255f, 180 / 255f, 1);
        font2 = generator.generateFont(parameter);
        generator.dispose();
        /// -----Sliders-----
        SliderBack.setMinSize(48 * 15 * Main.SIZECHANGE.x, 1 * 15 * Main.SIZECHANGE.y);
        Knob.setMinSize(3 * 15 * Main.SIZECHANGE.x, 7 * 15 * Main.SIZECHANGE.y);
        style = new Slider.SliderStyle(SliderBack, Knob);
        MusicSlider = new Slider(0, 1, 0.01f, false, style);
        SoundSlider = new Slider(0, 1, 0.01f, false, style);
        MusicSlider.setValue(MusicVolume);
        SoundSlider.setValue(SoundVolume);
        /// -------------------

        camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
        camera.setToOrtho(false);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        container = new Table();
        Coins = new Image(new Texture("CoinValue.png"));

        homeBtn = createImageButton(new Texture("Buttons/homeBtn.png"), 150, 150);
        paramBtn = createImageButton(new Texture("Buttons/paramBtn.png"), 150, 150);
        cancelBtn = createImageButton(new Texture("Buttons/cancelBtn.png"), 60, 60);
        homeBtn.setPosition(Main.WIDTH - (homeBtn.getWidth() + 30), Main.HEIGHT - (homeBtn.getHeight() + 60 + paramBtn.getHeight()));
        paramBtn.setPosition(Main.WIDTH - (paramBtn.getWidth() + 30), Main.HEIGHT - (paramBtn.getHeight() + 30));

        parametrsBackground = new Image(new Texture("paramBackground.png"));
        parametrsBackground.setSize(960 * Main.SIZECHANGE.x, 540 * Main.SIZECHANGE.y);
        parametrsBackground.setPosition(Main.WIDTH / 2 - parametrsBackground.getWidth() / 2, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2);


        scrollPane = new ScrollPane(container);
        scrollPane.setFillParent(true);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, false);

        Coins.setSize(120 * Main.SIZECHANGE.x, 120 * Main.SIZECHANGE.y);
        Coins.setPosition((30), Main.HEIGHT - (180 * Main.SIZECHANGE.y));

        container.defaults().size(240 * Main.SIZECHANGE.x, 240);
        container.defaults().pad(120, 45 * Main.SIZECHANGE.x, 60, 45 * Main.SIZECHANGE.x);

        for (int i = 0; i < images.length; i++) {
            container.add(createImageButton(i,images[i]));
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
                    SoundBtn.play();
                    gsm.set(new MenuState(gsm, tempMusic, tempSound, stars));
                    MenuMusic.stop();
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
                SoundBtn.play();
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
                SoundBtn.play();

            }
        });

        MusicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tempMusic = MusicSlider.getValue();
                prefs.putFloat("Music", tempMusic);
                prefs.flush();
            }
        });

        SoundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tempSound = SoundSlider.getValue();
                prefs.putFloat("Sound", tempSound);
                prefs.flush();
            }
        });
    }

    @Override
    public void handleInpute() {

    }

    @Override
    public void update(float dt) {
        if (SoundVolume != tempSound) {

            SoundVolume = tempSound;
            Blocked.setVolume(SoundVolume);
            SoundBtn.setVolume(SoundVolume);
        }

        if (MusicVolume != tempMusic) {
            MusicVolume = tempMusic;
            MenuMusic.setVolume(MusicVolume);

        }
    }

    @Override
    public void render(SpriteBatch sb) {
        ScreenUtils.clear(180 / 255f, 180 / 255f, 180 / 255f, 1);
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        sb.begin();
        if (parametrsBackground.isVisible()) {
            font3.draw(sb, String.valueOf((int) (100 * SoundVolume)), Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 42 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 17 * 15 * Main.SIZECHANGE.y);
            font3.draw(sb, String.valueOf((int) (100 * MusicVolume)), Main.WIDTH / 2 - parametrsBackground.getWidth() / 2 + 42 * 15 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - parametrsBackground.getHeight() / 2 + 32 * 15 * Main.SIZECHANGE.y);
        }
        font2.draw(sb, String.valueOf(money), 36 * Main.SIZECHANGE.x + Coins.getWidth(), Main.HEIGHT - 90 * Main.SIZECHANGE.y);
        sb.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
