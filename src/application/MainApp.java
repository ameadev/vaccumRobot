package application;

import agent.Agent;
import environment.Manor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class MainApp extends Application {
	Group root;	
	
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
        Thread robotThread = new Thread(new Agent(myManor));
        robotThread.start();           
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
