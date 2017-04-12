package com.sixthousandfeetdeep.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {

	final SixThousandFeetDeep game;

    OrthographicCamera camera;

    Texture startImg, logoImg;
    
    public static Music bgMusic;
    
    public MainMenuScreen(final SixThousandFeetDeep gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        startImg = new Texture(Gdx.files.internal("bg3.png"));
        logoImg = new Texture(Gdx.files.internal("logo.png"));
        
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("MainMenuSound.mp3"));
        bgMusic.setLooping(true);
        bgMusic.play();
        
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(startImg, 0, 0);
        game.batch.draw(logoImg, 270, 500);
        game.font.draw(game.batch, "Welcome to 6000ft. Deep !!! ", 1280 / 2 - 180, 720 / 2 - 50);
        game.font.draw(game.batch, "Tap anywhere to begin!", 1280 / 2 - 150, 720 / 2 - 100);
        game.batch.end();
        

        if (Gdx.input.isTouched()) {
            game.setScreen(new SubMenu(game));
            bgMusic.dispose();
            dispose();
        }
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
        
}
