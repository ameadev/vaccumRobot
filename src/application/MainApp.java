package application;



import environment.Card;
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
        Card myCard = new Card();
        root.getChildren().add(myCard);
        myCard.showCard();

}
	
	public static void main(String[] args) {
		launch(args);

	}

}
