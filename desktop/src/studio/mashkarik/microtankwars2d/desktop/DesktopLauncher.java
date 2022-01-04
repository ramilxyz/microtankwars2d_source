package studio.mashkarik.microtankwars2d.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import studio.mashkarik.microtankwars2d.MicroTankWarsGame;

public class DesktopLauncher  {

	private static DesktopLauncher desktopLauncher;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		if(desktopLauncher == null) {
			desktopLauncher = new DesktopLauncher();
		}

		config.width = 800;
		config.height = 480;

	new LwjglApplication(new MicroTankWarsGame(), config);
	}
}
