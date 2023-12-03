package dsa.contacts.logic;

import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Company;
import dsa.contacts.model.Contact;

public class isCompany extends Filter{
    public isCompany(String value) {
        super(value);
    }

    public isCompany() {
        super();
    }

    public boolean equals(Object o) {
        return o instanceof isCompany;
    }

    @Override
    public ArrayList<Contact> filter(ArrayList<Contact> contacts) {
        ArrayList<Contact> result = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact instanceof Company) {
                result.add(contact);
            }
        }
        return result;
    }
}
