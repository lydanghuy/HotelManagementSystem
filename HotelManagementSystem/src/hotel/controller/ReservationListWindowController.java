package hotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ReservationListWindowController {

    @FXML
    private TextField Total_Fee;

    @FXML
    private TextField name;

    @FXML
    private TextField RID;

    @FXML
    private TextField BillID;
    
    @FXML
    private void initialize(){
    	
    	
    }
    public void setData(String R_ID, String first_name, String last_name, String Bill, String total_fee){
    	RID.setText(R_ID);
    	name.setText(first_name + " " + last_name);
    	BillID.setText(Bill);
    	Total_Fee.setText(total_fee);
    }
    
}

