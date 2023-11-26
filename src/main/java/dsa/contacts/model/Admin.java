
package dsa.contacts.model;

import dsa.contacts.App;
import java.util.List;


public class Admin extends User{
    public Admin(String userName, String password){
        super(userName, password);
    }
    
    @Override
    public boolean isAdmin(){return true;}
    
    public List<User> getUser(){
        return App.users;
    }
}
