package agent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import application.Settings;
import environment.Element;
import environment.Manor;
import environment.Map;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Agent implements Runnable {
	
	/******************
	 * Initialisation *
	 ******************/
	static int posX = 0, newPosX = 0, posY = 0, newPosY = 0, actionCount, energy;
	private int Status; //1 for alive and 0 for died
	private Element[][] belief = new Element[Settings.LINE_NUMBER][Settings.COLUMN_NUMBER];
	private Element[][] content = new Element[Settings.LINE_NUMBER][Settings.COLUMN_NUMBER];
	private Element[][] desire = new Element[Settings.LINE_NUMBER][Settings.COLUMN_NUMBER]; 
	private ArrayList<Effector> intension ;
	private DrawUp drawup;
	private PickUp pickup;
	private Camera camera;
	private Move left = new Move("left");
	private Move right = new Move("right");
	private Move up = new Move("up");
	private Move down = new Move("down");
	//on a un problème à moins qu'on mette à jour cette information à chaque 
	//fois que l'environnement change 
	static Manor manor;
	

	public Agent(Manor myManor)
	{
		manor = myManor;
		//System.out.println(manor);
	}
	
	/************
	 * Methodes *
	 ************/
	public void run() {	
			do 
			{
				
				try {
					Thread.sleep(Settings.ROBOT_DELAY);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				updateBelief(manor.getRooms());
				//updateIntension();
				//executeIntension();
				System.out.println("bfs");
				bfs();
				manor.delRoomContent(newPosX, newPosY);
				
				}while(!this.goalTest());
			
		
		
		
    }
	
	// Find a box that contain dust and/or diamond
	private void exploreNotInformed () {
		Element exist;
		
		for(int i = 0; i < Settings.LINE_NUMBER-1; i++) {
			for (int j = 0; j < Settings.COLUMN_NUMBER-1; j++) {
				exist = belief[i][i];
				
				if(exist.getSize() == 1 || exist.getSize() == 2) {
					newPosX = j;
					newPosY = i;
				}
				else {} // Do nothing
			}
		}
	}
	private void bfs()
	{
		
		ConcurrentLinkedQueue<Node> nodeList = new ConcurrentLinkedQueue<Node>();				
		//ArrayList<Node> nodeList = new ArrayList<Node>();
		nodeList.add(new Node(posX, posY));
		for (int n=0; n < nodeList.size(); n++)
		{	Node cn = nodeList.poll();
			//System.out.println(nodeList.size());
			if (this.nodeTest(cn.getNodeX(), cn.getNodeY())) //
			{
				// create list of new nodes
				//System.out.println("explore " + cn.getNodeX() + " " + cn.getNodeY());
				if (cn.getNodeX() == 0) // moov down and right
				{
					nodeList.add(new Node(cn.getNodeX()+1, cn.getNodeY())); // down
					
					if(cn.getNodeY()==0)
					{					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right
						
					}
					else if (cn.getNodeY()==Settings.COLUMN_NUMBER-1)
					{					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); //left
						
					}
					else
					{
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); //left
					}
					
				}
				else if(cn.getNodeX() == Settings.LINE_NUMBER-1)
				{
					nodeList.add(new Node(cn.getNodeX()-1, cn.getNodeY())); // up
					if(cn.getNodeY()==0)
					{					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right
						
						
					}
					else if (cn.getNodeY()==Settings.COLUMN_NUMBER-1)
					{					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); //left
						
					}
					else
					{
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); //left
					}
				}
				else
				{
					nodeList.add(new Node(cn.getNodeX()-1, cn.getNodeY())); // up
					nodeList.add(new Node(cn.getNodeX()+1, cn.getNodeY())); // down
					if(cn.getNodeY()==0)
					{					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right
						
						
					}
					else if (cn.getNodeY()==Settings.COLUMN_NUMBER-1)
					{					
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); //left
						
					}
					else
					{
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()+1)); // right
						nodeList.add(new Node(cn.getNodeX(), cn.getNodeY()-1)); //left
					}
				}
				
				
				
			}
			else // node has dirt
			{
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
	private boolean nodeTest(int nodeX, int nodeY)
	{
		
		return belief[nodeX][nodeY].getSize() == 0;
	}
	// Create a clone of the map
	protected void updateBelief (Element [][] manorRoom) {
		for(int i = 0; i < Settings.LINE_NUMBER; i++) {
			for (int j = 0; j < Settings.COLUMN_NUMBER; j++) {
				belief[i][j] = manorRoom[i][j];
				//System.out.println(manorRoom[i][j]);
			}
		}
		
		//belief = manor.getRooms();
	}
	
	// Create a list of actions
	protected void updateIntension () {
		for(int i = 0; i < Settings.LINE_NUMBER; i++) {
			for (int j = 0; j < Settings.COLUMN_NUMBER; j++) {
				Element currentElement = belief[i][j];
				
				// Explore map and move to next task
				if (currentElement.getSize() == 0) {
					exploreNotInformed();
					
					while (Agent.posX != newPosX || Agent.posY != newPosY) {
						//if (agent.posX < newPosX) { new agent.Move("right"); } pas possible par ce que l'agent est inp
						if (Agent.posX < newPosX) { intension.add(right); }
						else if (Agent.posX > newPosX) { intension.add(left); }
						else {} // Do nothing
						
						if (Agent.posY < newPosY) { intension.add(up); }
						else if (Agent.posY > newPosY) { intension.add(down); }
						else {} // Do nothing
					}
				}
				else if(currentElement.getSize() != 0) // room not empty
				{
					for(Integer dirt : currentElement.getContent())
					{
						if (dirt == 0)  //dust
						{
							intension.add(drawup);
						}
						else  //diamond
						{
							intension.add(pickup);
						}
								
					}
				}
				/*
				else if (currentElement.getSize() == 1) {
					if (currentElement.getContent().get(0) == 0) { // ==> dust
						
						intension.add(drawup);
					}
					else { // ==>  diamond
						intension.add(pickup);
					}
				}
				else if (currentElement.getSize() == 2) {
					intension.add(pickup);
					intension.add(drawup);
				} */
			}
		}
	}
	
	//
	protected void executeIntension () {
		
		Iterator<Effector> exe = intension.iterator();
        while(exe.hasNext()){
            exe.next().doAction();;
        }
        
	}
	
	protected boolean goalTest () {
		
		return manor.isManorClean();
	}

	
	
}
