
package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.Contact;
import dsa.contacts.model.User;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ContactRelatedEdit {

    @FXML
    private ImageView profilePic;
    int picView;
    private User user;
    @FXML
    private Label nameLabel;
    @FXML
    public void initialize() throws FileNotFoundException{
        user = Logger.loggedUser;
        if (!user.getContacts().isEmpty()){
           profilePic.setImage(Util.loadImage(App.IMAGEPATH+user.getContacts().get(0).getProfilePic()));
           nameLabel.setText(user.getContacts().get(0).getName());
        }
        
        
    }


    @FXML
    private void prevPic(MouseEvent event) throws FileNotFoundException {
        if (picView > 0){picView--;}
        else{picView = user.getContacts().size()-1;}
        nameLabel.setText(user.getContacts().get(picView).getName());
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+user.getContacts().get(picView).getProfilePic()));
    }

    @FXML
    private void nextPic(MouseEvent event) throws FileNotFoundException {
        picView = (picView+1)%user.getContacts().size();
        nameLabel.setText(user.getContacts().get(picView).getName());
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+user.getContacts().get(picView).getProfilePic()));
    }

    @FXML
    private void seleccionar(ActionEvent event){
        EditContactController.editedContact.getRelatedContacts().add(
        user.getContacts().get(picView));
        EditContactController.nuevaVentana.close();
    }

    @FXML
    private void quitar(ActionEvent event) {
        EditContactController.editedContact.getRelatedContacts().remove(
        user.getContacts().get(picView));
        EditContactController.nuevaVentana.close();
    }
}
