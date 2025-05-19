package io.github.some_example_name.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


import org.w3c.dom.Text;

import java.io.FileNotFoundException;

import io.github.some_example_name.Main;
import io.github.some_example_name.Sprites.Button;

public class MenuState extends State{

    private Stage stage;
    private Texture buttonTexture;
    private ImageButton imageButton;
    private int rightIndent =210;
    private int leftIndent =210;
    private int bottomIndent=120;

    private Texture background;
    private static int countLevel=20;
    private Array<Button> buttonLvl=new Array<>();
    public MenuState(GameStateManager gsm) {
        super(gsm);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Загружаем текстуру для кнопки
        buttonTexture = new Texture(Gdx.files.internal("libgdx.png")); // Положите ваш файл изображения в assets

        // Создаем стиль для кнопки
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        // Можно добавить другие состояния (нажатая, подсвеченная и т.д.)
        // style.imageDown = new TextureRegionDrawable(...);

        // Создаем кнопку с нашим стилем
        imageButton = new ImageButton(style);
        // Настраиваем размер и позицию кнопки
        imageButton.setSize(200, 100); // Размер в пикселях
        imageButton.setPosition(
            Gdx.graphics.getWidth()/2 - 100,  // Центрируем по горизонтали
            Gdx.graphics.getHeight()/2 - 50   // Центрируем по вертикали
        );

        // Добавляем обработчик клика
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("ImageButton", "Кнопка нажата!");
                // Здесь можно добавить любую логику при нажатии
            }
        });

        // Добавляем кнопку на сцену
        stage.addActor(imageButton);



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
        stage.act(Gdx.graphics.getDeltaTime()); // Обновляем логику
        stage.draw();
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
