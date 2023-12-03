package dsa.contacts.logic;

import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Contact;

import java.util.HashMap;

public class AttributeFilter extends Filter{
    public AttributeFilter(String value) {
        super(value);
    }

    @Override
    public ArrayList<Contact> filter(ArrayList<Contact> contacts) {
        ArrayList<Contact> filteredContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            HashMap<String, Object> attributes = contact.getFields();
            for (String key : attributes.keySet()) {
                if (attributes.get(key).toString().equals(this.value)) {
                    filteredContacts.add(contact);
                    break;
                }
            }
        }
        return filteredContacts;
    }
}
