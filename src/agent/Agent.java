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
	
	/******************
	 * Initialisation *
	 ******************/
	static int x, newX, y, newY, actionCount, energy;
	private int Status; //1 for alive and 0 for died
	private Element[][] belif, content;
	private Element[][] desire = new Element[Settings.LINE_NUMBER][Settings.COLUMN_NUMBER]; 
	private ArrayList<Effector> intension ;
	private DrawUp drawup;
	private PickUp pickup;
	private Camera camera;
	private Move left = new Move("left");
	private Move right = new Move("right");
	private Move up = new Move("up");
	private Move down = new Move("down");
	

	
	/************
	 * Methodes *
	 ************/
	public void run() {	
			
    }
	
	// Find a box that contain dust and/or diamond
	private void exploreNotInformed () {
		Element exist;
		
		for(int i = 0; i < Settings.LINE_NUMBER-1; i++) {
			for (int j = 0; j < Settings.COLUMN_NUMBER-1; j++) {
				exist = belif[i][i];
				
				if(exist.getSize() == 1 || exist.getSize() == 2) {
					newX = j;
					newY = i;
				}
				else {} // Do nothing
			}
		}
	}
	
	// Create a clone of the map
	protected void updateBelif (Element [][] manorRoom) {
		for(int i = 0; i < Settings.LINE_NUMBER; i++) {
			for (int j = 0; j < Settings.COLUMN_NUMBER; j++) {
				belif[i][j] = manorRoom[i][j];
			}
		}
	}
	
	// Create a list of actions
	protected void updateIntension () {
		for(int i = 0; i < Settings.LINE_NUMBER; i++) {
			for (int j = 0; j < Settings.COLUMN_NUMBER; j++) {
				Element currentElemnet = belif[i][j];
				
				// Explore map and move to next task
				if (currentElemnet.getSize() == 0) {
					exploreNotInformed();
					
					while (Agent.x != newX || Agent.y != newY) {
						if (Agent.x < newX) { new agent.Move("right"); }
						else if (Agent.x > newX) { new agent.Move("left"); }
						else {} // Do nothing
						
						if (Agent.y < newY) { new agent.Move("up"); }
						else if (Agent.y > newY) { new agent.Move("down"); }
						else {} // Do nothing
					}
				}
				else if (currentElemnet.getSize() == 1) {
					if (currentElemnet.getContent().get(0) == 0) { // ==> dust
						intension.add(drawup);
					}
					else { // ==>  diamond
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
