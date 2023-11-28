
package dsa.contacts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class User implements Serializable{
    private String userName;
    private String password;
    private String pic;
    private LinkedList<Contact> contacts;
    private List<String> groups;
    private Types phoneTypes;
    private Types addressTypes;
    private Types emailTypes;
    private Types socialMediaTypes;
    private Types dateTypes;
    
    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        contacts = new LinkedList<Contact>();
        groups = new ArrayList<String>();
        phoneTypes = new Types();
        emailTypes = new Types();
        socialMediaTypes = new Types();
        dateTypes = new Types();
        addressTypes = new Types();
    }
    
    public String getUserName(){return userName;}
    
    public String getPassword(){return password;}
    
    public List<String> getGroups(){return groups;}
    
    public LinkedList<Contact> getContacts(){return contacts;}
    
    public String getPic(){return pic;}
    
    public Types getPhoneTypes(){return phoneTypes;}
    
    public Types getAddressTypes(){return addressTypes;}
    
    public Types getEmailTypes(){return emailTypes;}
    
    public Types getSocialMediaTypes(){return socialMediaTypes;}
    
    public Types dateTypes(){return dateTypes;}
    
    public boolean isAdmin(){return true;}
    
    public void setUserName(String userName){this.userName = userName;}
    
    public void setPassword(String password){this.password = password;}
    
    public void setPic(String pic){this.pic = pic;}
    
    @Override
    public boolean equals(Object o){
        User u = (User)o;
        return userName.equals(u.getUserName().trim());
    }
    
    public void addGroup(String group){groups.add(group.toUpperCase());}
}
