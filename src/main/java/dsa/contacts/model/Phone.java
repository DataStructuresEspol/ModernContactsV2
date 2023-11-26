
package dsa.contacts.model;

import dsa.contacts.model.exceptions.PhoneException;


public class Phone {
    private String num;
    private String phoneType;
    private String countryCode;
    
    public Phone(String num, String phoneType, String countryCode) throws PhoneException{
        setNum(num);
        this.phoneType = phoneType;
        this.countryCode = countryCode;
    }
    
    public String getNum(){return num;}
    
    public String getPhoneType(){return phoneType;}
    
    public String getCountryCode(){return countryCode;}
    
    public void setNum(String num) throws PhoneException{
        String newNum;
        if (num.startsWith("0")){newNum = num;}
        else{newNum = "0" + num;}
        if (newNum.length()!=10){throw new PhoneException();}
        this.num = newNum;
    }
    
    public void setPhoneType(String phoneType){this.phoneType = phoneType;}
    
    public void setCountryCode(String countryCode){this.countryCode = countryCode;}
    
}
