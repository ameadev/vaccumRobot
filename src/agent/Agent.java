package agent;

import java.util.ArrayList;

import application.Settings;
import environment.Element;

public class Agent 
{
	static int x;
	static int y;
	static int actionCount;
	static int energy;
	private int Status; //1 for alive and 0 for died
	private Element[][] bilieves; //clone de l'environnement
	private Element[][] desire = new Element[10][10]; 
	private ArrayList<Effector> intension ;
	
	
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
		
	}
	
	protected void executeIntension ()
	{
		
	}
	
	protected boolean goalTest ()
	{
		return false;
	}
	

}
