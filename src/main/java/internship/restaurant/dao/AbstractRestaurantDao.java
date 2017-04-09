package internship.restaurant.dao;

import internship.restaurant.entity.Restaurant;

/**
 * Created by Admin on 4/8/2017.
 */
public class AbstractRestaurantDao {
    public double findDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double deltaLongitude = degreeToRadian(longitude2) - degreeToRadian(longitude1);
        double deltaLatitude = degreeToRadian(latitude2) - degreeToRadian(latitude1);
        double a = Math.pow(Math.sin(deltaLatitude / 2), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(deltaLongitude / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6373 * c;
        return d;
    }

    public double degreeToRadian(double degree) {
        return degree * Math.PI / 180;
    }

    public boolean isOpen(String string, Restaurant restaurant) {
        if (string.charAt(0) == '0') {
            return compareAvailableTime(string, restaurant.getMonOpeningHour());
        } else if (string.charAt(0) == '1') {
            return compareAvailableTime(string, restaurant.getTueOpeningHour());
        } else if (string.charAt(0) == '2') {
            return compareAvailableTime(string, restaurant.getWedOpeningHour());
        } else if (string.charAt(0) == '3') {
            return compareAvailableTime(string, restaurant.getThuOpeningHour());
        } else if (string.charAt(0) == '4') {
            return compareAvailableTime(string, restaurant.getFriOpeningHour());
        } else if (string.charAt(0) == '5') {
            return compareAvailableTime(string, restaurant.getSatOpeningHour());
        } else if (string.charAt(0) == '6') {
            return compareAvailableTime(string, restaurant.getSunOpeningHour());
        }
        return false;
    }

    public boolean compareAvailableTime(String string, String[] openTime) {
        boolean returnBoolean = false;
        if (openTime.length == 0) {
            return false;
        } else {
            int currentHour = Integer.parseInt(string.substring(1, 3));
            int currentMinute = Integer.parseInt(string.substring(3));
            for (int i = 0; i < openTime.length; i++) {
                int startHour = Integer.parseInt(openTime[i].substring(0, 2));
                int startMinute = Integer.parseInt(openTime[i].substring(2, 4));
                int endHour = Integer.parseInt(openTime[i].substring(4, 6));
                int endMinute = Integer.parseInt(openTime[i].substring(6));
                if (((startHour == currentHour) && (startMinute <= currentMinute)) || ((currentHour == endHour) && (currentMinute <= endMinute))) {
                    returnBoolean = true;
                } else if (startHour < currentHour && currentHour < endHour) {
                    returnBoolean = true;
                }
            }
        }
        return returnBoolean;
    }
}
