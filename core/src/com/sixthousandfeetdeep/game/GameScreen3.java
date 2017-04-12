package com.sixthousandfeetdeep.game;

import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen3 implements Screen {
	public static final int HEIGHT = 720;
	int y = HEIGHT / 2;
	
	int spwcoin = 0;
	int coinGathered = 0;
	int chestGathered = 0;
	
	public static Music bgMusic1, bgMusic4, boostMusic;
	Sound coinSound, bombSound, keySound;
	
	public static int hitboxHeight = 100, hitboxWidth = 180, topGap = 126;
	public static Texture imgSubm = new Texture(Gdx.files.internal("submarine1.png"));

	final SixThousandFeetDeep game;
	Texture mineImage , submarineImg , coinImage, boostImg, chestImg, bg;

	OrthographicCamera camera;
	Rectangle submarine;

	Array<Rectangle> minedrops;
	long lastDropTime;

	Array<Rectangle> coins;
	long lastCoinTime;

	Array<Rectangle> boosts;
	long lastBoostTime;
	
	Array<Rectangle> chests;
	long lastChestTime;

	int speed = 400;
	int flags = 0;
	long boostTime = 0;
	float startBoost = 0;
	double speed1 = 2000000000 * 1.2, speed2 = 1000000000 / 1.3, speed3 = 20000 / 1.3, speed4 = 25000 / 1.3;
	double boost1 = 2000000000 * 1.2 / 4, boost2 = 1000000000 / 4, boost3 = 20000 / 4, boost4 = 25000 / 4;
	
	int positionX = 0;

	public GameScreen3 (final SixThousandFeetDeep gam) {
		this.game = gam;

		bg = new Texture(Gdx.files.internal("bgImg2.jpg"));
		submarineImg = imgSubm;
		mineImage = new Texture(Gdx.files.internal("mine1.png"));
		coinImage = new Texture(Gdx.files.internal("coin.png"));
		boostImg = new Texture(Gdx.files.internal("boost-item.png"));
		chestImg = new Texture(Gdx.files.internal("chest.png"));

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);

		submarine = new Rectangle();
		submarine.x = 50;
		submarine.y = HEIGHT / 2;
		submarine.width = hitboxWidth;
		submarine.height = hitboxHeight;
		
		bgMusic1 = Gdx.audio.newMusic(Gdx.files.internal("bgsound.mp3"));
		bgMusic4 = Gdx.audio.newMusic(Gdx.files.internal("bg3sound.mp3"));
        bgMusic4.setLooping(true);
        bgMusic4.play();
        
        bombSound = Gdx.audio.newSound(Gdx.files.internal("bomb.mp3"));
        coinSound = Gdx.audio.newSound(Gdx.files.internal("coin.mp3"));
        boostMusic = Gdx.audio.newMusic(Gdx.files.internal("boost.mp3"));
        keySound = Gdx.audio.newSound(Gdx.files.internal("collectitem.mp3"));
        
		minedrops = new Array<Rectangle>();
		spawnMinedrop();
		
		coins = new Array<Rectangle>();
		spawnCoin();
		spwcoin += 1;
		
		boosts = new Array<Rectangle>();
		spawnBoost();
		
		chests = new Array<Rectangle>();
		spawnChest();
	}
	

	private void spawnMinedrop() {
	    Rectangle minedrop = new Rectangle();
	    minedrop.x = 1280;
	    minedrop.y = MathUtils.random(0, 720-50);
	    minedrop.width = 100;
	    minedrop.height = 100;
	    minedrops.add(minedrop);
	    lastDropTime = TimeUtils.nanoTime();
	}
	
	private void spawnCoin(){
		Rectangle coin = new Rectangle();
		coin.x = 1280;
		coin.y = 720 / 2;
		if (spwcoin % 3 == 0)
	    	coin.y = MathUtils.random(0, 720-50);
		coin.y = MathUtils.random(0, 720-50);
		coin.width = 80;
	    coin.height = 80;
	    coins.add(coin);
	    lastCoinTime = TimeUtils.nanoTime();
	}
	
	private void spawnBoost() {
	    Rectangle boost = new Rectangle();
	    boost.x = 1280;
	    boost.y = MathUtils.random(0, 720-50);
	    boost.width = 80;
	    boost.height = 91;
	    boosts.add(boost);
	    lastBoostTime = TimeUtils.millis();
	}
	
	private void spawnChest() {
	    Rectangle chest = new Rectangle();
	    chest.x = 1280;
	    chest.y = MathUtils.random(0, 720-50);
	    chest.width = 106;
	    chest.height = 101;
	    chests.add(chest);
	    lastChestTime = TimeUtils.millis();
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		
		SubMenu.bgMusic1.dispose();

		camera.update();

		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.batch.draw(bg, positionX, 0);
		game.batch.draw(submarineImg, submarine.x, submarine.y);
		
		if (submarine.x - submarine.height / 2 < 720 / 2 - 103)
			submarine.x += speed * Gdx.graphics.getDeltaTime();
		
		for(Rectangle minedrop: minedrops) {
		    game.batch.draw(mineImage, minedrop.x, minedrop.y);
		}
		for(Rectangle coin: coins) {
		    game.batch.draw(coinImage, coin.x, coin.y);
		}
		for(Rectangle boost: boosts) {
		    game.batch.draw(boostImg, boost.x, boost.y);
		}
		for(Rectangle chest: chests) {
		    game.batch.draw(chestImg, chest.x, chest.y);
		}
		game.font.draw(game.batch, "Coins : " + coinGathered, 20, 720);
		game.font.draw(game.batch, "Chest : " + chestGathered, 20, 690);
		 //game.font.draw(game.batch, " / 10", 150, 690);
		game.batch.end();
		
		if (submarine.x - submarine.height / 2 > 720 / 2 - 103)
		{
			if (Gdx.input.isTouched()) {
				submarine.y += 320 * Gdx.graphics.getDeltaTime();
			}
			else  {
				submarine.y -= 350 * Gdx.graphics.getDeltaTime();
			}
		}
		
		if (submarine.y > HEIGHT - topGap) {
			submarine.y = HEIGHT - topGap;
        }
		if (submarine.y < 0) {
			submarine.y = 0;
		}
		
		if (positionX >= -10240){
			positionX -= 100 * delta;
		}
		
		if (positionX == -10240){
			positionX = -10240;
		}
		
		if(TimeUtils.nanoTime() - lastDropTime > speed1) 
			spawnMinedrop();
			
		if(TimeUtils.nanoTime() - lastCoinTime > speed2) 
			spawnCoin();
		
		if(TimeUtils.millis() - lastBoostTime > speed3) 
			spawnBoost();
		
		if(TimeUtils.millis() - lastChestTime > speed4) 
			spawnChest();
			
		
		Iterator<Rectangle> iter = minedrops.iterator();
		Iterator<Rectangle> iter2 = coins.iterator();
		Iterator<Rectangle> iter3 = boosts.iterator();
		Iterator<Rectangle> iter4 = chests.iterator();
		
		while(iter.hasNext()) {
		    Rectangle minedrop = iter.next();
		    minedrop.x -= speed * Gdx.graphics.getDeltaTime();
		    if (flags == 0)
		    {
		    	if(minedrop.overlaps(submarine)) {
		    		ShopMenu.coin += coinGathered;
		    		if (coinGathered > SelectLevelMenu.highLV3)
		    		{
		    			SelectLevelMenu.highLV3 = coinGathered;
		    		}
		    		ShowScore.score = coinGathered;
		    		ShowScore.bgScore = new Texture(Gdx.files.internal("bg2.png"));
		    		ShowScore.flags = 3;
		    		bgMusic4.dispose();
		    		boostMusic.dispose();
		    		bombSound.play();
		    		game.setScreen(new ShowScore(game));
		    		dispose();
		    	}
		    }
		    else
		    {
		    	if(minedrop.overlaps(submarine)) {
		    		iter.remove();
		    	}
		    }
		}
		while(iter2.hasNext()) {
			Rectangle coin = iter2.next();
			coin.x -= speed * Gdx.graphics.getDeltaTime();
			if(coin.overlaps(submarine)) {
	            iter2.remove();
	            coinGathered += 1;
	            coinSound.play();
	         }
		}
		while(iter3.hasNext()) {
			Rectangle boost = iter3.next();
			boost.x -= speed * Gdx.graphics.getDeltaTime();
			if(boost.overlaps(submarine)) {
	            iter3.remove();
	            flags = 1;
	            speed = 800;
	            speed1 = boost1;
	            speed2 = boost2;
	            speed3 = boost3;
	            speed4 = boost4;
	            startBoost = 5;
	            boostMusic.play();
			}
			if (startBoost > 0) {
		    	startBoost -= delta;
		    }
			else {
		    	flags = 0;
		        speed = 200;
		        speed1 = 2000000000 * 1.2;
	            speed2 = 1000000000 / 1.3;
	            speed3 = 20000 / 1.3;
	            speed4 = 20000 / 1.3;
	            boostMusic.dispose();
		    }
		}
		while(iter4.hasNext()) {
			Rectangle chest = iter4.next();
			chest.x -= speed * Gdx.graphics.getDeltaTime();
			if(chest.overlaps(submarine)) {
	            iter4.remove();
	            chestGathered += 1;
	            keySound.play();
			}
		}
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
	public void dispose () {
		//submarineImg.dispose();
		mineImage.dispose();
		coinImage.dispose();
		boostImg.dispose();
	}
}
