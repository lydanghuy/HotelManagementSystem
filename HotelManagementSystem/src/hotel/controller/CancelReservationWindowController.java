package hotel.controller;

import java.sql.SQLException;
import java.util.List;

import hotel.database.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CancelReservationWindowController {
	private Stage primaryStage = new Stage();
    @FXML
    private TextField ReserveID;

    @FXML
    private Button cancelbutton;

    @FXML
    private Label status;
    
    @FXML
    private TextField LastName;
    @FXML
    private TextField RoomNumber;

    private static String R_ID, room_number,first_name,last_name,arrival_date,departure_date;
    private static int RID, room_no, result, delete;
    
    @FXML
    void cancelReservation(ActionEvent event) throws SQLException {
    	DBAccess dbaccess = new DBAccess();
    	
    	status.setText("");
    	
    	if(ReserveID.getText().matches("[0-9]+")){
    	
    		RID = Integer.parseInt(ReserveID.getText());
    	}else{
    		RID = -1;
    		status.setText("Incorrect Reservation ID/ Room Number/ Last Name. Please try again.");
    	}
    	
    	if(RoomNumber.getText().matches("[0-9]+")){
    	
    		room_no = Integer.parseInt(RoomNumber.getText());
    	}else {
    		room_no = -1;
    		status.setText("Incorrect Reservation ID/ Room Number/ Last Name. Please try again.");
    	}
    	
    	if(LastName.getText().isEmpty()){
    		status.setText("Incorrect Reservation ID/ Room Number/ Last Name. Please try again.");
    	}else{
    		last_name = LastName.getText();
    	}
    	
    	if(RID > 99999 && room_no > 0 && last_name!=null){ //because RID is 6 digit code
    		
    		result = dbaccess.checkIdentity(RID, room_no, last_name);
    	}
    	
    	if(result == 1){
    		
	    	try{
	    		
	    		List<String> checklist = dbaccess.checkreservation(RID);
	    		
	    		FXMLLoader loader = new FXMLLoader();
	    		
				Pane mainPane = loader.load(getClass().getResource("/hotel/UI/CancelMessageWindow.fxml").openStream());
				CancelMessageWindowController info = new CancelMessageWindowController();
				info = loader.getController();
				
				R_ID = Integer.toString(RID);
				room_number = Integer.toString(room_no);
				
				first_name = checklist.get(2);
				arrival_date = checklist.get(3);
				departure_date = checklist.get(4);
				
				info.setInfo(R_ID, room_number, first_name, last_name, arrival_date, departure_date);	
				
				
				primaryStage.setScene(new Scene(mainPane));
				primaryStage.setTitle("CANCEL RESERVATION");
				primaryStage.show();	
			
			} catch (Exception e){
				e.printStackTrace();
			}
	    	
	    	//delete in database
	    	 dbaccess.delete_rows(RID);
	    	
    	}else{
    		status.setText("Incorrect Reservation ID/ Room Number/ Last Name. Please try again.");
    	}
    	
    }
}
