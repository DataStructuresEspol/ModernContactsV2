package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.Contact;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.HashSet;
import java.util.LinkedList;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class HomeController {
    private HashSet<String> groups;
    private HashSet<String> tags;
    private HashSet<String> attributes;

    @FXML
    private HBox groupList;

    @FXML
    private HBox tagList;

    @FXML
    private HBox attributeList;

    @FXML
    CheckBox person;

    @FXML
    CheckBox company;

    @FXML
    CheckBox favorite;

    @FXML
    ChoiceBox<String> nameOrder;

    @FXML
    ChoiceBox<String> countryOrder;
    @FXML
    private ImageView addGroupIcon;
    @FXML
    private ImageView addTagIcon;
    @FXML
    private Label contactName;
    
    private LinkedList<Contact> contacts;
    private int picView;
    @FXML
    private ImageView contactPic;
    @FXML
    private Label saludo;
    @FXML
    private ImageView favoriteIcon;
    @FXML
    private void initialize() throws FileNotFoundException {
        groups = new HashSet<>();
        tags = new HashSet<>();
        attributes = new HashSet<>();

        nameOrder.getItems().addAll("Ascendente", "Descendente");
        countryOrder.getItems().addAll("Ascendente", "Descendente");

        // TODO: get groups, tags and attributes from database (data)
        groups.add("Familia");
        groups.add("Amigos");
        tags.add("Trabajo");
        tags.add("Universidad");
        attributes.add("Nombre");
        attributes.add("Apellidos");
        
        contacts = Logger.loggedUser.getContacts();
        System.out.println(contacts.size());
        saludo.setText("!Hola "+Logger.loggedUser.getUserName()+"!");
        if (!contacts.isEmpty()){
            contactPic.setImage(Util.loadImage(App.IMAGEPATH+contacts.get(0).getProfilePic()));
            contactName.setText(contacts.get(0).getName());
            if (contacts.get(picView).isFavorite()){
                favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite-solid.png"));
            }
            else{favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite.png"));}
        }
        
        
    }

    @FXML
    private void settingsMenu() {

    }

    @FXML
    private void addGroup() {
        addElementToFilter(groupList, groups);
    }

    @FXML
    private void addTag() {
        addElementToFilter(tagList, tags);
    }

    @FXML
    private void addAttribute() {
        addElementToFilter(attributeList, attributes);
    }

    public HashSet<String> getChosenGroups() {
        return getChosenTypes(groupList);
    }

    public HashSet<String> getChosenTags() {
        return getChosenTypes(tagList);
    }

    public HashSet<String> getChosenAttributes() {
        return getChosenTypes(attributeList);
    }

    private void addElementToFilter(HBox list, HashSet<String> elements) {
        if (elements.isEmpty()) {
            return;
        }

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(elements);
        list.getChildren().add(list.getChildren().size() - 1, choiceBox);

        choiceBox.setOnAction(choose -> {
            Label label = new Label(choiceBox.getValue());

            label.setOnMouseClicked(remove -> {
                list.getChildren().remove(label);
                elements.add(label.getText());
            });

            label.setStyle("-fx-cursor: hand;");

            list.getChildren().add(list.getChildren().size()-1, label);
            list.getChildren().remove(choiceBox);
            elements.remove(choiceBox.getValue());
        });
    }

    public HashSet<String> getChosenTypes(HBox typeList) {
        HashSet<String> chosenTypes = new HashSet<>();

        for (int i = 0; i < typeList.getChildren().size() - 1; i++) {
            CheckBox checkBox = (CheckBox) typeList.getChildren().get(i);
            if (checkBox.isSelected()) {
                chosenTypes.add(checkBox.getText());
            }
        }

        return chosenTypes;
    }

    @FXML
    private void regresar(MouseEvent event) throws IOException {
        App.retroceder();
    }

    @FXML
    private void addContact(MouseEvent event) throws IOException {
        App.setRoot("choice");
    }

    @FXML
    private void prevContact(MouseEvent event) throws FileNotFoundException {
        if (picView > 0){picView--;}
        else{picView = contacts.size()-1;}
        contactPic.setImage(Util.loadImage(App.IMAGEPATH+contacts.get(picView).getProfilePic()));
        contactName.setText(contacts.get(picView).getName());
        if (contacts.get(picView).isFavorite()){
            favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite-solid.png"));
        }
        else{favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite.png"));}
    }

    @FXML
    private void nextContact(MouseEvent event) throws FileNotFoundException {
        picView = (picView+1)%contacts.size();
        contactPic.setImage(Util.loadImage(App.IMAGEPATH+contacts.get(picView).getProfilePic()));
        contactName.setText(contacts.get(picView).getName());
        if (contacts.get(picView).isFavorite()){
            favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite-solid.png"));
        }
        else{favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite.png"));}
    }

    @FXML
    private void setFavorite(MouseEvent event) throws FileNotFoundException, IOException {
        contacts.get(picView).setFavorite();
        if (contacts.get(picView).isFavorite()){
            favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite-solid.png"));
        }
        else{favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite.png"));}
        App.save();
    }

    @FXML
    private void infoContact(MouseEvent event) throws IOException {
        ContactInfoController.selectedContact = contacts.get(picView);
        App.setRoot("contactInfo");
        
    }
}
