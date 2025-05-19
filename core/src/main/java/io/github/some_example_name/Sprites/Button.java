package io.github.some_example_name.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.Text;

import io.github.some_example_name.Main;


public class Button {
    private Vector2 position;
    private int width;
    private int height;
    private Texture texture;

    public Button(int x, int y,int width,int height, Texture texture){
        position=new Vector2(x,y);
        this.width=width;
        this.height=height;
        this.texture=texture;
    }
    private boolean inButton(int x,int y){
        //System.out.println(x+" "+y+" "+(0<y-position.y)+" "+( y-position.y<height*Main.SIZECHANGE.y));
        return (0<x-position.x*Main.SIZECHANGE.x&& x-position.x*Main.SIZECHANGE.x<width*Main.SIZECHANGE.x)&&(0<y-position.y*Main.SIZECHANGE.y && y-position.y*Main.SIZECHANGE.y<height*Main.SIZECHANGE.y);
    }
    public boolean onClick(){
        //System.out.println(Gdx.input.getX());
        return Gdx.input.justTouched() &&inButton(Gdx.input.getX(),Math.abs(Gdx.input.getY()-Gdx.graphics.getHeight()));
    }
    public void addPosition(Vector2 force){
        position.x+=force.x;
        position.y+=force.y;
    }

    public void setPosition(Vector2 pos){
        position=pos;
    }
    public void draw(SpriteBatch sb){
        sb.draw(texture,position.x*Main.SIZECHANGE.x,position.y* Main.SIZECHANGE.y,width*Main.SIZECHANGE.x,height*Main.SIZECHANGE.y);
    }
    public void dispose(){
        texture.dispose();
    }
}
