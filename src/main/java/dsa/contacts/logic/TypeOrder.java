package dsa.contacts.logic;

import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Company;
import dsa.contacts.model.Contact;
import dsa.contacts.model.Person;

public class TypeOrder extends Filter{
    @Override
    public ArrayList<Contact> filter(ArrayList<Contact> contacts) {
        ArrayList<Contact> filtered;

        switch (this.value) {
            case "personFirst":
                filtered = this.getPerson(contacts);
                filtered.addAll(this.getCompany(contacts));
                break;
            case "companyFirst":
                filtered = this.getCompany(contacts);
                filtered.addAll(this.getPerson(contacts));
                break;
            default:
                filtered = contacts;
        }
        return filtered;
    }

    public ArrayList<Contact> getPerson(ArrayList<Contact> contacts) {
        ArrayList<Contact> filtered = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact instanceof Person) {
                filtered.add(contact);
            }
        }

        return filtered;
    }

    public ArrayList<Contact> getCompany(ArrayList<Contact> contacts) {
        ArrayList<Contact> filtered = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact instanceof Company) {
                filtered.add(contact);
            }
        }

        return filtered;
    }

    public void setPersonFirst() {
        this.value = "personFirst";
    }

    public void setCompanyFirst() {
        this.value = "companyFirst";
    }

    public void setNoOrder() {
        this.value = "";
    }

    public boolean equals(TypeOrder typeOrder) {
        return this.value.equals(typeOrder.value);
    }
}
