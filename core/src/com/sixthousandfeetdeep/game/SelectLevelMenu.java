package com.sixthousandfeetdeep.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SelectLevelMenu implements Screen {
	
	final SixThousandFeetDeep game;
	
	OrthographicCamera camera;
	
	Texture level1ImgActive, level2ImgActive, level3ImgActive, bg;
	Texture level1ImgInactive, level2ImgInactive, level3ImgInactive;
	Texture lv2Lock, lv3Lock;
	
	Rectangle lv1Button, lv2Button, lv3Button;
	
	Sound buttonSound;
	
	public static int lv1 = 0, lv2 = 0, lv3 = 0, hitHeight = 100, hitWidth = 180, gap = 126, highLV1 = 0, highLV2 = 0, highLV3 = 0;
	
	public SelectLevelMenu(final SixThousandFeetDeep gam) {
		
		this.game = gam;
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        
        level1ImgActive = new Texture(Gdx.files.internal("level1Img-Active.png"));
        level2ImgActive = new Texture(Gdx.files.internal("level2Img-Active.png"));
        level3ImgActive = new Texture(Gdx.files.internal("level3Img-Active.png"));
        
        level1ImgInactive = new Texture(Gdx.files.internal("level1Img-Inactive.png"));
        level2ImgInactive = new Texture(Gdx.files.internal("level2Img-Inactive.png"));
        level3ImgInactive = new Texture(Gdx.files.internal("level3Img-Inactive.png"));
        
        lv2Lock = new Texture(Gdx.files.internal("lv2Lock.png"));
        lv3Lock = new Texture(Gdx.files.internal("lv3Lock.png"));
        
        bg = new Texture(Gdx.files.internal("LevelBG.jpg"));
        
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
        
        lv1Button = new Rectangle();
        lv1Button.x = 1280/4 - 150;
        lv1Button.y = 100;
        
        lv2Button = new Rectangle();
        lv2Button.x = 1280 / 2 - 150;;
        lv2Button.y = 100;
        
        lv3Button = new Rectangle();
        lv3Button.x = 1280/4 * 3 - 150;
        lv3Button.y = 100;
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        if (Gdx.input.getX() >= lv1Button.x && Gdx.input.getX() <= lv1Button.x + 300 && Gdx.input.getY() >= lv1Button.y + 20 && Gdx.input.getY() <= lv1Button.y + 520)
        {
        	game.batch.draw(level1ImgActive, lv1Button.x, lv1Button.y);
        	if (Gdx.input.isTouched()){
        		buttonSound.play();
        		GameScreen.hitboxHeight = hitHeight;
        		GameScreen.hitboxWidth = hitWidth;
        		GameScreen.topGap = gap;
        		game.setScreen(new GameScreen(game));
        		this.dispose();
        	}
        		
        }
        else
        	game.batch.draw(level1ImgInactive, lv1Button.x, lv1Button.y);
        
        if (lv1 == 5) {
        	if (Gdx.input.getX() >= lv2Button.x && Gdx.input.getX() <= lv2Button.x + 300 && Gdx.input.getY() >= lv2Button.y + 20 && Gdx.input.getY() <= lv2Button.y + 520)
        	{
        		game.batch.draw(level2ImgActive, lv2Button.x, lv2Button.y);
        		if (Gdx.input.isTouched()){
        			buttonSound.play();
        			GameScreen2.hitboxHeight = hitHeight;
        			GameScreen2.hitboxWidth = hitWidth;
        			GameScreen2.topGap = gap;
        			game.setScreen(new GameScreen2(game));
        		}
        	}
        	else
            	game.batch.draw(level2ImgInactive, lv2Button.x, lv2Button.y);
        }
        else
        	game.batch.draw(lv2Lock, lv2Button.x, lv2Button.y);
        
        if (lv2 == 10) {
        	if (Gdx.input.getX() >= lv3Button.x && Gdx.input.getX() <= lv3Button.x + 300 && Gdx.input.getY() >= lv3Button.y + 20 && Gdx.input.getY() <= lv3Button.y + 520)
            {
            	game.batch.draw(level3ImgActive, lv3Button.x, lv3Button.y);
            	if (Gdx.input.isTouched()){
            		buttonSound.play();
            		GameScreen3.hitboxHeight = hitHeight;
            		GameScreen3.hitboxWidth = hitWidth;
            		GameScreen3.topGap = gap;
            		game.setScreen(new GameScreen3(game));
            	}
            }
        	else
            	game.batch.draw(level3ImgInactive, lv3Button.x, lv3Button.y);
        }
        else
        	game.batch.draw(lv3Lock, lv3Button.x, lv3Button.y);
        
        game.font.draw(game.batch, "" + highLV1, 310, 280);
        game.font.draw(game.batch, "" + highLV2, 630, 280);
        game.font.draw(game.batch, "" + highLV3, 950, 280);
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
