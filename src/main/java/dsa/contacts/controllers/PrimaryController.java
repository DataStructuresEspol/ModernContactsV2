package dsa.contacts.controllers;

import java.io.IOException;
import javafx.fxml.FXML;

import dsa.contacts.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
