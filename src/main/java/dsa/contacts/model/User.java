
package dsa.contacts.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class User {
    private String userName;
    private String password;
    private String pic;
    private LinkedList<Contact> contacts;
    private List<String> groups;
    
    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        contacts = new LinkedList<Contact>();
        groups = new ArrayList<String>();
    }
    
    public String getUserName(){return userName;}
    
    public String getPassword(){return password;}
    
    public List<String> getGroups(){return groups;}
    
    public LinkedList<Contact> getContacts(){return contacts;}
    
    public String getPic(){return pic;}
    
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
