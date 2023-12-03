
package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.Address;
import dsa.contacts.model.Company;
import dsa.contacts.model.Contact;
import dsa.contacts.model.Email;
import dsa.contacts.model.Info;
import dsa.contacts.model.MyDate;
import dsa.contacts.model.Person;
import dsa.contacts.model.Phone;
import dsa.contacts.model.SocialMedia;
import dsa.contacts.model.Types;
import dsa.contacts.model.User;
import dsa.contacts.model.exceptions.EmailException;
import dsa.contacts.model.exceptions.PhoneException;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditContactController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField apellidoField;
    @FXML
    private ImageView profilePic;
    @FXML
    private HBox groupBox;
    @FXML
    private ImageView checkView;
    @FXML
    private ComboBox<String> phoneTypeBox;
    @FXML
    private TextField phoneField;
    @FXML
    private ComboBox<String> emailTypeBox;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<String> addressTypeBox;
    @FXML
    private TextField addressField;
    @FXML
    private ComboBox<String> socialMediaTypeBox;
    @FXML
    private TextField socialMediaField;
    
    private Contact editedContact;
    
    private int picView;
    
    private TextField entryGroup;
    
    private Label selectedGroupLabel;
    
    private TextField infoOtro;
    
    private User user;
    @FXML
    private Label apellidoLabel;
    @FXML
    private ComboBox<String> dateTypeBox;
    @FXML
    private TextField dateField;
    @FXML
    private HBox groupTag;
    @FXML
    private ImageView checkViewTag;
    
    private TextField entryTag;
    
    private Label selectedTagLabel;
    @FXML
    private void initialize() throws FileNotFoundException{
        user = Logger.loggedUser;
        editedContact = ContactInfoController.selectedContact;
        if (editedContact instanceof Company){
            apellidoField.setVisible(false);
            apellidoLabel.setVisible(false);
        }
        else{
            apellidoField.setText(((Person)editedContact).getLastName());}
        
        nameField.setText(editedContact.getName());
        checkView.setVisible(false);
        infoOtro = new TextField();
        entryGroup = new TextField();
        entryTag = new TextField();
        entryGroup.setStyle("-fx-border-width: 1");
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+editedContact.getProfilePic()));
        initBox(phoneTypeBox, user.getPhoneTypes(), editedContact.getPhones(), phoneField);
        initBox(emailTypeBox, user.getEmailTypes(), editedContact.getEmails(), emailField);
        initBox(addressTypeBox, user.getAddressTypes(), editedContact.getAddresses(), addressField);
        initBox(socialMediaTypeBox, user.getSocialMediaTypes(), editedContact.getSocialMedias(), socialMediaField);
        initBox(dateTypeBox, user.dateTypes(), editedContact.getDates(), dateField);
        initGroups();
        initTags();
        initValues();
    }
    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.retroceder();
    }
    
    private void initGroups(){
        for (String group : editedContact.getJoinedGroups()){
            Label lbl = new Label(group);
            lbl.setStyle("-fx-padding: 5");
            lbl.setOnMouseClicked(eh -> labelGroupClicked(lbl));
            groupBox.getChildren().add(lbl);
        }
    }
    
    private void initTags(){
        for (String group : editedContact.getTags()){
            Label lbl = new Label(group);
            lbl.setStyle("-fx-padding: 5");
            lbl.setOnMouseClicked(eh -> labelTagClicked(lbl));
            groupTag.getChildren().add(lbl);
        }
    }
    private void initValues(){
        if (!editedContact.getPhones().isEmpty()){
            phoneTypeBox.setValue(editedContact.getPhones().get(0).getType());
            phoneField.setText(editedContact.getPhones().get(0).getInfo());
        }
        if (!editedContact.getEmails().isEmpty()){
            emailTypeBox.setValue(editedContact.getEmails().get(0).getType());
            emailField.setText(editedContact.getEmails().get(0).getInfo());
        }
        if (!editedContact.getAddresses().isEmpty()){
            addressTypeBox.setValue(editedContact.getAddresses().get(0).getType());
            addressField.setText(editedContact.getAddresses().get(0).getInfo());
        }
        
        if (!editedContact.getSocialMedias().isEmpty()){
            socialMediaTypeBox.setValue(editedContact.getSocialMedias().get(0).getType());
            socialMediaField.setText(editedContact.getSocialMedias().get(0).getInfo());
        }
        
        if (!editedContact.getDates().isEmpty()){
            dateTypeBox.setValue(editedContact.getDates().get(0).getType());
            dateField.setText(editedContact.getDates().get(0).getInfo());
        }
    }
    private void initBox(ComboBox<String> box, Types items,List<? extends Info> info, TextField field){
        ObservableList<String> opcionesObservable = FXCollections.observableArrayList(items.getTypes());
        opcionesObservable.add("otro");
        box.setItems(opcionesObservable);
        box.setOnAction(eh -> boxAction(box, items, info, field));
    }
    
    private void boxAction(ComboBox<String> box, Types items, List<? extends Info> info, TextField field){
        if ("otro".equalsIgnoreCase(box.getSelectionModel().getSelectedItem())){
            abrirNuevaVentana(box,items);
        }
        else{
            boolean value = true;
            for(Info i: info){
                if(i.getType().equalsIgnoreCase(box.getSelectionModel().getSelectedItem())){
                    field.setText(i.getInfo());
                    value = false;
                }
        }
            if (value){field.setText("");}
    }}
    
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

    @FXML
    private void selectPic(MouseEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione una imagen");

        // Configurar el filtro para mostrar solo archivos de imagen (por ejemplo, PNG, JPG)
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el cuadro de diálogo de selección de archivo
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String rutaArchivo = selectedFile.getAbsolutePath();
            String nombreCompleto = selectedFile.getName();
            int indexPunto = nombreCompleto.lastIndexOf('.');
            String nombre = nombreCompleto.substring(0, indexPunto);
            String rutaFinal = App.IMAGEPATH+nombre+"_copy.png";
            try {
            Util.copiarImagen(rutaArchivo, rutaFinal);
            profilePic.setImage(Util.loadImage(rutaFinal));
            editedContact.setProfilePic(nombre+"_copy.png");
            System.out.println("Imagen copiada con éxito."+rutaFinal);
        } catch (IOException e) {
            System.out.println("Error al copiar la imagen: " + e.getMessage());
            profilePic.setImage(Util.loadImage(rutaFinal));
            editedContact.setProfilePic(nombre+"_copy.png");
        }

        } else {
            System.out.println("Selección de foto cancelada por el usuario");
        }
    }

    @FXML
    private void prevPic(MouseEvent event) throws FileNotFoundException {
        if (picView > 0){picView--;}
        else{picView = editedContact.getPics().size()-1;}
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+editedContact.getPics().get(picView)));
    }

    @FXML
    private void nextPic(MouseEvent event) throws FileNotFoundException {
        picView = (picView+1)%editedContact.getPics().size();
        profilePic.setImage(Util.loadImage(App.IMAGEPATH+editedContact.getPics().get(picView)));
    }


    @FXML
    private void addPic(MouseEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione una imagen");

        // Configurar el filtro para mostrar solo archivos de imagen (por ejemplo, PNG, JPG)
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el cuadro de diálogo de selección de archivo
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String rutaArchivo = selectedFile.getAbsolutePath();
            String nombreCompleto = selectedFile.getName();
            int indexPunto = nombreCompleto.lastIndexOf('.');
            String nombre = nombreCompleto.substring(0, indexPunto);
            String rutaFinal = App.IMAGEPATH+nombre+"_copy.png";
            try {
            Util.copiarImagen(rutaArchivo, rutaFinal);
            editedContact.getPics().add(nombre+"_copy.png");
            System.out.println("Imagen copiada con éxito."+rutaFinal);
        } catch (IOException e) {
            System.out.println("Error al copiar la imagen: " + e.getMessage());
            editedContact.getPics().add(nombre+"_copy.png");
        }

        } else {
            System.out.println("Selección de foto cancelada por el usuario");
        }
    }

    @FXML
    private void addGroup(MouseEvent event) {
        checkView.setVisible(true);
        checkView.setOnMouseClicked(eh->checkGroupClick());
        groupBox.getChildren().add(entryGroup);
    }
    
    private void checkGroupClick(){
        //Agregar validaciones
        editedContact.getJoinedGroups().add(entryGroup.getText());
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

    @FXML
    private void deleteGroup(MouseEvent event) {
        //Agregar validaciones
        if (selectedGroupLabel != null){
            editedContact.getJoinedGroups().remove(selectedGroupLabel.getText());
        groupBox.getChildren().remove(selectedGroupLabel);
        }
    }

    @FXML
    private void addPhone(MouseEvent event) throws PhoneException {
        //Agregar validaciones
        String countryCode = "+593";
        editedContact.getPhones().add(new Phone(phoneField.getText(),
                phoneTypeBox.getSelectionModel().getSelectedItem(),countryCode));
        phoneTypeBox.setValue("");
        phoneField.setText("");
    }

    @FXML
    private void deletePhone(MouseEvent event) throws PhoneException {
        //Agregar validaciones
        String countryCode = "+593";
        editedContact.getPhones().remove(new Phone(phoneField.getText(),
                phoneTypeBox.getSelectionModel().getSelectedItem(),countryCode));
        phoneTypeBox.setValue("");
        phoneField.setText("");
    }

    @FXML
    private void addEmail(MouseEvent event) throws EmailException {
        //Agregar validaciones
        editedContact.getEmails().add(new Email(emailField.getText(),
                emailTypeBox.getSelectionModel().getSelectedItem()));
        emailTypeBox.setValue("");
        emailField.setText("");
    }

    @FXML
    private void deleteEmail(MouseEvent event) throws EmailException {
        //Agregar validaciones
        editedContact.getPhones().remove(new Email(emailField.getText(),
                emailTypeBox.getSelectionModel().getSelectedItem()));
        emailTypeBox.setValue("");
        emailField.setText("");
    }

    @FXML
    private void addAddress(MouseEvent event) {
        //Agregar validaciones
        editedContact.getAddresses().add(new Address(addressField.getText(),
                addressTypeBox.getSelectionModel().getSelectedItem()));
        addressTypeBox.setValue("");
        addressField.setText("");
    }

    @FXML
    private void deleteAddress(MouseEvent event) {
        //Agregar validaciones
        editedContact.getAddresses().remove(new Address(addressField.getText(),
                addressTypeBox.getSelectionModel().getSelectedItem()));
        addressTypeBox.setValue("");
        addressField.setText("");
    }

    @FXML
    private void addSocialMedia(MouseEvent event) {
        //Agregar validaciones
        editedContact.getSocialMedias().add(new SocialMedia(socialMediaField.getText(),
                socialMediaTypeBox.getSelectionModel().getSelectedItem()));
        socialMediaTypeBox.setValue("");
        socialMediaField.setText("");
    }

    @FXML
    private void deleteSocialMedia(MouseEvent event) {
        //Agregar validaciones
        editedContact.getSocialMedias().remove(new SocialMedia(socialMediaField.getText(),
                socialMediaTypeBox.getSelectionModel().getSelectedItem()));
        socialMediaTypeBox.setValue("");
        socialMediaField.setText("");
    }

    @FXML
    private void saveContact(ActionEvent event) throws IOException {
        if (editedContact instanceof Person){
            Person p = (Person) editedContact;
            p.setLastName(apellidoField.getText());
        }
        App.save();
        App.retroceder();
    }

    @FXML
    private void addDate(MouseEvent event) {
        //Agregar validaciones
        String[] date = dateField.getText().split("/");
        editedContact.getDates().add(new MyDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
                Integer.parseInt(date[2]), dateTypeBox.getSelectionModel().getSelectedItem()));
        dateTypeBox.setValue("");
        dateField.setText("");
    }

    @FXML
    private void deleteDate(MouseEvent event) {
            //Agregar validaciones
        String[] date = dateField.getText().split("/");
        editedContact.getDates().remove(new MyDate(Integer.parseInt(date[1]), Integer.parseInt(date[1]),
                Integer.parseInt(date[1]), dateTypeBox.getSelectionModel().getSelectedItem()));
        dateTypeBox.setValue("");
        dateField.setText("");
    }

    @FXML
    private void addTag(MouseEvent event) {
        checkViewTag.setVisible(true);
        checkViewTag.setOnMouseClicked(eh->checkTagClick());
        groupTag.getChildren().add(entryTag);
    }

    @FXML
    private void deleteTag(MouseEvent event) {
        //Agregar validaciones
        if (selectedTagLabel != null){
            editedContact.getTags().remove(selectedTagLabel.getText());
        groupTag.getChildren().remove(selectedTagLabel);
        }
    }
    
        private void checkTagClick(){
        //Agregar validaciones
        editedContact.getTags().add(entryTag.getText());
        Label lbl = new Label(entryTag.getText());
        lbl.setStyle("-fx-padding: 5");
        lbl.setOnMouseClicked(eh -> labelTagClicked(lbl));
        groupTag.getChildren().add(lbl);
        checkViewTag.setVisible(false);
        checkViewTag.setOnMouseClicked(null);
        entryTag.setText("");
        groupTag.getChildren().remove(entryTag);
    }
    
    private void labelTagClicked(Label lbl){
        lbl.setStyle("-fx-border-color: black; -fx-border-width: 2");
        if (selectedTagLabel != null){
            for(Node n: groupTag.getChildren()){
            Label label = (Label)n;
            if (label.equals(selectedTagLabel)){label.setStyle(null);}
        }
        }
        
        selectedTagLabel = lbl;
    }
}
