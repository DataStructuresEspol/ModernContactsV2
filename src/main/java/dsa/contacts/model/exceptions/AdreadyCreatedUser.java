
package dsa.contacts.model.exceptions;

public class AdreadyCreatedUser extends ValidationException{


    public AdreadyCreatedUser() {
        super("ESTE USUARIO YA HA SIDO CREADO");
    }

}
