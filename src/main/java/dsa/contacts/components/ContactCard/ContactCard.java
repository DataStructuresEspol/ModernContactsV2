package dsa.contacts.components.ContactCard;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import dsa.contacts.model.Contact;

public class ContactCard {
    public static ContactCard instance;

    public HBox buildContactCard(Contact contact) {
        try {
            FXMLLoader fxml = FXMLLoader.load(ContactCard.class.getResource("src/main/java/resources/dsa/contacts/contactCard.fxml"));
            fxml.setController(new ContactCardController(contact));
            return fxml.load();

        } catch (
                IOException e) {
            System.out.println("There was an error loading the contact " + contact + ". " + e.getMessage());
        }
        return null;
    }

    private ContactCard() {}

    public static ContactCard getInstance() {
        if (instance == null) {
            instance = new ContactCard();
        }
        return instance;
    }
}
