package environment;

import java.util.ArrayList;
import java.util.Random;

import java.util.Iterator;

import application.Settings;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;



/*
 * environment class
 */
public class Manor extends Parent  {
	
	Element [][] rooms = new Element[Settings.LINE_NUMBER][Settings.COLUMN_NUMBER];
	
	public Manor(){
		
		fillRooms();
		showCard();
	}
	
	public void run() {
		
			//do{
			int lineIndex = new Random().nextInt(Settings.LINE_NUMBER); // line random choice
			int colIndex = new Random().nextInt(Settings.COLUMN_NUMBER); // column random choice
			int component = new Random().nextInt(2); // element random choice (0: dust, 1: diamond)
			this.rooms[lineIndex][colIndex].addContent(component);
			System.out.println("a");
		
			
			this.showCard();
			//System.out.println(this.isManorClean());
			//}while(!this.isManorClean());
	}
	
	protected void fillRooms()
	{
		for(int i = 0; i < Settings.LINE_NUMBER; i++)
		{
			for (int j = 0; j < Settings.COLUMN_NUMBER; j++)
			{
				Element myElement = new Element();
				this.rooms[i][j] = myElement;
			}
		}
	}
	/*
	 * display configuration of manor
	 */
	public void showCard()
	{
		
		for(int i = 0; i < Settings.LINE_NUMBER; i++)
		{
			
			for(int j = 0; j < Settings.COLUMN_NUMBER; j++)
			{
				Rectangle room = new Rectangle(Settings.W_WIDTH/Settings.LINE_NUMBER, Settings.W_HEIGHT/Settings.COLUMN_NUMBER);
				room.setX(i * Settings.W_WIDTH/Settings.LINE_NUMBER);
				room.setY(j * (Settings.W_HEIGHT/Settings.COLUMN_NUMBER));
				room.setFill(Color.TRANSPARENT);
			    room.setStroke(Color.BLACK);		    
			    this.getChildren().add(room);
			    
			    if(rooms[i][j].getSize() != 0) //cel has element
			    {
			    	//System.out.println("element");
			    	ArrayList<Integer> celContent = this.rooms[i][j].getContent();
			    	Iterator<Integer> content = celContent.iterator();
			    	if(content.hasNext()) //check content type
			    	{
			    		Integer el = content.next();
			    		if(el == 0) //dust
			    		{
			    			displayDust(room.getX(), room.getY());
			    		}
			    		else // diamond
			    		{
			    			displayDiamond(room.getX(), room.getY());
			    		}
			    	}
			    } 		    
			}
							
		}
	} 
	protected void displayDust(double posX, double posY)
	{
		Circle dust = new Circle();
	    dust.setCenterX(posX + 20);
	    dust.setCenterY(posY + 20);
	    dust.setRadius(10);
	    dust.setFill(Color.BLACK);
	    this.getChildren().add(dust);
	}
	protected void displayDiamond(double posX, double posY)
	{
		Circle diamond = new Circle();
	    diamond.setCenterX(posX + 40);
	    diamond.setCenterY(posY + 40);
	    diamond.setRadius(10);
	    diamond.setFill(Settings.COLOR_LIST[new Random().nextInt(Settings.COLOR_LIST.length)]);
	    this.getChildren().add(diamond);
	}
	protected boolean isManorClean()
	{
		for(int i = 0; i < Settings.LINE_NUMBER; i++)
		{
			
			for(int j = 0; j < Settings.COLUMN_NUMBER; j++)
			{
				if (rooms[i][j].getSize() != 0) {return false;}
			}
		}
		return true;
	}
}


