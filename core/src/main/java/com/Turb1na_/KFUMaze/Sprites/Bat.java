package com.Turb1na_.KFUMaze.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.Turb1na_.KFUMaze.Main;

public class Bat {
    private Vector2 target = new Vector2(-1, -1);
    private Vector2 position;
    private Vector2 go=new Vector2(1,1);
    private String map[];
    private Vector2 posInMap;
    private boolean isY = false;
    private Texture batTexture = new Texture("Sprites/Bat.png");
    private Vector2 size;
    private Vector2 speed ;

    public Bat(Vector2 position, Vector2 size, String map[], Vector2 speed,boolean isY) {
        this.map = map;
        this.position = position;
        this.size = size;
        this.speed = speed;
        this.isY=isY;

        posInMap=new Vector2(Math.round(position.x / 60f),31 - Math.round(position.y / 60f));
    }

    public void move(float dt) {
        if (isY && target.y == -1&&go.y==1) {
            firstCicle:
            for (int i = (int) posInMap.y; i >= 0; i--) {
                switch (map[i].charAt((int) posInMap.x)){
                    case '#':
                    case 'e':
                    case 'h':
                        posInMap.y = i + 1;
                        target.y = Main.HEIGHT / Main.SIZECHANGE.y - (int) ((i + 2) * size.y);
                        go.y = 0;
                        break firstCicle;
                }
            }
        }
        if (isY && target.y == -1&&go.y==0) {
            secondCicle:
            for (int i = (int) posInMap.y; i < 32; i++) {
                switch (map[i].charAt((int) posInMap.x)){
                    case '#':
                    case 'e':
                    case 'h':
                        posInMap.y = i - 1;
                        target.y = Main.HEIGHT / Main.SIZECHANGE.y - (int) ((i) * size.y);
                        go.y = 1;
                        break secondCicle;
                }
            }
        }
        if (!isY && target.x == -1&&go.x==0){
            thirdCicle:
            for (int i = (int) posInMap.x; i >= 0; i--) {
                switch (map[(int) posInMap.y].charAt(i)){
                    case '#':
                    case 'e':
                    case 'h':
                        posInMap.x = i + 1;
                        target.x = (int) ((i + 1) * size.x);
                        go.x = 1;
                        break thirdCicle;
                }
            }
        }
        if (!isY && target.x == -1&&go.x==1) {
            fourthCicle:
            for (int i = (int) posInMap.x; i < 18; i++) {
                switch (map[(int) posInMap.y].charAt(i)){
                    case '#':
                    case 'e':
                    case 'h':
                        posInMap.x = i - 1;
                        target.x = (int) ((i - 1) * size.x);
                        go.x = 0;
                        break fourthCicle;
                }
            }
        }

        if (position.x <= target.x && target.x >= 0) {
            position.x += speed.x*dt;
            if (position.x >= target.x) {
                position.x = target.x;
                target.x = -1;
            }
        } else if (position.x >= target.x && target.x >= 0) {
            position.x -= speed.x*dt;
            if (position.x <= target.x) {
                position.x = target.x;
                target.x = -1;
            }
        } else if (position.y <= target.y && target.y >= 0) {
            position.y += speed.y*dt;
            if (position.y >= target.y) {
                position.y = target.y;
                target.y = -1;
            }
        } else if (position.y >= target.y && target.y >= 0) {
            position.y -= speed.y*dt;
            if (position.y <= target.y) {
                position.y = target.y;
                target.y = -1;
            }
        }
    }

    public void draw(SpriteBatch sb) {
        sb.draw(batTexture, position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
    }

    public void dispose() {
        batTexture.dispose();
    }
    public Vector2 getPosition(){
        return position;
    }
}
