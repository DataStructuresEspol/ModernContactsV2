package dsa.contacts.logic;

import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Contact;

public class TagFilter extends Filter {
    public TagFilter(String value) {
        super(value);
    }

    @Override
    public ArrayList<Contact> filter(ArrayList<Contact> contacts) {
        ArrayList<Contact> filteredContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            for (String tag: contact.getTags()) {
                if (tag.equals(this.value)) {
                    filteredContacts.add(contact);
                    break;
                }
            }
        }
        return filteredContacts;
    }
}
