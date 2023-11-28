
package dsa.contacts.model;

import java.io.Serializable;
import java.util.List;


public class Person extends Contact{
    private String lastName;
    
    public Person(){super();}
    public Person(String name, String lastName, List<Phone> phones){
        super(name, phones);
        this.lastName = lastName;
    }
    
    public Person(String name, String lastName, Phone phone){
        super(name, phone);
        this.lastName = lastName;
    }
    
    public String getLastName(){return lastName;}
    
    public void setLastName(String lastName){this.lastName = lastName;}
}
