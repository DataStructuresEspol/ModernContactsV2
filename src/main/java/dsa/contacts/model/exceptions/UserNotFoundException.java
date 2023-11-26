
package dsa.contacts.model.exceptions;

public class UserNotFoundException extends ValidationException{

    public UserNotFoundException() {
        super("NO SE ENCONTRÓ EL USUARIO");
    }
    
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
