package internship.restaurant.service;

import internship.restaurant.dao.RestaurantDao;
import internship.restaurant.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 4/7/2017.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantDao restaurantDao;

    public List<Restaurant> getRestaurants() {
        return restaurantDao.getRestaurants();
    }

    public Restaurant getRestaurantById(long id) {
        return restaurantDao.getRestaurantById(id);
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantDao.addRestaurant(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant) {
        restaurantDao.updateRestaurant(restaurant);
    }

    public void deleteRestaurant(long id) {
        restaurantDao.deleteRestaurant(id);
    }

    public void deleteAllRestaurants() {
        restaurantDao.deleteAllRestaurants();
    }

    public List<Restaurant> searchByNameAndDescription(String name, String description) {
        return restaurantDao.searchByNameAndDescription(name, description);
    }

    public List<Restaurant> searchByLocation(double latitude, double longitude, double radius) {
        return restaurantDao.searchByLocation(latitude, longitude, radius);
    }

    public List<Restaurant> searchOpening(String time) {
        return restaurantDao.searchOpening(time);
    }

    public List<Restaurant> searchOpeningNearby(double latitude, double longitude, double radius, String time) {
        return restaurantDao.searchOpeningNearby(latitude, longitude, radius, time);
    }

    public List<Restaurant> searchNearbyByNameAndDescription(double latitude, double longitude, double radius, String name, String description) {
        return restaurantDao.searchNearbyByNameAndDescription(latitude, longitude, radius, name, description);
    }

    public List<Restaurant> searchOpeningByNameAndDescription(String time, String name, String description) {
        return restaurantDao.searchOpeningByNameAndDescription(time, name, description);
    }

    public List<Restaurant> searchNearbyOpeningByNameAndDescription(double latitude, double longitude, double radius, String time, String name, String description) {
        return restaurantDao.searchNearbyOpeningByNameAndDescription(latitude, longitude, radius, time, name, description);
    }
}
