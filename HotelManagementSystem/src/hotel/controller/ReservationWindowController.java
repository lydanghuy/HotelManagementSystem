package hotel.controller;

import javafx.event.ActionEvent;



import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.IOException;
import java.sql.*;
import java.time.format.DateTimeFormatter;


import hotel.Management;
import hotel.database.DBAccess;

import java.time.LocalDate;

public class ReservationWindowController {

	private Stage primaryStage = new Stage();
    @FXML
    private ChoiceBox<String> RoomType;

    @FXML
    private DatePicker arrival;

    @FXML
    private TextField children;

    @FXML
    private ChoiceBox<String> BedType;

    @FXML
    private TextField adults;

    @FXML
    private DatePicker departure;

    @FXML
    private Button check;
    
    @FXML
    private Label warning;
    
    @FXML
    private Label missArrival;
    
    @FXML
    private Label missAdults;

    @FXML
    private Label missDeparture;
    @FXML
    private Label wrongFormatChild;

    
    public String room_type, bed_type;
    private String adult , child ;
    public int no_adults , no_children , result;
    public long no_of_days;
    public LocalDate input_arrival, input_departure;
    public String arrival_date, departure_date;
    
    @FXML
    private void initialize(){
    	//initialize room types
    	
    	
    	RoomType.getItems().add("Standard");
    	RoomType.getItems().add("Deluxe");
    	RoomType.getItems().add("Suite");
    	
    	//set default room type to Standard
    	RoomType.setValue("Standard");
	    
	    //initialize bed types
    	BedType.getItems().add("Single");
    	BedType.getItems().add("Twins");
    	BedType.getItems().add("Double");
    	
    	//set default bed type to Single
	    BedType.setValue("Single");
	    
	   
    }
   

    @FXML
    public void checkAvailability(ActionEvent event) throws IOException, SQLException {
    	
    	DBAccess dbaccess = new DBAccess();
    	room_type = RoomType.getValue();
    	
    	bed_type = BedType.getValue();
    	
    	input_arrival = arrival.getValue();
    	
    	input_departure = departure.getValue();
    	
    	if(input_arrival != null && input_departure != null ){
    		no_of_days = ChronoUnit.DAYS.between(input_arrival, input_departure);
    	}
    	
    	adult = adults.getText();
    	if(adult.isEmpty()) adult = "0";
    	
    	if(adult.matches("[0-9]+")) {
    		no_adults = Integer.parseInt(adult); //user input number
    	} else{
    		no_adults = -1; // user input character (not a number)
    	}
    	
    	
    	child = children.getText();
    	if(child.isEmpty()) child = "0"; //number of children is not required, default is 0
    	if(child.matches("[0-9]+")){     //if users have children, they have to input a number
    		no_children = Integer.parseInt(child);
    	}else{
    		no_children = -1;
    	}
    	
    	missArrival.setText("");
    	missDeparture.setText("");
    	missAdults.setText("");
    	warning.setText("");
    	wrongFormatChild.setText("");
    	
		if(!room_type.isEmpty() && !bed_type.isEmpty() && input_arrival!=null && input_departure!=null && no_adults>0 
				&& no_children >=0 && input_departure.isAfter(input_arrival) ){
			 result = dbaccess.search_Available_Room(room_type,bed_type,input_arrival,input_departure);	
		}
    	
    	
    	if(result == 1){
    		try{
    		
    			Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource("/hotel/UI/GuestInfoWindow.fxml"));
    			
    			GuestInfoWindowController info = new GuestInfoWindowController();
    			info.setInfo(room_type, bed_type, input_arrival.toString(), input_departure.toString(), adult, child, no_of_days);
    			
    			primaryStage.setScene(new Scene(mainPane));
    			primaryStage.setTitle("GUEST PEERSONAL INFORMATION");
    			primaryStage.show();	
    			

    			
    			
    			
    			
    		} catch (Exception e){
    			e.printStackTrace();
    		}
    	
    	} else if(result == 0 && !room_type.isEmpty() && !bed_type.isEmpty() && input_arrival!=null && input_departure!=null 
    			&& no_adults>0 && no_children >=0 && input_departure.isAfter(input_arrival)){
    		
    		try{
        		
    			Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource("/hotel/UI/FailReservation.fxml"));
    			primaryStage.setScene(new Scene(mainPane));
    			primaryStage.setTitle("NO AVAILABLE ROOMS");
    			primaryStage.show();	
    		
    		} catch (Exception e){
    			e.printStackTrace();
    		}
    	}
    	else{
    		if(input_arrival == null) missArrival.setText("Required");
    		if(input_departure == null) missDeparture.setText("Required");
    		if(no_adults == 0) missAdults.setText("Required");
    		if (no_adults == -1) missAdults.setText("Must be a number");
    		if (no_children == -1) wrongFormatChild.setText("Must be a number");
    		if(input_arrival != null && input_departure != null ){
    			if(input_departure.isBefore(input_arrival) || input_departure.isEqual(input_arrival)) 
    			{
    				warning.setText("Departure Date must be after Arrival Date.");
    			}
    		}
    	}
    	
    }
    	
}
