package com.sixthousandfeetdeep.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sixthousandfeetdeep.game.MainMenuScreen;

public class SixThousandFeetDeep extends Game{
	 SpriteBatch batch;
	 BitmapFont font;

	 public void create() {
	     batch = new SpriteBatch();
	     font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
	     this.setScreen(new MainMenuScreen(this));
	  }

	 public void render() {
	      super.render();
	  }

	 public void dispose() {
	    batch.dispose();
	    font.dispose();
	 }
}
