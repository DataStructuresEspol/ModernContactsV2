package dsa.contacts.logic;

import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Contact;

public class Favorite extends Filter{
    public Favorite() {
        super();
    }

    @Override
    public ArrayList<Contact> filter(ArrayList<Contact> contacts) {
        ArrayList<Contact> result = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.isFavorite()) {
                result.add(contact);
            }
        }
        return result;
    }
}
