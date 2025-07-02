package com.Turb1na_.KFUMaze;

import com.Turb1na_.KFUMaze.States.AdState;
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

import java.util.Date;
import java.util.Timer;

public class Main extends Game {
    public static int WIDTH = 0;
    private Stage stage;
    private float Timer=0;
    private Date date;
    public static int HEIGHT = 0;

    /// ---------------------------------------
    public Preferences prefs;
    public OrthographicCamera camera;
    public int money;
    public long hearth;
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
    private AdState adState;

    public InfoState getInfoState() {
        return infoState;
    }

    public StartState getStartState() {
        return startState;
    }

    public ShopState getShopState() {
        return shopState;
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public PlayState getPlayState() {
        return playState;
    }

    public AdState getAdState() {
        return adState;
    }

    /// -----------------------------------------------

    @Override
    public void create() {
        date=new Date();
        prefs = Gdx.app.getPreferences("Game");
        camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
        sb = new SpriteBatch();
        HEIGHT = Gdx.graphics.getHeight();
        WIDTH = Gdx.graphics.getWidth();
        SIZECHANGE = new Vector2(Main.WIDTH / 1080f, Main.HEIGHT / 1920f);
        stage = new Stage(new ScreenViewport());


        camera.setToOrtho(false);
        Gdx.input.setInputProcessor(stage);

        if(!prefs.contains("Date")){
            prefs.putLong("Date",date.getTime());
        }
        if (!prefs.contains("Skin")) {
            prefs.putInteger("Skin", 0);
        }
        if (!prefs.contains("Hearth")) {
            prefs.putLong("Hearth", 3);
            hearth=3;
        }else {
            hearth=prefs.getLong("Hearth");
            if(hearth<(date.getTime()- prefs.getLong("Date"))/(30*1000)){
                hearth=Math.min((date.getTime()- prefs.getLong("Date"))/(30*1000),3);
                prefs.putLong("Hearth",hearth);
            }
            else {
                Timer+=(date.getTime()- prefs.getLong("Date"))/1000f;
            }
            prefs.putLong("Date",date.getTime());
        }
        if (!prefs.contains("Music")) {
            prefs.putFloat("Music", 1);
        }
        if (!prefs.contains("Sound")) {
            prefs.putFloat("Sound", 1);
        }
        if (!prefs.contains("Coins")) {
            prefs.putInteger("Coins", 0);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                if(!prefs.contains("" + i + j))
                    prefs.putBoolean("" + i + j, false);
            }
        }
        prefs.flush();


        Skin = prefs.getInteger("Skin");
        money = prefs.getInteger("Coins");

        tm = new TextureManager();
        sm = new SoundManager(this);
        /// --------------------
        startState = new StartState(this);
        adState = new AdState(this,stage);
        shopState = new ShopState(this, stage);
        menuState = new MenuState(this, stage);
        playState = new PlayState(this, stage);
        infoState = new InfoState(this, stage);

        ///-----------------------
        shopState.hide();
        menuState.hide();
        playState.hide();
        infoState.hide();
        adState.hide();
        setScreen(startState);
    }

    @Override
    public void render() {
        super.render();
        if(hearth<3){
            if(hearth<0)hearth=0;
            Timer+=Gdx.graphics.getDeltaTime();
            if(Timer>=30){
                hearth+=1;
                Timer=0;
            }
        }
        else{
            Timer=0;
        }

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
        adState.dispose();
    }
}
