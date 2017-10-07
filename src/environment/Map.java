package environment;

import java.util.Random;

import application.Settings;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Map extends Parent  {
	
	public void showCard()
	{
		
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
			Rectangle room = new Rectangle(Settings.W_WIDTH/10, Settings.W_HEIGHT/10);
			room.setX(i * Settings.W_WIDTH/10);
			room.setY(j * (Settings.W_HEIGHT/10));
			room.setFill(Color.TRANSPARENT);

		    room.setStroke(Color.BLACK);
		    this.getChildren().add(room);
		    Circle dust = new Circle();
		    dust.setCenterX(room.getX()+20);
		    dust.setCenterY(room.getY()+20);
		    dust.setRadius(10);
		    dust.setFill(Color.BLACK);
		    Circle diamond = new Circle();
		    diamond.setCenterX(room.getX()+40);
		    diamond.setCenterY(room.getY()+40);
		    diamond.setRadius(10);
		    diamond.setFill(Settings.COLOR_LIST[new Random().nextInt(Settings.COLOR_LIST.length)]);
		    this.getChildren().add(dust);
		    this.getChildren().add(diamond);
			}
							
		}
	} 
}
	
