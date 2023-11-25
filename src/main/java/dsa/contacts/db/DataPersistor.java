package dsa.contacts.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dsa.contacts.ds.ArrayList;
import dsa.contacts.model.Company;
import dsa.contacts.model.Contact;
import dsa.contacts.model.Person;

public class DataPersistor {
    private static DataPersistor instance;
    private final String contactsPath = "src/main/resources/dsa/contacts/data/contacts.ser";

    private DataPersistor() {}

    public static DataPersistor getInstance() {
        if (instance == null) {
            instance = new DataPersistor();
        }
        return instance;
    }

    public ArrayList<Contact> loadContacts() {
        /*
        try {
            FileInputStream fis = new FileInputStream(contactsPath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Contact> contacts = (ArrayList<Contact>) ois.readObject();
            ois.close();
            fis.close();
            return contacts;
        } catch (FileNotFoundException e) {
            createFile();
        } catch (IOException e) {
            System.out.println("Input/Output error while loading contacts" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error reading contacts" + e.getMessage());
        }
        return  null;*/
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Person(1));
        contacts.add(new Person(2));
        contacts.add(new Person(3));
        contacts.add(new Company(4));

        return contacts;
    }

    public void createFile() {
        try {
            FileOutputStream fos = new FileOutputStream(contactsPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Contact>());
            oos.close();
            fos.close();
            loadContacts();
        } catch (IOException e) {
            System.out.println("Error initializing file" + e.getMessage());
        }
    }

    public void saveContacts(ArrayList<Contact> contacts) {
        try {
            FileOutputStream fos = new FileOutputStream(contactsPath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contacts);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error saving contacts" + e.getMessage());
        }
    }
}
