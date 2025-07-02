package com.Turb1na_.KFUMaze;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class TextureManager {

    /// -----Backgrounds-------------
    public Texture startBackground;
    public Texture priceBackground;
    public Texture paramBackground;
    public Texture loadingMenu;
    public Texture menuBackground;
    public Texture playBackground;
    public Texture winBackground;
    public Texture killBackground;
    public Texture gameBackground;
    public Texture background;
    public Texture plashka;
    public Texture Ad;

    /// ------Buttons------------
    public Texture playBtn;
    public Texture equippedBtn;
    public Texture equipBtn;
    public Texture adBtn;
    public Texture nextBtn;
    public Texture homeBtn;
    public Texture paramBtn;
    public Texture cancelBtn;
    public Texture enterBtn;
    public Texture shopBtn;
    public Texture stopBtn;
    public Texture ctnBtn;
    public Texture exitBtn;
    public Texture againBtn;
    public Texture Black;
    public Texture lock;

    /// ------Sprites-------------
    public Texture sliderBack;
    public Texture hearthZero;
    public Texture hearth;
    public Texture batTexture;
    public Texture knob;
    public Texture coinValue;
    public Texture way;
    public Texture progressBar;
    public Texture door = new Texture("Sprites/Decorations/door.png");
    public Texture exit = new Texture("Sprites/exit.png");
    public Texture shelfFront = new Texture("Sprites/Decorations/bookShelf2.png");
    public Texture shelfLeft = new Texture("Sprites/Decorations/bookShelf1.png");
    public Texture shelfRight = new Texture("Sprites/Decorations/bookShelf3.png");
    public Texture kitchenTable = new Texture("Sprites/Decorations/kitchenTable.png");
    public Texture gym = new Texture("Sprites/Decorations/gym.png");
    public Texture coin = new Texture("Sprites/coin.png");
    public Texture arrowUp = new Texture("Sprites/Tutorial/arrowUp.png");
    public Texture arrowDown = new Texture("Sprites/Tutorial/arrowDown.png");
    public Texture arrowRight = new Texture("Sprites/Tutorial/arrowRight.png");
    public Texture arrowLeft = new Texture("Sprites/Tutorial/arrowLeft.png");
    /// ------Stars-----
    public Texture KStar;
    public Texture FStar;
    public Texture UStar;

    public Texture[][] skinsModel = {
        {new Texture("Player/playerUp.png"), new Texture("Player/playerLeft.png"), new Texture("Player/playerRight.png"), new Texture("Player/playerStop.png")},
        {new Texture("Player/playerUp3.png"), new Texture("Player/playerLeft3.png"), new Texture("Player/playerRight3.png"), new Texture("Player/playerStop3.png")},
        {new Texture("Player/playerUp4.png"), new Texture("Player/playerLeft4.png"), new Texture("Player/playerRight4.png"), new Texture("Player/playerStop4.png")},
        {new Texture("Player/playerUp1.png"), new Texture("Player/playerLeft1.png"), new Texture("Player/playerRight1.png"), new Texture("Player/playerStop1.png")},
        {new Texture("Player/playerUp2.png"), new Texture("Player/playerLeft2.png"), new Texture("Player/playerRight2.png"), new Texture("Player/playerStop2.png")},
        {new Texture("Player/playerUp5.png"), new Texture("Player/playerLeft5.png"), new Texture("Player/playerRight5.png"), new Texture("Player/playerStop5.png")}};
    public Texture[] info = {
        new Texture("Info/info1.png"),
        new Texture("Info/info2.png"),
        new Texture("Info/info3.png"),
        new Texture("Info/info4.png"),
        new Texture("Info/info5.png"),
        new Texture("Info/info6.png"),
        new Texture("Info/info7.png"),
        new Texture("Info/info8.png"),
        new Texture("Info/info9.png"),
        new Texture("Info/info10.png"),
    };
    public Texture[] skins = {
        new Texture("Player/playerStop.png"),
        new Texture("Player/playerStop3.png"),
        new Texture("Player/playerStop4.png"),
        new Texture("Player/playerStop1.png"),
        new Texture("Player/playerStop2.png"),
        new Texture("Player/playerStop5.png")
    };

    public Texture[] levels = {
        new Texture("levels/1.png"),
        new Texture("levels/2.png"),
        new Texture("levels/3.png"),
        new Texture("levels/4.png"),
        new Texture("levels/5.png"),
        new Texture("levels/6.png"),
        new Texture("levels/7.png"),
        new Texture("levels/8.png"),
        new Texture("levels/9.png"),
        new Texture("levels/10.png"),
    };

    public Texture[] levelsNumbers = {
        new Texture("LevelsName/level1.png"),
        new Texture("LevelsName/level2.png"),
        new Texture("LevelsName/level3.png"),
        new Texture("LevelsName/level4.png"),
        new Texture("LevelsName/level5.png"),
        new Texture("LevelsName/level6.png"),
        new Texture("LevelsName/level7.png"),
        new Texture("LevelsName/level8.png"),
        new Texture("LevelsName/level9.png"),
        new Texture("LevelsName/level10.png")
    };

    public Texture[][] killBlocks = {
        {new Texture("Sprites/killBlock/killBlock11.png"), new Texture("Sprites/killBlock/killBlock12.png"), new Texture("Sprites/throns/thron5.png")},
        {new Texture("Sprites/killBlock/killBlock21.png"), new Texture("Sprites/killBlock/killBlock22.png"), new Texture("Sprites/throns/thron6.png")},
        {new Texture("Sprites/killBlock/killBlock31.png"), new Texture("Sprites/killBlock/killBlock32.png"), new Texture("Sprites/throns/thron7.png")},
        {new Texture("Sprites/killBlock/killBlock41.png"), new Texture("Sprites/killBlock/killBlock42.png"), new Texture("Sprites/throns/thron8.png")}
    };
    public Texture[] walls = new Texture[45];
    public Texture[] throns = new Texture[8];
    public Texture[] tables = {new Texture("Sprites/Decorations/table1.png"), new Texture("Sprites/Decorations/table2.png"), new Texture("Sprites/Decorations/table3.png"), new Texture("Sprites/Decorations/table4.png")};
    public Texture[] chairs = {new Texture("Sprites/Decorations/chair1.png"), new Texture("Sprites/Decorations/chair2.png"), new Texture("Sprites/Decorations/chair3.png"), new Texture("Sprites/Decorations/chair4.png")};
    public Texture boardsFront = (new Random().nextInt(100) <= 49 ? new Texture("Sprites/Decorations/boardFront1.png") : new Random().nextInt(50) < 49 ? new Texture("Sprites/Decorations/boardFront2.png") : new Texture("Sprites/Decorations/boardFrontSecret.png"));
    public Texture boardsLeft = (new Random().nextInt(2) == 0 ? new Texture("Sprites/Decorations/boardLeft1.png") : new Texture("Sprites/Decorations/boardLeft2.png"));
    public Texture boardsRight = (new Random().nextInt(2) == 0 ? new Texture("Sprites/Decorations/boardRight1.png") : new Texture("Sprites/Decorations/boardRight2.png"));


    public TextureManager() {
        priceBackground = new Texture("priceBackground.png");
        paramBackground = new Texture("paramBackground.png");
        startBackground = new Texture("StartBackground.png");
        loadingMenu = new Texture("loadingMenu.png");
        menuBackground = new Texture("menuBackground.png");
        playBackground = new Texture("PlayBackground.png");
        winBackground = new Texture("winBackground.png");
        killBackground = new Texture("killBackground.png");
        gameBackground = new Texture("gameBackground.png");
        background = new Texture("background.png");
        plashka = new Texture("plashka.png");
        Ad = new Texture("Info/Ad.png");

        playBtn = new Texture("Buttons/playbtn.png");
        nextBtn = new Texture("Buttons/nextBtn.png");
        homeBtn = new Texture("Buttons/homeBtn.png");
        paramBtn = new Texture("Buttons/paramBtn.png");
        cancelBtn = new Texture("Buttons/cancelBtn.png");
        enterBtn = new Texture("Buttons/enterBtn.png");
        shopBtn = new Texture("Buttons/shopBtn.png");
        Black = new Texture("Buttons/Black.png");
        lock = new Texture("levels/lock.png");
        stopBtn = new Texture("Buttons/stopBtn.png");
        ctnBtn = new Texture("Buttons/ctnBtn.png");
        exitBtn = new Texture("Buttons/exitBtn.png");
        againBtn = new Texture("Buttons/againBtn.png");
        adBtn = new Texture("Buttons/adButton.png");
        equipBtn = new Texture("Buttons/equip.png");
        equippedBtn = new Texture("Buttons/equipped.png");

        batTexture = new Texture("Sprites/Bat.png");
        coinValue = new Texture("CoinValue.png");
        knob = new Texture("Knob.png");
        sliderBack = new Texture("SliderBack.png");
        hearth = new Texture("Sprites/hearth.png");
        hearthZero = new Texture("Sprites/hearthZero.png");
        way = new Texture("Sprites/way.png");
        progressBar = new Texture("Sprites/progressBar.png");

        KStar = new Texture("Sprites/KFU/K.png");
        FStar = new Texture("Sprites/KFU/F.png");
        UStar = new Texture("Sprites/KFU/U.png");

        for (int i = 0; i < walls.length; i++) {
            walls[i] = new Texture("Sprites/walls/" + (i + 1) + ".png");
        }

        for (int i = 0; i < throns.length; i++) {
            throns[i] = new Texture("Sprites/throns/thron" + (i + 1) + ".png");
        }
    }

    public void dispose() {
        equippedBtn.dispose();
        equipBtn.dispose();
        startBackground.dispose();
        priceBackground.dispose();
        paramBackground.dispose();
        loadingMenu.dispose();
        menuBackground.dispose();
        playBackground.dispose();
        winBackground.dispose();
        killBackground.dispose();
        gameBackground.dispose();
        playBtn.dispose();
        nextBtn.dispose();
        homeBtn.dispose();
        paramBtn.dispose();
        cancelBtn.dispose();
        enterBtn.dispose();
        shopBtn.dispose();
        stopBtn.dispose();
        ctnBtn.dispose();
        exitBtn.dispose();
        againBtn.dispose();
        Black.dispose();
        lock.dispose();
        sliderBack.dispose();
        batTexture.dispose();
        knob.dispose();
        coinValue.dispose();
        door.dispose();
        exit.dispose();
        shelfFront.dispose();
        shelfLeft.dispose();
        shelfRight.dispose();
        kitchenTable.dispose();
        gym.dispose();
        coin.dispose();
        arrowUp.dispose();
        arrowDown.dispose();
        arrowRight.dispose();
        arrowLeft.dispose();
        boardsFront.dispose();
        boardsRight.dispose();
        boardsLeft.dispose();
        KStar.dispose();
        FStar.dispose();
        UStar.dispose();
        Ad.dispose();

        for (int i = 0; i < info.length; i++) {
            info[i].dispose();
        }
        for (int i = 0; i < skins.length; i++) {
            skins[i].dispose();
        }
        for (int i = 0; i < levels.length; i++) {
            levels[i].dispose();
        }
        for (int i = 0; i < levelsNumbers.length; i++) {
            levelsNumbers[i].dispose();
        }
        for (int i = 0; i < walls.length; i++) {
            walls[i].dispose();
        }
        for (int i = 0; i < throns.length; i++) {
            throns[i].dispose();
        }
        for (int i = 0; i < tables.length; i++) {
            tables[i].dispose();
        }
        for (int i = 0; i < chairs.length; i++) {
            chairs[i].dispose();
        }

        for (int i = 0; i < skinsModel.length; i++) {
            for (int j = 0; j < skinsModel[0].length; j++) {
                skinsModel[i][j].dispose();
            }
        }
        for (int i = 0; i < killBlocks.length; i++) {
            for (int j = 0; j < killBlocks[0].length; j++) {
                killBlocks[i][j].dispose();
            }
        }
    }
}
