package dsa.contacts.controllers;

import dsa.contacts.ds.ArrayList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import dsa.contacts.App;
import static dsa.contacts.App.loadFXML;
import dsa.contacts.logic.*;
import dsa.contacts.model.Contact;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController {
    private HashSet<String> groups;
    private HashSet<String> tags;
    private HashSet<String> attributes;

    @FXML
    private TextField searchbar;

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
    ChoiceBox<String> typeOrder;

    @FXML
    ChoiceBox<String> attributeOrder;
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
    
    public static Stage settings;

    // Filter
    private ArrayList<Filter> filters;
    @FXML
    private ImageView adminView;
    @FXML
    private void initialize() throws FileNotFoundException {
        if (!Logger.loggedUser.isAdmin()){adminView.setVisible(false);}
        groups = new HashSet<>();
        tags = new HashSet<>();
        attributes = new HashSet<>();
        filters = new ArrayList<>();

        nameOrder.getItems().addAll("Ascendente", "Descendente");
        typeOrder.getItems().addAll("Primero personas", "Primero empresas");
        attributeOrder.getItems().addAll("Ascendente", "Descendente");
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
        // Order by amount of attributes
        attributeOrder.setOnAction(order -> {
            if (attributeOrder.getValue().equals("Ascendente")) {
                contacts.sort((c1, c2) -> {
                    int c1AttributesSize = getAttributesSize(c1.getFields());
                    int c2AttributesSize = getAttributesSize(c2.getFields());
                    return c2AttributesSize - c1AttributesSize;
                });
            } else {
                contacts.sort((c1, c2) -> {
                    int c1AttributesSize = getAttributesSize(c1.getFields());
                    int c2AttributesSize = getAttributesSize(c2.getFields());
                    return c1AttributesSize - c2AttributesSize;
                });
            }
            try {
                updateView();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        // Order by type
        Filter typeFilter = new TypeOrder();
        ((TypeOrder) typeFilter).setNoOrder();
        filters.add(typeFilter);

        typeOrder.setOnAction(order -> {
            if (typeOrder.getValue().equals("Primero personas")) {
                ((TypeOrder) typeFilter).setPersonFirst();
            } else if (typeOrder.getValue().equals("Primero empresas")) {
                ((TypeOrder) typeFilter).setCompanyFirst();
            } else {
                ((TypeOrder) typeFilter).setNoOrder();
            }
            updateFilters();
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

        // filter by searchbar
        Filter sbf = new SearchBar("");
        filters.add(sbf);
        searchbar.setOnKeyReleased(search -> {
            for (Filter f: filters) {
                if (f instanceof SearchBar) {
                    f.setValue(searchbar.getText());
                    break;
                }
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
    private void settingsMenu() throws IOException {
        // Crear la nueva ventana
        settings = new Stage();
        settings.setTitle("Configuraciones");
        // Crear la escena y agregar el contenido
        Scene scene = new Scene(loadFXML("settings"), 780, 600);
        settings.setScene(scene);

        // Mostrar la nueva ventana
        settings.show();

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

    @FXML
    private void adminUsers(MouseEvent event) throws IOException {
        App.setRoot("adminUsers");
    }
    private int getAttributesSize(HashMap<String, Object> attributes) {
        int attributesSize = 0;
        for (String attribute: attributes.keySet()) {
            if (attributes.get(attribute) instanceof String) {
                if (!attributes.get(attribute).equals("")) {
                    attributesSize++;
                }
            } else if (attributes.get(attribute) instanceof List) {
                if (!((List<?>) attributes.get(attribute)).isEmpty()) {
                    attributesSize++;
                }
            }
        }
        return attributesSize;
    }
}
