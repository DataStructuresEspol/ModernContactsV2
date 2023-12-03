
package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.User;
import dsa.contacts.model.exceptions.InvalidPasswordException;
import dsa.contacts.model.exceptions.UserNotFoundException;
import dsa.contacts.model.exceptions.ValidationException;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class LoginController implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SignIn(MouseEvent event) throws IOException {
        App.setRoot("register");
    }

    @FXML
    private void SignUp(MouseEvent event) throws IOException {
        try {
            Logger.logIn(userNameField.getText(), passwordField.getText());
            App.setRoot("home");
            
        } catch (ValidationException ex) {
            showAlert(ex);
        }
    }
    
    private void showAlert(ValidationException e){
        Node node = null;
        if (e instanceof UserNotFoundException){node = userNameField;}
        if (e instanceof InvalidPasswordException){node = passwordField;}
        Util.alertStyle(node);
        App.showAlert(Alert.AlertType.ERROR, e);
        Util.removeAlerstStyle(node);
    }
    
}
