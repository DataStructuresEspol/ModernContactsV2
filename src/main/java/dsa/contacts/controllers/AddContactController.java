
package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.Contact;
import dsa.contacts.model.Types;
import dsa.contacts.model.User;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AddContactController {

    @FXML
    private ComboBox<String> phoneTypeBox;
    
    private User user;
    @FXML
    private TextField phoneField;
    @FXML
    private ImageView checkView;
    
    private Contact newContact;
    
    private Label selectedGroupLabel;
    @FXML
    private HBox groupBox;
    
    private TextField entryGroup;
    
    private TextField infoOtro;
    @FXML
    private ComboBox<String> emailTypeBox;
    @FXML
    private TextField emailField;
    @FXML
    public void initialize(){
        user = Logger.loggedUser;
        checkView.setVisible(false);
        newContact = new Contact();
        infoOtro = new TextField();
        entryGroup = new TextField();
        entryGroup.setStyle("-fx-border-width: 1");
        initBox(phoneTypeBox, user.getPhoneTypes());
        initBox(emailTypeBox, user.getEmailTypes());
        
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.retroceder();
    }
    
    private void addType(ComboBox<String> box, Types type) throws IOException{
        String nuevoTipo = infoOtro.getText().toUpperCase();
        if (!infoOtro.getText().isBlank()){
            type.addType(nuevoTipo);
            if (box.getItems().size() > 1)
            {box.getItems().add(box.getItems().size()-2, nuevoTipo);}
            else{box.getItems().add(box.getItems().size()-1, nuevoTipo);}
            box.setValue(nuevoTipo);
            App.save();
        }  
    }
    
    private void initBox(ComboBox<String> box, Types items){
        ObservableList<String> opcionesObservable = FXCollections.observableArrayList(items.getTypes());
        opcionesObservable.add("otro");
        box.setItems(opcionesObservable);
        box.setOnAction(eh -> boxAction(box, items));
    }
    
    private void boxAction(ComboBox<String> box, Types items){
        if (box.getSelectionModel().getSelectedItem().equals("otro")){
            abrirNuevaVentana(box,items);
        }
    }

    @FXML
    private void addGroup(MouseEvent event) throws FileNotFoundException {
        checkView.setVisible(true);
        checkView.setOnMouseClicked(eh->checkGroupClick());
        groupBox.getChildren().add(entryGroup);
    }

    @FXML
    private void deleteGroup(MouseEvent event) {
        //Agregar validaciones
        if (selectedGroupLabel != null){
            newContact.getJoinedGroups().remove(selectedGroupLabel.getText());
        groupBox.getChildren().remove(selectedGroupLabel);
        }
        
    }

    private void checkGroupClick(){
        //Agregar validaciones
        newContact.getJoinedGroups().add(entryGroup.getText());
        Label lbl = new Label(entryGroup.getText());
        lbl.setStyle("-fx-padding: 5");
        lbl.setOnMouseClicked(eh -> labelGroupClicked(lbl));
        groupBox.getChildren().add(lbl);
        checkView.setVisible(false);
        checkView.setOnMouseClicked(null);
        entryGroup.setText("");
        groupBox.getChildren().remove(entryGroup);

    }
    
    private void labelGroupClicked(Label lbl){
        lbl.setStyle("-fx-border-color: black; -fx-border-width: 2");
        if (selectedGroupLabel != null){
            for(Node n: groupBox.getChildren()){
            Label label = (Label)n;
            if (label.equals(selectedGroupLabel)){label.setStyle(null);}
        }
        }
        
        selectedGroupLabel = lbl;
    }
    
    private void abrirNuevaVentana(ComboBox<String> box, Types items) {
        // Crear la nueva ventana
        Stage nuevaVentana = new Stage();
        nuevaVentana.setTitle("Nueva Opción");

        // Crear contenido para la nueva ventana
        StackPane contenido = new StackPane();
        VBox vBox = new VBox();
        Button button = new Button("Aceptar");
        button.setOnAction(e -> {
            nuevaVentana.close();
            try {addType(box, items);} 
            catch (IOException ex) {ex.printStackTrace();}
            finally {infoOtro.setText("");}
        });
        vBox.getChildren().addAll(infoOtro, button);
        contenido.getChildren().add(vBox);

        // Crear la escena y agregar el contenido
        Scene nuevaVentanaScene = new Scene(contenido, 200, 150);
        nuevaVentana.setScene(nuevaVentanaScene);

        // Mostrar la nueva ventana
        nuevaVentana.show();
    }
}
