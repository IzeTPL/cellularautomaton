package com.cellular.automaton.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cellular.automaton.GameOfLife;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1200;
		config.height = 1000;
		config.resizable = true;
		config.foregroundFPS = 60;
		config.vSyncEnabled = false;
		new LwjglApplication(new GameOfLife(), config);
	}
}
