package dsa.contacts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Contact implements Serializable{
    private String name;
    private List<Phone> phones;
    private String profilePic;
    private List<Address> addresses;
    private List<Email> emails;
    private List<SocialMedia> socialMedias;
    private ArrayList<String> pics;
    private List<MyDate> dates;
    private LinkedList<Contact> relatedContacts;
    private List<String> joinedGroups;
    private List<String> tags;
    private boolean favorite;
    
    public Contact(String name, List<Phone> phones){
        this();
        this.name = name;
        this.phones = phones;
    }
    
    public Contact(String name, Phone phone){
        this();
        this.name = name;
        phones = new ArrayList<Phone>();
        phones.add(phone);
    }
    
    public Contact(){
        phones = new ArrayList<>();
        addresses = new ArrayList<>();
        emails = new ArrayList<>();
        socialMedias = new ArrayList<>();
        pics = new ArrayList<>();
        dates = new ArrayList<>();
        joinedGroups = new ArrayList<>();
        tags = new ArrayList<>();
        relatedContacts = new LinkedList<>();
    }
    
    public String getName(){return name;}
    
    public List<Phone> getPhones(){return phones;}
    
    public String getProfilePic(){return profilePic;}
    
    public List<Address> getAddresses(){return addresses;}
    
    public List<Email> getEmails(){return emails;}
    
    public List<SocialMedia> getSocialMedias(){return socialMedias;}
    
    public List<String> getPics(){return pics;}
    
    public List<MyDate> getDates(){return dates;}
    
    public LinkedList<Contact> getRelatedContacts(){return relatedContacts;}
    
    public List<String> getJoinedGroups(){return joinedGroups;}
    
    public List<String> getTags(){return tags;}
    
    public boolean isFavorite(){return favorite;}
    
    public void setNombre(String name){this.name = name;}
    
    public void setProfilePic(String profilePic){
        this.profilePic = profilePic;
        pics.add(0, profilePic);
    }
    
    public void addJoinedGroup(String group){joinedGroups.add(group.toUpperCase());}
    
    public void setFavorite(){favorite = !favorite;}
    
    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<>();
        fields.put("name", name);
        fields.put("phones", phones);
        fields.put("profilePic", profilePic);
        fields.put("addresses", addresses);
        fields.put("emails", emails);
        fields.put("socialMedias", socialMedias);
        fields.put("pics", pics);
        fields.put("dates", dates);
        fields.put("relatedContacts", relatedContacts);
        fields.put("joinedGroups", joinedGroups);
        fields.put("tags", tags);
        fields.put("favorite", favorite);
        return fields;
    };

}
