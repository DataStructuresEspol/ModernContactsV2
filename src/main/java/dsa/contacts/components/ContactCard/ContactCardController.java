package dsa.contacts.components.ContactCard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import dsa.contacts.model.Contact;

public class ContactCardController {
    private Contact contact;

    @FXML
    private Circle circle;

    @FXML
    private ImageView favorite;

    @FXML
    private Label group;

    @FXML
    private Label name;

    @FXML
    private HBox tags;

    @FXML
    public void initialize() {
    }

    public ContactCardController(Contact contact) {
        this.contact = contact;
    }

}
