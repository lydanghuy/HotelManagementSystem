package hotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShowReservationWindowController {

    @FXML
    private ImageView proposal;
    
    @FXML
    private ImageView logo;
    
    @FXML
    private TextField Roomtype;

    @FXML
    private TextField ArrivalDate;

    @FXML
    private TextField BedType;

    @FXML
    private Label total_fee;

    @FXML
    private TextField Adults;

    @FXML
    private TextField DepartureDate;

    @FXML
    private TextField Children;

    @FXML
    private TextField RoomNumber;

    @FXML
    private TextField R_ID;

    @FXML
    private TextField Name;
    
    @FXML
    private void initialize(){
    	Image img, img2;
    	img=new Image("file:src/hotel/image/proposal.jpg");
    	proposal.setImage(img);
    	img2=new Image("file:src/hotel/image/nw.png");
    	logo.setImage(img2);
    }
    
    public void setInfo(String RID, String room_no, String room_type, String bed_type, String first_name, String last_name,
    						String arrival_date, String departure_date, String adults, String children, String total_cost ){
    	
    	
    	R_ID.setText(RID);
    	RoomNumber.setText(room_no);
    	Roomtype.setText(room_type);
    	BedType.setText(bed_type);
    	Name.setText(first_name +" "+ last_name);
    	ArrivalDate.setText(arrival_date);
    	DepartureDate.setText(departure_date);
    	Adults.setText(adults);
    	Children.setText(children);
    	total_fee.setText(total_cost + " USD");
    }
}
