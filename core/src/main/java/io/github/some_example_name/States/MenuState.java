package io.github.some_example_name.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


import org.w3c.dom.Text;

import java.io.FileNotFoundException;

import io.github.some_example_name.Main;
import io.github.some_example_name.Sprites.Button;

public class MenuState extends State{
    private int rightIndent =210;
    private int leftIndent =210;
    private int bottomIndent=120;

    private Texture background;
    private static int countLevel=20;
    private Array<Button> buttonLvl=new Array<>();
    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        background = new Texture("menuBackground.png");
        //create levels
        //-----------------------------------------
        for (int i = 0; i < countLevel; i++) {
            switch (i%4) {
                case 1:
                    try {
                        buttonLvl.add(new Button(1080-leftIndent-240, bottomIndent+720*(i/4), 240, 240, new Texture("levels/"+(i +1)+ ".png")));
                    }
                    catch (Exception e){
                        buttonLvl.add(new Button(1080-leftIndent-240, bottomIndent+720*(i/4), 240, 240, new Texture("levels/lock.png")));
                    }
                    break;
                case 0:
                    try{
                        buttonLvl.add(new Button(rightIndent, bottomIndent+720*(i/4), 240, 240, new Texture("levels/"+(i +1)+ ".png")));
                    }
                    catch (Exception e){
                        buttonLvl.add(new Button(rightIndent, bottomIndent+720*(i/4), 240, 240, new Texture("levels/lock.png")));
                    }
                    break;
                case 2:
                    try{
                        buttonLvl.add(new Button(1080-leftIndent-240, bottomIndent+360+720*(i/4), 240, 240, new Texture("levels/"+(i +1)+ ".png")));
                    }
                    catch (Exception e){
                        buttonLvl.add(new Button(1080-leftIndent-240, bottomIndent+360+720*(i/4), 240, 240, new Texture("levels/lock.png")));
                    }
                    break;
                case 3:
                    try{
                        buttonLvl.add(new Button(rightIndent, bottomIndent+360+720*(i/4), 240, 240, new Texture("levels/"+(i +1)+ ".png")));
                    }
                    catch (Exception e){
                        buttonLvl.add(new Button(rightIndent, bottomIndent+360+720*(i/4), 240, 240, new Texture("levels/lock.png")));
                    }
                    break;
            }
        }
        //-----------------------------------------
    }

    @Override
    public void handleInpute() {
        for (int i = 0; i < countLevel; i++) {
            if(buttonLvl.get(i).onClick()){
                try {
                    gsm.set(new PlayState(gsm,i+1));
                }catch (Exception e){

                }
            }
        }

        if(camera.position.y+Gdx.input.getDeltaY()>=Main.HEIGHT/2 && camera.position.y+Gdx.input.getDeltaY()<=background.getHeight()*Main.SIZECHANGE.y*countLevel/4-Main.HEIGHT/2) {
            camera.translate(0,Gdx.input.getDeltaY());
        }
    }

    @Override
    public void update(float dt) {
        handleInpute();

        //System.out.println(camera.position.y);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        camera.update();
        sb.begin();

        for (int i = -1; i < countLevel/4+1; i++) {
            sb.draw(background, 0, 60*Main.SIZECHANGE.y+i*background.getHeight()*Main.SIZECHANGE.y,background.getWidth()*Main.SIZECHANGE.x,background.getHeight()*Main.SIZECHANGE.y);
        }

        for (int i = 0; i < countLevel; i++) {
            buttonLvl.get(i).draw(sb);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();

        for (int i = 0; i < countLevel; i++) {
            buttonLvl.get(i).dispose();
        }
    }
}
