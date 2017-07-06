package hotel.controller;

import java.io.IOException;

import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminWindowController {

//	private MainWindowController mainwindow;
//    public void setMainApp(MainWindowController mainwindow) {
//        this.mainwindow = mainwindow;
//    }
    
	private Stage stage = new Stage();
//	@FXML
//    private AnchorPane AdminStage;
	
    @FXML
    private Label missUsername;

    @FXML
    private TextField Username;

    @FXML
    private Button Cancel;

    @FXML
    private Label missPassword;

    @FXML
    private Button Login;

    @FXML
    private PasswordField Password;

    @FXML
    private Label incorrectInput;
    
    private String username = "admin";
    private String password = "0pass";
    
    
    @FXML
    void getUsername(ActionEvent event) {
    	 
    }

    @FXML
    void getPassword(ActionEvent event) {
    	 
    }

    @FXML
    void goLogin(ActionEvent event) throws IOException, InterruptedException {
    
    		
    		
    		if(Username.getText().equals(username) && Password.getText().equals(password)){
    			try{
    	    		
    	    		Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource("/hotel/UI/RoomStatusWindow.fxml"));
    	    		stage.setScene(new Scene(mainPane));
    	    		stage.setTitle("ROOM STATUS");
    	    		stage.show();	
    	    		
    	    	} catch (Exception e){
    	    		e.printStackTrace();
    	    	}
    		}
    		else{
    			
    			incorrectInput.setText("Incorrect username/password. Please try again.");
    			
    		}
    	   	
    	 
    }
    	
    
    
    @FXML
    void goCancel(ActionEvent event) throws IOException {
    	
    	stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
    }

}

