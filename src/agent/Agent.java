package agent;

import java.util.ArrayList;
import java.util.Random;

import application.Settings;
import environment.Element;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Agent 
{
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
	
	
	
	public void run() 
	{	
		
		
    }

	
	private void explore () //find a box that contains dust
	{
		
	}
	
	protected void updateBilieves (Element [][] manorRoom)
	{
		for(int i = 0; i < Settings.LINE_NUMBER; i++)
		{
			for (int j = 0; j < Settings.COLUMN_NUMBER; j++)
			{
				bilieves[i][j] = manorRoom[i][j];
			}
		}
	}
	
	protected void updateIntension ()
	{
		for(int i = 0; i < Settings.LINE_NUMBER; i++)
		{
			for (int j = 0; j < Settings.COLUMN_NUMBER; j++)
			{
				Element currentElemnet = bilieves[i][j];
				
				if (currentElemnet.getSize() == 0)
				{
				//move
					
				}
				else if (currentElemnet.getSize() == 1)
				{
					if (currentElemnet.getContent().get(0) == 0) //if dust
					{
						intension.add(drawup);
					}
					else //if diamond
					{
						intension.add(pickup);
					}
				}
				else if (currentElemnet.getSize() == 2)
				{
					intension.add(pickup);
					intension.add(drawup);
				}
			}
		}
	}
	
	protected void executeIntension ()
	{
		
	}
	
	protected boolean goalTest ()
	{
		return false;
	}
	

}
