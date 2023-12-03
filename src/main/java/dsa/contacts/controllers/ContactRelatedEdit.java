
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
    private Contact contact;
    @FXML
    private Label nameLabel;
    @FXML
    public void initialize() throws FileNotFoundException{
        contact = EditContactController.editedContact;
        if (Logger.loggedUser.getContacts().isEmpty()){
            profilePic.setImage(Util.loadImage(App.IMAGEPATH+contact.getRelatedContacts().get(0).getProfilePic()));
        nameLabel.setText(contact.getRelatedContacts().get(0).getName());
        }
        
        
    }


    @FXML
    private void prevPic(MouseEvent event) throws FileNotFoundException {
        if (picView > 0){picView--;}
        else{picView = contact.getRelatedContacts().size()-1;}
        nameLabel.setText(Logger.loggedUser.getContacts().get(picView).getName());
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+contact.getRelatedContacts().get(picView).getProfilePic()));
    }

    @FXML
    private void nextPic(MouseEvent event) throws FileNotFoundException {
        picView = (picView+1)%contact.getRelatedContacts().size();
        nameLabel.setText(Logger.loggedUser.getContacts().get(picView).getName());
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+contact.getRelatedContacts().get(picView).getProfilePic()));
    }

    @FXML
    private void seleccionar(ActionEvent event){
        contact.getRelatedContacts().add(
        Logger.loggedUser.getContacts().get(picView));
        EditContactController.nuevaVentana.close();
    }

    @FXML
    private void quitar(ActionEvent event) {
        EditContactController.editedContact.getRelatedContacts().remove(
        Logger.loggedUser.getContacts().get(picView));;
        EditContactController.nuevaVentana.close();
    }
}
