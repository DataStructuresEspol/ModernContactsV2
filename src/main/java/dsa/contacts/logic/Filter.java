package dsa.contacts.logic;

import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Contact;

import java.util.Objects;

public abstract class Filter {
    protected String value;

    public Filter(String value) {
        this.value = value;
    }

    public Filter() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return Objects.equals(value, filter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public abstract ArrayList<Contact> filter(ArrayList<Contact> contacts);

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
