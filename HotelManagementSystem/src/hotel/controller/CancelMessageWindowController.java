package hotel.controller;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CancelMessageWindowController {

    @FXML
    private TextField ReserveID;

    @FXML
    private TextField ArrivalDate;

    @FXML
    private TextField DepartureDate;

    @FXML
    private TextField RoomNumber;

    @FXML
    private TextField Name;
    
    @FXML
    private ImageView logo;
    
    @FXML
    private void initialize(){
    	Image img;
    	img=new Image("file:src/hotel/image/nw.png");
    	logo.setImage(img);
    }
    public void setInfo(String RID, String room_number, String first_name, String last_name, String arrival_date, String departure_date){
    	ReserveID.setText(RID);
    	RoomNumber.setText(room_number);
    	Name.setText(first_name +" "+ last_name);
    	ArrivalDate.setText(arrival_date);
    	DepartureDate.setText(departure_date);
    	
    }
}
