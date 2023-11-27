
package dsa.contacts.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Types implements Serializable{
    private ArrayList<String> types;
    
    public Types(){types = new ArrayList<String>();}
    
    public ArrayList<String> getTypes() {return types;}
    
    public void addType(String type){types.add(type.toUpperCase());}
    
    public String getType(String type){
        type = type.toUpperCase();
        if (types.contains(type)){return type;}
        else{
            addType(type);
            return type;
        }
    }
}
