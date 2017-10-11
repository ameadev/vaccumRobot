package agent;

import java.util.ArrayList;
import java.util.Random;

import application.Settings;
import environment.Element;
import environment.Map;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Agent {
	static int x;
	static int y;
	static int actionCount;
	static int energy;
	private int Status; //1 for alive and 0 for died
	private Element[][] bilieves; //clone de l'environnement
	private Element[][] desire = new Element[Settings.LINE_NUMBER][Settings.COLUMN_NUMBER]; 
	private ArrayList<Effector> intension ;
	private DrawUp drawup;
	private PickUp pickup;
	private Camera camera;
	private Move left = new Move("left");
	private Move right = new Move("right");
	private Move top = new Move("top");
	private Move down = new Move("down");
	
	public void run() {	
			
    }

	private Effector exploreNotInformed () { //find a box that contains dust
		// 1. Read Bilieves
		// 2. Check if Dust and/or Diamond exist
		// 3. If true, save position
		// 4. move agent to position
		Effector content;
		Element exist;
		
		for(int i = 0; i < Settings.LINE_NUMBER-1; i++) {
			for (int j = 0; j < Settings.COLUMN_NUMBER-1; j++) {
				exist = bilieves[i][i];
				
				if(exist.getSize() == 1 || exist.getSize() == 2) {
					// ==> Effector or Element
					// ==> move here or just return position of dust/diamond
				}
				else {} // Do nothing
			}
		}
		
		//return content;
	}
	
	protected void updateBilieves (Element [][] manorRoom) {
		for(int i = 0; i < Settings.LINE_NUMBER; i++) {
			for (int j = 0; j < Settings.COLUMN_NUMBER; j++) {
				bilieves[i][j] = manorRoom[i][j];
			}
		}
	}
	
	protected void updateIntension () {
		for(int i = 0; i < Settings.LINE_NUMBER; i++) {
			for (int j = 0; j < Settings.COLUMN_NUMBER; j++) {
				Element currentElemnet = bilieves[i][j];
				
				if (currentElemnet.getSize() == 0) {
					//explore and move
					exploreNotInformed();
				}
				else if (currentElemnet.getSize() == 1) {
					if (currentElemnet.getContent().get(0) == 0) { // If dust
						intension.add(drawup);
					}
					else { // If diamond
						intension.add(pickup);
					}
				}
				else if (currentElemnet.getSize() == 2) {
					intension.add(pickup);
					intension.add(drawup);
				}
			}
		}
	}
	
	protected void executeIntension () {
		
	}
	
	protected boolean goalTest () {
		return false;
	}
}
