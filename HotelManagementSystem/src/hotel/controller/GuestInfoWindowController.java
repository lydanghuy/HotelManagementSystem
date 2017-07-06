package hotel.controller;


import hotel.database.DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import hotel.Management;
import hotel.controller.ReservationInfoWindowController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.controlsfx.control.textfield.TextFields;

public class GuestInfoWindowController {

	private Stage primaryStage = new Stage();
    @FXML
    private Label missFirstName;

    @FXML
    private TextField Email;

    @FXML
    public TextField FirstName;

    @FXML
    public ChoiceBox<Integer> AvailableRoom;

    @FXML
    private Label missEmail;

    @FXML
    private TextField Country;

    @FXML
    private Button Confirm;

    @FXML
    private Label missLastName;

    @FXML
    public TextField LastName;

    @FXML
    private TextField Age;

    @FXML
    private Label missAge;

    @FXML
    private Label missCountry;

    public String first_name, last_name, age, country, email, room_number;
    public int years_old, room_no;
    private static String room_type, bed_type, arrival_date, departure_date, Adults, Children; 
    private static long total_days;
    
    @FXML
    public void initialize(){
    	//show list of available rooms
    	for(int i: DBAccess.room_list){
    		AvailableRoom.getItems().add(i);
    		
    	}
    	//set default value of room number
    	AvailableRoom.setValue(DBAccess.room_list.get(0));
    	
    	String[] locales = Locale.getISOCountries(); //Locale.getISOCountries() will return a list of all countries
		
		List <String> countrynames = new ArrayList<String>();
		for (String countryname : locales) {
			
		    Locale nameofcountry = new Locale("", countryname);
		    countrynames.add(nameofcountry.getDisplayCountry());

		 }
		TextFields.bindAutoCompletion(Country, countrynames);
    	
    }
    
    public void setInfo(String roomtype, String bedtype, String arrival, String departure, String adults, String children, long no_of_days){
    	GuestInfoWindowController.room_type = roomtype;
    	GuestInfoWindowController.bed_type = bedtype;
    	GuestInfoWindowController.arrival_date = arrival;
    	GuestInfoWindowController.departure_date = departure;
    	GuestInfoWindowController.Adults = adults;
    	GuestInfoWindowController.Children = children;
    	GuestInfoWindowController.total_days = no_of_days;
		
    }
    public static String getRoomType() {
        return room_type;
    }
    
    public static String getBedType() {
        return bed_type;
    }
    
    public static String getArrivalDate() {
        return arrival_date;
    }
    
    public static String getDepartureDate() {
        return departure_date;
    }
    
    public static String getAdults() {
        return Adults;
    }
    
    public static String getChildren() {
        return Children;
    }
    
    public static long getTotalDays() {
        return total_days;
    }
    
    @FXML
    public void ConfirmReservation(ActionEvent event) {
    		missFirstName.setText("");
    		missLastName.setText("");
    		missAge.setText("");
    		missCountry.setText("");
    		missEmail.setText("");
    		
    		room_no = AvailableRoom.getValue();
    		room_number = Integer.toString(room_no);
    		
    		first_name = FirstName.getText();
    		last_name = LastName.getText();
    		
    		age = Age.getText();
    		if(age.isEmpty()) age = "0";
    		if(age.matches("[0-9]+")) {
    			years_old = Integer.parseInt(age);
    		} else{
    			years_old = -1;
    		}
    		
    		
    		
    		country = Country.getText();
    		
    		email = Email.getText();
    		
    		if(!first_name.isEmpty() && !last_name.isEmpty() && years_old>17 && !country.isEmpty() && !email.isEmpty() ){
    			
    			try{
    				
    				FXMLLoader loader = new FXMLLoader();
        			Pane mainPane = loader.load(getClass().getResource("/hotel/UI/ReservationInfoWindow.fxml").openStream());
        			
        			
        			ReservationInfoWindowController info = new ReservationInfoWindowController();
        			info = loader.getController();
        			
        			
        			info.setName(first_name, last_name);
        			info.setRoomNumber(room_number);
        		    info.setAge_Country_Email(age, country, email);
        		    
        		    
        			primaryStage.setScene(new Scene(mainPane));
        			primaryStage.setTitle("RESERVATION INFORMATION");
        			primaryStage.show();	
    		
        		} catch (Exception e){
        			e.printStackTrace();
        		}
    		}
    		else{
    			if(first_name.isEmpty()) missFirstName.setText("Required");
    			
    			if(last_name.isEmpty()) missLastName.setText("Required");
    			
    			if(age.equals("0")) missAge.setText("Required");
    			
    			if(!age.equals("0")){
    				if(years_old < 18) missAge.setText("Age >=18");
    				if(years_old == -1) missAge.setText("Must be a number");
    			}
    			
    			if(country.isEmpty()) missCountry.setText("Required");
    			
    			if(email.isEmpty()) missEmail.setText("Required");
    		
    		}
    		
    }


}
