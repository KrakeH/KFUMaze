package KFU.github.KFUMaze.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


import KFU.github.KFUMaze.Main;


public class MenuState extends State {
    private Stage stage;
    private ScrollPane scrollPane;
    private Table container;
    private ImageButton enterBtn;
    private ImageButton cancelBtn;
    private TextureRegion background;
    private Texture loadingMenu;
    private Image loadingBackground;
    private Image KStar;
    private Image FStar;
    private Image UStar;
    private Music MenuMusic=Gdx.audio.newMusic(Gdx.files.internal("Audio/MenuMusic.mp3"));
    private Music Blocked=Gdx.audio.newMusic(Gdx.files.internal("Audio/blocked.mp3"));
    private Music SoundBtn=Gdx.audio.newMusic(Gdx.files.internal("Audio/ButtonSound.mp3"));
    Preferences prefs=Gdx.app.getPreferences("Game");

    private int levelTo = 0;
    private static int countLevel = 20;
    private boolean levelStars[][] = {
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false},
        {false, false, false}};

    private ImageButton createImageButton(int level, Texture buttonTexture) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(240 * Main.SIZECHANGE.y);
        style.imageUp.setMinWidth(240 * Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!loadingBackground.isVisible()) {
                    try {
                        if (level==0||(levelStars[level-1][0]||levelStars[level-1][1]||levelStars[level-1][2])) {
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

    public MenuState(GameStateManager gsm, boolean[][] stars) {
        super(gsm);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                prefs.putBoolean(""+i+j,stars[i][j]);
            }
        }
        prefs.flush();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                levelStars[i][j]=prefs.getBoolean(""+i+j);
            }
        }

        MenuMusic.setLooping(true);
        MenuMusic.setVolume(0.7f);
        MenuMusic.play();
        for (int i = 0; i < levelStars.length; i++) {
            for (int k = 0; k < 3; k++) {
                System.out.print(levelStars[i][k]+" ");
            }
            System.out.println();
        }
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        container = new Table();

        container.defaults().pad(60 * Main.SIZECHANGE.y, 90 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y, 90 * Main.SIZECHANGE.x);

        container.defaults().size(240 * Main.SIZECHANGE.x, 240 * Main.SIZECHANGE.y);


        for (int i = 0; i < countLevel / 4 + 1; i++) {
            try {
                if(levelStars[i*4][0]||levelStars[i*4][1]||levelStars[i*4][2])
                    container.add(createImageButton(i * 4 + 1, new Texture("levels/" + (i * 4 + 2) + ".png")));
                else
                    container.add(createImageButton(i * 4 + 1, new Texture("levels/lock.png")));

            } catch (Exception e) {
                container.add(createImageButton(i * 4 + 1, new Texture("levels/lock.png")));
            }
            try {
                if(i==0)
                    container.add(createImageButton(i * 4, new Texture("levels/" + (i * 4 + 1) + ".png")));
                else
                    if(levelStars[i*4-1][0]||levelStars[i*4-1][1]||levelStars[i*4-1][2])
                        container.add(createImageButton(i * 4, new Texture("levels/" + (i * 4 + 1) + ".png")));
                    else
                        container.add(createImageButton(i * 4, new Texture("levels/lock.png")));

            } catch (Exception e) {
                container.add(createImageButton(i * 4, new Texture("levels/lock.png")));
            }
            container.row();

            try {
                if(levelStars[i*4+1][0]||levelStars[i*4+1][1]||levelStars[i*4+1][2])
                    container.add(createImageButton(i * 4 + 2, new Texture("levels/" + (i * 4 + 3) + ".png")));
                else
                    container.add(createImageButton(i * 4 + 2, new Texture("levels/lock.png")));
            } catch (Exception e) {
                container.add(createImageButton(i * 4 + 2, new Texture("levels/lock.png")));
            }
            try {
                if(levelStars[i*4+2][0]||levelStars[i*4+2][1]||levelStars[i*4+2][2])
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

        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);

        scrollPane = new ScrollPane(container);
        scrollPane.setFillParent(true);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setOverscroll(false, false);


        loadingMenu = new Texture("loadingMenu.png");
        loadingBackground = new Image(loadingMenu);
        KStar = new Image(new Texture("Sprites/KFU/K.png"));
        FStar = new Image(new Texture("Sprites/KFU/F.png"));
        UStar = new Image(new Texture("Sprites/KFU/U.png"));
        loadingBackground.setSize(1125 / 1.5f * Main.SIZECHANGE.x, 1020 / 1.5f * Main.SIZECHANGE.y);
        loadingBackground.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y);

        enterBtn = createImageButton(new Texture("Buttons/enterBtn.png"), 16 * 10 , 67 * 10);
        cancelBtn = createImageButton(new Texture("Buttons/cancelBtn.png"), 60 , 60 );

        KStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
        FStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
        UStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);

        enterBtn.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4f * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 8 * 10 * Main.SIZECHANGE.y);
        cancelBtn.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 68f * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 60 * 10 * Main.SIZECHANGE.y);

        KStar.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 34 * 10 * Main.SIZECHANGE.y);
        FStar.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 25 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 34 * 10 * Main.SIZECHANGE.y);
        UStar.setPosition(Main.WIDTH / 2 - loadingMenu.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 49 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - loadingMenu.getHeight() / 3 * Main.SIZECHANGE.y + 34 * 10 * Main.SIZECHANGE.y);

        stage.addActor(scrollPane);
        stage.addActor(loadingBackground);
        stage.addActor(KStar);
        stage.addActor(FStar);
        stage.addActor(UStar);
        stage.addActor(cancelBtn);
        stage.addActor(enterBtn);

        KStar.setVisible(false);
        FStar.setVisible(false);
        UStar.setVisible(false);
        loadingBackground.setVisible(false);
        cancelBtn.setVisible(false);
        enterBtn.setVisible(false);
        ///-----------------------------------------

        cancelBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                KStar.setVisible(false);
                FStar.setVisible(false);
                UStar.setVisible(false);
                loadingBackground.setVisible(false);
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
                gsm.set(new PlayState(gsm, levelTo+1,levelStars));
            }
        });
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
        MenuMusic.dispose();
        loadingMenu.dispose();
        SoundBtn.dispose();
    }
}
