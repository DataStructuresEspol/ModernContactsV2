
package dsa.contacts.model;

import java.io.Serializable;


public class Address implements Serializable{
    private String description;
    private String addressType;
    private String link;
    
    public Address(String description, String addressType){
        this.description = description;
        this.addressType = addressType;
    }
    
    public Address(String description, String addressType, String link){
        this(description, addressType);
        this.link = link;
    }
    
    public String getDescription(){return description;}
    
    public String getAddressType(){return addressType;}
    
    public String getLink(){return link;}
    
    public void setDescription(String desription){this.description = description;}
    
    public void setAddressType(String addressType){this.addressType = addressType;}
    
    public void setLink(String link){this.link = link;}
}
