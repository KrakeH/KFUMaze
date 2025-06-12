package com.Turb1na_.KFUMaze.Sprites;

import com.Turb1na_.KFUMaze.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.sql.Time;


public class KillBlock {
    private Vector2 position;
    private String map[];
    private Vector2 posInMap;
    private char Direction;
    private Vector2 size;
    private boolean isRunning = false;
    private boolean canKill = false;
    private float Timer;
    private Texture[][] textures={
        {new Texture("Sprites/killBlock/killBlock11.png"),new Texture("Sprites/killBlock/killBlock12.png"),new Texture("Sprites/throns/thron5.png")},
        {new Texture("Sprites/killBlock/killBlock21.png"),new Texture("Sprites/killBlock/killBlock22.png"),new Texture("Sprites/throns/thron6.png")},
        {new Texture("Sprites/killBlock/killBlock31.png"),new Texture("Sprites/killBlock/killBlock32.png"),new Texture("Sprites/throns/thron7.png")},
        {new Texture("Sprites/killBlock/killBlock41.png"),new Texture("Sprites/killBlock/killBlock42.png"),new Texture("Sprites/throns/thron8.png")}};


    public KillBlock(Vector2 position, Vector2 size, String map[], char Direction) {
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
                    sb.draw(textures[0][0], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else if(Timer>=0.5f &&Timer<1.4f)
                    sb.draw(textures[0][1], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else
                    sb.draw(textures[0][2], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                break;
            case '2':
                if(Timer<0.5f)
                    sb.draw(textures[1][0], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else if(Timer>=0.5f &&Timer<1.4f)
                    sb.draw(textures[1][1], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else
                    sb.draw(textures[1][2], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                break;
            case '3':
                if(Timer<0.7f)
                    sb.draw(textures[2][0], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else if(Timer>=0.5f &&Timer<1.4f)
                    sb.draw(textures[2][1], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else
                    sb.draw(textures[2][2], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                break;
            case '4':
                if(Timer<0.5f)
                    sb.draw(textures[3][0], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else if(Timer>=0.5f &&Timer<1.4f)
                    sb.draw(textures[3][1], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
                else
                    sb.draw(textures[3][2], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
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
        for (int i = 0; i < textures.length; i++) {
            for (int j = 0; j < textures[0].length; j++) {
                textures[i][j].dispose();
            }
        }
    }

    public Vector2 getPosition() {
        return position;
    }
}
