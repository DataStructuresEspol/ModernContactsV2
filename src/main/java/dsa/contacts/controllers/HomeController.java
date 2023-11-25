package dsa.contacts.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import dsa.contacts.ds.ArrayList;

public class HomeController {
    private HomeContactShower contactShower;

    @FXML
    private ScrollPane contacts;


    @FXML
    public void initialize() {
        this.contactShower = HomeContactShower.getInstance();
        ArrayList<HBox> contactCards = contactShower.buildContactCards();
        for (HBox contactCard : contactCards) {
            contacts.setContent(contactCard);
        }
    }
}
