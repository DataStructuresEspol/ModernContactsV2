package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.Contact;
import dsa.contacts.model.Info;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ContactInfoController {

    @FXML
    private ImageView favoriteIcon;
    @FXML
    private ImageView profilePic;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label socialMediaLabel;
    @FXML
    private Label nameLabel;
    
    public static Contact selectedContact;
    @FXML
    private Label groupLabel;
    private int picView;
    @FXML
    private void initialize() throws FileNotFoundException{
        nameLabel.setText(selectedContact.getName());
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+selectedContact.getProfilePic()));
        groupLabel.setText(this.groupLabelString());
        phoneLabel.setText(infoString(selectedContact.getPhones()));
        emailLabel.setText(infoString(selectedContact.getEmails()));
        addressLabel.setText(infoString(selectedContact.getAddresses()));
        socialMediaLabel.setText(infoString(selectedContact.getSocialMedias()));
        if (selectedContact.isFavorite()){
            favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite-solid.png"));
        }
        else{favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite.png"));}
        
    }
    @FXML
    private void retroceder(MouseEvent event) throws IOException {
        App.retroceder();
    }

    @FXML
    private void setFavorite(MouseEvent event) throws FileNotFoundException, IOException {
        selectedContact.setFavorite();
        if (selectedContact.isFavorite()){
            favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite-solid.png"));
        }
        else{favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite.png"));}
        App.save();
    }

    @FXML
    private void deleteContact(MouseEvent event) throws IOException {
        Logger.loggedUser.getContacts().remove(selectedContact);
        App.save();
        App.retroceder();
    }

    @FXML
    private void editContact(MouseEvent event) throws IOException {
        App.setRoot("editContact");
    }

    @FXML
    private void nextPic(MouseEvent event) throws FileNotFoundException {
        picView = (picView+1)%selectedContact.getPics().size();
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+selectedContact.getPics().get(picView)));
    }

    @FXML
    private void prevPic(MouseEvent event) throws FileNotFoundException {
        if (picView > 0){picView--;}
        else{picView = selectedContact.getPics().size()-1;}
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+selectedContact.getPics().get(picView)));
    }
    
    private String groupLabelString(){
        String groups="";
        for (String s: selectedContact.getJoinedGroups()){
            groups += "\t"+s;
        }
        return groups;
    }
    
    private String infoString(List<? extends Info> listInfo){
        String info="";
        for(Info i: listInfo){
            info += i.getType()+": "+i.getInfo()+"\n";
        }
        return info;
    }
}
