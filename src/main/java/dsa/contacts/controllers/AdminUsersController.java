
package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.Admin;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private void initialize() throws FileNotFoundException{
        Logger.loggedUser = Logger.loggedAdmin;
        admin = Logger.loggedAdmin;
        nameLabel.setText(admin.getUser().get(0).getUserName());
        if (admin.getUser().get(0).getPic()!=null){
            profilePic.setImage(Util.loadImage(App.IMAGEPATH+admin.getUser().get(0).getPic()));
        }
        else{profilePic.setImage(Util.loadImage(App.IMAGEPATH+"default.png"));}
    }
    @FXML
    private void prevPic(MouseEvent event) throws FileNotFoundException {
        if (picView > 0){picView--;}
        else{picView = admin.getUser().size()-1;}
        System.out.println(picView);
        nameLabel.setText(admin.getUser().get(picView).getUserName());
        if (admin.getUser().get(picView).getPic()!=null){
            profilePic.setImage(Util.loadImage(App.IMAGEPATH+admin.getUser().get(picView).getPic()));
        }
        else{profilePic.setImage(Util.loadImage(App.IMAGEPATH+"default.png"));}
    }

    @FXML
    private void nextPic(MouseEvent event) throws FileNotFoundException {
        picView = (picView+1)%admin.getUser().size();
        System.out.println(picView);
        nameLabel.setText(admin.getUser().get(picView).getUserName());
        if (admin.getUser().get(picView).getPic()!=null){
            profilePic.setImage(Util.loadImage(App.IMAGEPATH+admin.getUser().get(picView).getPic()));
        }
        else{profilePic.setImage(Util.loadImage(App.IMAGEPATH+"default.png"));}
    }

    @FXML
    private void enterUser(MouseEvent event) throws IOException {
        Logger.loggedUser = admin.getUser().get(picView);
        App.setRoot("home");
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.retroceder();
    }
    
}
