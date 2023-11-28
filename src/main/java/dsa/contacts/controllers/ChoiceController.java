
package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.Company;
import dsa.contacts.model.Contact;
import dsa.contacts.model.Person;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;

public class ChoiceController implements Initializable {

    public static Contact preContact;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void addPerson(MouseEvent event) throws IOException {
        preContact = new Person();
        App.setRoot("addContact");
    }

    @FXML
    private void AddCompany(MouseEvent event) throws IOException {
        preContact = new Company();
        App.setRoot("addContact");
    }

    @FXML
    private void retroceder(MouseEvent event) throws IOException {
        App.retroceder();
    }
   

}
