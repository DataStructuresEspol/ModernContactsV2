
package dsa.contacts.model;

import dsa.contacts.model.exceptions.EmailException;

public class Email {
    private String email;
    private String emailType;
    
    public Email(String email, String emailType) throws EmailException{
        setEmail(email);
        setEmailType(emailType);
    }
    
    public String getEmail(){return email;}
    
    public String getEmailType(){return emailType;}
    
    public void setEmail(String email) throws EmailException{
        if (!email.contains("@")){throw new EmailException();}
        this.email = email;
    }
    
    public void setEmailType(String emailType){this.emailType = emailType;}
}
