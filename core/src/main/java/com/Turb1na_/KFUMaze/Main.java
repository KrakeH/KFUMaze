package com.Turb1na_.KFUMaze;

import com.Turb1na_.KFUMaze.States.InfoState;
import com.Turb1na_.KFUMaze.States.MenuState;
import com.Turb1na_.KFUMaze.States.PlayState;
import com.Turb1na_.KFUMaze.States.ShopState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.Turb1na_.KFUMaze.States.StartState;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends Game {
    public static int WIDTH = 0;
    private Stage stage;
    public static int HEIGHT = 0;

    /// ---------------------------------------
    public Preferences prefs;
    public OrthographicCamera camera;
    public int money;
    public int Skin;
    public static Vector2 SIZECHANGE;
    public SpriteBatch sb;
    public SoundManager sm;
    public TextureManager tm;

    /// ---------------Screens----------------
    private StartState startState;
    private MenuState menuState;
    private ShopState shopState;
    private PlayState playState;
    private InfoState infoState;
    public InfoState getInfoState(){return infoState;}
    public StartState getStartState(){return startState;}
    public ShopState getShopState(){return shopState;}
    public MenuState getMenuState(){return menuState;}
    public PlayState getPlayState(){return playState;}
    /// -----------------------------------------------

    @Override
    public void create() {
        prefs=Gdx.app.getPreferences("Game");
        tm=new TextureManager();
        camera=new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
        sb=new SpriteBatch();
        HEIGHT=Gdx.graphics.getHeight();
        WIDTH=Gdx.graphics.getWidth();
        SIZECHANGE=new Vector2( Main.WIDTH/1080f,Main.HEIGHT/1920f);
        stage = new Stage(new ScreenViewport());


        camera.setToOrtho(false);
        Gdx.input.setInputProcessor(stage);
        sm=new SoundManager(this);

        if(!prefs.contains("Skin")){
            prefs.putInteger("Skin",0);
        }
        if (!prefs.contains("Music")) {
            prefs.putFloat("Music", 1);
        }
        if (!prefs.contains("Sound")) {
            prefs.putFloat("Sound", 1);
        }
        if(!prefs.contains("Coins")){
            prefs.putInteger("Coins",0);
        }
        if (!prefs.contains("00")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    prefs.putBoolean("" + i + j, false);
                }
            }
        }
        prefs.flush();



        Skin= prefs.getInteger("Skin");
        money= prefs.getInteger("Coins");
        /// --------------------
        startState=new StartState(this);
        shopState=new ShopState(this,stage);
        menuState=new MenuState(this,stage);
        playState=new PlayState(this,stage);
        infoState=new InfoState(this,stage);

        ///-----------------------
        shopState.hide();
        menuState.hide();
        playState.hide();
        infoState.hide();
        setScreen(startState);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        sb.dispose();
        stage.dispose();
        sm.dispose();
        tm.dispose();
        startState.dispose();
        shopState.dispose();
        menuState.dispose();
        playState.dispose();
        infoState.dispose();
    }
}
