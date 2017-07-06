package hotel.controller;

import java.io.IOException;



import hotel.MainWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindowController {

	
	private Stage primaryStage = new Stage();
	
	   @FXML
	    private Button nextimage1;

	   
	    @FXML
	    private Button nextimage2;

	    @FXML
	    private Button nextimage3;

	    @FXML
	    private Button nextimage4;

	    @FXML
	    private Button nextimage5;
	    @FXML
	    private ImageView logo;
	    @FXML
	    private ImageView background;
		@FXML
		private Button checkbookingButton;
		@FXML
	    private Button adminButton;
	    @FXML
	    private Button reserveButton;
	    @FXML
	    private Button roomButton;
	    @FXML
	    private Button cancelButton;
	    
	@FXML
	private void goNextImage(ActionEvent event) {
		 Image img;
		 if(event.getSource()==nextimage1) 
	        { 
	        	img=new Image("file:src/hotel/image/main1.jpg");
	        	
	        }
		 else if(event.getSource()==nextimage2) 
	        { 
	        	img=new Image("file:src/hotel/image/main2.jpg");
	        	
	        }
		 else if(event.getSource()==nextimage3) 
	        { 
	        	img=new Image("file:src/hotel/image/main3.jpg");
	        	
	        }
		 else if(event.getSource()==nextimage4) 
	        { 
	        	img=new Image("file:src/hotel/image/pool.jpg");
	        	
	        }
		 else if(event.getSource()==nextimage5) 
	        { 
	        	img=new Image("file:src/hotel/image/gym.jpg");
	        	
	        }
		 else{
			 img=null;
		 }
		 background.setImage(img);
	}

	    
	    
	    
	    
	    
    @FXML
     private void goReservation(ActionEvent event) throws IOException {
    	//		main_window.showReservation();
    	
    	
    	try{
    		
    		Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource("/hotel/UI/ReservationWindow.fxml"));
    		primaryStage.setScene(new Scene(mainPane));
    		primaryStage.setTitle("RESERVATION");
    		primaryStage.show();	
    		
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    }

    @FXML
    private void viewRoom(ActionEvent event) throws IOException {
    	
    	try{
    		
    		
    		Parent root = FXMLLoader.load(getClass().getResource("/hotel/UI/RoomIntroWindow.fxml"));
    		Scene scene = new Scene(root);
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("ROOM INFORMATION");
    		primaryStage.show();	
    		
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    }

    @FXML
    private void accessAdmin(ActionEvent event) {
    	try{
    		
			Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource("/hotel/UI/AdminWindow.fxml"));
			primaryStage.setScene(new Scene(mainPane));
			primaryStage.setTitle("ADMINISTRATION");
			primaryStage.show();	
		
		} catch (Exception e){
			e.printStackTrace();
	}
    }
    
    @FXML
    private void cancelResrvation(ActionEvent event) {
    	
    		try{
    		
    			Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource("/hotel/UI/CancelReservationWindow.fxml"));
    			primaryStage.setScene(new Scene(mainPane));
    			primaryStage.setTitle("CANCEL RESERVATION");
    			primaryStage.show();	
    		
    		} catch (Exception e){
    			e.printStackTrace();
    	}
    }
    
    @FXML
    private void goCheckBooking(ActionEvent event) {
    	try{
    		
			Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource("/hotel/UI/CheckReservationWindow.fxml"));
			primaryStage.setScene(new Scene(mainPane));
			primaryStage.setTitle("CHECK RESERVATION");
			primaryStage.show();	
		
		} catch (Exception e){
			e.printStackTrace();
		}
    }
    
    public void closeStage() throws IOException{
		primaryStage.close();
	}

}


