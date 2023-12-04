
package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.Admin;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class AdminUsersController {

    @FXML
    private Label nameLabel;
    @FXML
    private ImageView profilePic;
    private Admin admin;
    private int picView;
    @FXML
    private void initialize(){
        admin = Logger.loggedAdmin;
        nameLabel.setText(admin.getUserName());
    }
    @FXML
    private void prevPic(MouseEvent event) throws FileNotFoundException {
        if (picView > 0){picView--;}
        else{picView = admin.getUser().size()-1;}
        nameLabel.setText(admin.getUser().get(picView).getUserName());
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+admin.getUser().get(picView).getPic()));
    }

    @FXML
    private void nextPic(MouseEvent event) throws FileNotFoundException {
        picView = (picView+1)%admin.getUser().size();
        nameLabel.setText(admin.getUser().get(picView).getUserName());
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+admin.getUser().get(picView).getPic()));
    }
    
}
