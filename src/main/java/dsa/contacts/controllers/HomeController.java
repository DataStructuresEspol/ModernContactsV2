package dsa.contacts.controllers;

import dsa.contacts.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.HashSet;
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
    private void initialize() {
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
}
