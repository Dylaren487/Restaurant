package internship.restaurant.dao;

import internship.restaurant.entity.Restaurant;

import java.util.List;

/**
 * Created by Admin on 4/7/2017.
 */
public interface RestaurantDao {
    List<Restaurant> getRestaurants();

    Restaurant getRestaurantById(long id);

    void addRestaurant(Restaurant restaurant);

    void updateRestaurant(Restaurant restaurant);

    void deleteAllRestaurants();

    void deleteRestaurant(long id);

    List<Restaurant> searchByNameAndDescription(String name,String description);

    List<Restaurant> searchByLocation(double latitude, double longitude, double radius);

    List<Restaurant> searchOpeningNearby(double latitude, double longitude, double radius, String time);

    List<Restaurant> searchNearbyByNameAndDescription(double latitude, double longitude, double radius, String name, String description);

    List<Restaurant> searchOpeningByNameAndDescription(String time, String name, String description);

    List<Restaurant> searchNearbyOpeningByNameAndDescription(double latitude, double longitude, double radius, String time, String name, String description);
}
