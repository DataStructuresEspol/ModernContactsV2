
package dsa.contacts.util;

import dsa.contacts.App;
import dsa.contacts.model.User;
import dsa.contacts.model.exceptions.ValidationException;

public class Logger {
    public static User loggedUser;
    
    public static boolean validatePassword(User u, String password){
        return u.getPassword().equals(password);
    }
    
    public static User findUser(String userName) throws ValidationException{
        int index = App.users.indexOf(userName);
        if (index >= 0){
            return App.users.get(index);
        }
        else{throw new ValidationException("No se encontró el usuario");}
    }
    
    public static void logIn(String userName, String password) throws ValidationException{
        User u = findUser(userName);
        if (validatePassword(u, password)){loggedUser = u;}
        else{throw new ValidationException("Contraseña incorrecta");}
    }
}
