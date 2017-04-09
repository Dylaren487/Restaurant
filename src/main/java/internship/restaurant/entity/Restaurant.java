package internship.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Admin on 4/5/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {
    long id;
    String name;
    String description;
    String[] monOpeningHour;
    String[] tueOpeningHour;
    String[] wedOpeningHour;
    String[] thuOpeningHour;
    String[] friOpeningHour;
    String[] satOpeningHour;
    String[] sunOpeningHour;
    double latitude;
    double longitude;

    public Restaurant() {

    }

    public Restaurant(long id, String name, String description, String[] monOpeningHour, String[] tueOpeningHour, String[] wedOpeningHour, String[] thuOpeningHour, String[] friOpeningHour, String[] satOpeningHour, String[] sunOpeningHour, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.monOpeningHour = monOpeningHour;
        this.tueOpeningHour = tueOpeningHour;
        this.wedOpeningHour = wedOpeningHour;
        this.thuOpeningHour = thuOpeningHour;
        this.friOpeningHour = friOpeningHour;
        this.satOpeningHour = satOpeningHour;
        this.sunOpeningHour = sunOpeningHour;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMonOpeningHour(String[] monOpeningHour) {
        this.monOpeningHour = monOpeningHour;
    }

    public void setTueOpeningHour(String[] tueOpeningHour) {
        this.tueOpeningHour = tueOpeningHour;
    }

    public void setWedOpeningHour(String[] wedOpeningHour) {
        this.wedOpeningHour = wedOpeningHour;
    }

    public void setThuOpeningHour(String[] thuOpeningHour) {
        this.thuOpeningHour = thuOpeningHour;
    }

    public void setFriOpeningHour(String[] friOpeningHour) {
        this.friOpeningHour = friOpeningHour;
    }

    public void setSatOpeningHour(String[] satOpeningHour) {
        this.satOpeningHour = satOpeningHour;
    }

    public void setSunOpeningHour(String[] sunOpeningHour) {
        this.sunOpeningHour = sunOpeningHour;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getMonOpeningHour() {
        return monOpeningHour;
    }

    public String[] getTueOpeningHour() {
        return tueOpeningHour;
    }

    public String[] getWedOpeningHour() {
        return wedOpeningHour;
    }

    public String[] getThuOpeningHour() {
        return thuOpeningHour;
    }

    public String[] getFriOpeningHour() {
        return friOpeningHour;
    }

    public String[] getSatOpeningHour() {
        return satOpeningHour;
    }

    public String[] getSunOpeningHour() {
        return sunOpeningHour;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
