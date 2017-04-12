package com.sixthousandfeetdeep.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SubMenu implements Screen {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	final SixThousandFeetDeep game;

    OrthographicCamera camera;

    Texture button1Inactive, button2Inactive, button3Inactive, bg, logoImg;
    Texture button1Active, button2Active, button3Active;
    
    Rectangle button1, button2, button3;
    
    public static Music bgMusic1;
    Sound buttonSound;
    
    public static int flags2 = 0, flags3 = 0;
	
	public SubMenu(final SixThousandFeetDeep gam) {
		
		this.game = gam;

		camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        
        button1Inactive = new Texture(Gdx.files.internal("start-inactive.png"));
        button2Inactive = new Texture(Gdx.files.internal("shop-inactive.png"));
        button3Inactive = new Texture(Gdx.files.internal("exit-inactive.png"));
        
        button1Active = new Texture(Gdx.files.internal("start-active.png"));
        button2Active = new Texture(Gdx.files.internal("shop-active.png"));
        button3Active = new Texture(Gdx.files.internal("exit-active.png"));
        
        logoImg = new Texture(Gdx.files.internal("logo.png"));
        bg = new Texture(Gdx.files.internal("SubMenuBG.jpg"));
        
        bgMusic1 = Gdx.audio.newMusic(Gdx.files.internal("SubMenuSound.mp3"));
        bgMusic1.setLooping(true);
        bgMusic1.play();
        
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
        
        button1 = new  Rectangle();
        button1.x = WIDTH / 2 - 125;
        button1.y = HEIGHT / 2;
        button1.width =  250;
        button1.height = 77;
        
        button2 = new  Rectangle();
        button2.x = WIDTH / 2 - 125;
        button2.y = HEIGHT / 2 - 125;
        button2.width = 250;
        button2.height = 77;
        
        button3 = new  Rectangle();
        button3.x = WIDTH / 2 - 125;
        button3.y = HEIGHT / 2 - 250;
        button3.width = 250;
        button3.height = 77;

	}
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        game.batch.draw(logoImg, 270, 500);
        
        if (Gdx.input.getX() >= button1.x && Gdx.input.getX() <= button1.x + button1.width && Gdx.input.getY() <= button1.y && Gdx.input.getY() >= button1.y - button1.height)
        {
        	game.batch.draw(button1Active, button1.x, button1.y);
        	if (Gdx.input.isTouched()) {
        		buttonSound.play();
                game.setScreen(new SelectLevelMenu(game));
                dispose();
            }
        }
        else
        {
        	game.batch.draw(button1Inactive, button1.x, button1.y);
        }
        if (Gdx.input.getX() >= button2.x && Gdx.input.getX() <= button2.x + button2.width && Gdx.input.getY() <= button2.y + 100 + 77 + button2.height && Gdx.input.getY() >= button2.y + 100 + 77)
        {
        	game.batch.draw(button2Active, button2.x, button2.y);
        	if (Gdx.input.isTouched()) {
        		buttonSound.play();
        		ShopMenu.subm2 = flags2;
        		ShopMenu.subm3 = flags3;
                game.setScreen(new ShopMenu(game));
                dispose();
        	}
        }
        else
        {
        	game.batch.draw(button2Inactive, button2.x, button2.y);
        }
        if (Gdx.input.getX() >= button3.x && Gdx.input.getX() <= button3.x + button3.width && Gdx.input.getY() <= button3.y + 350 + 77 + button3.height && Gdx.input.getY() >= button3.y + 350 + 77)
        {
        	game.batch.draw(button3Active, button3.x, button3.y);
//        	if (Gdx.input.isTouched())
        	if (Gdx.input.isTouched()){
        		buttonSound.play();
        		Gdx.app.exit();
        	}
        }
        else
        {
        	game.batch.draw(button3Inactive, button3.x, button3.y);
        }
        game.batch.end();
	}

	@Override
    public void resize(int width, int height) {
    
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    	
        button1Active.dispose();
        button2Active.dispose();
        button3Active.dispose();
        button1Inactive.dispose();
        button2Inactive.dispose();
        button3Inactive.dispose();
        bg.dispose();
        
    }

}

