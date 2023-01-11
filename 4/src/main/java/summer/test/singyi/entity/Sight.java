package summer.test.singyi.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sight")
public class Sight{

    private String sightName;
    private String zone;
    private String catagory;
    private String photoURL = null;
    private String description;
    private String address;

    public Sight(){

    }

    public void setSightName(String input){
        sightName = input;
    }

    public String getSightName(){
        return sightName;
    }

    public void setZone(String input){
        zone = input;
    }

    public String getZone(){
        return zone;
    }

    public void setCatagory(String input){
        catagory = input;
    }

    public String getCatagory(){
        return catagory;
    }

    public void setPhotoURL(String input){
        photoURL = input;
    }

    public String getPhotoURL(){
        return photoURL;
    }

    public void setDescription(String input){
        description = input;
    }

    public String getDescription(){
        return description;
    }

    public void setAddress(String input){
        address = input;
    }

    public String getAddress(){
        return address;
    }

    public String toString(){
        return "\nSightName: " + sightName + "\nZone: " + zone + "\nCatagory: " + catagory
                + "\nphotoURL: \n" + photoURL + "\nDescription: " + description + "\nAddress: " + address;
    }
}