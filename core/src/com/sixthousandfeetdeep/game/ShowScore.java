package com.sixthousandfeetdeep.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class ShowScore implements Screen{

	final SixThousandFeetDeep game;
	OrthographicCamera camera;
	
	Texture replayBtnImg, menuBtnImg, bg;
	Texture replayBtnImgActv, menuBtnImgActv, scorepadImg;
	
	Rectangle replayBtn, menuBtn;
	
	Sound buttonSound;
	Music boostMusic;
	
	public static int flags = 0;
	public static int score = 0;
	public static Texture bgScore = new Texture(Gdx.files.internal("SubMenuBG.jpg"));
	
	public ShowScore(final SixThousandFeetDeep gam) {
		this.game = gam;
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
		
		replayBtnImg = new Texture(Gdx.files.internal("replayImg.png"));
		replayBtnImgActv = new Texture(Gdx.files.internal("replayImg2.png"));
		menuBtnImg = new Texture(Gdx.files.internal("menuImg1.png"));
		menuBtnImgActv = new Texture(Gdx.files.internal("menuImg2.png"));
		scorepadImg = new Texture(Gdx.files.internal("scorepad.png"));
		bg = bgScore;
		
		
		
		buttonSound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
		boostMusic = Gdx.audio.newMusic(Gdx.files.internal("boost.mp3"));
		
		replayBtn = new Rectangle();
		replayBtn.x = 400;
		replayBtn.y = 200;
		replayBtn.width = 200;
		replayBtn.height = 80;
		
		menuBtn = new Rectangle();
		menuBtn.x = 700;
		menuBtn.y = 200;
		menuBtn.width = 200;
		menuBtn.height = 80;
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		if (flags == 1)
		{
			GameScreen.boostMusic.dispose();
		}
		if (flags == 2)
		{
			GameScreen2.boostMusic.dispose();
		}
		if (flags == 3)
		{
			GameScreen3.boostMusic.dispose();
		}

		camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        game.batch.draw(scorepadImg, 250, 120);
        game.font.draw(game.batch, "" + score, 640, 395);
        if (Gdx.input.getX() >= replayBtn.x && Gdx.input.getX() <= replayBtn.x + 200 && Gdx.input.getY() >= replayBtn.y + 240 && Gdx.input.getY() <= replayBtn.y + 320)
        {
        	game.batch.draw(replayBtnImgActv, replayBtn.x, replayBtn.y);
        	if (Gdx.input.isTouched())
        	{
        		buttonSound.play();
        		if (flags == 1)
        		{
        			game.setScreen(new GameScreen(game));
        		}
        		else if (flags == 2)
        		{
        			game.setScreen(new GameScreen2(game));
        		}
        		else if (flags == 3)
        		{
        			game.setScreen(new GameScreen3(game));
        		}
        	}
        }
        else
        {
        	game.batch.draw(replayBtnImg, replayBtn.x, replayBtn.y);
        }
        
        if (Gdx.input.getX() >= menuBtn.x && Gdx.input.getX() <= menuBtn.x + 200 && Gdx.input.getY() >= replayBtn.y + 240 && Gdx.input.getY() <= replayBtn.y + 320)
        {
        	game.batch.draw(menuBtnImgActv, menuBtn.x, menuBtn.y);
        	if (Gdx.input.isTouched())
        	{
        		buttonSound.play();
        		game.setScreen(new SubMenu(game));
        	}
        }
        else
        {
        	 game.batch.draw(menuBtnImg, menuBtn.x, menuBtn.y);
        }
        
        game.batch.end();
		
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
