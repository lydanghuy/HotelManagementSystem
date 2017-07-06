package hotel.controller;

import java.sql.SQLException;


import java.util.Random;


import hotel.database.DBAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ReservationInfoWindowController {

	@FXML
    private ImageView logo;
	
    @FXML
    private TextField No_of_Adults;

    @FXML
    private TextField BedType;

    @FXML
    private TextField Name;

    @FXML
    private TextField No_of_Children;

    @FXML
    private TextField RoomType;

    @FXML
    private TextField ReservationID;

    @FXML
    private TextField ArrivalDate;
    
    @FXML
    private Label status;
    
    @FXML
    private Label no_of_days;

    @FXML
    private Label total_fee;

    @FXML
    private Button finish;

    @FXML
    private TextField DepartureDate;

    @FXML
    private Label price_per_day;

    @FXML
    private TextField RoomNumber;

    private static String  first_name, last_name, Room_Type, Bed_Type, Arrival_Date, Departure_Date, Adults, Children,
    					    country, email, R_ID, day_no, price_a_day, total_in_USD;
    private static int room_no, age;
    public int RID, room_number,  price;
    private static double total;
    public long number_of_days;
    
    @FXML
    public void initialize() throws SQLException{
    	DBAccess dbaccess = new DBAccess();
    	Image img;
    	img=new Image("file:src/hotel/image/nw.png");
    	logo.setImage(img);
    	
    	//Create Reservation ID for Guest
    	Random rand = new Random();
		RID = rand.nextInt(900000) + 100000;
		int check = dbaccess.checkReservationID(RID);
		while(check == 0){
			RID = rand.nextInt(900000) + 100000;
			check = DBAccess.checkReservationID(RID);
		}
		
		if(check == 1){
			R_ID = Integer.toString(RID);
			ReservationID.setText(R_ID);
		}
		
		RoomType.setText(GuestInfoWindowController.getRoomType());
    	BedType.setText(GuestInfoWindowController.getBedType());
    	ArrivalDate.setText(GuestInfoWindowController.getArrivalDate());
    	DepartureDate.setText(GuestInfoWindowController.getDepartureDate());
    	No_of_Adults.setText(GuestInfoWindowController.getAdults());
    	No_of_Children.setText(GuestInfoWindowController.getChildren());
    	
    	//Bill zone
    	if(GuestInfoWindowController.getRoomType().equals("Standard")){
    		if(GuestInfoWindowController.getBedType().equals("Single"))		price_per_day.setText("100");
    		if(GuestInfoWindowController.getBedType().equals("Twins"))		price_per_day.setText("120");
    		if(GuestInfoWindowController.getBedType().equals("Double"))		price_per_day.setText("150");
    
    	} else if(GuestInfoWindowController.getRoomType().equals("Deluxe")){
    		if(GuestInfoWindowController.getBedType().equals("Single")) 	price_per_day.setText("200");
    		if(GuestInfoWindowController.getBedType().equals("Twins")) 		price_per_day.setText("220");
    		if(GuestInfoWindowController.getBedType().equals("Double")) 	price_per_day.setText("250");
    		
    	} else{
    		if(GuestInfoWindowController.getBedType().equals("Single")) 	price_per_day.setText("300");
    		if(GuestInfoWindowController.getBedType().equals("Twins")) 		price_per_day.setText("320");
    		if(GuestInfoWindowController.getBedType().equals("Double"))	 	price_per_day.setText("350");
    	}
		
    	day_no = Long.toString(GuestInfoWindowController.getTotalDays());
    	no_of_days.setText(day_no + " days");
    	price_a_day = price_per_day.getText();
    	price = Integer.parseInt(price_a_day);
    	
    	ReservationInfoWindowController.total = (double) GuestInfoWindowController.getTotalDays() * (double) price;
    	total_in_USD = Double.toString(total);
    	total_fee.setText(total_in_USD);
    	
    }
  
    public void setName(String first_name, String last_name){
    	ReservationInfoWindowController.first_name = first_name;
    	ReservationInfoWindowController.last_name = last_name;
    	Name.setText(first_name +" "+ last_name);
    	
    }
    public void setRoomNumber(String room_number){
    	ReservationInfoWindowController.room_no = Integer.parseInt(room_number);
    	RoomNumber.setText(room_number);
    	
    }
    public void setAge_Country_Email(String age, String country, String email){
    	ReservationInfoWindowController.age = Integer.parseInt(age);
    	ReservationInfoWindowController.country = country;
    	ReservationInfoWindowController.email = email;
    }
   
   
   
    @FXML
    void goFinish(ActionEvent event) throws SQLException {
    	DBAccess dbaccess = new DBAccess();
    	int result = dbaccess.insert_rows(first_name, last_name, age, country, email, RID, room_no
    				,GuestInfoWindowController.getArrivalDate(), GuestInfoWindowController.getDepartureDate()
    				, GuestInfoWindowController.getAdults(), GuestInfoWindowController.getChildren(), total);
    		
    		if(result == 1) status.setText("SUCCESSFUL RESERVATION");
    		else status.setText("SYSTEM ERROR");
    		
    		
    }

}
