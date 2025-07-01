package com.Turb1na_.KFUMaze.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.Turb1na_.KFUMaze.Main;

public class Player {
    final Main game;
    private int coins=0;
    private Vector2 position;
    private boolean die = false;
    private Vector2 speed=new Vector2(0,0);
    private Vector2 target = new Vector2(-1, -1);
    private Vector2 acceleration;
    private Vector2 size;
    private String map[];
    private Vector2 posInMap;
    private char[] s;
    private boolean[] stars = {false, false, false};


    public Player(final Main game,Vector2 position,  Vector2 size, String map[], Vector2 acceleration) {
        this.game=game;
        this.map = map;
        this.position = position;
        this.size = size;
        this.acceleration = acceleration;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length(); j++) {
                if (map[i].charAt(j) == '*') {
                    posInMap = new Vector2(j, i);
                }
            }
        }
    }

    public void draw(SpriteBatch sb) {
        if(target.x==-1 && target.y==-1)
            sb.draw(game.tm.skinsModel[game.Skin][3], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
        if(position.x <= target.x && target.x >= 0)
            sb.draw(game.tm.skinsModel[game.Skin][2], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
        if(position.x >= target.x && target.x >= 0)
            sb.draw(game.tm.skinsModel[game.Skin][1], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
        if(target.y>0)
            sb.draw(game.tm.skinsModel[game.Skin][0], position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
    }

    public void dispose() {

    }

    public void input(int deltaX, int deltaY) {
        if (Math.abs(deltaX*1f/Main.WIDTH) >= 0.028f) {
            if (deltaX < 0 && target.x == -1 && target.y == -1) {
                firstCicle:
                for (int i = (int) posInMap.x; i >= 0; i--) {
                    switch (map[(int) posInMap.y].charAt(i)){
                        case '#':
                        case 'e':
                        case 'y':
                            posInMap.x = i + 1;
                            target.x = (int) ((i + 1) * size.x);
                            break firstCicle;
                    }
                }
            } else if (deltaX > 0 && target.x == -1 && target.y == -1) {
                secondCicle:
                for (int i = (int) posInMap.x; i < 18; i++) {
                    switch (map[(int) posInMap.y].charAt(i)){
                        case'#':
                        case 'e':
                        case 'y':
                            posInMap.x = i - 1;
                            target.x = (int) ((i - 1) * size.x);
                            break secondCicle;
                    }
                }
            }
        } else if (Math.abs(deltaY*1f/Main.HEIGHT) >= 0.0157f) {
            if (deltaY < 0 && target.x == -1 && target.y == -1) {
                thirdCicle:
                for (int i = (int) posInMap.y; i >= 0; i--) {
                    switch (map[i].charAt((int) posInMap.x)){
                        case '#':
                        case 'e':
                        case 'y':
                            posInMap.y = i + 1;
                            target.y = Main.HEIGHT / Main.SIZECHANGE.y - (int) ((i + 2) * size.y);
                            break thirdCicle;
                    }
                }

            } else if (deltaY > 0 && target.x == -1 && target.y == -1) {
                fourthCicle:
                for (int i = (int) posInMap.y; i < 32; i++) {
                    switch (map[i].charAt((int) posInMap.x)){
                        case'#':
                        case 'e':
                        case 'y':
                            posInMap.y = i - 1;
                            target.y = Main.HEIGHT / Main.SIZECHANGE.y - (int) ((i) * size.y);
                            break fourthCicle;
                    }
                }
            }
        }
    }

    public void move(float dt) {
        if (position.x <= target.x && target.x >= 0) {
            if(speed.x<=50)
                speed.x+=acceleration.x*dt;
            position.x += speed.x;
            if (position.x >= target.x) {
                position.x = target.x;
                target.x = -1;
                speed=new Vector2(0,0);
            }
        } else if (position.x >= target.x && target.x >= 0) {
            if(Math.abs(speed.x)<=50)
                speed.x-=acceleration.x*dt;
            position.x += speed.x;
            if (position.x <= target.x) {
                position.x = target.x;
                target.x = -1;
                speed=new Vector2(0,0);
            }
        } else if (position.y <= target.y && target.y >= 0) {
            if(speed.y<=50)
                speed.y+=acceleration.y*dt;
            position.y += speed.y;
            if (position.y >= target.y) {
                position.y = target.y;
                target.y = -1;
                speed=new Vector2(0,0);
            }
        } else if (position.y >= target.y && target.y >= 0) {
            if(Math.abs(speed.y)<=50)
                speed.y-=acceleration.y*dt;
            position.y += speed.y;
            if (position.y <= target.y) {
                position.y = target.y;
                target.y = -1;
                speed=new Vector2(0,0);
            }
        }
        switch (map[31 - Math.round(position.y / 60f)].charAt(Math.round(position.x / 60f))) {
            case 'k':
                game.sm.Star.stop();
                stars[0] = true;
                s = map[31 - Math.round(position.y / 60f)].toCharArray();
                s[Math.round(position.x / 60f)] = ' ';
                map[31 - Math.round(position.y / 60f)] = new String(s);
                game.sm.Star.play(game.sm.SoundVolume);
                break;
            case 'f':
                game.sm.Star.stop();
                stars[1] = true;
                s = map[31 - Math.round(position.y / 60f)].toCharArray();
                s[Math.round(position.x / 60f)] = ' ';
                map[31 - Math.round(position.y / 60f)] = new String(s);
                game.sm.Star.play(game.sm.SoundVolume);
                break;
            case 'u':
                game.sm.Star.stop();
                stars[2] = true;
                s = map[31 - Math.round(position.y / 60f)].toCharArray();
                s[Math.round(position.x / 60f)] = ' ';
                map[31 - Math.round(position.y / 60f)] = new String(s);
                game.sm.Star.play(game.sm.SoundVolume);
                break;
            case '-':
                game.sm.Star.stop();
                coins++;
                s = map[31 - Math.round(position.y / 60f)].toCharArray();
                s[Math.round(position.x / 60f)] = ' ';
                map[31 - Math.round(position.y / 60f)] = new String(s);
                game.sm.Star.play(game.sm.SoundVolume);
                break;
        }
    }

    public boolean exit() {
        return (map[(int) posInMap.y].charAt((int) posInMap.x) == '0' && target.y == -1 && target.x == -1);
    }

    public boolean isDie() {
        return(map[31 - Math.round(position.y / 60f)].charAt(Math.round(position.x / 60f))=='w');
    }

    public Vector2 getPosition() {
        return new Vector2(position.x + size.x / (2 / 0.75f), position.y + size.y / (2 / 0.75f));
    }
    public Vector2 getTruthPosition(){
        return position;
    }

    public boolean[] getStars() {
        return stars;
    }
    public void setAcceleration(Vector2 acceleration){
        this.acceleration=acceleration;
    }
    public int getCoins(){
        return coins;
    }
}
