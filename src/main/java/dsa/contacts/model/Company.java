
package dsa.contacts.model;

import java.util.List;


public class Company extends Contact{
    public Company(String name, List<Phone> phones){
        super(name, phones);
    }
    
    public Company(String name, Phone phones){
        super(name, phones);
    }
    
    public Company(){super();}
}
