
package dsa.contacts.model;
import dsa.contacts.model.exceptions.DateException;

public class MyDate {
    private int year;
    private int month;
    private int day;
    private String dateType;
    
    public MyDate(int year, int month, int day, String dateType){
        this.year = year;
        this.month = month;
        this.day = day;
        this.dateType = dateType;
    }
    
    public int getYear(){return year;}
    
    public int getMonth(){return month;}
    
    public int getDay(){return day;}
    
    public String getDateType(){return dateType;}
    
    public String getDate(){return String.format("%d/%d/%d", year,month,day);}
    
    public void setYear(int year){this.year = year;}
    
    public void setMonth(int month) throws DateException{
        if (month > 12 || month <= 0){throw new DateException();}
        this.month = month;
    }
    
    public void setDay(int day) throws DateException{
        boolean c1 = (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month== 10 || month == 12);
        boolean c2 = !c1 && month != 2;
        if(day <= 0){throw new DateException();}
        if(c1 && day > 31){throw new DateException();}
        if (c2 && day > 30){throw new DateException();}
        if (month == 2 && day > 28){throw new DateException();}
        this.day = day;
    }
    
    public void setDate(int year, int month, int day, String dateType) throws DateException{
        setYear(year);
        setMonth(month);
        setDay(day);
        setDateType(dateType);
    }
    
    public void setDateType(String dateType){this.dateType = dateType;}
    
}
