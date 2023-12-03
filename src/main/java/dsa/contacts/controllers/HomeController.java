package dsa.contacts.controllers;

import dsa.contacts.ds.ArrayList;

import java.util.Comparator;
import java.util.HashSet;

import dsa.contacts.App;
import dsa.contacts.logic.*;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    
    private ArrayList<Contact> contacts;

    private int picView;
    @FXML
    private ImageView contactPic;
    @FXML
    private Label saludo;
    @FXML
    private ImageView favoriteIcon;

    // Filter
    private ArrayList<Filter> filters;
    @FXML
    private void initialize() throws FileNotFoundException {
        groups = new HashSet<>();
        tags = new HashSet<>();
        attributes = new HashSet<>();
        filters = new ArrayList<>();

        nameOrder.getItems().addAll("Ascendente", "Descendente");
        countryOrder.getItems().addAll("Ascendente", "Descendente");

        // TODO: get groups, tags and attributes from database (data)
        groups.add("Familia");
        groups.add("Amigos");
        groups.add("panas");
        tags.add("Trabajo");
        tags.add("Universidad");
        tags.add("familia");
        attributes.add("Nombre");
        attributes.add("Apellidos");

        restoreContacts();

        System.out.println(contacts.size());
        saludo.setText("!Hola "+Logger.loggedUser.getUserName()+"!");

        updateView();

        // Ordering contacts

        // Order by name
        nameOrder.setOnAction(order -> {
            if (nameOrder.getValue().equals("Ascendente")) {
                contacts.sort(Comparator.comparing(Contact::getName));
            } else {
                contacts.sort(Comparator.comparing(Contact::getName).reversed());
            }
            try {
                updateView();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        // Filters

        // filter if favorite
        favorite.setOnAction(fav -> {
            if (favorite.isSelected()){
                Filter f = new Favorite();
                filters.add(f);
            }
            else{
                filters.remove(new Favorite());
            }
            updateFilters();
        });

        // filter by type
        // person
        person.setOnAction(p -> {
            if (person.isSelected()) {
                Filter f = new isPerson();
                filters.add(f);
            } else {
                filters.remove(new isPerson());
            }
            updateFilters();
        });

        company.setOnAction(filter-> {
            if (company.isSelected()) {
                Filter f = new isCompany();
                filters.add(f);
            } else {
                filters.remove(new isCompany());
            }
            updateFilters();
        });
        
    }

    private void updateView() throws FileNotFoundException {
        if (!contacts.isEmpty()){
            contactPic.setImage(Util.loadImage(App.IMAGEPATH+contacts.get(0).getProfilePic()));
            contactName.setText(contacts.get(0).getName());
            
            if (contacts.get(picView).isFavorite()){
                favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite-solid.png"));
            }
            else{favoriteIcon.setImage(Util.loadImage(App.ICONPATH+"favorite.png"));}
        } else {
            contactPic.setImage(Util.loadImage(App.IMAGEPATH+"default.png"));
            contactName.setText("No hay contactos");
        }
    }

    private void restoreContacts() {
        contacts = new ArrayList<>();
        contacts.addAll(Logger.loggedUser.getContacts());
    }

    @FXML
    private void settingsMenu() {

    }

    @FXML
    private void addGroup() {
        addElementToFilter(groupList, groups, "group");
    }

    @FXML
    private void addTag() {
        addElementToFilter(tagList, tags, "tag");
    }

    @FXML
    private void addAttribute() {
        addElementToFilter(attributeList, attributes, "attribute");
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

    private void addElementToFilter(HBox list, HashSet<String> elements, String type) {
        if (elements.isEmpty()) {
            return;
        }

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(elements);
        list.getChildren().add(list.getChildren().size() - 1, choiceBox);

        choiceBox.setOnAction(choose -> {
            Label label = new Label(choiceBox.getValue());

            label.setOnMouseClicked(remove -> {
                switch (type) {
                    case "group":
                        filters.remove(new GroupFilter(label.getText()));
                        break;
                    case "tag":
                        filters.remove(new TagFilter(label.getText()));
                        break;
                    case "attribute":
                        filters.remove(new AttributeFilter(label.getText()));
                        break;
                }
                updateFilters();
                list.getChildren().remove(label);
                elements.add(label.getText());
            });

            switch (type) {
                case "group":
                    filters.add(new GroupFilter(choiceBox.getValue()));
                    break;
                case "tag":
                    filters.add(new TagFilter(choiceBox.getValue()));
                    break;
                case "attribute":
                    filters.add(new AttributeFilter(choiceBox.getValue()));
                    break;
            }

            label.setStyle("-fx-cursor: hand;");
            list.getChildren().add(list.getChildren().size()-1, label);
            list.getChildren().remove(choiceBox);
            elements.remove(choiceBox.getValue());

            updateFilters();
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

    private void updateFilters() {
        restoreContacts();
        for (Filter f: filters) {
            contacts = f.filter(contacts);
        }
        try {
            updateView();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
