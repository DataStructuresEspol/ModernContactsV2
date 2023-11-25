package dsa.contacts.model;

import java.io.Serializable;

import dsa.contacts.ds.ArrayList;

public abstract class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private ArrayList<String> phones;
    private ArrayList<String> emails;
    private ArrayList<String> addresses;
    private boolean favorite;

    public Contact(int id) {
        this.id = id;
        this.phones = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.addresses = new ArrayList<>();
        this.favorite = false;
    }

}
