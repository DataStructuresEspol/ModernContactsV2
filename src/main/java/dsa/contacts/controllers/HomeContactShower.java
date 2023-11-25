package dsa.contacts.controllers;

import javafx.scene.layout.HBox;

import dsa.contacts.components.ContactCard.ContactCard;
import dsa.contacts.db.DataPersistor;
import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Contact;

public class HomeContactShower {
    private static HomeContactShower instance;
    private ArrayList<Contact> contacts;

    private HomeContactShower() {
        contacts = new ArrayList<>();
        init();
    }

    public void init() {
        DataPersistor dp = DataPersistor.getInstance();
        contacts = dp.loadContacts();
    }

    public static HomeContactShower getInstance() {
        if (instance == null) {
            instance = new HomeContactShower();
        }
        return instance;
    }

    public ArrayList<HBox> buildContactCards() {
        ArrayList<HBox> contactCards = new ArrayList<>();
        ContactCard cc = ContactCard.getInstance();
        for (Contact contact : contacts) {
            HBox contactCard = cc.buildContactCard(contact);
            contactCards.add(contactCard);
        }
        return contactCards;
    }


}
