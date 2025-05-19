package io.github.some_example_name.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;


import io.github.some_example_name.Main;
import io.github.some_example_name.Sprites.Button;
import io.github.some_example_name.Sprites.Player;

public class PlayState extends State {

    private OrthographicCamera camera;
    private Texture background;
    private Texture wall;
    private Texture exit;
    private Texture thorns;
    private Texture stopTexture;
    private Button stopButton;
    private int level;
    private boolean Time = true;
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
            "######   ### #####\n" +
            "############ #####\n" +
            "############ #####\n" +
            "############ #####\n" +
            "############ #####\n" +
            "####    #### #####\n" +
            "#### ## #### #####\n" +
            "#### ##      #####\n" +
            "#### #############\n" +
            "#         ###    #\n" +
            "#    ####        #\n" +
            "#    #########   #\n" +
            "#    ######      #\n" +
            "########### *    #\n" +
            "###########      #\n" +
            "##################\n",

            "##################\n" +
            "##################\n" +
            "##              ##\n" +
            "##         #### ##\n" +
            "##         #### ##\n" +
            "########## #### ##\n" +
            "########## #### ##\n" +
            "########## #### ##\n" +
            "########## #### ##\n" +
            "#            #  ##\n" +
            "# ######## # #  ##\n" +
            "#     #### # #  ##\n" +
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
            "## ### ### #    ##\n" +
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
            "#w     #    #   w#\n" +
            "##     #        w#\n" +
            "#w    ##     #  w#\n" +
            "#w     #        w#\n" +
            "#w              w#\n" +
            "#w        #     w#\n" +
            "#wwww         # w#\n" +
            "####w  #        w#\n" +
            "####w      #    w#\n" +
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

    private String levelMap[];
    private Vector2 sizeMap;
    private Player player;

    public PlayState(GameStateManager gsm, int level) {
        super(gsm);
        this.level = level;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        background = new Texture("background.png");
        wall = new Texture("Sprites/wall.png");
        exit = new Texture("Sprites/exit.png");
        thorns = new Texture("Sprites/thorns.png");
        stopTexture = new Texture("Buttons/stopBtn.png");
        stopButton = new Button((int) (Main.HEIGHT / Main.SIZECHANGE.x - (stopTexture.getWidth() / 2 + 60)), (int) (Main.HEIGHT / Main.SIZECHANGE.y - ((float) stopTexture.getHeight() / 2 + 60)), 120, 120, stopTexture);
        sizeMap = new Vector2(maps[level - 1].split("\\r?\\n")[0].length(), maps[level - 1].split("\\r?\\n").length);
        levelMap = maps[level - 1].split("\\r?\\n");

        for (int i = 0; i < sizeMap.y; i++) {
            for (int j = 0; j < sizeMap.x; j++) {
                if (levelMap[i].charAt(j) == '*') {
                    player = new Player(new Vector2(wall.getWidth() * j, wall.getHeight() * (sizeMap.y - 1 - i)), new Texture("Sprites/player.png"), new Vector2(60, 60), levelMap, new Vector2(20 * Main.SIZECHANGE.x, 20 * Main.SIZECHANGE.y));
                }
            }
        }
    }

    @Override
    public void handleInpute() {
        player.input(Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
        if (stopButton.onClick()) {
            Time = false;
        }
    }

    @Override
    public void update(float dt) {
        //camera.position.y+=Gdx.input.getDeltaY();
        //System.out.println(camera.position+" "+Gdx.input.getY());
        //stopButton.addPosition(new Vector2(0,-Gdx.input.getDeltaY()/Main.SIZECHANGE.y));
        handleInpute();

        if (Time) {
            player.move();

            if (player.exit()) {
                gsm.set(new MenuState(gsm));
            }

            if (player.die()) {

            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        System.out.println();
        sb.setProjectionMatrix(camera.combined);
        camera.update();
        sb.begin();


        sb.draw(background, 0, 0, background.getWidth() * Main.SIZECHANGE.x, background.getHeight() * Main.SIZECHANGE.y);

        for (int i = 0; i < sizeMap.y; i++) {
            for (int j = 0; j < sizeMap.x; j++) {
                if (levelMap[i].charAt(j) == '#') {
                    sb.draw(wall, wall.getWidth() * Main.SIZECHANGE.x * j, wall.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), wall.getWidth() * Main.SIZECHANGE.x, wall.getHeight() * Main.SIZECHANGE.y);
                }
                if (levelMap[i].charAt(j) == '0') {
                    sb.draw(exit, exit.getWidth() * Main.SIZECHANGE.x * j, exit.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), exit.getWidth() * Main.SIZECHANGE.x, exit.getHeight() * Main.SIZECHANGE.y);
                }
                if (levelMap[i].charAt(j) == 'w') {
                    sb.draw(thorns, thorns.getWidth() * Main.SIZECHANGE.x * j, thorns.getHeight() * Main.SIZECHANGE.y * (sizeMap.y - 1 - i), thorns.getWidth() * Main.SIZECHANGE.x, thorns.getHeight() * Main.SIZECHANGE.y);
                }
            }
        }

        if (!player.die()) {
            player.draw(sb);
        }

        stopButton.draw(sb);


        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
        thorns.dispose();
        exit.dispose();
        wall.dispose();
    }
}
