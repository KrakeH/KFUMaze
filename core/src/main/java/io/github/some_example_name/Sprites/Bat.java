package io.github.some_example_name.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import io.github.some_example_name.Main;

public class Bat {
    private Vector2 target = new Vector2(-1, -1);
    private Vector2 position;
    private Vector2 go=new Vector2(1,1);
    private String map[];
    private Vector2 posInMap;
    private boolean isY = false;
    private Texture batTexture = new Texture("Sprites/Bat.png");
    private Vector2 size;
    private Vector2 speed = new Vector2(0, 0);

    public Bat(Vector2 position, Vector2 size, String map[], Vector2 speed,boolean isY) {
        this.map = map;
        this.position = position;
        this.size = size;
        this.speed = speed;
        this.isY=isY;

        posInMap=new Vector2(Math.round(position.x / 60f),31 - Math.round(position.y / 60f));
        System.out.println(posInMap);
    }

    public void move() {
        if (isY && target.y == -1&&go.y==1) {
            firstCycle:
            for (int i = (int) posInMap.y; i >= 0; i--) {
                switch (map[i].charAt((int) posInMap.x)) {
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case 'z':
                    case 'x':
                    case 'c':
                    case 'v':
                    case 'b':
                    case 'n':
                    case 'm':
                        posInMap.y = i + 1;
                        target.y = Main.HEIGHT / Main.SIZECHANGE.y - (int) ((i + 2) * size.y);
                        go.y = 0;
                        break firstCycle;
                }
            }
        }
        if (isY && target.y == -1&&go.y==0) {
            secondCycle:
            for (int i = (int) posInMap.y; i < 32; i++) {
                switch (map[i].charAt((int) posInMap.x)) {
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case 'z':
                    case 'x':
                    case 'c':
                    case 'v':
                    case 'b':
                    case 'n':
                    case 'm':
                        posInMap.y = i - 1;
                        target.y = Main.HEIGHT / Main.SIZECHANGE.y - (int) ((i) * size.y);
                        go.y=1;
                        break secondCycle;
                }
            }
        }
        if (!isY && target.x == -1&&go.x==0){
            thirdCycle:
            for (int i = (int) posInMap.x; i >= 0; i--) {
                switch (map[(int) posInMap.y].charAt(i)) {
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case 'z':
                    case 'x':
                    case 'c':
                    case 'v':
                    case 'b':
                    case 'n':
                    case 'm':
                        posInMap.x = i + 1;
                        target.x = (int) ((i + 1) *size.x);
                        go.x=1;
                        break thirdCycle;
                }
            }
        }
        if (!isY && target.x == -1&&go.x==1) {
            secondCycle:
            for (int i = (int) posInMap.x; i < 18; i++) {
                switch (map[(int) posInMap.y].charAt(i)) {
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case 'z':
                    case 'x':
                    case 'c':
                    case 'v':
                    case 'b':
                    case 'n':
                    case 'm':
                        posInMap.x = i - 1;
                        target.x = (int) ((i - 1) *size.x);
                        go.x=0;
                        break secondCycle;
                }
            }
        }

        if (position.x <= target.x && target.x >= 0) {
            position.x += speed.x;
            if (position.x >= target.x) {
                position.x = target.x;
                target.x = -1;
            }
        } else if (position.x >= target.x && target.x >= 0) {
            position.x -= speed.x;
            if (position.x <= target.x) {
                position.x = target.x;
                target.x = -1;
            }
        } else if (position.y <= target.y && target.y >= 0) {
            position.y += speed.y;
            if (position.y >= target.y) {
                position.y = target.y;
                target.y = -1;
            }
        } else if (position.y >= target.y && target.y >= 0) {
            position.y -= speed.y;
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
