package hotel.controller;

import java.net.*;
import java.util.*;

import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import hotel.Management;
import hotel.database.DBAccess;
import hotel.database.DBUtil;


public class RoomStatusWindowController  {

	private Stage subStage = new Stage();
	private Stage stage = new Stage();
	@FXML
	private Button LoadButton;
	@FXML
    private Label missSelect;

	@FXML
	private Button logoutButton;
	@FXML
	 private Label totalguest;
	
	 @FXML
	private TableView<Management> RoomStatus;
	 
	@FXML
	private TableColumn<Management, Integer> guestID;
	
	@FXML
    private TableColumn<Management, Integer> RoomNo;

    @FXML
    private TableColumn<Management, String> Email;

    @FXML
    private TableColumn<Management, String> RoomType;

    @FXML
    private TableColumn<Management, String> Departure;

    @FXML
    private TableColumn<Management, String> BedType;

    @FXML
    private TableColumn<Management, String> Country;

    @FXML
    private TableColumn<Management, Integer> Adults;

    @FXML
    private TableColumn<Management, Integer> Children;

    @FXML
    private TableColumn<Management, String> Arrival;

    @FXML
    private TableColumn<Management, Integer> Age;

    @FXML
    private TableColumn<Management, String> Name;

  
    private static String guest_id, first_name, last_name, R_ID, arrival_date, bill_ID, total_fee;
    
    	@FXML
    	private void goLogOut(ActionEvent event) {
    		stage = (Stage) logoutButton.getScene().getWindow();
            stage.close();
    		
    	}
    	
    	
    	
    	private ObservableList<Management> data;
			
		@FXML
	    private void LoadingInfo(ActionEvent event) throws SQLException {
			
		DBAccess dbaccess = new DBAccess();
	    try{
	    
	    	data =  FXCollections.observableArrayList();
	    	data = dbaccess.getAllInfo();
    
	    }catch (SQLException e){
			DBUtil.showErrorMessage(e);
		}
	    //Set cell value factory to table view.
	    guestID.setCellValueFactory(cellValue -> cellValue.getValue().guestidProperty().asObject());
	    Name.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
    	Age.setCellValueFactory(cellValue -> cellValue.getValue().ageProperty().asObject());
    	Email.setCellValueFactory(cellValue -> cellValue.getValue().emailProperty());
    	Country.setCellValueFactory(cellValue -> cellValue.getValue().countryProperty());
    	RoomNo.setCellValueFactory(cellValue -> cellValue.getValue().roomNumberProperty().asObject());
    	RoomType.setCellValueFactory(cellValue -> cellValue.getValue().roomTypeProperty());
    	BedType.setCellValueFactory(cellValue -> cellValue.getValue().bedTypeProperty());
    	Arrival.setCellValueFactory(cellValue -> cellValue.getValue().arrivalProperty());
    	Departure.setCellValueFactory(cellValue -> cellValue.getValue().departureProperty());
    	Adults.setCellValueFactory(cellValue -> cellValue.getValue().adultsProperty().asObject());
    	Children.setCellValueFactory(cellValue -> cellValue.getValue().childrenProperty().asObject());
	    	
	    
	    	RoomStatus.setItems(data);
		}    
		
		@FXML
		private void showReservation(ActionEvent event) {
			missSelect.setText("");
			Management manage = RoomStatus.getSelectionModel().getSelectedItem();
			if(manage == null){
				missSelect.setText("Please select a guest to view.");
			}else{
				
				try{
					FXMLLoader loader = new FXMLLoader();
		    		Pane mainPane = loader.load(getClass().getResource("/hotel/UI/ReservationListWindow.fxml").openStream());
		    		ReservationListWindowController list = new ReservationListWindowController();
		    		list = loader.getController();
		    		
		    		DBAccess dbaccess = new DBAccess();
		    		List<String> checklist = dbaccess.showReservationandBill(RoomStatus.getSelectionModel().getSelectedItem().getGuestID()
		    				, RoomStatus.getSelectionModel().getSelectedItem().getRoomNumber()
		    				,RoomStatus.getSelectionModel().getSelectedItem().getArrival());
		    		
		    		first_name = checklist.get(0);
		    		bill_ID = checklist.get(1);
		    		total_fee = checklist.get(2);
		    		R_ID = checklist.get(3);
		    		last_name = RoomStatus.getSelectionModel().getSelectedItem().getName();
		    		
		    		list.setData(R_ID, first_name, last_name, bill_ID, total_fee);
		    	
		    		subStage.setScene(new Scene(mainPane));
		    		subStage.setTitle("RESERVATION");
		    		subStage.show();	
		    		
		    	} catch (Exception e){
		    		e.printStackTrace();
		    	}
			}

		}
}



