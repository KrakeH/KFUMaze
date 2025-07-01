package com.Turb1na_.KFUMaze.Sprites;

import com.Turb1na_.KFUMaze.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.sql.Time;


public class KillBlock {
    final Main game;
    private Vector2 position;
    private String map[];
    private Vector2 posInMap;
    private char Direction;
    private Vector2 size;
    private boolean isRunning = false;
    private boolean canKill = false;
    private float Timer;



    public KillBlock(final Main game,Vector2 position, Vector2 size, String map[], char Direction) {
        this.game=game;
        this.map = map;
        this.position = position;
        this.size = size;
        this.Direction=Direction;
        posInMap = new Vector2(Math.round(position.x / 60f), 31 - Math.round(position.y / 60f));
    }

    public void draw(SpriteBatch sb) {
        switch (Direction) {
            case '1':
                if(Timer<0.5f)
                    sb.draw(game.tm.killBlocks[0][0], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else if(Timer>=0.5f &&Timer<1.4f)
                    sb.draw(game.tm.killBlocks[0][1], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else
                    sb.draw(game.tm.killBlocks[0][2], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                break;
            case '2':
                if(Timer<0.5f)
                    sb.draw(game.tm.killBlocks[1][0], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else if(Timer>=0.5f &&Timer<1.4f)
                    sb.draw(game.tm.killBlocks[1][1], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else
                    sb.draw(game.tm.killBlocks[1][2], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                break;
            case '3':
                if(Timer<0.7f)
                    sb.draw(game.tm.killBlocks[2][0], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else if(Timer>=0.5f &&Timer<1.4f)
                    sb.draw(game.tm.killBlocks[2][1], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else
                    sb.draw(game.tm.killBlocks[2][2], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                break;
            case '4':
                if(Timer<0.5f)
                    sb.draw(game.tm.killBlocks[3][0], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else if(Timer>=0.5f &&Timer<1.4f)
                    sb.draw(game.tm.killBlocks[3][1], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else
                    sb.draw(game.tm.killBlocks[3][2], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                break;
        }
    }

    public void run() {
        isRunning = true;
    }

    public void update(float dt) {
        if (isRunning) {
            Timer += dt;
            canKill = Timer >= 0.5f;
            if (Timer >= 2f) {
                Timer = 0;
                canKill = false;
                isRunning = false;
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isCanKill() {
        return canKill;
    }

    public void dispose() {
    }

    public Vector2 getPosition() {
        return position;
    }
}
