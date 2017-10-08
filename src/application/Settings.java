package application;

import javafx.scene.paint.Color;

/*
 * content application settings
 * @APP_NAME : Application's name
 * @ROBOT_DELAY : robot's delay time into two iterations
 * @ENV_DELAY : Environment delay
 */
public class Settings {
	public static String APP_NAME = "AWESOME VACUUM";
	public static int W_WIDTH = 800;
	public static int W_HEIGHT = 600;
	public static int ROBOT_DELAY = 1;
	public static int ENV_DELAY = 1;
	public static int COLUMN_NUMBER = 10;
	public static int LINE_NUMBER = 10;
	public static Color COLOR_LIST[] = {Color.ALICEBLUE, Color.BLUE, Color.AQUAMARINE, Color.AQUA,
										Color.BEIGE, Color.BLUEVIOLET, Color.BROWN};
	
	public Settings()
	{
		
	}
		
}
