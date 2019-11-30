package Application;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.PropertyResourceBundle;

public class propertyModel {
    @FXML
    ArrayList<Property> propertys = new ArrayList<Property>();

    @FXML
    public void addProperty(int propertyId, String description, String address, String category, String locationGeneral,
                            String locationSpecific, String BER, String eircode, double price) {
        Property propertyNew = new Property(propertyId, description, address, category, locationGeneral, locationSpecific,
                                            BER, eircode, price);
        propertys.add(propertyNew);
    }

    @FXML
    public void loadProperty() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("property.xml"));
        propertys = (ArrayList<Property>) is.readObject();
        is.close();
    }

    @FXML
    public String listAllProperties() {
        String displayPropertys ="";
        for(int i = 0; i < propertys.size(); i++){
            displayPropertys += "\n"+ i + ": " + propertys.get(i);
        }
        return displayPropertys;
    }

    @FXML
    public String listDetailProperties(int index) {
         String displayDetail;
         displayDetail = " Property Number " + index + ": \n" + propertys.get(index);
         return displayDetail;
    }


    @FXML
    public String listAllLowDetailProperties() {
        String displayPropertys ="";
        for(int i = 0; i < propertys.size(); i++){
            displayPropertys += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                                 "€" + propertys.get(i).getPrice() + "\n";
        }
        return displayPropertys;
    }

    @FXML
    public String listAllPropertiesSearchCountyPropertyType(String county, String propertyType) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getLocationGeneral().equals(county) && propertys.get(i).getCategory().equals(propertyType)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCountyMin(String county, double min) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getLocationGeneral().equals(county) && propertys.get(i).getPrice() > min) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCounty(String county) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getLocationGeneral().equals(county)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCountyMax(String county,double max) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getLocationGeneral().equals(county) && propertys.get(i).getPrice() < max) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }
    @FXML
    public String listAllPropertiesSearchCountyMinMax(String county,double min, double max) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getLocationGeneral().equals(county) && propertys.get(i).getPrice() < max && propertys.get(i).getPrice() > min) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCountyMinMaxType(String county,double min, double max,String type) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getLocationGeneral().equals(county) && propertys.get(i).getPrice() < max && propertys.get(i).getPrice() > min && propertys.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchMinMax(double min, double max) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getPrice() < max && propertys.get(i).getPrice() > min) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchMinMaxType(double min, double max,String type) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getPrice() < max && propertys.get(i).getPrice() > min && propertys.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }
    @FXML
    public String listAllPropertiesSearchMinType(double min, String type) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getPrice() > min && propertys.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchMaxType(double max, String type) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getPrice() < max && propertys.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCountyMaxType(String county, double max, String type) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getLocationGeneral().equals(county) && propertys.get(i).getPrice() < max && propertys.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchMax(double max) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getPrice() < max) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchMin(double min) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getPrice() > min) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchType(String type) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public String listAllPropertiesSearchCountyMinType(String county, double min, String type) {
        String displayProperties ="";
        for(int i = 0; propertys.size() > i; i++){
            if(propertys.get(i).getLocationGeneral().equals(county) && propertys.get(i).getPrice() > min && propertys.get(i).getCategory().equals(type)) {
                displayProperties += "\nProperty Number "+ i + ": \n" + propertys.get(i).getAddress() + "\n" + propertys.get(i).getLocationGeneral() + "\n" +
                        "€" + propertys.get(i).getPrice() + "\n";
            }
        }
        return displayProperties;
    }

    @FXML
    public Property propertyDetails(String index) {
        int id1;
        try {
            id1 = Integer.parseInt(index);
        }
        catch (Exception e){
            return null;
        }
        for(Property home:propertys) {
            if (home.getPropertyId() == id1) {
                return home;
            }
        }
        return null;
    }

    @FXML
    public void deleteProperty(int index){
            propertys.remove(index);
    }

    @FXML
    public boolean updateProperty(int index,int propertyId, String description, String address, String category, String locationGeneral, String locationSpecific, String BER, String eircode, double price){
        if(index <= propertys.size()){
            if(propertyId != propertys.get(index).getPropertyId()) {
                propertys.get(index).setPropertyId(propertyId);
            }
            propertys.get(index).setDescription(description);
            propertys.get(index).setAddress(address);
            propertys.get(index).setCategory(category);
            propertys.get(index).setLocationGeneral(locationGeneral);
            propertys.get(index).setLocationSpecific(locationSpecific);
            propertys.get(index).setBER(BER);
            propertys.get(index).setEircode(eircode);
            propertys.get(index).setPrice(price);
            return true;
        }
        else{
            return false;
        }
    }

    @FXML
    public String listPropertyType(String category){
        String propertyType ="";
        for(Property home:propertys){
            if(home.getCategory().equals(category)) {
                propertyType = "\n" + home;
            }
        }
        return propertyType;
    }

    @FXML
    public String listLocationGeneral(String location){

        String locationGeneral = "";

        for(Property home:propertys){
            if(home.getCategory().equals(location)) {
                locationGeneral = "\n" + home;
            }
        }

        return locationGeneral;
    }
}
