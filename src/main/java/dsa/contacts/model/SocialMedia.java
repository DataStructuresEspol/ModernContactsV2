
package dsa.contacts.model;

import java.io.Serializable;


public class SocialMedia implements Serializable, Info{
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
    
    @Override
    public boolean equals(Object o){
        SocialMedia s = (SocialMedia)o;
        return user.equals(s.getUser()) && socialMedia.equalsIgnoreCase(s.getSocialMedia());
    }

    @Override
    public String getType() {
        return getSocialMedia();
    }

    @Override
    public String getInfo() {
        return getUser();
    }
}
