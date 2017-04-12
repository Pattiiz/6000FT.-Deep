package com.sixthousandfeetdeep.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class ShopMenu implements Screen {
	
	final SixThousandFeetDeep game;
	
	OrthographicCamera camera;
	
	Texture submImg1Active, submImg2Active, submImg3Active, coinImg, bg;
	Texture submImg2Buy, submImg2Lock;
	Texture submImg3Buy, submImg3Lock;
	Texture submImg1Inactive, submImg2Inactive, submImg3Inactive;
	Texture playBtnImg1, playBtnImg2, backBtnImg1, backBtnImg2;
	
	Rectangle button1, button2, button3, playBtn, backBtn;
	
	Sound buttonSound;
	
	public static int coin = 0;
	public static int subm2 = 0, subm3 = 0;
	int priceSubm2 = 100, priceSubm3 = 200;
	
	public ShopMenu(final SixThousandFeetDeep gam) {
		
		this.game = gam;
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
		
        submImg1Active = new Texture(Gdx.files.internal("subm-button1.png"));
        submImg2Active = new Texture(Gdx.files.internal("subm-button2.png"));
        submImg3Active = new Texture(Gdx.files.internal("subm-button3.png"));
        
        submImg1Inactive = new Texture(Gdx.files.internal("subm-button1-Inactv.png"));
        submImg2Inactive = new Texture(Gdx.files.internal("subm-button2-Inactv.png"));
        submImg3Inactive = new Texture(Gdx.files.internal("subm-button3-Inactv.png"));
        
        submImg2Buy = new Texture(Gdx.files.internal("submImg2Buy.png"));
        submImg2Lock = new Texture(Gdx.files.internal("submImg2Lock.png"));
        
        submImg3Buy = new Texture(Gdx.files.internal("submImg3Buy.png"));
        submImg3Lock = new Texture(Gdx.files.internal("submImg3Lock.png"));
        
        coinImg = new Texture(Gdx.files.internal("coin2.png"));
        bg = new Texture(Gdx.files.internal("ShopBG.jpg"));
        
        playBtnImg1 = new Texture(Gdx.files.internal("playBtnImg1.png"));
        playBtnImg2 = new Texture(Gdx.files.internal("playBtnImg2.png"));
        
        backBtnImg1 = new Texture(Gdx.files.internal("backBtnImg1.png"));
        backBtnImg2 = new Texture(Gdx.files.internal("backBtnImg2.png"));
        
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
        
        button1 = new  Rectangle();
        button1.x = 1280/4 - 150;
        button1.y = 100;

        button2 = new  Rectangle();
        button2.x = 1280 / 2 - 150;
        button2.y = 100;

        button3 = new  Rectangle();
        button3.x = 1280/4 * 3 - 150;
        button3.y = 100;
        
        playBtn = new Rectangle();
        playBtn.x = 700;
        playBtn.y = 30;
        backBtn = new Rectangle();
        backBtn.x = 380;
        backBtn.y = 30;
        
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        game.batch.draw(coinImg, 20, 640);
        game.font.draw(game.batch, ": " + coin, 100, 700);
        
        if (Gdx.input.getX() >= button1.x && Gdx.input.getX() <= button1.x + 300 && Gdx.input.getY() >= button1.y && Gdx.input.getY() <= button1.y + 500)
        {
        	game.batch.draw(submImg1Active, button1.x, button1.y);
        	if (Gdx.input.isTouched())
        	{
        		buttonSound.play();
        		GameScreen.imgSubm = new Texture(Gdx.files.internal("submarine1.png"));
        		GameScreen2.imgSubm = new Texture(Gdx.files.internal("submarine1.png"));
        		GameScreen3.imgSubm = new Texture(Gdx.files.internal("submarine1.png"));
        		SelectLevelMenu.hitHeight = 100;
        		SelectLevelMenu.hitWidth = 180;
        		SelectLevelMenu.gap = 126;
        	}
        }
        else
        {
        	game.batch.draw(submImg1Inactive, button1.x, button1.y);
        }
        
        if (Gdx.input.getX() >= button2.x && Gdx.input.getX() <= button2.x + 300 && Gdx.input.getY() >= button2.y && Gdx.input.getY() <= button2.y + 500)
        {
        	if (subm2 == 1)
        	{
        		game.batch.draw(submImg2Active, button2.x, button2.y);
            	if (Gdx.input.isTouched())
            	{
            		buttonSound.play();
            		GameScreen.imgSubm = new Texture(Gdx.files.internal("sub2.png"));
            		GameScreen2.imgSubm = new Texture(Gdx.files.internal("sub2.png"));
            		GameScreen3.imgSubm = new Texture(Gdx.files.internal("sub2.png"));
            		SelectLevelMenu.hitHeight = 100;
            		SelectLevelMenu.hitWidth = 180;
            		SelectLevelMenu.gap = 120;
            	}
        	}
        	else if (subm2 == 0)
        	{
        		game.batch.draw(submImg2Buy, button2.x, button2.y);
        		if (Gdx.input.isTouched())
            	{
        			buttonSound.play();
            		if (coin >= priceSubm2)
            		{
            			subm2 = 1;
            			coin -= priceSubm2;
            			game.batch.draw(submImg2Active, button2.x, button2.y);
            		}
            	}
        	}
        }
        else if (subm2 == 1)
        {
        	game.batch.draw(submImg2Inactive, button2.x, button2.y);
        }
        else
        {
        	game.batch.draw(submImg2Lock, button2.x, button2.y);
        }
        
        if (Gdx.input.getX() >= button3.x && Gdx.input.getX() <= button3.x + 300 && Gdx.input.getY() >= button3.y && Gdx.input.getY() <= button3.y + 500)
        {
        	if (subm3 == 1)
        	{
        		game.batch.draw(submImg3Active, button3.x, button3.y);
            	if (Gdx.input.isTouched())
            	{
            		buttonSound.play();
            		GameScreen.imgSubm = new Texture(Gdx.files.internal("sub3.png"));
            		GameScreen2.imgSubm = new Texture(Gdx.files.internal("sub3.png"));
            		GameScreen3.imgSubm = new Texture(Gdx.files.internal("sub3.png"));
            		SelectLevelMenu.hitHeight = 60;
            		SelectLevelMenu.hitWidth = 180;
            		SelectLevelMenu.gap = 77;
            	}
        	}
        	else if (subm3 == 0)
        	{
        		game.batch.draw(submImg3Buy, button3.x, button3.y);
        		if (Gdx.input.isTouched())
            	{
        			buttonSound.play();
            		if (coin >= priceSubm3)
            		{
            			subm3 = 1;
            			coin -= priceSubm3;
            			game.batch.draw(submImg3Active, button3.x, button3.y);
            		}
            	}
        	}
        }
        else if (subm3 == 1)
        {
        	game.batch.draw(submImg3Inactive, button3.x, button3.y);
        }
        else
        {
        	game.batch.draw(submImg3Lock, button3.x, button3.y);
        }
        
        if (Gdx.input.getX() >= backBtn.x && Gdx.input.getX() <= backBtn.x + 200 && Gdx.input.getY() >= backBtn.y + 610 && Gdx.input.getY() <= backBtn.y + 610 + 50)
        {
        	game.batch.draw(backBtnImg2, backBtn.x, backBtn.y);
        	if (Gdx.input.isTouched())
        	{
        		buttonSound.play();
        		SubMenu.flags2 = subm2;
        		SubMenu.flags3 = subm3;
        		SubMenu.bgMusic1.dispose();
        		game.setScreen(new SubMenu(game));
        	}
        }
        else
        {
        	game.batch.draw(backBtnImg1, backBtn.x, backBtn.y);
        }
        
        if (Gdx.input.getX() >= playBtn.x && Gdx.input.getX() <= playBtn.x + 200 && Gdx.input.getY() >= playBtn.y + 610 && Gdx.input.getY() <= playBtn.y + 610 + 50)
        {
        	game.batch.draw(playBtnImg2, playBtn.x, playBtn.y);
        	if (Gdx.input.isTouched())
        	{
        		buttonSound.play();
        		SubMenu.flags2 = subm2;
        		SubMenu.flags3 = subm3;
        		game.setScreen(new SelectLevelMenu(game));
        	}
        }
        else
        {
        	game.batch.draw(playBtnImg1, playBtn.x, playBtn.y);
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
		submImg1Active.dispose();
		submImg1Active.dispose();
		submImg1Active.dispose();
		submImg1Inactive.dispose();
		submImg1Inactive.dispose();
		submImg1Inactive.dispose();
		bg.dispose();
	}

}
