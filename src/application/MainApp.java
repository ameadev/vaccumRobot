package application;



import environment.Manor;
import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        Manor myManor = new Manor();
        root.getChildren().add(myManor);
        //myManor.showCard();
        myManor.run();
        ScheduledService<Void> backTask = new ScheduledService<Void>(){

			  @Override
			  protected Task<Void> createTask() {
			    return new Task<Void>(){

			     @Override
				     protected Void call() throws Exception {
			    	
			 			//myManor.run();
			 		
			    	 return null;
			      }
			    };
			  }
			};
			
		backTask.setDelay(Duration.seconds(0));
		backTask.setPeriod(Duration.seconds(5));
		backTask.start();
	}       
        


	
	public static void main(String[] args) {
		launch(args);

	}

}
