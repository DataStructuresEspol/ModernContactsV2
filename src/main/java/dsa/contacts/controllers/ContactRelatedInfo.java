
package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.Contact;
import dsa.contacts.model.User;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.FileNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class ContactRelatedInfo {

    @FXML
    private Label nameLabel;
    @FXML
    private ImageView profilePic;
    
    private Contact contact;
    private int picView;
    @FXML
    public void initialize() throws FileNotFoundException{
        contact = ContactInfoController.selectedContact;
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+contact.getRelatedContacts().get(0).getProfilePic()));
        nameLabel.setText(contact.getRelatedContacts().get(0).getName());
        
    }
    @FXML
    private void prevPic(MouseEvent event) throws FileNotFoundException {
        if (picView > 0){picView--;}
        else{picView = contact.getRelatedContacts().size()-1;}
        nameLabel.setText(contact.getRelatedContacts().get(picView).getName());
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+contact.getRelatedContacts().get(picView).getProfilePic()));
    }

    @FXML
    private void nextPic(MouseEvent event) throws FileNotFoundException {
        picView = (picView+1)%contact.getRelatedContacts().size();
        nameLabel.setText(contact.getRelatedContacts().get(picView).getName());
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+contact.getRelatedContacts().get(picView).getProfilePic()));
    }
    
}
