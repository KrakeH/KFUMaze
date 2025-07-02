package com.Turb1na_.KFUMaze.States;

import com.Turb1na_.KFUMaze.Sprites.KillBlock;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Turb1na_.KFUMaze.Main;
import com.Turb1na_.KFUMaze.Sprites.Bat;
import com.Turb1na_.KFUMaze.Sprites.Player;

public class PlayState implements Screen {

    final Main game;
    private boolean isShowed = false;
    private final OrthographicCamera camera;
    private final Stage stage;
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
    private Vector3 deltaAngle = new Vector3(360, 360, 360);
    private Vector3 deltaSize = new Vector3(0, 0, 0);
    private Vector3 deltaSizeIs = new Vector3(0, 0, 0);
    private Vector3 Music = new Vector3(0, 0, 0);
    private float timeToOpen = 0.8f;
    private boolean Time = true;
    private boolean Exit = false;
    private boolean batsDie = false;
    private boolean killBlockDie = false;
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
    private List<Bat> bats = new ArrayList<>();
    private String maps[] = {
        "##################\n" +
            "#########0########\n" +
            "######### ########\n" +
            "######### ########\n" +
            "######### ########\n" +
            "######   -########\n" +
            "######  ##########\n" +
            "######  ##5a5a5a #\n" +
            "######   #5a5a5a h\n" +
            "######## #5a5a5a h\n" +
            "########ue       h\n" +
            "######## #5a5a5a #\n" +
            "######## #########\n" +
            "######    -  #####\n" +
            "######   ### #####\n" +
            "######   ### #####\n" +
            "############ #####\n" +
            "###### c7c7# #####\n" +
            "######i    ef#####\n" +
            "###### c7c7# #####\n" +
            "############ #####\n" +
            "####    #### #####\n" +
            "#### ## #### #####\n" +
            "#### ##      #####\n" +
            "#### #############\n" +
            "#         ###  -##\n" +
            "#    #### ###   ##\n" +
            "#    ####       ##\n" +
            "#k   #########  ##\n" +
            "###########     ##\n" +
            "###########    *y#\n" +
            "##################\n",

        "##################\n" +
            "##              ##\n" +
            "##        k#### ##\n" +
            "##        -#6 e ##\n" +
            "########## #b # ##\n" +
            "##  ggg  # #6 # ##\n" +
            "##ddd ddd# #b # ##\n" +
            "##888 888# #  # ##\n" +
            "#####e#### #### ##\n" +
            "#            #  ##\n" +
            "# ######## # #  ##\n" +
            "#   1f#### # #  ##\n" +
            "###   ##     #  ##\n" +
            "###   -# # ### ###\n" +
            "###### # # ### ###\n" +
            "#i c7# # # ### ###\n" +
            "#i   e # # ### ###\n" +
            "#i c7# #      -###\n" +
            "###### ### #######\n" +
            "##       -     2##\n" +
            "## ### ### #    ##\n" +
            "## ### ### #    ##\n" +
            "## ### ### #   u##\n" +
            "## ### ###-# #####\n" +
            "## ### ### #-#####\n" +
            "## ###       #####\n" +
            "## ####### #######\n" +
            "## ####### #######\n" +
            "##-1111111      ##\n" +
            "########## #   *y#\n" +
            "##########0#    ##\n" +
            "##################",

        "##################\n" +
            "# gg  #0# 5a5a5a h\n" +
            "#dddd # #        h\n" +
            "#8888 # # 5a5a5a h\n" +
            "#####e# #e########\n" +
            "#w1-    #       w#\n" +
            "#w         #    w#\n" +
            "#w   #-         w#\n" +
            "#w     ##       w#\n" +
            "#w     #f  2#   w#\n" +
            "##u - 3#        w#\n" +
            "#w    ##-    #  w#\n" +
            "#w     #        w#\n" +
            "#w              w#\n" +
            "#w        #     w#\n" +
            "#www      1  -# w#\n" +
            "####w  #        w#\n" +
            "####w     k#    w#\n" +
            "####w           w#\n" +
            "####w      # #  w#\n" +
            "####w #-        w#\n" +
            "########### ######\n" +
            "#   ggg   e-######\n" +
            "# 5d7 5d7 # ######\n" +
            "########### ######\n" +
            "##    ##        ##\n" +
            "## ## ## ## ### ##\n" +
            "#- ## ## ## ### ##\n" +
            "#   # ## ## ### ##\n" +
            "# * #   -##-    ##\n" +
            "##y###############\n" +
            "##################",

        "##################\n" +
            "##################\n" +
            "###########o######\n" +
            "# ggg ##     -0###\n" +
            "#d ddd## ## ######\n" +
            "#8 888## --    ###\n" +
            "#d ddd##### #w ###\n" +
            "#8 888#######w ###\n" +
            "##e##########w ###\n" +
            "#              ###\n" +
            "# ############-###\n" +
            "# ############-###\n" +
            "#-############u###\n" +
            "#      -  ####3###\n" +
            "######### ########\n" +
            "######### ########\n" +
            "#wwwwwww# #wwwwww#\n" +
            "#w   #          w#\n" +
            "##f-      #     w#\n" +
            "#w  #     -     w#\n" +
            "#w  -      #    w#\n" +
            "#ww#-#wwwww wwwww#\n" +
            "#### #############\n" +
            "#### #######5a5ai#\n" +
            "#p     #####5a5ai#\n" +
            "# p    #####     #\n" +
            "#  p   ### ##e####\n" +
            "#### #####o#     #\n" +
            "#    ##### #     #\n" +
            "#k    --        *#\n" +
            "########## #####y#\n" +
            "##################",

        "##################\n" +
            "#ggg #    *y#o####\n" +
            "#ddd e     ## ####\n" +
            "#888 #     ## ####\n" +
            "#ddd ###       k##\n" +
            "#888 ### #### #-##\n" +
            "######## ######-##\n" +
            "# 3 #### ######-##\n" +
            "#2#4####        ##\n" +
            "# 1   -  #####e###\n" +
            "### #########    #\n" +
            "### ####   -# 5j7#\n" +
            "###f  -     # 5j7#\n" +
            "######## ####    #\n" +
            "#######    p# 5j7#\n" +
            "######## #### 5j7#\n" +
            "####### -####    #\n" +
            "####### ##### 5j7#\n" +
            "####p    #### 5j7#\n" +
            "####### ##########\n" +
            "#######-        ##\n" +
            "############### ##\n" +
            "#####u         -##\n" +
            "##### ############\n" +
            "##### -    o    ##\n" +
            "#######       - ##\n" +
            "# c7c7#         ##\n" +
            "#ic7c7# p       ##\n" +
            "#i    e         ##\n" +
            "#ic7c7#         ##\n" +
            "# c7c7# q     - 0#\n" +
            "##################",

        "##################\n" +
            "##################\n" +
            "##################\n" +
            "##################\n" +
            "# 1111111111111###\n" +
            "# #####e###### ###\n" +
            "# #6666 6666##u###\n" +
            "# #bbbb bbbb## ###\n" +
            "#-#6666 6666##-###\n" +
            "# #bbbb bbbb## ###\n" +
            "# #         ##0###\n" +
            "# ################\n" +
            "#-#### 11111######\n" +
            "# ####     -######\n" +
            "# ####p-    ######\n" +
            "#   -   f#-    ###\n" +
            "#######    p#  ###\n" +
            "#######     #  ###\n" +
            "#######-3333#  ###\n" +
            "#############-  2#\n" +
            "#        #      2#\n" +
            "# 5d75d7 e      -#\n" +
            "# 5d75d7 #p  k   #\n" +
            "#        #   #   #\n" +
            "# 5d75d7 #   -   #\n" +
            "# 5d75d7 #### ####\n" +
            "#        #### ####\n" +
            "# lmmmmn #### - ##\n" +
            "#        #      ##\n" +
            "# lmmmmn e     *y#\n" +
            "#        #      ##\n" +
            "##################",

        "##################\n" +
            "##################\n" +
            "##-    ###########\n" +
            "##    p###########\n" +
            "## ###u    -   0##\n" +
            "#  ###############\n" +
            "# #####f##########\n" +
            "# ### 3   # ggggg#\n" +
            "# ###2#4  # ddddd#\n" +
            "# ### 1-# # 88888#\n" +
            "# ##### # # ddddd#\n" +
            "# ##### # # 88888#\n" +
            "#-      # # ddddd#\n" +
            "######### # 88888#\n" +
            "####o#### e ddddd#\n" +
            "#        -# 88888#\n" +
            "# ## #############\n" +
            "# ## ######## ####\n" +
            "# ## #      -    #\n" +
            "#-    2###### ## #\n" +
            "###########     -#\n" +
            "#        -  #o####\n" +
            "# ####e###########\n" +
            "# ###  5a#########\n" +
            "# ###lmmn#wwwwwww#\n" +
            "# #o######w  3  w#\n" +
            "# # ##-  #w 2#4 w#\n" +
            "#-     #-    1  w#\n" +
            "### #   k#w     w#\n" +
            "### ######w  *  w#\n" +
            "### #########y####\n" +
            "##################",

        "##################\n" +
            "###0##############\n" +
            "#             -###\n" +
            "# #u##########2###\n" +
            "# #-2#########2###\n" +
            "# ##2#########   #\n" +
            "#  - #######w f  #\n" +
            "############w # -#\n" +
            "## ggg ggg e     #\n" +
            "## ddd ddd ##    #\n" +
            "## 888 888 #   ###\n" +
            "## ddd ddd #  ####\n" +
            "## 888 888 e  ####\n" +
            "############-   ##\n" +
            "##########  p   ##\n" +
            "###############  #\n" +
            "######### ###### #\n" +
            "######### ###### #\n" +
            "##     -# -  ### #\n" +
            "#y*   #      ### #\n" +
            "##   ##    #  2#-#\n" +
            "##   #   ok#  2# #\n" +
            "##   #33#####  # #\n" +
            "####e#### 3 #  # #\n" +
            "#      ##2#4#  # #\n" +
            "#5j75j7## 1    # #\n" +
            "#5j75j7##-   o-# #\n" +
            "#      #### #### #\n" +
            "#5j75j7#-  -     #\n" +
            "#5j75j7# ##2######\n" +
            "########   2######\n" +
            "##################",

            "##################\n" +
            "####lmmmn#0#######\n" +
            "#### 5d7 e #######\n" +
            "##########-#######\n" +
            "#w              w#\n" +
            "#w      #       w#\n" +
            "#w      - u#    w#\n" +
            "#w  #           w#\n" +
            "#w  1   2#      w#\n" +
            "#w     #        w#\n" +
            "#w     1   #    w#\n" +
            "#w              w#\n" +
            "#w #      -     w#\n" +
            "####### ##########\n" +
            "#######       f###\n" +
            "#############w w##\n" +
            "#i c7c7c7c7 #w w##\n" +
            "#i c7c7c7c7 #w-w##\n" +
            "#i          #w w##\n" +
            "############## ###\n" +
            "#      -       ###\n" +
            "# ################\n" +
            "# ### ## #########\n" +
            "#   -      -#lmmn#\n" +
            "##### ## ## e  5a#\n" +
            "#   -     k-#lmmn#\n" +
            "# ###o##o#########\n" +
            "# ################\n" +
            "# 2########     ##\n" +
            "##2######## #  *y#\n" +
            "##33333-    #   ##\n" +
            "##################\n",

        "##################\n" +
            "##################\n" +
            "###        - #####\n" +
            "###        p #####\n" +
            "###       p  #####\n" +
            "###      p   #####\n" +
            "###u######## #####\n" +
            "#66-66#####- #####\n" +
            "#     #####f######\n" +
            "#66 66#wwww www###\n" +
            "#     #w       ###\n" +
            "#66 66#w  #-   1w#\n" +
            "#     #w     #  w#\n" +
            "#66 66#w     1 -##\n" +
            "#     #w    #    #\n" +
            "###0###wwwww ww#-#\n" +
            "################ #\n" +
            "###  -    #lmmn# #\n" +
            "### ##o## #    # #\n" +
            "### ## ##-#5d7 # #\n" +
            "###   p   #    e #\n" +
            "###-## ## #lmmn# #\n" +
            "### ## ## ###### #\n" +
            "###        -    k#\n" +
            "######### ##e#####\n" +
            "#4 -      ##     #\n" +
            "# ##########5a5a h\n" +
            "# ##wwwwww##5a5a h\n" +
            "# ##w    w##5a5a h\n" +
            "# ##w    w##5a5a h\n" +
            "#  -     *y#5a5a #\n" +
            "##################\n"};

    private String[] levelMap;
    private int level = 1;
    private Vector2 sizeMap;
    private Player player;
    private int[][] WallMap = new int[32][18];
    private int[][] ThronsMap = new int[32][18];
    private int[][] Tutorial = {
        {4, 4, 9},
        {4, 7, 6},
        {4, 14, 8},
        {4, 22, 12},
        {4, 27, 4},
        {3, 27, 12}
    };
    private KillBlock[][] KillMap = new KillBlock[32][18];
    private List<KillBlock> KillBlocks = new ArrayList<>();
    private Image[] levelsNumber = new Image[10];

    private int GetWallMask(int x, int y, String[] map) {
        int mask = 0;
        if (!(y - 1 >= 0) || map[y - 1].charAt(x) == '#' || map[y - 1].charAt(x) == 'h') mask += 1;
        if (!(x + 1 < 18) || map[y].charAt(x + 1) == '#' || map[y].charAt(x + 1) == 'h') mask += 2;
        if (!(y + 1 < 32) || map[y + 1].charAt(x) == '#' || map[y + 1].charAt(x) == 'h') mask += 4;
        if (!(x - 1 >= 0) || map[y].charAt(x - 1) == '#' || map[y].charAt(x - 1) == 'h') mask += 8;

        if (mask == 3 && map[y - 1].charAt(x + 1) == '#') return 16;
        if (mask == 6 && map[y + 1].charAt(x + 1) == '#') return 17;
        if (mask == 9 && map[y - 1].charAt(x - 1) == '#') return 18;
        if (mask == 12 && map[y + 1].charAt(x - 1) == '#') return 19;

        if (mask == 7) {
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#'))
                return 20;
            if (!((y - 1 < 0 || x + 1 >= 18) || (map[y - 1].charAt(x + 1) == '#')) && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#'))
                return 21;
            if (((y - 1 < 0 || x + 1 >= 18) || (map[y - 1].charAt(x + 1) == '#') || (map[y - 1].charAt(x + 1) == 'h')) && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#' || map[y + 1].charAt(x + 1) == 'h'))
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
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#' || map[y - 1].charAt(x + 1) == 'h') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#' || map[y + 1].charAt(x + 1) == 'h') && !((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#' || map[y + 1].charAt(x - 1) == 'h') && !((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#' || map[y - 1].charAt(x - 1) == 'h'))
                return 36;
            if (!((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && !((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 37;
            if (!((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 38;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 39;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#' || map[y - 1].charAt(x + 1) == 'h') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#' || map[y + 1].charAt(x + 1) == 'h') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#' || map[y + 1].charAt(x - 1) == 'h') && !((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#' || map[y - 1].charAt(x - 1) == 'h'))
                return 40;
            if (!((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 41;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#') && !((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#'))
                return 42;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#' || map[y - 1].charAt(x + 1) == 'h') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#' || map[y + 1].charAt(x + 1) == 'h') && !((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#' || map[y + 1].charAt(x - 1) == 'h') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#' || map[y - 1].charAt(x - 1) == 'h'))
                return 43;
            if (((y - 1 < 0 || x + 1 >= 18) || map[y - 1].charAt(x + 1) == '#' || map[y - 1].charAt(x + 1) == 'h') && ((y + 1 >= 32 || x + 1 >= 18) || map[y + 1].charAt(x + 1) == '#' || map[y + 1].charAt(x + 1) == 'h') && ((y + 1 >= 32 || x - 1 < 0) || map[y + 1].charAt(x - 1) == '#' || map[y + 1].charAt(x - 1) == 'h') && ((y - 1 < 0 || x - 1 < 0) || map[y - 1].charAt(x - 1) == '#' || map[y - 1].charAt(x - 1) == 'h'))
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
        if (((y + 1 >= 32 || map[y + 1].charAt(x) == 'w') && (x - 1 < 0 || map[y].charAt(x - 1) == 'w')) || ((x + 1 >= 18 || map[y].charAt(x + 1) == '#') && (y - 1 < 0 || map[y - 1].charAt(x) == '#')))
            return 1;
        if (((y - 1 < 0 || map[y - 1].charAt(x) == 'w') && (x - 1 < 0 || map[y].charAt(x - 1) == 'w')) || ((x + 1 >= 18 || map[y].charAt(x + 1) == '#') && (y + 1 >= 32 || map[y + 1].charAt(x) == '#')))
            return 2;
        if (((y - 1 < 0 || map[y - 1].charAt(x) == 'w') && (x + 1 >= 18 || map[y].charAt(x + 1) == 'w')) || ((x - 1 < 0 || map[y].charAt(x - 1) == '#') && (y + 1 >= 32 || map[y + 1].charAt(x) == '#')))
            return 3;

        if (y - 1 < 0 || map[y - 1].charAt(x) == '#') return 4;
        if (x + 1 >= 18 || map[y].charAt(x + 1) == '#') return 5;
        if (y + 1 >= 32 || map[y + 1].charAt(x) == '#') return 6;
        if (x - 1 < 0 || map[y].charAt(x - 1) == '#') return 7;

        return mask;
    }

    public void regenerate(int level) {
        this.level = level;

        Time = true;

        KillBlocks.clear();
        bats.clear();

        sizeMap = new Vector2(maps[level - 1].split("\\r?\\n")[0].length(), maps[level - 1].split("\\r?\\n").length);
        levelMap = maps[level - 1].split("\\r?\\n");

        for (int i = 0; i < sizeMap.y; i++) {
            for (int j = 0; j < sizeMap.x; j++) {
                KillMap[i][j] = null;
                switch (levelMap[i].charAt(j)) {
                    case '*':
                        player = new Player(game,new Vector2(60 * j, 60 * (sizeMap.y - 1 - i)), new Vector2(60, 60), levelMap, new Vector2(5 * Main.SIZECHANGE.x * 50, 5 * 50));
                        break;
                    case 'o':
                        bats.add(new Bat(game,new Vector2(60 * j, 60 * (sizeMap.y - 1 - i)), new Vector2(60, 60), levelMap, new Vector2(6 * Main.SIZECHANGE.x * 50, 6 * 50), true));
                        break;
                    case 'p':
                        bats.add(new Bat(game,new Vector2(60 * j, 60 * (sizeMap.y - 1 - i)), new Vector2(60, 60), levelMap, new Vector2(6 * Main.SIZECHANGE.x * 50, 6 * 50), false));
                        break;
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                        KillMap[i][j] = new KillBlock(game,new Vector2(60 * j, 60 * (sizeMap.y - 1 - i)), new Vector2(60, 60), levelMap, levelMap[i].charAt(j));
                        KillBlocks.add(KillMap[i][j]);
                        break;
                }
            }
        }

        ///----------------

        generateWallMap(levelMap);
        generateThronsMap(levelMap);

        create();
    }

    private void create() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 3; j++) {
                levelStars[i][j] = game.prefs.getBoolean("" + i + j);
            }
        }

        /// -----------Buttons------------------
        stopButton.clearListeners();
        stopButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!winBackground.isVisible() && !killBackground.isVisible() && !menuBackground.isVisible()) {
                    game.sm.SoundBtn.play(game.sm.SoundVolume);
                    Time = false;
                    KStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
                    FStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
                    UStar.setSize(24 * 10 * Main.SIZECHANGE.x, 24 * 10 * Main.SIZECHANGE.y);
                    KStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 74 * 10 * Main.SIZECHANGE.y);
                    FStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 25 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 74 * 10 * Main.SIZECHANGE.y);
                    UStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 49 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 74 * 10 * Main.SIZECHANGE.y);
                    KStar.setOrigin(0, 0);
                    FStar.setOrigin(0, 0);
                    UStar.setOrigin(0, 0);
                    KStar.setRotation(0);
                    FStar.setRotation(0);
                    UStar.setRotation(0);

                    continueButton.setPosition(Main.WIDTH / 2 - game.tm.gameBackground.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.gameBackground.getHeight() / 3 * Main.SIZECHANGE.y + 10 * 48 * Main.SIZECHANGE.y);
                    exitButton.setPosition(Main.WIDTH / 2 - game.tm.gameBackground.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.gameBackground.getHeight() / 3 * Main.SIZECHANGE.y + 10 * 8 * Main.SIZECHANGE.y);
                    againButton.setPosition(Main.WIDTH / 2 - game.tm.gameBackground.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.gameBackground.getHeight() / 3 * Main.SIZECHANGE.y + 10 * 28 * Main.SIZECHANGE.y);

                    menuBackground.setVisible(true);
                    continueButton.setVisible(true);
                    againButton.setVisible(true);
                    exitButton.setVisible(true);

                    if (levelStars[level - 1][0])
                        KStar.setVisible(true);
                    if (levelStars[level - 1][1])
                        FStar.setVisible(true);
                    if (levelStars[level - 1][2])
                        UStar.setVisible(true);
                }
            }
        });
        exitButton.clearListeners();
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sm.SoundBtn.play(game.sm.SoundVolume);

                menuBackground.setVisible(false);
                againButton.setVisible(false);
                continueButton.setVisible(false);
                exitButton.setVisible(false);
                nextButton.setVisible(false);
                KStar.setVisible(false);
                FStar.setVisible(false);
                UStar.setVisible(false);
                killBackground.setVisible(false);
                winBackground.setVisible(false);

                game.setScreen(game.getMenuState());
                game.getMenuState().update();
                game.getMenuState().show();
                game.getPlayState().hide();
                game.sm.MenuMusicPlay();
            }
        });
        nextButton.clearListeners();
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sm.SoundBtn.play(game.sm.SoundVolume);
                game.sm.GameMusic.stop();
                game.money += player.getCoins();
                game.prefs.putInteger("Coins", game.money);
                game.prefs.flush();

                game.setScreen(game.getInfoState());
                game.getInfoState().regenerate(level);
                game.getInfoState().show();
                game.getPlayState().hide();
            }
        });
        continueButton.clearListeners();
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sm.SoundBtn.play(game.sm.SoundVolume);
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
        againButton.clearListeners();
        againButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.sm.SoundBtn.play(game.sm.SoundVolume);
                game.sm.GameMusic.stop();
                if (Exit) {
                    game.money += player.getCoins();
                    game.prefs.putInteger("Coins", game.money);
                    game.prefs.flush();
                }
                if(game.hearth>0) {
                    regenerate(level);
                    menuBackground.setVisible(false);
                    againButton.setVisible(false);
                    continueButton.setVisible(false);
                    exitButton.setVisible(false);
                    nextButton.setVisible(false);
                    KStar.setVisible(false);
                    FStar.setVisible(false);
                    UStar.setVisible(false);
                    killBackground.setVisible(false);
                    winBackground.setVisible(false);
                    Time = true;
                    Exit = false;
                    batsDie = false;
                    killBlockDie = false;
                }else{
                    game.setScreen(game.getAdState());
                    game.getAdState().regenerate(level-1);
                    game.getAdState().show();
                    game.getMenuState().hide();
                }
            }
        });
        /// -------------------------------------------------

        stopButton.setVisible(true);
        againButton.setVisible(false);
        continueButton.setVisible(false);
        exitButton.setVisible(false);
        nextButton.setVisible(false);
        menuBackground.setVisible(false);
        killBackground.setVisible(false);
        winBackground.setVisible(false);
        for (int i = 0; i < 10; i++) {
            levelsNumber[i].setVisible(false);
        }
        KStar.setVisible(false);
        FStar.setVisible(false);
        UStar.setVisible(false);

        game.sm.GameMusic.play();

        Time = true;
        Exit = false;
        batsDie = false;
        killBlockDie = false;

        deltaAngle = new Vector3(360, 360, 360);
        deltaSize = new Vector3(0, 0, 0);
        deltaSizeIs = new Vector3(0, 0, 0);
        Music = new Vector3(0, 0, 0);
    }

    private ImageButton createImageButton(Texture buttonTexture, float Height, float Width) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        style.imageUp.setMinHeight(Height * Main.SIZECHANGE.y);
        style.imageUp.setMinWidth(Width * Main.SIZECHANGE.x);

        ImageButton button = new ImageButton(style);

        return button;
    }

    public PlayState(Main game, Stage stage) {
        this.game = game;
        this.stage = stage;

        camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
        camera.setToOrtho(false);

        ///download-wall-thorns---------------

        ///-----------------------------

        menuBackground = new Image(game.tm.gameBackground);
        killBackground = new Image(game.tm.killBackground);
        winBackground = new Image(game.tm.winBackground);

        for (int i = 0; i < 10; i++) {
            levelsNumber[i] = new Image(game.tm.levelsNumbers[i]);
            levelsNumber[i].setVisible(false);
            levelsNumber[i].setSize(520 * Main.SIZECHANGE.x, 110 * Main.SIZECHANGE.y);
            levelsNumber[i].setPosition(Main.WIDTH / 2 - game.tm.winBackground.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 12 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.winBackground.getHeight() / 3 * Main.SIZECHANGE.y + 10 * 82 * Main.SIZECHANGE.y);
        }

        stopButton = createImageButton(game.tm.stopBtn, 120, 120);
        continueButton = createImageButton(game.tm.ctnBtn, 160, 67 * 10);
        againButton = createImageButton(game.tm.againBtn, 160, 67 * 10);
        exitButton = createImageButton(game.tm.exitBtn, 160, 67 * 10);
        nextButton = createImageButton(game.tm.nextBtn, 160, 67 * 10);

        menuBackground.setSize(game.tm.gameBackground.getWidth() / 1.5f * Main.SIZECHANGE.x, game.tm.gameBackground.getHeight() / 1.5f * Main.SIZECHANGE.y);
        menuBackground.setPosition(Main.WIDTH / 2 - game.tm.gameBackground.getWidth() / 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.gameBackground.getHeight() / 3 * Main.SIZECHANGE.y);

        killBackground.setSize(game.tm.killBackground.getWidth() / 1.5f * Main.SIZECHANGE.x, game.tm.killBackground.getHeight() / 1.5f * Main.SIZECHANGE.y);
        killBackground.setPosition(Main.WIDTH / 2 - game.tm.killBackground.getWidth() / 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.killBackground.getHeight() / 3 * Main.SIZECHANGE.y);

        winBackground.setSize(game.tm.winBackground.getWidth() / 1.5f * Main.SIZECHANGE.x, game.tm.winBackground.getHeight() / 1.5f * Main.SIZECHANGE.y);
        winBackground.setPosition(Main.WIDTH / 2 - game.tm.winBackground.getWidth() / 3 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.winBackground.getHeight() / 3 * Main.SIZECHANGE.y);

        stopButton.setPosition(Main.WIDTH - stopButton.getWidth() - 60 * Main.SIZECHANGE.x, Main.HEIGHT - stopButton.getHeight() - 60 * Main.SIZECHANGE.y);
        nextButton.setPosition(Main.WIDTH / 2 - game.tm.winBackground.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.winBackground.getHeight() / 3 * Main.SIZECHANGE.y + 10 * 28 * Main.SIZECHANGE.y);

        KStar = new Image(game.tm.KStar);
        FStar = new Image(game.tm.FStar);
        UStar = new Image(game.tm.UStar);

        create();

        ///-----------------------

        stage.addActor(stopButton);
        stage.addActor(menuBackground);
        stage.addActor(killBackground);
        stage.addActor(winBackground);
        for (int i = 0; i < 10; i++) {
            stage.addActor(levelsNumber[i]);
        }
        stage.addActor(continueButton);
        stage.addActor(nextButton);
        stage.addActor(againButton);
        stage.addActor(exitButton);
        stage.addActor(KStar);
        stage.addActor(FStar);
        stage.addActor(UStar);


    }

    @Override
    public void show() {
        isShowed = true;
        create();
        Time = true;
        Exit = false;
        batsDie = false;
        killBlockDie = false;

        deltaAngle = new Vector3(360, 360, 360);
        deltaSize = new Vector3(0, 0, 0);
        deltaSizeIs = new Vector3(0, 0, 0);
        Music = new Vector3(0, 0, 0);

        /// -----------Music--------------
        game.sm.setVolume();
        /// ---------------------------
    }

    @Override
    public void render(float delta) {
        for (int i = 0; i < KillBlocks.size(); i++) {
            KillBlocks.get(i).update(delta);
        }

        if (KillMap[31 - Math.round(player.getTruthPosition().y / 60f)][Math.round(player.getTruthPosition().x / 60f)] != null && !KillMap[31 - Math.round(player.getTruthPosition().y / 60f)][Math.round(player.getTruthPosition().x / 60f)].isRunning()) {
            KillMap[31 - Math.round(player.getTruthPosition().y / 60f)][Math.round(player.getTruthPosition().x / 60f)].run();
        }
        if (KillMap[31 - Math.round(player.getTruthPosition().y / 60f)][Math.round(player.getTruthPosition().x / 60f)] != null && KillMap[31 - Math.round(player.getTruthPosition().y / 60f)][Math.round(player.getTruthPosition().x / 60f)].isCanKill()) {
            killBlockDie = true;
        }

        for (int i = 0; i < bats.size() && !batsDie; i++) {

            float x = bats.get(i).getPosition().x + 5;
            float y = bats.get(i).getPosition().y + 5;
            float batWidth = 50;
            float batHeight = 50;
            if ((x < player.getTruthPosition().x + 60 && x > player.getTruthPosition().x && y < player.getTruthPosition().y + 60 && y > player.getTruthPosition().y) || (x + batWidth < player.getTruthPosition().x + 60 && x + batWidth > player.getTruthPosition().x && y < player.getTruthPosition().y + 60 && y > player.getTruthPosition().y) || (x + batWidth < player.getTruthPosition().x + 60 && x + batWidth > player.getTruthPosition().x && y + batHeight < player.getTruthPosition().y + 60 && y + batHeight > player.getTruthPosition().y) || (x < player.getTruthPosition().x + 60 && x > player.getTruthPosition().x && y + batHeight < player.getTruthPosition().y + 60 && y + batHeight > player.getTruthPosition().y)) {
                batsDie = true;
            }
        }
        if (Time) {
            for (int i = 0; i < bats.size(); i++) {
                bats.get(i).move(delta);
            }
            player.move(delta);


            if (player.exit()) Exit = true;
            if (player.exit()) player.setAcceleration(new Vector2(0, 0));
            if (Exit && !winBackground.isVisible()) {
                game.sm.GameMusic.stop();
                game.sm.Win.play();
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

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 3; j++) {
                        game.prefs.putBoolean("" + i + j, levelStars[i][j]);
                    }
                }
                game.prefs.flush();

                winBackground.setVisible(true);

                levelsNumber[level-1].setVisible(true);
                againButton.setVisible(true);
                nextButton.setVisible(true);
                KStar.setSize(0, 0);
                FStar.setSize(0, 0);
                UStar.setSize(0, 0);
                againButton.setPosition(Main.WIDTH / 2 - game.tm.winBackground.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.winBackground.getHeight() / 3 * Main.SIZECHANGE.y + 10 * 8 * Main.SIZECHANGE.y);


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
            if ((player.isDie() || batsDie || killBlockDie) && !killBackground.isVisible()) {
                game.hearth-=1;
                game.prefs.putLong("Hearth", game.hearth);
                game.prefs.flush();

                game.sm.Die.play(game.sm.SoundVolume);
                killBackground.setVisible(true);
                game.sm.GameMusic.stop();

                exitButton.setPosition(Main.WIDTH / 2 - game.tm.killBackground.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.killBackground.getHeight() / 3 * Main.SIZECHANGE.y + 10 * 8 * Main.SIZECHANGE.y);
                againButton.setPosition(Main.WIDTH / 2 - game.tm.killBackground.getWidth() / 3 * Main.SIZECHANGE.x + 10 * 4 * Main.SIZECHANGE.x, Main.HEIGHT / 2 - game.tm.killBackground.getHeight() / 3 * Main.SIZECHANGE.y + 10 * 28 * Main.SIZECHANGE.y);


                againButton.setVisible(true);
                exitButton.setVisible(true);
                Time = false;

            }
        }
        /// ------------------------
        player.input(Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
        ///------------------
        camera.position.set(player.getPosition().x * Main.SIZECHANGE.x, player.getPosition().y * Main.SIZECHANGE.y, 0);
        camera.zoom = 0.75f;
        camera.update();
        game.sb.setProjectionMatrix(camera.combined);

        ///----K-STAR------------------------------
        if (Exit && deltaSizeIs.x != 2 && KStar.isVisible()) {
            if (Music.x == 0) {
                Music.x = 1;
                game.sm.WinStar.play(game.sm.SoundVolume);
            }
            KStar.setSize(deltaSize.x * Main.SIZECHANGE.x, deltaSize.x * Main.SIZECHANGE.y);
            KStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 3 * Main.SIZECHANGE.x + (120 - deltaSize.x / 2) * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 60.5f * 10 * Main.SIZECHANGE.y + (120 - deltaSize.x / 2) * Main.SIZECHANGE.y);

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
                game.sm.WinStar.play(game.sm.SoundVolume);
            }
            FStar.setSize(deltaSize.y * Main.SIZECHANGE.x, deltaSize.y * Main.SIZECHANGE.y);
            FStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 25 * Main.SIZECHANGE.x + (120 - deltaSize.y / 2) * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 60.5f * 10 * Main.SIZECHANGE.y + (120 - deltaSize.y / 2) * Main.SIZECHANGE.y);

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
                game.sm.WinStar.play(game.sm.SoundVolume);

            }
            UStar.setSize(deltaSize.z * Main.SIZECHANGE.x, deltaSize.z * Main.SIZECHANGE.y);
            UStar.setPosition(Main.WIDTH / 2 - menuBackground.getWidth() / 2 + 10 * 49 * Main.SIZECHANGE.x + (120 - deltaSize.z / 2) * Main.SIZECHANGE.x, Main.HEIGHT / 2 - menuBackground.getHeight() / 2 + 60.5f * 10 * Main.SIZECHANGE.y + (120 - deltaSize.z / 2) * Main.SIZECHANGE.y);

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

        game.sb.begin();
        ScreenUtils.clear((float) 71 / 255, (float) 71 / 255, (float) 71 / 255, 1);

        game.sb.draw(game.tm.playBackground, 0, 0, game.tm.playBackground.getWidth() * Main.SIZECHANGE.x, game.tm.playBackground.getHeight() * Main.SIZECHANGE.y);

        if (level == 1) {
            for (int i = 0; i < Tutorial.length; i++) {
                switch (Tutorial[i][0]) {
                    case 1:
                        game.sb.draw(game.tm.arrowRight, 60 * Main.SIZECHANGE.x * Tutorial[i][2], 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - Tutorial[i][1]), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                        break;
                    case 2:
                        game.sb.draw(game.tm.arrowDown, 60 * Main.SIZECHANGE.x * Tutorial[i][2], 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - Tutorial[i][1]), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                        break;
                    case 3:
                        game.sb.draw(game.tm.arrowLeft, 60 * Main.SIZECHANGE.x * Tutorial[i][2], 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - Tutorial[i][1]), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                        break;
                    case 4:
                        game.sb.draw(game.tm.arrowUp, 60 * Main.SIZECHANGE.x * Tutorial[i][2], 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - Tutorial[i][1]), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                        break;
                }
            }
        }

        for (int i = 0; i < sizeMap.y; i++) {
            for (int j = 0; j < sizeMap.x; j++) {
                if (levelMap[i].charAt(j) == '#') {
                    try {
                        game.sb.draw(game.tm.walls[WallMap[i][j]], game.tm.walls[0].getWidth() * Main.SIZECHANGE.x * j, game.tm.walls[0].getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), game.tm.walls[0].getWidth() * Main.SIZECHANGE.x, game.tm.walls[0].getHeight() * Main.SIZECHANGE.y);
                    } catch (Exception e) {
                    }
                } else if (levelMap[i].charAt(j) == 'w') {
                    try {
                        game.sb.draw(game.tm.throns[ThronsMap[i][j]], game.tm.throns[0].getWidth() * Main.SIZECHANGE.x * j, game.tm.throns[0].getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), game.tm.throns[0].getWidth() * Main.SIZECHANGE.x, game.tm.throns[0].getHeight() * Main.SIZECHANGE.y);
                    } catch (Exception e) {
                    }
                } else {
                    switch (levelMap[i].charAt(j)) {
                        case '0':
                            game.sb.draw(game.tm.exit, 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), game.tm.exit.getWidth() * Main.SIZECHANGE.x, game.tm.exit.getHeight() * Main.SIZECHANGE.y);
                            break;
                        case 'k':
                            game.sb.draw(game.tm.KStar, game.tm.KStar.getWidth() * Main.SIZECHANGE.x * j, game.tm.KStar.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), game.tm.KStar.getWidth() * Main.SIZECHANGE.x, game.tm.KStar.getHeight() * Main.SIZECHANGE.y);
                            break;
                        case 'f':
                            game.sb.draw(game.tm.FStar, game.tm.FStar.getWidth() * Main.SIZECHANGE.x * j, game.tm.FStar.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), game.tm.FStar.getWidth() * Main.SIZECHANGE.x, game.tm.FStar.getHeight() * Main.SIZECHANGE.y);
                            break;
                        case 'u':
                            game.sb.draw(game.tm.UStar, game.tm.UStar.getWidth() * Main.SIZECHANGE.x * j, game.tm.UStar.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), game.tm.UStar.getWidth() * Main.SIZECHANGE.x, game.tm.UStar.getHeight() * Main.SIZECHANGE.y);
                            break;
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                            game.sb.draw(game.tm.chairs[levelMap[i].charAt(j) - '5'], 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                            game.sb.draw(game.tm.tables[levelMap[i].charAt(j) - 'a'], 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case 'e':
                            game.sb.draw(game.tm.door, 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case 'y':
                            game.sb.draw(game.tm.exit, 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case 'q':
                            game.sb.draw(game.tm.gym, 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * 7 * Main.SIZECHANGE.x, 60 * 7 * Main.SIZECHANGE.y);
                            break;
                        case 'l':
                            game.sb.draw(game.tm.shelfLeft, 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case 'm':
                            game.sb.draw(game.tm.shelfFront, 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case 'n':
                            game.sb.draw(game.tm.shelfRight, 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case 'j':
                            game.sb.draw(game.tm.kitchenTable, 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case 'g':
                            game.sb.draw(game.tm.boardsFront, 60 * Main.SIZECHANGE.x * j, 60 * Main.SIZECHANGE.y * (sizeMap.y - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case 'h':
                            game.sb.draw(game.tm.boardsLeft, 60 * Main.SIZECHANGE.x * (j), 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case 'i':
                            game.sb.draw(game.tm.boardsRight, 60 * Main.SIZECHANGE.x * (j - 1), 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                        case '-':
                            game.sb.draw(game.tm.coin, 60 * Main.SIZECHANGE.x * (j), 60 * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), 60 * Main.SIZECHANGE.x, 60 * Main.SIZECHANGE.y);
                            break;
                    }
                }
            }
        }
        for (int i = 0; i < bats.size(); i++) {
            bats.get(i).draw(game.sb);
        }
        for (int i = 0; i < KillBlocks.size(); i++) {
            KillBlocks.get(i).draw(game.sb);

        }
        if (!player.isDie()) {
            player.draw(game.sb);
        }
        game.sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
        stopButton.setVisible(false);
        againButton.setVisible(false);
        continueButton.setVisible(false);
        exitButton.setVisible(false);
        nextButton.setVisible(false);
        menuBackground.setVisible(false);
        killBackground.setVisible(false);
        winBackground.setVisible(false);
        for (int i = 0; i < 10; i++) {
            levelsNumber[i].setVisible(false);
        }

        KStar.setVisible(false);
        FStar.setVisible(false);
        UStar.setVisible(false);

        isShowed = false;
        game.sm.GameMusic.stop();

        deltaAngle = new Vector3(360, 360, 360);
        deltaSize = new Vector3(0, 0, 0);
        deltaSizeIs = new Vector3(0, 0, 0);
        Music = new Vector3(0, 0, 0);
    }

    @Override
    public void dispose() {
        stage.dispose();
        player.dispose();
    }
}
