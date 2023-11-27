
package dsa.contacts.model;

import java.io.Serializable;


public class SocialMedia implements Serializable{
    private String user;
    private String socialMedia;
    
    public SocialMedia(String user, String socialMedia){
        this.user = user;
        this.socialMedia = socialMedia;
    }
    
    public String getUser(){return user;}
    
    public String getSocialMedia(){return socialMedia;}
    
    public void setUser(String user){this.user = user;}
    
    public void setSocialMedia(String socialMedia){this.socialMedia = socialMedia;}
}
