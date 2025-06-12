package com.Turb1na_.KFUMaze.States;

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
import java.util.Timer;

import com.Turb1na_.KFUMaze.Main;
import com.Turb1na_.KFUMaze.Sprites.Bat;
import com.Turb1na_.KFUMaze.Sprites.Player;

import jdk.internal.foreign.abi.fallback.FallbackLinker;

public class PlayState extends State {

    private OrthographicCamera camera;
    private Stage stage;
    Preferences prefs = Gdx.app.getPreferences("Game");
    private Sound SoundBtn = Gdx.audio.newSound(Gdx.files.internal("Audio/ButtonSound.wav"));
    private Music Win = Gdx.audio.newMusic(Gdx.files.internal("Audio/Win.mp3"));
    private Sound WinStar = Gdx.audio.newSound(Gdx.files.internal("Audio/WinStar.wav"));
    private Sound Die = Gdx.audio.newSound(Gdx.files.internal("Audio/Die.mp3"));
    private com.badlogic.gdx.audio.Music GameMusic = Gdx.audio.newMusic(Gdx.files.internal("Audio/GameMusic.mp3"));
    private Texture background;
    /// wallTexturs---------------
    private Texture[] walls = new Texture[45];
    private Texture[] throns = new Texture[8];

    /// ------------------------
    private Texture exit;
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
    private float timeToOpen = 0.8f;
    private boolean Time = true;
    private boolean Exit = false;
    private boolean batsDie = false;
    private boolean[][] levelStars;
    private List<Bat> bats = new ArrayList<>();
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
            "#www          # w#\n" +
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
            "##################",

        "##################\n" +
            "##################\n" +
            "###########o######\n" +
            "########      0###\n" +
            "######## ## ######\n" +
            "########       ###\n" +
            "########### #w ###\n" +
            "#############w ###\n" +
            "#############w ###\n" +
            "#              ###\n" +
            "# ############ ###\n" +
            "# ############ ###\n" +
            "# ############ ###\n" +
            "#         ####u###\n" +
            "######### ########\n" +
            "######### ########\n" +
            "#wwwwwww# #wwwwww#\n" +
            "#w   #          w#\n" +
            "##f       #     w#\n" +
            "#w  #           w#\n" +
            "#w         #    w#\n" +
            "#ww# #wwwww wwwww#\n" +
            "#### #############\n" +
            "#### #############\n" +
            "#p     ###########\n" +
            "# p    ###########\n" +
            "#  p   ### #######\n" +
            "#### #####o#     #\n" +
            "#    ##### #  *  #\n" +
            "#k               #\n" +
            "########## #######\n" +
            "##################",

        "##################\n" +
            "######    ###o####\n" +
            "######  *  ## ####\n" +
            "######     ## ####\n" +
            "########       k##\n" +
            "#     ## #### # ##\n" +
            "#  w  ## ###### ##\n" +
            "# w#w ## ###### ##\n" +
            "#  w  ##        ##\n" +
            "#        #########\n" +
            "##### ############\n" +
            "##### ##    ######\n" +
            "#####f      ######\n" +
            "######## #########\n" +
            "#######    p######\n" +
            "######## #########\n" +
            "#######  #########\n" +
            "####### ##########\n" +
            "####p    #########\n" +
            "####### ##########\n" +
            "#######         ##\n" +
            "############### ##\n" +
            "#####u          ##\n" +
            "##### ############\n" +
            "##### #  o   #####\n" +
            "#####        #####\n" +
            "#######      #####\n" +
            "#######p     #####\n" +
            "#######      #####\n" +
            "############ #####\n" +
            "############0#####\n" +
            "##################",

        "##################\n" +
            "##################\n" +
            "##################\n" +
            "##wwwwwwwwwwwww###\n" +
            "#              ###\n" +
            "# ############ ###\n" +
            "# ##########  u###\n" +
            "# ##########  ####\n" +
            "# ##########   ###\n" +
            "# ############ ###\n" +
            "# ############0###\n" +
            "# ################\n" +
            "# ####      ######\n" +
            "# ####      ######\n" +
            "# ####p     ######\n" +
            "#       f#     ###\n" +
            "#######    p#  ###\n" +
            "#######     #  ###\n" +
            "#######     #  ###\n" +
            "#############    #\n" +
            "##########       #\n" +
            "##########       #\n" +
            "##########p  k   #\n" +
            "##########   #   #\n" +
            "##########       #\n" +
            "############# ####\n" +
            "############# ####\n" +
            "#############   ##\n" +
            "###########     ##\n" +
            "###########  *  ##\n" +
            "###########     ##\n" +
            "##################",

        "##################\n" +
            "##################\n" +
            "##     ###########\n" +
            "##    p###########\n" +
            "##  ##u        0##\n" +
            "#  ###############\n" +
            "# #####f##########\n" +
            "# ###     ########\n" +
            "# ### #   ########\n" +
            "# ###   # ########\n" +
            "# ##### # ########\n" +
            "# ##### # ########\n" +
            "#       # ########\n" +
            "######### ########\n" +
            "####o#### ########\n" +
            "#         ########\n" +
            "# ## #############\n" +
            "# ## ######## ####\n" +
            "# ## #           #\n" +
            "#      ###### ## #\n" +
            "###########      #\n" +
            "#           #o####\n" +
            "# ################\n" +
            "# ################\n" +
            "# ########wwwwwww#\n" +
            "# #o######w     w#\n" +
            "# # ##   #w  #  w#\n" +
            "#      #        w#\n" +
            "### #   k#w     w#\n" +
            "### ######w     w#\n" +
            "### ######w  *  w#\n" +
            "##################",

        "##################\n" +
            "###0##############\n" +
            "#              ###\n" +
            "# #u########## ###\n" +
            "# #  w######## ###\n" +
            "# ## w########   #\n" +
            "#    #######w f  #\n" +
            "############w #  #\n" +
            "#############    #\n" +
            "#############    #\n" +
            "############   ###\n" +
            "############  ####\n" +
            "############  ####\n" +
            "############    ##\n" +
            "##########  p   ##\n" +
            "###############  #\n" +
            "######### ###### #\n" +
            "#www##### ###### #\n" +
            "#       #    ### #\n" +
            "# *  ##      ### #\n" +
            "#    #     #   # #\n" +
            "######   ok#   # #\n" +
            "######  #####  # #\n" +
            "#########   #  # #\n" +
            "######### # #  # #\n" +
            "#########      # #\n" +
            "#########    o # #\n" +
            "########### #### #\n" +
            "########         #\n" +
            "######## ## w#####\n" +
            "########    w#####\n" +
            "##################",

        "##################\n" +
            "##################\n" +
            "###         ######\n" +
            "###        p######\n" +
            "###       p ######\n" +
            "###      p  ######\n" +
            "###u####### ######\n" +
            "### ######  ######\n" +
            "### ######f#######\n" +
            "### ##wwww wwww###\n" +
            "###0##w        ###\n" +
            "######w  #      w#\n" +
            "######w     #   w#\n" +
            "######w         ##\n" +
            "######w    #     #\n" +
            "#######wwww www# #\n" +
            "################ #\n" +
            "###       ###### #\n" +
            "### ##o## ###### #\n" +
            "### ## ## ###### #\n" +
            "###   p   ###### #\n" +
            "### ## ## ###### #\n" +
            "### ## ## ###### #\n" +
            "###             k#\n" +
            "######### ########\n" +
            "#         ########\n" +
            "# ################\n" +
            "# ##wwwwwww#######\n" +
            "# ##w     w#######\n" +
            "# ##w     w#######\n" +
            "#       * w#######\n" +
            "##################\n"};

    private String[] levelMap;
    private Vector2 sizeMap;
    private Player player;
    private int[][] WallMap = new int[32][18];
    private int[][] ThronsMap = new int[32][18];

    private int GetWallMask(int x, int y, String[] map) {
        int mask = 0;
        if (!(y - 1 >= 0) || map[y - 1].charAt(x) == '#') mask += 1;
        if (!(x + 1 < 18) || map[y].charAt(x + 1) == '#') mask += 2;
        if (!(y + 1 < 32) || map[y + 1].charAt(x) == '#') mask += 4;
        if (!(x - 1 >= 0) || map[y].charAt(x - 1) == '#') mask += 8;

        if (mask == 3 && map[y - 1].charAt(x + 1) == '#') return 16;
        if (mask == 6 && map[y + 1].charAt(x + 1) == '#') return 17;
        if (mask == 9 && map[y - 1].charAt(x - 1) == '#') return 18;
        if (mask == 12 && map[y + 1].charAt(x - 1) == '#') return 19;

        if (mask == 7) {
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#'))
                return 20;
            if (!((y - 1 < 0 || x + 1 >= 18) || (map[y - 1].charAt(x + 1) == '#')) && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#'))
                return 21;
            if (((y - 1 < 0 || x + 1 >= 18) || (map[y - 1].charAt(x + 1) == '#')) && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#'))
                return 22;

        }

        if (mask == 11) {
            if (((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#') && !((y - 1 < 0 || x + 1 >= 18) || (map[y - 1].charAt(x + 1) == '#')))
                return 23;
            if (!((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x + 1 >= 18) || (map[y - 1].charAt(x + 1) == '#')))
                return 24;
            if (((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x + 1 >= 18) || (map[y - 1].charAt(x + 1) == '#')))
                return 25;
        }

        if (mask == 13) {
            if (((y - 1 < 0 || x - 1 < 0) || (map[y - 1].charAt(x - 1) == '#')) && !((y + 1 >= 32 || x - 1 < 0) || (map[y + 1].charAt(x - 1) == '#')))
                return 26;
            if (!((y - 1 < 0 || x - 1 < 0) || (map[y - 1].charAt(x - 1) == '#')) && ((y + 1 >= 32 || x - 1 < 0) || (map[y + 1].charAt(x - 1) == '#')))
                return 27;
            if (((y - 1 < 0 || x - 1 < 0) || (map[y - 1].charAt(x - 1) == '#')) && ((y + 1 >= 32 || x - 1 < 0) || (map[y + 1].charAt(x - 1) == '#')))
                return 28;
        }

        if (mask == 14) {
            if (((y + 1 >= 32 || x - 1 < 0) || (map[y + 1].charAt(x - 1) == '#')) && !((y + 1 >= 32 || x + 1 >= 18) || (map[y + 1].charAt(x + 1) == '#')))
                return 29;
            if (!((y + 1 >= 32 || x - 1 < 0) || (map[y + 1].charAt(x - 1) == '#')) && ((y + 1 >= 32 || x + 1 >= 18) || (map[y + 1].charAt(x + 1) == '#')))
                return 30;
            if (((y + 1 >= 32 || x - 1 < 0) || (map[y + 1].charAt(x - 1) == '#')) && ((y + 1 >= 32 || x + 1 >= 18) || (map[y + 1].charAt(x + 1) == '#')))
                return 31;
        }
        if (mask == 15) {
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && !((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 32;
            if (!((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && !((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 33;
            if (!((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && !((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 34;
            if (!((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 35;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && !((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 36;
            if (!((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && !((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 37;
            if (!((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 38;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 39;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && !((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 40;
            if (!((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 41;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 42;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 43;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 44;
        }

        return mask;
    }

    private void generateWallMap(String[] map) {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 18; j++) {
                if (map[i].charAt(j) == '#') {
                    WallMap[i][j] = GetWallMask(j, i, map);
                } else {
                    WallMap[i][j] = -1;
                }
            }
        }
    }

    private void generateThronsMap(String[] map) {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 18; j++) {
                if (map[i].charAt(j) == 'w') {
                    ThronsMap[i][j] = GetThronsMask(j, i, map);
                } else {
                    ThronsMap[i][j] = -1;
                }
            }
        }

    }

    private int GetThronsMask(int x, int y, String[] map) {
        int mask = 0;

        if (((y + 1 >= 32 || map[y + 1].charAt(x) == 'w') && (x + 1 >= 18 || map[y].charAt(x + 1) == 'w')) || ((x - 1 < 0 || map[y].charAt(x - 1) == '#') && (y - 1 < 0 || map[y - 1].charAt(x) == '#')))
            return 0;
        if (((y + 1 >= 32 || map[y + 1].charAt(x) == 'w') && (x - 1 < 0 || map[y].charAt(x - 1) == 'w')) || ((x + 1 >=18 || map[y].charAt(x + 1) == '#') && (y - 1 < 0 || map[y - 1].charAt(x) == '#')))
            return 1;
        if (((y - 1 < 0 || map[y - 1].charAt(x) == 'w') && (x - 1 < 0 || map[y].charAt(x - 1) == 'w')) || ((x + 1 >=18 || map[y].charAt(x + 1) == '#') && (y + 1 >=32 || map[y + 1].charAt(x) == '#')))
            return 2;
        if (((y - 1 < 0 || map[y - 1].charAt(x) == 'w') && (x + 1 >= 18 || map[y].charAt(x + 1) == 'w')) || ((x - 1 < 0 || map[y].charAt(x - 1) == '#') && (y + 1 >=32 || map[y + 1].charAt(x) == '#')))
            return 3;

        if (y - 1 < 0 || map[y - 1].charAt(x) == '#') return 4;
        if (x + 1 >= 18 || map[y].charAt(x + 1) == '#') return 5;
        if (y + 1 >= 32 || map[y + 1].charAt(x) == '#') return 6;
        if (x - 1 < 0 || map[y].charAt(x - 1) == '#') return 7;

        return mask;
    }


    private ImageButton createImageButton(Texture buttonTexture, float Height, float Width) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(Height * Main.SIZECHANGE.y);
        style.imageUp.setMinWidth(Width * Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        return button;
    }

    public PlayState(GameStateManager gsm, float MusicVolume,float SoundVolume,int level, boolean[][] stars) {
        super(gsm, MusicVolume, SoundVolume);

        Win.setVolume(MusicVolume);
        GameMusic.setVolume(MusicVolume);



        levelStars = stars;
        this.level = level;

        GameMusic.setLooping(true);
        GameMusic.play();

        camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
        camera.setToOrtho(false);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        background = new Texture("PlayBackground.png");

        ///download-wall-thorns---------------
        for (int i = 0; i < walls.length; i++) {
            walls[i] = new Texture("Sprites/walls/" + (i + 1) + ".png");
        }

        for (int i = 0; i < throns.length; i++) {
            throns[i] = new Texture("Sprites/throns/thron" + (i + 1) + ".png");
        }
        ///-----------------------------
        exit = new Texture("Sprites/exit.png");
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

        generateWallMap(levelMap);
        generateThronsMap(levelMap);

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
                if (!winBackground.isVisible() && !killBackground.isVisible()) {
                    SoundBtn.play(SoundVolume);
                    Time = false;
                    KStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
                    FStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
                    UStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
                    KStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 74 * 10 * Main.SIZECHANGE.y);
                    FStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 25 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 74 * 10 * Main.SIZECHANGE.y);
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
                SoundBtn.play(SoundVolume);
                gsm.set(new MenuState(gsm, MusicVolume,SoundVolume,levelStars));
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundBtn.play(SoundVolume);
                gsm.set(new InfoState(gsm, MusicVolume, SoundVolume, levelStars, level));
            }
        });

        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundBtn.play(SoundVolume);
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
                SoundBtn.play(SoundVolume);
                gsm.set(new PlayState(gsm, MusicVolume, SoundVolume, level, levelStars));
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
                    player = new Player(new Vector2(60 * j, 60 * (sizeMap.y - 1 - i)), new Vector2(60, 60), levelMap, new Vector2(50 * Main.SIZECHANGE.x, 50 * Main.SIZECHANGE.y), Gdx.graphics.getDeltaTime(),SoundVolume);
                }
                if (levelMap[i].charAt(j) == 'o') {
                    bats.add(new Bat(new Vector2(60 * j, 60 * (sizeMap.y - 1 - i)), new Vector2(60, 60), levelMap, new Vector2(5 * Main.SIZECHANGE.x, 5 * Main.SIZECHANGE.y), true));
                }
                if (levelMap[i].charAt(j) == 'p') {
                    bats.add(new Bat(new Vector2(60 * j, 60 * (sizeMap.y - 1 - i)), new Vector2(60, 60), levelMap, new Vector2(5 * Main.SIZECHANGE.x, 5 * Main.SIZECHANGE.y), false));
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
        for (int i = 0; i < bats.size() && !batsDie; i++) {

            float x = bats.get(i).getPosition().x+5 ;
            float y = bats.get(i).getPosition().y+5;
            float batWidth=50 ;
            float batHeight=50 ;
            if ((x < player.getTruthPosition().x + 60  && x > player.getTruthPosition().x && y < player.getTruthPosition().y + 60 && y > player.getTruthPosition().y) || (x + batWidth < player.getTruthPosition().x + 60  && x + batWidth > player.getTruthPosition().x && y < player.getTruthPosition().y + 60  && y > player.getTruthPosition().y) || (x + batWidth < player.getTruthPosition().x + 60  && x + batWidth > player.getTruthPosition().x && y + batHeight < player.getTruthPosition().y + 60  && y + batHeight > player.getTruthPosition().y) || (x < player.getTruthPosition().x + 60  && x > player.getTruthPosition().x && y + batHeight < player.getTruthPosition().y + 60  && y + batHeight > player.getTruthPosition().y)) {
                batsDie = true;
            }
        }
        handleInpute();

        if (Time) {
            for (int i = 0; i < bats.size(); i++) {
                bats.get(i).move();
            }
            player.move();
            if (player.exit()) Exit = true;
            if (player.exit()) player.setAcceleration(new Vector2(0, 0));
            if (Exit && !winBackground.isVisible()) {
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
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        prefs.putBoolean("" + i + j, levelStars[i][j]);
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


                KStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 64 * 10 * Main.SIZECHANGE.y);
                FStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 25 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 64 * 10 * Main.SIZECHANGE.y);
                UStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 49 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 64 * 10 * Main.SIZECHANGE.y);

                if (player.getStars()[0])
                    KStar.setVisible(true);
                if (player.getStars()[1])
                    FStar.setVisible(true);
                if (player.getStars()[2])
                    UStar.setVisible(true);
            }
            if ((player.isDie() || batsDie) && !killBackground.isVisible()) {
                Die.play(SoundVolume);
                killBackground.setVisible(true);
                GameMusic.dispose();

                exitButton.setPosition(Main.WIDTH / 2 - new Texture("killBackground.png").getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("killBackground.png").getHeight() / 3 * Main.SIZECHANGE.y + 10 * 8 * Main.SIZECHANGE.y);
                againButton.setPosition(Main.WIDTH / 2 - new Texture("killBackground.png").getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - new Texture("killBackground.png").getHeight() / 3 * Main.SIZECHANGE.y + 10 * 28 * Main.SIZECHANGE.y);


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
        if (Exit && deltaSizeIs.x != 2 && KStar.isVisible()) {
            if (Music.x == 0) {
                Music.x = 1;
                WinStar.play(SoundVolume);
            }
            KStar.setSize(deltaSize.x * Main.SIZECHANGE.x, deltaSize.x * Main.SIZECHANGE.y);
            KStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 3 * Main.SIZECHANGE.x + (120 - deltaSize.x / 2) * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 64 * 10 * Main.SIZECHANGE.y + (120 - deltaSize.x / 2) * Main.SIZECHANGE.y);

            KStar.setOrigin(deltaSize.x * Main.SIZECHANGE.x / 2, deltaSize.x * Main.SIZECHANGE.y / 2);

            deltaAngle.x -= (360 * Gdx.graphics.getDeltaTime()) / timeToOpen;
            if (deltaSizeIs.x == 0)
                deltaSize.x += (360 * Gdx.graphics.getDeltaTime()) / 0.66f / timeToOpen;
            else if (deltaSizeIs.x == 1)
                deltaSize.x -= (240 * Gdx.graphics.getDeltaTime()) / 0.33f / timeToOpen;
            if (deltaAngle.x <= 0) {
                deltaAngle.x = 0;
                deltaSizeIs.x = 2;
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
        if (Exit && (deltaSizeIs.x == 2 || !KStar.isVisible()) && FStar.isVisible() && deltaSizeIs.y != 2) {
            if (Music.y == 0) {
                Music.y = 1;
                WinStar.play(SoundVolume);
            }
            FStar.setSize(deltaSize.y * Main.SIZECHANGE.x, deltaSize.y * Main.SIZECHANGE.y);
            FStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 25 * Main.SIZECHANGE.x + (120 - deltaSize.y / 2) * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 64 * 10 * Main.SIZECHANGE.y + (120 - deltaSize.y / 2) * Main.SIZECHANGE.y);

            FStar.setOrigin(deltaSize.y * Main.SIZECHANGE.x / 2, deltaSize.y * Main.SIZECHANGE.y / 2);

            deltaAngle.y -= (360 * Gdx.graphics.getDeltaTime()) / timeToOpen;
            if (deltaSizeIs.y == 0)
                deltaSize.y += (360 * Gdx.graphics.getDeltaTime()) / 0.66f / timeToOpen;
            else if (deltaSizeIs.y == 1)
                deltaSize.y -= (240 * Gdx.graphics.getDeltaTime()) / 0.33f / timeToOpen;
            if (deltaAngle.y <= 0) {
                deltaAngle.y = 0;
                deltaSizeIs.y = 2;
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
        if (Exit && ((deltaSizeIs.x == 2 && deltaSizeIs.y == 2) || (!KStar.isVisible() && deltaSizeIs.y == 2) || (deltaSizeIs.x == 2 && !FStar.isVisible()) || (!KStar.isVisible() && !FStar.isVisible())) && deltaSizeIs.z != 2 && UStar.isVisible()) {
            if (Music.z == 0) {
                Music.z = 1;
                WinStar.play(SoundVolume);
            }
            UStar.setSize(deltaSize.z * Main.SIZECHANGE.x, deltaSize.z * Main.SIZECHANGE.y);
            UStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 49 * Main.SIZECHANGE.x + (120 - deltaSize.z / 2) * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 64 * 10 * Main.SIZECHANGE.y + (120 - deltaSize.z / 2) * Main.SIZECHANGE.y);

            UStar.setOrigin(deltaSize.z * Main.SIZECHANGE.x / 2, deltaSize.z * Main.SIZECHANGE.y / 2);

            deltaAngle.z -= (360 * Gdx.graphics.getDeltaTime()) / timeToOpen;
            if (deltaSizeIs.z == 0)
                deltaSize.z += (360 * Gdx.graphics.getDeltaTime()) / 0.66f / timeToOpen;
            else if (deltaSizeIs.z == 1)
                deltaSize.z -= (240 * Gdx.graphics.getDeltaTime()) / 0.33f / timeToOpen;
            if (deltaAngle.z <= 0) {
                deltaAngle.z = 0;
                deltaSizeIs.z = 2;
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
        ScreenUtils.clear((float) 71 / 255, (float) 71 / 255, (float) 71 / 255, 1);

        sb.draw(background, 0, 0, background.getWidth() * Main.SIZECHANGE.x, background.getHeight() * Main.SIZECHANGE.y);

        for (int i = 0; i < bats.size(); i++) {
            bats.get(i).draw(sb);
        }
        for (int i = 0; i < sizeMap.y; i++) {
            for (int j = 0; j < sizeMap.x; j++) {
                if (levelMap[i].charAt(j) == '#') {
                    try {
                        sb.draw(walls[WallMap[i][j]], walls[0].getWidth() * Main.SIZECHANGE.x * j, walls[0].getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), walls[0].getWidth() * Main.SIZECHANGE.x, walls[0].getHeight() * Main.SIZECHANGE.y);
                    } catch (Exception e) {
                    }
                } else if (levelMap[i].charAt(j) == 'w') {
                    try {
                        sb.draw(throns[ThronsMap[i][j]], throns[0].getWidth() * Main.SIZECHANGE.x * j, throns[0].getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), throns[0].getWidth() * Main.SIZECHANGE.x, throns[0].getHeight() * Main.SIZECHANGE.y);
                    } catch (Exception e) {
                    }
                } else {
                    switch (levelMap[i].charAt(j)) {
                        case '0':
                            sb.draw(exit, exit.getWidth() * Main.SIZECHANGE.x * j, exit.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), exit.getWidth() * Main.SIZECHANGE.x, exit.getHeight() * Main.SIZECHANGE.y);
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
        exit.dispose();
        Ktexture.dispose();
        Ftexture.dispose();
        Utexture.dispose();
        for (int i = 0; i < walls.length; i++) {
            walls[i].dispose();
        }
        for (int i = 0; i < throns.length; i++) {
            throns[i].dispose();
        }
    }
}
