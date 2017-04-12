package com.sixthousandfeetdeep.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sixthousandfeetdeep.game.SixThousandFeetDeep;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "6000 ft.";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new SixThousandFeetDeep(), config);
	}
}
