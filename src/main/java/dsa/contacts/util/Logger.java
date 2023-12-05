
package dsa.contacts.util;

import dsa.contacts.App;
import dsa.contacts.model.Admin;
import dsa.contacts.model.User;
import dsa.contacts.model.exceptions.AdreadyCreatedUser;
import dsa.contacts.model.exceptions.InvalidPasswordException;
import dsa.contacts.model.exceptions.UserNotFoundException;
import dsa.contacts.model.exceptions.ValidationException;
import java.io.IOException;

public class Logger {
    public static User loggedUser;
    public static Admin loggedAdmin;
    
    public static boolean validatePassword(User u, String password) throws InvalidPasswordException{
        if (password.isBlank()){throw new InvalidPasswordException("NO HAS LLENADO LA CASILLA");}
        return u.getPassword().equals(password);
    }
    
    public static User findUser(User u) throws ValidationException{
        int index = App.users.indexOf(u);
        if (index >= 0){
            return App.users.get(index);
        }
        else{throw new UserNotFoundException();}
    }
    
    public static void logIn(String userName, String password) throws ValidationException{
        if (userName.isBlank()){throw new UserNotFoundException("NO HAS LLENADO LA CASILLA");}
        User u = findUser(new User(userName, password));
        if (validatePassword(u, password)){loggedUser = u;}
        else{throw new InvalidPasswordException("CONTRASEÑA INCORRECTA");}
        if(u.isAdmin()){
            loggedUser = (Admin)u;
            loggedAdmin = (Admin)u;}
    }
    
    public static void registUser(String userName, String password) throws IOException, UserNotFoundException, ValidationException{
        if (userName.isBlank()){throw new UserNotFoundException("NO HAS LLENADO LA CASILLA");}
        if (password.isBlank()){throw new InvalidPasswordException("NO HAS LLENADO LA CASILLA");}
        if (password.length()<8){throw new InvalidPasswordException("LA CONTRASEÑA DEBE TENER AL MENOS 8 DÍGITOS");}
        if (App.users.contains(new User(userName, password))){throw new AdreadyCreatedUser();}
        App.users.add(new User(userName, password));
        App.save();
    }
}
