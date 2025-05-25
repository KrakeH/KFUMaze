package io.github.some_example_name.States;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.List;

import io.github.some_example_name.Main;
import io.github.some_example_name.Sprites.Player;

public class PlayState extends State {

    private OrthographicCamera camera;
    private Stage stage;
    Preferences prefs=Gdx.app.getPreferences("Game");
    private Sound SoundBtn=Gdx.audio.newSound(Gdx.files.internal("Audio/ButtonSound.wav"));
    private Sound Win=Gdx.audio.newSound(Gdx.files.internal("Audio/Win.mp3"));
    private Sound WinStar=Gdx.audio.newSound(Gdx.files.internal("Audio/WinStar.wav"));
    private Sound Die=Gdx.audio.newSound(Gdx.files.internal("Audio/Die.mp3"));
    private com.badlogic.gdx.audio.Music GameMusic=Gdx.audio.newMusic(Gdx.files.internal("Audio/GameMusic.mp3"));
    private Texture background;
    /// wallTexturs---------------
    private Texture wall1;
    private Texture wall2;
    private Texture wall3;
    private Texture wall4;
    private Texture wall5;
    private Texture wall6;
    private Texture wall7;
    private Texture wall8;
    private Texture wall9;
    private Texture wallz;
    private Texture wallx;
    private Texture wallc;
    private Texture wallv;
    private Texture wallb;
    private Texture walln;
    private Texture wallm;

    /// ------------------------
    private Texture exit;
    private Texture thorns;
    private Texture Ktexture;
    private Texture Ftexture;
    private Texture Utexture;
    private ImageButton stopButton;
    private ImageButton continueButton;
    private ImageButton againButton;
    private ImageButton exitButton;
    private ImageButton nextButton;
    private Image menuBackground;
    private Image killBackground;
    private Image winBackground;
    private Image KStar;
    private Image FStar;
    private Image UStar;
    private int level;
    private Vector3 deltaAngle = new Vector3(360, 360, 360);
    private Vector3 deltaSize = new Vector3(0, 0, 0);
    private Vector3 deltaSizeIs = new Vector3(0, 0, 0);
    private Vector3 Music = new Vector3(0, 0, 0);
    private float timeToOpen=0.8f;
    private boolean Time = true;
    private boolean[][] levelStars;
    private String maps[] = {
        "##################\n" +
            "#########0########\n" +
            "######### ########\n" +
            "######### ########\n" +
            "######### ########\n" +
            "######    ########\n" +
            "######  ##########\n" +
            "######  ##########\n" +
            "######   #########\n" +
            "######## #########\n" +
            "######## #########\n" +
            "######## #########\n" +
            "######## #########\n" +
            "######## #########\n" +
            "######       #####\n" +
            "######   ### #####\n" +
            "######  u### #####\n" +
            "############ #####\n" +
            "############ #####\n" +
            "############ #####\n" +
            "############ #####\n" +
            "####   f#### #####\n" +
            "#### ## #### #####\n" +
            "#### ##      #####\n" +
            "#### #############\n" +
            "#         ###    #\n" +
            "#k   ####        #\n" +
            "#    #########   #\n" +
            "#    ######      #\n" +
            "########### *    #\n" +
            "###########      #\n" +
            "##################\n",

        "##################\n" +
            "##################\n" +
            "##              ##\n" +
            "##        k#### ##\n" +
            "##         #### ##\n" +
            "########## #### ##\n" +
            "########## #### ##\n" +
            "########## #### ##\n" +
            "########## #### ##\n" +
            "#            #  ##\n" +
            "# ######## # #  ##\n" +
            "#    f#### # #  ##\n" +
            "###   ##     #  ##\n" +
            "###    # # ### ###\n" +
            "###### # # ### ###\n" +
            "###### # # ### ###\n" +
            "###### # # ### ###\n" +
            "###### #       ###\n" +
            "###### ### #######\n" +
            "##              ##\n" +
            "## ### ### #    ##\n" +
            "## ### ### #    ##\n" +
            "## ### ### #   u##\n" +
            "## ### ### # #####\n" +
            "## ### ### # #####\n" +
            "## ###       #####\n" +
            "## ####### #######\n" +
            "## ####### #######\n" +
            "##               #\n" +
            "########## #   * #\n" +
            "##########0##    #\n" +
            "##################",

        "##################\n" +
            "##################\n" +
            "#######0##########\n" +
            "####### ##########\n" +
            "####### ##########\n" +
            "#w      #       w#\n" +
            "#w         #    w#\n" +
            "#w   #          w#\n" +
            "#w     ##       w#\n" +
            "#w     #f   #   w#\n" +
            "##u    #        w#\n" +
            "#w    ##     #  w#\n" +
            "#w     #        w#\n" +
            "#w              w#\n" +
            "#w        #     w#\n" +
            "#wwww         # w#\n" +
            "####w  #        w#\n" +
            "####w     k#    w#\n" +
            "####w           w#\n" +
            "####w      # #  w#\n" +
            "####w #         w#\n" +
            "########### ######\n" +
            "########### ######\n" +
            "########### ######\n" +
            "########### ######\n" +
            "##    ##        ##\n" +
            "## ## ## ## ### ##\n" +
            "#  ## ## ## ### ##\n" +
            "#   # ## ## ### ##\n" +
            "# * #    ##     ##\n" +
            "#   ##############\n" +
            "##################"};

    private String[] levelMap;
    private Vector2 sizeMap;
    private Player player;

    private String[] generateMap(String[] map) {
        List<String> newMap = new ArrayList<>();
        boolean up = true;
        boolean bottom = true;
        boolean left = true;
        boolean right = true;
        for (int i = 0; i < map.length; i++) {
            String s = "";
            for (int j = 0; j < map[0].length(); j++) {
                up = true;
                bottom = true;
                left = true;
                right = true;
                if (map[i].charAt(j) == '#') {
                    try {
                        if (map[i - 1].charAt(j) == '#') {
                            up = false;
                        }
                    } catch (Exception e) {
                        up = false;
                    }
                    try {
                        if (map[i + 1].charAt(j) == '#') {
                            bottom = false;
                        }
                    } catch (Exception e) {
                        bottom = false;
                    }
                    try {
                        if (map[i].charAt(j + 1) == '#') {
                            right = false;
                        }
                    } catch (Exception e) {
                        right = false;
                    }
                    try {
                        if (map[i].charAt(j - 1) == '#') {
                            left = false;
                        }
                    } catch (Exception e) {
                        left = false;

                    }

                    if (up && left && right && bottom) {
                        s += '1';
                    } else if (up && !left && right && bottom) {
                        s += '2';
                    } else if (up && left && right && !bottom) {
                        s += '3';
                    } else if (!up && left && right && bottom) {
                        s += '4';
                    } else if (up && left && !right && bottom) {
                        s += '5';
                    } else if (up && !left && right && !bottom) {
                        s += '6';
                    } else if (!up && !left && right && bottom) {
                        s += '7';
                    } else if (!up && left && !right && bottom) {
                        s += '8';
                    } else if (up && left && !right && !bottom) {
                        s += '9';
                    } else if (!up && left && right && !bottom) {
                        s += 'z';
                    } else if (up && !left && !right && bottom) {
                        s += 'x';
                    } else if (!up && !left && right && !bottom) {
                        s += 'c';
                    } else if (up && !left && !right && !bottom) {
                        s += 'v';
                    } else if (!up && left && !right && !bottom) {
                        s += 'b';
                    } else if (!up && !left && !right && bottom) {
                        s += 'n';
                    } else if (!up && !left && !right && !bottom) {
                        s += 'm';
                    }
                } else {
                    s += map[i].charAt(j);
                }
            }
            newMap.add(s);
        }
        String ans[] = new String[32];
        for (int i = 0; i < newMap.size(); i++) {
            ans[i] = newMap.get(i);
        }
        return ans;
    }

    private ImageButton createImageButton(Texture buttonTexture, float Height, float Width) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(Height * Main.SIZECHANGE.y);
        style.imageUp.setMinWidth(Width * Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        return button;
    }

    public PlayState(GameStateManager gsm, int level, boolean[][] stars) {
        super(gsm);
        levelStars = stars;
        this.level = level;

        GameMusic.setLooping(true);
        GameMusic.setVolume(0.3f);
        GameMusic.play();

        camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
        camera.setToOrtho(false);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        background = new Texture("background.png");

        ///download-wall----------------
        wall1 = new Texture("Sprites/walls/1.png");
        wall2 = new Texture("Sprites/walls/2.png");
        wall3 = new Texture("Sprites/walls/3.png");
        wall4 = new Texture("Sprites/walls/4.png");
        wall5 = new Texture("Sprites/walls/5.png");
        wall6 = new Texture("Sprites/walls/6.png");
        wall7 = new Texture("Sprites/walls/7.png");
        wall8 = new Texture("Sprites/walls/8.png");
        wall9 = new Texture("Sprites/walls/9.png");
        wallz = new Texture("Sprites/walls/z.png");
        wallx = new Texture("Sprites/walls/x.png");
        wallc = new Texture("Sprites/walls/c.png");
        wallv = new Texture("Sprites/walls/v.png");
        wallb = new Texture("Sprites/walls/b.png");
        walln = new Texture("Sprites/walls/n.png");
        wallm = new Texture("Sprites/walls/m.png");
        ///-----------------------------
        exit = new Texture("Sprites/exit.png");
        thorns = new Texture("Sprites/thorns.png");
        Ktexture = new Texture("Sprites/KFU/K.png");
        Ftexture = new Texture("Sprites/KFU/F.png");
        Utexture = new Texture("Sprites/KFU/U.png");

        menuBackground = new Image(new Texture("gameBackground.png"));
        killBackground = new Image(new Texture("killBackground.png"));
        winBackground = new Image(new Texture("winBackground.png"));

        stopButton = createImageButton(new Texture("Buttons/stopBtn.png"), 120, 120);
        continueButton = createImageButton(new Texture("Buttons/ctnBtn.png"), 160, 67 * 10);
        againButton = createImageButton(new Texture("Buttons/againBtn.png"), 160, 67 * 10);
        exitButton = createImageButton(new Texture("Buttons/exitBtn.png"), 160, 67 * 10);
        nextButton = createImageButton(new Texture("Buttons/nextBtn.png"), 160, 67 * 10);
        sizeMap = new Vector2(maps[level - 1].split("\\r?\\n")[0].length(), maps[level - 1].split("\\r?\\n").length);
        levelMap = maps[level - 1].split("\\r?\\n");
        levelMap = generateMap(levelMap);

        menuBackground.setSize(new Texture("gameBackground.png").getWidth() / 1.5f * Main.SIZECHANGE.x, new Texture("gameBackground.png").getHeight() / 1.5f * Main.SIZECHANGE.y);
        menuBackground.setPosition(Main.WIDTH / 2 - new Texture("gameBackground.png").getWidth() / 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("gameBackground.png").getHeight() / 3 * Main.SIZECHANGE.y);

        killBackground.setSize(new Texture("killBackground.png").getWidth() / 1.5f * Main.SIZECHANGE.x, new Texture("killBackground.png").getHeight() / 1.5f * Main.SIZECHANGE.y);
        killBackground.setPosition(Main.WIDTH / 2 - new Texture("killBackground.png").getWidth() / 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("killBackground.png").getHeight() / 3 * Main.SIZECHANGE.y);

        winBackground.setSize(new Texture("winBackground.png").getWidth() / 1.5f * Main.SIZECHANGE.x, new Texture("winBackground.png").getHeight() / 1.5f * Main.SIZECHANGE.y);
        winBackground.setPosition(Main.WIDTH / 2 - new Texture("winBackground.png").getWidth() / 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("winBackground.png").getHeight() / 3 * Main.SIZECHANGE.y);

        stopButton.setPosition(Main.WIDTH - stopButton.getWidth() - 60 * Main.SIZECHANGE.x, Main.HEIGHT - stopButton.getHeight() - 60 * Main.SIZECHANGE.y);
        nextButton.setPosition(Main.WIDTH / 2 - new Texture("winBackground.png").getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("winBackground.png").getHeight() / 3 * Main.SIZECHANGE.y + 10 * 28 * Main.SIZECHANGE.y);

        againButton.setVisible(false);
        continueButton.setVisible(false);
        exitButton.setVisible(false);
        nextButton.setVisible(false);
        menuBackground.setVisible(false);
        killBackground.setVisible(false);
        winBackground.setVisible(false);

        KStar = new Image(new Texture("Sprites/KFU/K.png"));
        FStar = new Image(new Texture("Sprites/KFU/F.png"));
        UStar = new Image(new Texture("Sprites/KFU/U.png"));

        KStar.setVisible(false);
        FStar.setVisible(false);
        UStar.setVisible(false);
        ///-----------------------
        stopButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!winBackground.isVisible()&&!killBackground.isVisible()) {
                    SoundBtn.play();
                    Time = false;
                    KStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
                    FStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
                    UStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
                    KStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2  + 10 * 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 74 * 10 * Main.SIZECHANGE.y);
                    FStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2+ 10 * 25 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 74 * 10 * Main.SIZECHANGE.y);
                    UStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 49 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 74 * 10 * Main.SIZECHANGE.y);

                    continueButton.setPosition(Main.WIDTH / 2 - new Texture("gameBackground.png").getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("gameBackground.png").getHeight() / 3 * Main.SIZECHANGE.y + 10 * 48 * Main.SIZECHANGE.y);
                    exitButton.setPosition(Main.WIDTH / 2 - new Texture("gameBackground.png").getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("gameBackground.png").getHeight() / 3 * Main.SIZECHANGE.y + 10 * 8 * Main.SIZECHANGE.y);
                    againButton.setPosition(Main.WIDTH / 2 - new Texture("gameBackground.png").getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("gameBackground.png").getHeight() / 3 * Main.SIZECHANGE.y + 10 * 28 * Main.SIZECHANGE.y);

                    menuBackground.setVisible(true);
                    continueButton.setVisible(true);
                    againButton.setVisible(true);
                    exitButton.setVisible(true);

                    if (stars[level - 1][0])
                        KStar.setVisible(true);
                    if (stars[level - 1][1])
                        FStar.setVisible(true);
                    if (stars[level - 1][2])
                        UStar.setVisible(true);
                }
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundBtn.play();
                gsm.set(new MenuState(gsm, levelStars));
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundBtn.play();
                gsm.set(new InfoState(gsm, levelStars));
            }
        });

        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundBtn.play();
                Time = true;
                menuBackground.setVisible(false);
                againButton.setVisible(false);
                continueButton.setVisible(false);
                exitButton.setVisible(false);
                KStar.setVisible(false);
                FStar.setVisible(false);
                UStar.setVisible(false);
            }
        });
        againButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundBtn.play();
                gsm.set(new PlayState(gsm, level, levelStars));
            }
        });
        ///-----------------------

        stage.addActor(stopButton);
        stage.addActor(menuBackground);
        stage.addActor(killBackground);
        stage.addActor(winBackground);
        stage.addActor(continueButton);
        stage.addActor(nextButton);
        stage.addActor(againButton);
        stage.addActor(exitButton);
        stage.addActor(KStar);
        stage.addActor(FStar);
        stage.addActor(UStar);

        for (int i = 0; i < sizeMap.y; i++) {
            for (int j = 0; j < sizeMap.x; j++) {
                if (levelMap[i].charAt(j) == '*') {
                    player = new Player(new Vector2(60 * j, 60 * (sizeMap.y - 1 - i)), new Vector2(60, 60), levelMap, new Vector2(25 * Main.SIZECHANGE.x, 25 * Main.SIZECHANGE.y));
                }
            }
        }
    }

    @Override
    public void handleInpute() {
        player.input(Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
    }

    @Override
    public void update(float dt) {
        handleInpute();

        if (Time) {
            player.move();
            if (player.exit() && !winBackground.isVisible()) {
                GameMusic.stop();
                Win.play();
                int x = 0;
                int z = 0;
                if (levelStars[level - 1][0]) {
                    z++;
                }
                if (levelStars[level - 1][1]) {
                    z++;
                }
                if (levelStars[level - 1][2]) {
                    z++;
                }
                if (player.getStars()[0]) {
                    x++;
                }
                if (player.getStars()[1]) {
                    x++;
                }
                if (player.getStars()[2]) {
                    x++;
                }
                if (z <= x) {
                    levelStars[level - 1] = player.getStars();
                }
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 3; j++) {
                        prefs.putBoolean(""+i+j,levelStars[i][j]);
                    }
                }
                prefs.flush();

                winBackground.setVisible(true);
                againButton.setVisible(true);
                nextButton.setVisible(true);
                KStar.setSize(0, 0);
                FStar.setSize(0, 0);
                UStar.setSize(0, 0);
                againButton.setPosition(Main.WIDTH / 2 - new Texture("winBackground.png").getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("winBackground.png").getHeight() / 3 * Main.SIZECHANGE.y + 10 * 8 * Main.SIZECHANGE.y);


                KStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2  + 10 * 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 64 * 10 * Main.SIZECHANGE.y);
                FStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2  + 10 * 25 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 64 * 10 * Main.SIZECHANGE.y);
                UStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2  + 10 * 49 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2  + 64 * 10 * Main.SIZECHANGE.y);

                if (player.getStars()[0])
                    KStar.setVisible(true);
                if (player.getStars()[1])
                    FStar.setVisible(true);
                if (player.getStars()[2])
                    UStar.setVisible(true);
            }
            if (player.isDie()&&!killBackground.isVisible()) {
                GameMusic.dispose();
                Die.play();
                exitButton.setPosition(Main.WIDTH / 2 - new Texture("killBackground.png").getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("killBackground.png").getHeight() / 3 * Main.SIZECHANGE.y + 10 * 8 * Main.SIZECHANGE.y);
                againButton.setPosition(Main.WIDTH / 2 - new Texture("killBackground.png").getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("killBackground.png").getHeight() / 3 * Main.SIZECHANGE.y + 10 * 28 * Main.SIZECHANGE.y);

                killBackground.setVisible(true);
                againButton.setVisible(true);
                exitButton.setVisible(true);
                Time = false;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.position.set(player.getPosition().x * Main.SIZECHANGE.x, player.getPosition().y * Main.SIZECHANGE.y, 0);
        camera.zoom = 0.75f;
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        ///----K-STAR------------------------------
        if (player.exit() && deltaSizeIs.x!=2 && KStar.isVisible()) {
            if(Music.x==0){
                Music.x=1;
                WinStar.play();
            }
            KStar.setSize(deltaSize.x * Main.SIZECHANGE.x, deltaSize.x * Main.SIZECHANGE.y);
            KStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 3 * Main.SIZECHANGE.x + (120 - deltaSize.x / 2) * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 64 * 10 * Main.SIZECHANGE.y + (120 - deltaSize.x / 2) * Main.SIZECHANGE.y);

            KStar.setOrigin(deltaSize.x * Main.SIZECHANGE.x / 2, deltaSize.x * Main.SIZECHANGE.y / 2);

            deltaAngle.x -=  (360 * Gdx.graphics.getDeltaTime())/timeToOpen;
            if (deltaSizeIs.x == 0)
                deltaSize.x += (360 * Gdx.graphics.getDeltaTime()) / 0.66f/timeToOpen;
            else if (deltaSizeIs.x == 1)
                deltaSize.x -=(240 * Gdx.graphics.getDeltaTime()) / 0.33f/timeToOpen;
            if (deltaAngle.x <= 0) {
                deltaAngle.x = 0;
                deltaSizeIs.x=2;
            }
            KStar.setRotation(deltaAngle.x);
            if (deltaSize.x >= 360) {
                deltaSize.x = 360;
                deltaSizeIs.x = 1;
            }
            if (deltaSize.x <= 240 && deltaSizeIs.x == 1)
                deltaSize.x = 240;
        }
        ///----F-STAR------------------------------
        if (player.exit() &&(deltaSizeIs.x==2||!KStar.isVisible()) && FStar.isVisible()&&deltaSizeIs.y!=2) {
            if(Music.y==0){
                Music.y=1;
                WinStar.play();
            }
            FStar.setSize(deltaSize.y * Main.SIZECHANGE.x, deltaSize.y * Main.SIZECHANGE.y);
            FStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2  + 10 * 25 * Main.SIZECHANGE.x + (120 - deltaSize.y / 2) * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2  + 64 * 10 * Main.SIZECHANGE.y + (120 - deltaSize.y / 2) * Main.SIZECHANGE.y);

            FStar.setOrigin(deltaSize.y * Main.SIZECHANGE.x / 2, deltaSize.y * Main.SIZECHANGE.y / 2);

            deltaAngle.y -=  (360 * Gdx.graphics.getDeltaTime())/timeToOpen;
            if (deltaSizeIs.y == 0)
                deltaSize.y += (360 * Gdx.graphics.getDeltaTime()) / 0.66f/timeToOpen;
            else if (deltaSizeIs.y == 1)
                deltaSize.y -=  (240 * Gdx.graphics.getDeltaTime()) / 0.33f/timeToOpen;
            if (deltaAngle.y <= 0) {
                deltaAngle.y = 0;
                deltaSizeIs.y=2;
            }
            FStar.setRotation(deltaAngle.y);
            if (deltaSize.y >= 360) {
                deltaSize.y = 360;
                deltaSizeIs.y = 1;
            }
            if (deltaSize.y <= 240 && deltaSizeIs.y == 1)
                deltaSize.y = 240;
        }
        ///----U-STAR------------------------------
        if (player.exit() &&( (deltaSizeIs.x==2 && deltaSizeIs.y==2)||(!KStar.isVisible() && deltaSizeIs.y==2)||(deltaSizeIs.x==2 && !FStar.isVisible())||(!KStar.isVisible()&&!FStar.isVisible()))&&deltaSizeIs.z!=2&&UStar.isVisible()) {
            if(Music.z==0){
                Music.z=1;
                WinStar.play();
            }
            UStar.setSize(deltaSize.z * Main.SIZECHANGE.x, deltaSize.z * Main.SIZECHANGE.y);
            UStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2  + 10 * 49 * Main.SIZECHANGE.x + (120 - deltaSize.z / 2) * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 64 * 10 * Main.SIZECHANGE.y + (120 - deltaSize.z / 2) * Main.SIZECHANGE.y);

            UStar.setOrigin(deltaSize.z * Main.SIZECHANGE.x / 2, deltaSize.z * Main.SIZECHANGE.y / 2);

            deltaAngle.z -=(360 * Gdx.graphics.getDeltaTime())/timeToOpen;
            if (deltaSizeIs.z == 0)
                deltaSize.z +=  (360 * Gdx.graphics.getDeltaTime()) / 0.66f/timeToOpen;
            else if (deltaSizeIs.z == 1)
                deltaSize.z -= (240 * Gdx.graphics.getDeltaTime()) / 0.33f/timeToOpen;
            if (deltaAngle.z <= 0) {
                deltaAngle.z = 0;
                deltaSizeIs.z=2;
            }
            UStar.setRotation(deltaAngle.z);
            if (deltaSize.z >= 360) {
                deltaSize.z = 360;
                deltaSizeIs.z = 1;
            }
            if (deltaSize.z <= 240 && deltaSizeIs.z == 1)
                deltaSize.z = 240;
        }
        ///-------------------------------------

        sb.begin();
        ScreenUtils.clear((float) 70 / 255, (float) 70 / 255, (float) 70 / 255, 1);

        sb.draw(background, 0, 0, background.getWidth() * Main.SIZECHANGE.x, background.getHeight() * Main.SIZECHANGE.y);

        for (int i = 0; i < sizeMap.y; i++) {
            for (int j = 0; j < sizeMap.x; j++) {
                switch (levelMap[i].charAt(j)) {
                    /// walls-----------
                    case '1':
                        sb.draw(wall1, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case '2':
                        sb.draw(wall2, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case '3':
                        sb.draw(wall3, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case '4':
                        sb.draw(wall4, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case '5':
                        sb.draw(wall5, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case '6':
                        sb.draw(wall6, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case '7':
                        sb.draw(wall7, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case '8':
                        sb.draw(wall8, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case '9':
                        sb.draw(wall9, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'z':
                        sb.draw(wallz, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'x':
                        sb.draw(wallx, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'c':
                        sb.draw(wallc, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'v':
                        sb.draw(wallv, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'b':
                        sb.draw(wallb, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'n':
                        sb.draw(walln, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'm':
                        sb.draw(wallm, wall1.getWidth() * Main.SIZECHANGE.x * j, wall1.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall1.getWidth() * Main.SIZECHANGE.x, wall1.getHeight() * Main.SIZECHANGE.y);
                        break;
                    /// ------------------------------
                    case '0':
                        sb.draw(exit, exit.getWidth() * Main.SIZECHANGE.x * j, exit.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), exit.getWidth() * Main.SIZECHANGE.x, exit.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'w':
                        sb.draw(thorns, thorns.getWidth() * Main.SIZECHANGE.x * j, thorns.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), thorns.getWidth() * Main.SIZECHANGE.x, thorns.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'k':
                        sb.draw(Ktexture, Ktexture.getWidth() * Main.SIZECHANGE.x * j, Ktexture.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), Ktexture.getWidth() * Main.SIZECHANGE.x, Ktexture.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'f':
                        sb.draw(Ftexture, Ftexture.getWidth() * Main.SIZECHANGE.x * j, Ftexture.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), Ftexture.getWidth() * Main.SIZECHANGE.x, Ftexture.getHeight() * Main.SIZECHANGE.y);
                        break;
                    case 'u':
                        sb.draw(Utexture, Utexture.getWidth() * Main.SIZECHANGE.x * j, Utexture.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), Utexture.getWidth() * Main.SIZECHANGE.x, Utexture.getHeight() * Main.SIZECHANGE.y);
                        break;
                }
            }
        }
        if (!player.isDie()) {
            player.draw(sb);
        }

        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        GameMusic.dispose();
        SoundBtn.dispose();
        Win.dispose();
        WinStar.dispose();
        Die.dispose();
        stage.dispose();
        background.dispose();
        player.dispose();
        thorns.dispose();
        exit.dispose();
        Ktexture.dispose();
        Ftexture.dispose();
        Utexture.dispose();
        wall1.dispose();
        wall2.dispose();
        wall3.dispose();
        wall4.dispose();
        wall5.dispose();
        wall6.dispose();
        wall7.dispose();
        wall8.dispose();
        wall9.dispose();
        wallz.dispose();
        wallx.dispose();
        wallc.dispose();
        wallv.dispose();
        wallb.dispose();
        walln.dispose();
        wallm.dispose();
    }
}
