package dsa.contacts.controllers;

import dsa.contacts.App;
import static dsa.contacts.App.loadFXML;
import dsa.contacts.model.Contact;
import dsa.contacts.model.Info;
import dsa.contacts.model.Person;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private Label dateLabel;
    @FXML
    private Label tagLabel;
    
    private Stage nuevaVentana;
    @FXML
    private void initialize() throws FileNotFoundException{
        if (selectedContact instanceof Person){
            Person p = (Person)selectedContact;
        nameLabel.setText(p.getName()+" "+p.getLastName());
        }
        else{nameLabel.setText(selectedContact.getName());}
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+selectedContact.getProfilePic()));
        groupLabel.setText(this.groupLabelString());
        initInfo(phoneLabel,selectedContact.getPhones());
        initInfo(emailLabel, selectedContact.getEmails());
        initInfo(addressLabel,selectedContact.getAddresses());
        initInfo(socialMediaLabel,selectedContact.getSocialMedias());
        initInfo(dateLabel, selectedContact.getDates());
        tagLabel.setText(tagLabelString());
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
    
    
    private String tagLabelString(){
        String groups="";
        for (String s: selectedContact.getTags()){
            groups += "\t"+s;
        }
        return groups;
    }
    
    private void initInfo(Label lbl, List<? extends Info> listInfo){
        VBox box = (VBox)lbl.getParent();
        for (Info i: listInfo){
            box.getChildren().add(new Label(i.getType()+": "+i.getInfo()));
        }
    }

    @FXML
    private void VerContactosRelacionados(ActionEvent event) throws IOException {
        // Crear la nueva ventana
        nuevaVentana = new Stage();
        nuevaVentana.setTitle("Seleccione Contactos Relacionados");

        // Crear contenido para la nueva ventana

        // Crear la escena y agregar el contenido
        Scene scene = new Scene(loadFXML("contactRelatedInfo"), 780, 400);
        nuevaVentana.setScene(scene);

        // Mostrar la nueva ventana
        nuevaVentana.show();
    }
}
