package io.github.some_example_name.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import io.github.some_example_name.Main;

public class Player {
    private Vector2 position;
    private Texture texture;
    private boolean die=false;
    private Vector2 speed;
    private Vector2 target=new Vector2(-1,-1);
    private Vector2 size;
    private String map[];
    private Vector2 posInMap;

    public Player(Vector2 position, Texture texture, Vector2 size, String map[],Vector2 speed) {
        this.map = map;
        this.position = position;
        this.texture = texture;
        this.size = size;
        this.speed=speed;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length(); j++) {
                if (map[i].charAt(j) == '*') {
                    posInMap = new Vector2(j, i);
                }
            }
        }
    }

    public void draw(SpriteBatch sb) {
        sb.draw(texture, position.x * Main.SIZECHANGE.x, position.y * Main.SIZECHANGE.y, size.x * Main.SIZECHANGE.x, size.y * Main.SIZECHANGE.y);
    }

    public void dispose() {
        texture.dispose();
    }

    public void input(int deltaX, int deltaY) {
        if (Math.abs(deltaX) >= 30) {
            if (deltaX < 0 && target.x==-1&&target.y==-1) {
                for (int i = (int) posInMap.x; i>=0; i--) {
                    if(map[(int) posInMap.y].charAt(i)=='#'){
                        posInMap.x=i+1;
                        target.x= (int) ((i+1)*texture.getWidth());
                        break;
                    }
                }
            }
            else if(deltaX>0&&target.x==-1&&target.y==-1){
                for (int i = (int) posInMap.x; i<18; i++) {
                    if (map[(int) posInMap.y].charAt(i) == '#') {
                        posInMap.x=i-1;
                        target.x = (int) ((i-1) * texture.getWidth() );
                        break;
                    }
                }
            }
        } else if (Math.abs(deltaY)>=30) {
            if (deltaY < 0 && target.x==-1&&target.y==-1) {
                for (int i = (int) posInMap.y; i>=0; i--) {
                    if(map[i].charAt((int) posInMap.x)=='#'){
                        posInMap.y=i+1;
                        target.y= Main.HEIGHT/Main.SIZECHANGE.y-(int) ((i+2)*texture.getHeight());
                        break;
                    }
                }

            }
            else if(deltaY>0&&target.x==-1&&target.y==-1){
                for (int i = (int) posInMap.y; i<32; i++) {
                    if (map[i].charAt((int) posInMap.x)=='#') {
                        posInMap.y=i-1;
                        target.y= Main.HEIGHT/Main.SIZECHANGE.y-(int) ((i)*texture.getHeight());
                        break;
                    }
                }
            }
        }
    }
    public void move(){
        if(position.x<=target.x && target.x>=0){
            position.x+=speed.x;
            if(position.x>=target.x){
                position.x= target.x;
                target.x=-1;
            }
        }else if(position.x>=target.x && target.x>=0) {
            position.x -= speed.x;
            if (position.x <= target.x) {
                position.x = target.x;
                target.x = -1;
            }
        }
        else if(position.y<=target.y && target.y>=0){
            position.y+=speed.y;
            if(position.y>=target.y){
                position.y= target.y;
                target.y=-1;
            }
        }else if(position.y>=target.y && target.y>=0) {
            position.y -= speed.y;
            if (position.y <= target.y) {
                position.y = target.y;
                target.y = -1;
            }
        }
    }

    public boolean exit(){
        return (map[(int) posInMap.y].charAt((int) posInMap.x)=='0' && target.y==-1&& target.x==-1);
    }
    public boolean isDie(){
        die= (map[(int) posInMap.y].charAt((int) posInMap.x)=='w' && target.y==-1&& target.x==-1)||die;
        return die;
    }
}
