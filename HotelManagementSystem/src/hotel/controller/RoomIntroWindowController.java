package hotel.controller;


import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoomIntroWindowController implements Initializable {    
    @FXML
    private MenuButton btndeluxe;
    @FXML
    private MenuItem btnsingle;
    @FXML
    private MenuItem btnsingle2;
    @FXML
    private MenuItem btnsingle3;
    @FXML
    private MenuItem btndouble;
    @FXML
    private MenuItem btndouble2;
    @FXML
    private MenuItem btndouble3;
    @FXML
    private MenuItem btntwins;
    @FXML
    private MenuItem btntwins2;
    @FXML
    private MenuItem btntwins3;
    @FXML
    private MenuButton btnsuite;
    @FXML
    private MenuButton btnstandard;
   
    @FXML
    private ImageView imgpic;
    @FXML
    private ImageView infopic;
    @FXML
    private void MainImage(ActionEvent event) {
      Image img,immg;
        if(event.getSource()==btnsingle) 
        { 
        	img=new Image("file:src/hotel/image/standardsingle.jpg");
        	immg=new Image("file:src/hotel/image/infosinglestandard.jpg");
        }
      	else if(event.getSource()==btndouble) 
      	{ 
      		img=new Image("file:src/hotel/image/standarddouble.jpg");
      		immg=new Image("file:src/hotel/image/infodoublestandard.jpg");
      	}
      	else if(event.getSource()==btntwins) 
        {
      		img=new Image("file:src/hotel/image/standardtwins.jpg");
      		immg=new Image("file:src/hotel/image/infotwinstandard.jpg");
      	}
      	else if(event.getSource()==btnsingle2) 
        {
      		img=new Image("file:src/hotel/image/deluxesingle.jpg");
      		immg=new Image("file:src/hotel/image/infosingledeluxe.jpg");
      	}
        else if(event.getSource()==btndouble2) 
        {
        	img=new Image("file:src/hotel/image/deluxedouble.jpg");
        	immg=new Image("file:src/hotel/image/infodoubledeluxe.jpg");
        }
        else if(event.getSource()==btntwins2) 
        {
        	img=new Image("file:src/hotel/image/deluxetwins.jpg");
        	immg=new Image("file:src/hotel/image/infotwindeluxe.jpg");
        }
        else if(event.getSource()==btnsingle3) 
        {
        	img=new Image("file:src/hotel/image/suitesingle.jpg");
        	immg=new Image("file:src/hotel/image/infosinglesuite.jpg");
        }
        else if(event.getSource()==btndouble3) 
        {
        	img=new Image("file:src/hotel/image/suitedouble.jpg");
        	immg=new Image("file:src/hotel/image/infodoublesuite.jpg");
        }
        else if(event.getSource()==btntwins3) 
        { 
        	img=new Image("file:src/hotel/image/suitetwins.jpg");
        	immg=new Image("file:src/hotel/image/infotwinsuite.jpg");
        }
      else
        {
    	  img=null;
    	  immg=null;
    	 }
      imgpic.setImage(img);
      infopic.setImage(immg);
    }
    
     @Override
        public void initialize(URL url, ResourceBundle rb) {
            // TODO
        }    
        
    }//end class
