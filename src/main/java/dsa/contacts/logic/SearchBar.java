package dsa.contacts.logic;

import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Contact;

public class SearchBar extends Filter{
    public SearchBar(String val) {
        super(val);
    }

    @Override
    public ArrayList<Contact> filter(ArrayList<Contact> contacts) {
        ArrayList<Contact> filtered = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(value.toLowerCase())) {
                filtered.add(c);
            }
        }
        return filtered;
    }
}
