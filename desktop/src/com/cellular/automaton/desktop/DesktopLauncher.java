package com.cellular.automaton.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cellular.automaton.naiveseedsgrowth.NaiveSeedsGrowth;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 500;
		config.resizable = true;
		config.foregroundFPS = 60;
		config.vSyncEnabled = false;
		new LwjglApplication(new NaiveSeedsGrowth(), config);
	}
}
