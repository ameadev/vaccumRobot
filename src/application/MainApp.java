package application;

import java.lang.reflect.InvocationTargetException;

import agent.Agent;
import environment.Manor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class MainApp extends Application {
	Group root;	
	static int algoChoice = 0;
	
	@Override
	public void start(Stage primaryStage) {
		root = new Group();
        Scene scene = new Scene(root, Settings.W_WIDTH, Settings.W_HEIGHT);
        scene.setFill(Color.WHITE);
        primaryStage.setTitle(Settings.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
        Manor myManor = new Manor(); //create manor
        root.getChildren().add(myManor); 
        myManor.run(); // start manor's life
        
        try {

			Thread.sleep(5000); //sleep 1 second before start robot

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        //robot's thread
        Thread robotThread = new Thread(new Agent(myManor,algoChoice));
        robotThread.start();           
	}
	
	public static void main(String[] args) throws InvocationTargetException {
		int tt = 0;
		try 
		{
			tt = args.length;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("nmbxja");
			e.printStackTrace();
		}
		
		if (tt == 0) 
		{
			algoChoice = 0;
			System.out.println("Exécuter en mettant 1 ou 2 en argument");
			System.out.println("1 - Algorithme non informé BFS");
			System.out.println("2 - Algorithme informé Greedy Search");
		}
		else 
		{
			algoChoice = Integer.parseInt(args[0]);
			if (Integer.parseInt(args[0]) == 1)
			{
				launch(args);
			}
			else if (Integer.parseInt(args[0]) == 2)
			{
				launch(args);
			}
			else 
			{
				algoChoice = 0;
				System.out.println("Exécuter en mettant 1 ou 2 en argument");
				System.out.println("1 - Algorithme non informé BFS");
				System.out.println("2 - Algorithme informé Greedy Search");
			}
		
		}
		
	}
	
	
}
