package environment;

import java.util.ArrayList;
import java.util.Random;
import application.Settings;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;



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
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(Settings.ENV_DELAY), ev -> {
			int lineIndex = new Random().nextInt(Settings.LINE_NUMBER); // line random choice
			int colIndex = new Random().nextInt(Settings.COLUMN_NUMBER); // column random choice
			int component = new Random().nextInt(3); // element random choice (0: dust, 1: diamond, 2:nothing)
			if (component != 2) // dust or diamond
			{
				if(!this.rooms[lineIndex][colIndex].getContent().contains(component)) //check content
				{
					this.rooms[lineIndex][colIndex].addContent(component);
				}
			}
			//diplay rooms			
			this.showCard();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
			
			
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
		
		for(int line = 0; line < Settings.LINE_NUMBER; line++)
		{
			
			for(int col = 0; col < Settings.COLUMN_NUMBER; col++)
			{
				Rectangle room = new Rectangle(Settings.W_WIDTH/Settings.LINE_NUMBER, Settings.W_HEIGHT/Settings.COLUMN_NUMBER);
				room.setX(line * Settings.W_WIDTH/Settings.LINE_NUMBER);
				room.setY(col * (Settings.W_HEIGHT/Settings.COLUMN_NUMBER));
				room.setFill(Color.TRANSPARENT);
			    room.setStroke(Color.BLACK);		    
			    this.getChildren().add(room);
			    
			    if(rooms[line][col].getSize() != 0) //cel has element
			    {
			    	ArrayList<Integer> celContent = this.rooms[line][col].getContent();
			    	for(int i=0; i < celContent.size(); i++) //check element
			    	{
			    		if(celContent.get(i) == 0) //dust
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
	/*
	 * create and display manor
	 */
	protected void displayDust(double posX, double posY)
	{
		Circle dust = new Circle();
	    dust.setCenterX(posX + 20);
	    dust.setCenterY(posY + 20);
	    dust.setRadius(10);
	    dust.setFill(Color.BLACK);
	    this.getChildren().add(dust);
	}
	/*
	 * create and display diamond 
	 */
	protected void displayDiamond(double posX, double posY)
	{
		Circle diamond = new Circle();
	    diamond.setCenterX(posX + 40);
	    diamond.setCenterY(posY + 40);
	    diamond.setRadius(10);
	    diamond.setFill(Settings.COLOR_LIST[new Random().nextInt(Settings.COLOR_LIST.length)]);
	    this.getChildren().add(diamond);
	}
	/*
	 * @return true if the manor is clean, false else
	 */
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
	/*
	 * return rooms
	 */
	public Element[][] getRooms()
	{
		return this.rooms;
	}
	
	
}


