package dsa.contacts.logic;

import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Contact;
import dsa.contacts.model.Person;

public class isPerson extends Filter{
    public isPerson(String value) {
        super(value);
    }

    public isPerson() {
        super();
    }

    public boolean equals(Object o) {
        return o instanceof isPerson;
    }

    @Override
    public ArrayList<Contact> filter(ArrayList<Contact> contacts) {
        ArrayList<Contact> result = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact instanceof Person) {
                result.add(contact);
            }
        }
        return result;
    }
}
