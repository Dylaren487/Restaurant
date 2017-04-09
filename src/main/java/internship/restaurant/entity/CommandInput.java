package internship.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Admin on 4/8/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommandInput {
    double latitude;
    double longitude;
    double radius;
    String dayTime;
    String name;
    String description;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {

        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRadius() {
        return radius;
    }

    public String getDayTime() {
        return dayTime;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CommandInput(double latitude, double longitude, double radius, String dayTime, String name, String description) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.dayTime = dayTime;
        this.name = name;
        this.description = description;
    }

    public CommandInput(){

    }
}
