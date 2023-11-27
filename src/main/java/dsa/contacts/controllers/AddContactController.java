
package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.Types;
import dsa.contacts.model.User;
import dsa.contacts.util.Logger;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;


public class AddContactController {

    @FXML
    private ComboBox<String> phoneTypeBox;
    
    private User user;
    
    public void initialize(){
        user = Logger.loggedUser;
        
        ObservableList<String> opcionesObservable = FXCollections.observableArrayList(user.getPhoneTypes().getTypes());
        opcionesObservable.add("otro");
        this.phoneTypeBox.setItems(opcionesObservable);
        phoneTypeBox.setOnAction(eh -> {
            if (phoneTypeBox.getSelectionModel().getSelectedItem().equals("otro")){
                try {
                    addType(user.getPhoneTypes());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.retroceder();
    }
    
    private void addType(Types type) throws IOException{
        String nuevoTipo = "hola";
        type.addType(nuevoTipo.toUpperCase());
        if (phoneTypeBox.getItems().size() > 1)
        {phoneTypeBox.getItems().add(phoneTypeBox.getItems().size()-2, nuevoTipo);}
        else{phoneTypeBox.getItems().add(phoneTypeBox.getItems().size()-1, nuevoTipo);}
        App.save();
        
    }
    
}
