package com.Turb1na_.KFUMaze.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.Turb1na_.KFUMaze.Main;

public class Player {
    private Vector2 position;
    private boolean die = false;
    private Vector2 speed=new Vector2(0,0);
    private Vector2 target = new Vector2(-1, -1);
    private Vector2 acceleration;
    private float SoundVolume;
    private Vector2 size;
    private String map[];
    private Vector2 posInMap;
    private float dt;
    private char[] s;
    private boolean[] stars = {false, false, false};
    private Sound Star= Gdx.audio.newSound(Gdx.files.internal("Audio/Star.mp3"));
    private Texture playerUp=new Texture("Player/playerUp.png");
    private Texture playerLeft=new Texture("Player/playerLeft.png");
    private Texture playerRight=new Texture("Player/playerRight.png");
    private Texture playerStop=new Texture("Player/playerStop.png");

    public Player(Vector2 position,  Vector2 size, String map[], Vector2 acceleration,float dt,float SoundVolume) {
        this.map = map;
        this.SoundVolume=SoundVolume;
        this.position = position;
        this.size = size;
        this.acceleration = acceleration;
        this.dt=dt/0.2f;

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
            sb.draw(playerStop, position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
        if(position.x <= target.x && target.x >= 0)
            sb.draw(playerRight, position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
        if(position.x >= target.x && target.x >= 0)
            sb.draw(playerLeft, position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
        if(target.y>0)
            sb.draw(playerUp, position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
    }

    public void dispose() {
        playerUp.dispose();
        playerRight.dispose();
        playerLeft.dispose();
        playerStop.dispose();
    }

    public void input(int deltaX, int deltaY) {
        if (Math.abs(deltaX) >= 30) {
            if (deltaX < 0 && target.x == -1 && target.y == -1) {
                for (int i = (int) posInMap.x; i >= 0; i--) {
                    if (map[(int) posInMap.y].charAt(i) == '#') {
                        posInMap.x = i + 1;
                        target.x = (int) ((i + 1) * size.x);
                        break;
                    }
                }
            } else if (deltaX > 0 && target.x == -1 && target.y == -1) {
                for (int i = (int) posInMap.x; i < 18; i++) {
                    if (map[(int) posInMap.y].charAt(i) == '#') {
                        posInMap.x = i - 1;
                        target.x = (int) ((i - 1) * size.x);
                        break ;
                    }
                }
            }
        } else if (Math.abs(deltaY) >= 30) {
            if (deltaY < 0 && target.x == -1 && target.y == -1) {
                for (int i = (int) posInMap.y; i >= 0; i--) {
                    if (map[i].charAt((int) posInMap.x) == '#') {
                        posInMap.y = i + 1;
                        target.y = Main.HEIGHT / Main.SIZECHANGE.y - (int) ((i + 2) * size.y);
                        break;
                    }
                }

            } else if (deltaY > 0 && target.x == -1 && target.y == -1) {
                for (int i = (int) posInMap.y; i < 32; i++) {
                    if (map[i].charAt((int) posInMap.x) == '#') {
                        posInMap.y = i - 1;
                        target.y = Main.HEIGHT / Main.SIZECHANGE.y - (int) ((i) * size.y);
                        break;
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
                stars[0] = true;
                s = map[31 - Math.round(position.y / 60f)].toCharArray();
                s[Math.round(position.x / 60f)] = ' ';
                map[31 - Math.round(position.y / 60f)] = new String(s);
                Star.play(SoundVolume);
                break;
            case 'f':
                stars[1] = true;
                s = map[31 - Math.round(position.y / 60f)].toCharArray();
                s[Math.round(position.x / 60f)] = ' ';
                map[31 - Math.round(position.y / 60f)] = new String(s);
                Star.play(SoundVolume);
                break;
            case 'u':
                stars[2] = true;
                s = map[31 - Math.round(position.y / 60f)].toCharArray();
                s[Math.round(position.x / 60f)] = ' ';
                map[31 - Math.round(position.y / 60f)] = new String(s);
                Star.play(SoundVolume);
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
}
