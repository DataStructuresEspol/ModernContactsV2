package dsa.contacts.logic;

import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Contact;

public class GroupFilter extends Filter{
    public GroupFilter(String value) {
        super(value);
    }

    @Override
    public ArrayList<Contact> filter(ArrayList<Contact> contacts) {
        ArrayList<Contact> filteredContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            for (String group: contact.getJoinedGroups()) {
                if (group.equals(this.value)) {
                    filteredContacts.add(contact);
                    break;
                }
            }
        }
        return filteredContacts;
    }
}
