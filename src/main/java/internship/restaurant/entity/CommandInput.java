package internship.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Admin on 4/8/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommandInput {
    Double latitude;
    Double longitude;
    Double radius;
    String dayTime;
    String name;
    String description;

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setRadius(Double radius) {
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

    public Double getLatitude() {

        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getRadius() {
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

    public CommandInput(Double latitude, Double longitude, Double radius, String dayTime, String name, String description) {

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
