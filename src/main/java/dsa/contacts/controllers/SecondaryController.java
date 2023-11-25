package dsa.contacts.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import dsa.contacts.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}