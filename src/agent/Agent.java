package agent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import application.Settings;
import environment.Element;
import environment.Manor;


public class Agent implements Runnable {
	
	/******************
	 * Initialization *
	 ******************/
	static int posX = 0, newPosX = 0, posY = 0, newPosY = 0, actionCount, energy;
	private int Status; //1 for alive and 0 for died
	private Element[][] belief = new Element[Settings.LINE_NUMBER][Settings.COLUMN_NUMBER];
	private Element[][] content = new Element[Settings.LINE_NUMBER][Settings.COLUMN_NUMBER];
	private Element[][] desire = new Element[Settings.LINE_NUMBER][Settings.COLUMN_NUMBER]; 

	private ArrayList<Effector> intension = new ArrayList<Effector>() ;
	private DrawUp drawup = new DrawUp();
	private PickUp pickup = new PickUp();
	private Camera camera = new Camera();
	private Move left = new Move("left");
	private Move right = new Move("right");
	private Move up = new Move("up");
	private Move down = new Move("down");
	static Manor manor;

	public Agent(Manor myManor) {
		manor = myManor;
		
		//System.out.println(manor);
	}
	
	/************
	 * Methods *
	 ************/
	public void run() {
		do {
			try {
				Thread.sleep(Settings.ROBOT_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateBelief(manor.getRooms());
			//System.out.println("update");
			//updateIntension();
			bfs();
			//System.out.println("execution");
			executeIntension();

		}while (!this.goalTest());
		
	}
	
	//	
	private void bfs()
	{
		
		ConcurrentLinkedQueue<Node> nodeList = new ConcurrentLinkedQueue<Node>();				
		//ArrayList<Node> nodeList = new ArrayList<Node>();
		nodeList.add(new Node(posX, posY));
		for (int n=0; n < nodeList.size(); n++) {
			Node cn = nodeList.poll();
			//System.out.println(nodeList.size());
			if (this.nodeTest(cn.getNodeX(), cn.getNodeY())) {
				// create list of new nodes
				//System.out.println("explore " + cn.getNodeX() + " " + cn.getNodeY());
				if (cn.getNodeX() == 0) { // move down and right
					nodeList.add(new Node(cn.getNodeX()+1, cn.getNodeY())); // down
					
					if(cn.getNodeY()==0) {					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right
					} else if (cn.getNodeY()==Settings.COLUMN_NUMBER-1) {					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); // left
					} else {
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); // left
					}
				} else if(cn.getNodeX() == Settings.LINE_NUMBER-1) {
					nodeList.add(new Node(cn.getNodeX()-1, cn.getNodeY())); // up
					
					if(cn.getNodeY()==0) {					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right			
					} else if (cn.getNodeY()==Settings.COLUMN_NUMBER-1) {					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); // left
					} else {
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); // left
					}
				} else {
					nodeList.add(new Node(cn.getNodeX()-1, cn.getNodeY())); // up
					nodeList.add(new Node(cn.getNodeX()+1, cn.getNodeY())); // down
					
					if(cn.getNodeY()==0) {					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right					
					} else if (cn.getNodeY()==Settings.COLUMN_NUMBER-1) {					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); // left
					} else {
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); // left
					}
				}			
				
			} else { // node has dirt
				newPosX = cn.getNodeX();
				newPosY = cn.getNodeY();
				System.out.println(newPosX + ":" + newPosY);
				break;
			}
		}
	}
	
	/*
	 * @input x, y
	 * @return true if belief[x][y].size == 0
	 */
	private boolean nodeTest(int nodeX, int nodeY) {
		return belief[nodeX][nodeY].getSize() == 0;
	}
	
	// Create a clone of the map
	protected void updateBelief (Element [][] manorRoom) {
		for(int i = 0; i < Settings.LINE_NUMBER; i++) {
			for (int j = 0; j < Settings.COLUMN_NUMBER; j++) {
				belief[i][j] = manorRoom[i][j];
			}
		}
		
	}
	
	// Create a list of actions
	protected void updateIntension () {
		// Explore map and move to next task
		bfs();
		int tempX = Agent.posX, tempY = Agent.posY;
		while (tempX != newPosX && tempY != newPosY) {
		//while (Agent.posX != newPosX && Agent.posY != newPosY) {
			if (Agent.posX < newPosX) { this.intension.add(left); tempX++;  /*left.doAction();*/ }
			else if (Agent.posX > newPosX) { this.intension.add(right); tempX--; /*right.doAction();*/ }
			else {} // Do nothing
	    
			if (Agent.posY < newPosY) { this.intension.add(down); tempY++; /*down.doAction();*/ }
			else if (Agent.posY > newPosY) { this.intension.add(up); tempY--; /*up.doAction();*/ }

			else {} // Do nothing
		
		System.out.println("lol dans while");
		}
		
		Element CurrentElement = belief[newPosX][newPosY];
		if (CurrentElement.getSize() == 2) {

			this.intension.add(pickup);
			this.intension.add(drawup);
		}
		else {
			if (CurrentElement.getContent().size() != 0) { 

				if ( CurrentElement.getContent().get(0) == 0) { //dust 
					intension.add(drawup);
				}
				else {  //diamond
					this.intension.add(pickup);

				}
			}
		}
	}
	
	
	//
	protected void executeIntension () {

	
		int tt = this.intension.size();
		System.out.println("taille de intension "+tt);
       	for(int k=0; k < tt; k++) 
       	{       		
       		this.intension.get(k).doAction();       		
       	}
       	
       this.intension.clear();
       //intension = new ArrayList<Effector>();

	}
	
	protected boolean goalTest () {
		return manor.isManorClean();
	}
	
	private void performanceMeasure() {
		System.out.println("Number of actions : " + actionCount);
		System.out.println("Energy lose : " + energy);
		
		if(actionCount == energy) { System.out.println("This agent is awesome"); }
		else if ((energy - actionCount)<5) { System.out.println("This agent is pretty usefull"); }
		else { System.out.println("This agent is useless"); }
	}
}
