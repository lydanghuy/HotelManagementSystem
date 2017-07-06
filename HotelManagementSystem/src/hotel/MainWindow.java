package hotel;

import java.io.IOException;


import hotel.controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindow  extends Application {

	//private static Stage primaryStage;

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//MainWindow.primaryStage = primaryStage;
		Pane mainPane = (Pane) FXMLLoader.load(MainWindow.class.getResource("UI/MainWindow.fxml"));
		
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.setTitle("WELCOME TO NEW WORLD HOTEL");
		
		primaryStage.show();	
		
		
	}
	
	
	
	public static void main(String[] args){
		Application.launch(args);
	}


}
