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
	
	Element [][] rooms = new Element[Settings.COLUMN_NUMBER][Settings.LINE_NUMBER];
	Element [][] emptyRooms = new Element[Settings.COLUMN_NUMBER][Settings.LINE_NUMBER];
	
	public Manor(){
		
		fillRooms();
		emptyRooms = rooms;
		showCard();
	}
	
	public void run() {
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(Settings.ENV_DELAY), ev -> {
			int lineIndex = new Random().nextInt(Settings.LINE_NUMBER); // line random choice
			int colIndex = new Random().nextInt(Settings.COLUMN_NUMBER); // column random choice
			int component = new Random().nextInt(3); // element random choice (0: dust, 1: diamond, 2:nothing)
			if (component != 2) // dust or diamond
			{
				if(!this.rooms[colIndex][lineIndex].getContent().contains(component)) //check content
				{
					this.rooms[colIndex][lineIndex].addContent(component);
				}
			}
			//diplay rooms		//this.showEmpty();
			
			this.showCard();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
			
			
	}
	
	protected void fillRooms()
	{
		for(int i = 0; i < Settings.COLUMN_NUMBER; i++)
		{
			for (int j = 0; j < Settings.LINE_NUMBER; j++)
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
		System.out.println("-----Manor-----");
		this.getChildren().clear();
		for(int col = 0; col < Settings.COLUMN_NUMBER; col++)
		{
			
			for(int line = 0; line < Settings.LINE_NUMBER; line++)
			{
				System.out.print(rooms[col][line].getContent() + "-");
				Rectangle room = new Rectangle(Settings.W_WIDTH/Settings.COLUMN_NUMBER, Settings.W_HEIGHT/Settings.LINE_NUMBER);
				room.setX(col * Settings.W_WIDTH/Settings.COLUMN_NUMBER);
				room.setY(line * (Settings.W_HEIGHT/Settings.LINE_NUMBER));
				room.setFill(Color.TRANSPARENT);
			    room.setStroke(Color.BLACK);		    
			    this.getChildren().add(room);
			    
			    if(rooms[col][line].getSize() != 0) //cel has element
			    {
			    	ArrayList<Integer> celContent = this.rooms[col][line].getContent();
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
			System.out.println("");
							
		}
	}
	public void showEmpty()
	{
		
		for(int col = 0; col < Settings.COLUMN_NUMBER; col++)
		{
			
			for(int line = 0; line < Settings.LINE_NUMBER; line++)
			{
				//System.out.print(rooms[col][line].getContent() + "-");
				Rectangle room = new Rectangle(Settings.W_WIDTH/Settings.COLUMN_NUMBER, Settings.W_HEIGHT/Settings.LINE_NUMBER);
				room.setX(col * Settings.W_WIDTH/Settings.COLUMN_NUMBER);
				room.setY(line * (Settings.W_HEIGHT/Settings.LINE_NUMBER));
				room.setFill(Color.TRANSPARENT);
			    room.setStroke(Color.BLACK);		    
			    this.getChildren().add(room);
			    
			    		    
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
	    dust.setRadius(15);
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
	public boolean isManorClean()
	{
		for(int i = 0; i < Settings.COLUMN_NUMBER; i++)
		{
			
			for(int j = 0; j < Settings.LINE_NUMBER; j++)
			{
				if (rooms[i][j].getSize() != 0) {return false;}
			}
		}
		return true;
	}
	/*
	 * return rooms
	 * @ensure this.rooms
	 */
	public Element[][] getRooms()
	{
		return this.rooms;
	}
	
	//
	public Element getRoomsElement(int elementX, int elementY)
	{
		return this.rooms[elementX][elementY];
	}
	
	/*delete cel content
	 * @input : lineIndex, colIndex
	 * @require lineIndex < Settings.LINE_NUMBER
	 * @require colIndex < Settings.COL_NUMBER
	 */
	
	public  void delRoomContent(int colIndex, int lineIndex)
	{
		//this.rooms[colIndex][lineIndex].content.clear();
		System.out.println(rooms[colIndex][lineIndex].getContent());
		this.rooms[colIndex][lineIndex] = new Element();
		System.out.println("del " + colIndex + ":" + lineIndex);
		System.out.println(rooms[colIndex][lineIndex].getContent());
		
	}
}


