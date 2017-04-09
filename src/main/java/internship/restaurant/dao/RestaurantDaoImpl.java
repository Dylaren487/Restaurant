package internship.restaurant.dao;

import internship.restaurant.entity.Restaurant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/7/2017.
 */
@Profile("arraySource")
@Repository
public class RestaurantDaoImpl extends AbstractRestaurantDao implements RestaurantDao {
    private List<Restaurant> restaurants = new ArrayList<>();

    @PostConstruct
    private void mock() {
        List<Restaurant> mockData = new ArrayList<>();
        mockData.add(new Restaurant(1, "Mock1", "First mock restaurant", new String[]{"08002000", "21002200"}, new String[]{"08002000"}, new String[]{"08002000"}, new String[]{"08002000"}, new String[]{"08002000"}, new String[]{"08002000"}, new String[]{"08002000"}, 18.791600, 98.950612));
        mockData.add(new Restaurant(2, "Mock2", "Second mock restaurant", new String[]{"08001300"}, new String[]{"08001500"}, new String[]{"07002300"}, new String[]{"09002000"}, new String[]{"10001800"}, new String[]{"11001900"}, new String[]{"10002000"}, 18.795645, 98.952811));
        mockData.add(new Restaurant(3, "Mock3", "Third mock restaurant", new String[]{"07001300"}, new String[]{"06001800"}, new String[]{"09001500"}, new String[]{"10002300"}, new String[]{"11000000"}, new String[]{"09002000"}, new String[]{"09002000"}, 18.791415, 98.950049));
        mockData.add(new Restaurant(4, "Mock4", "Forth mock restaurant", new String[]{"06001200"}, new String[]{"09001300"}, new String[]{"08001100"}, new String[]{"07001000"}, new String[]{"09002100"}, new String[]{"08002200"}, new String[]{"08002100"}, 18.791963, 98.951669));
        restaurants = mockData;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Restaurant getRestaurantById(long id){
        int index = 0;
        for (Restaurant r : restaurants) {
            if (r.getId() == id) {
                break;
            }
            index++;
        }
        return restaurants.get(index);
    }

    public long generateID() {
        if (restaurants.size() == 0) {
            return 1;
        } else {
            long currentID = 0;
            for (Restaurant restaurant : restaurants) {
                currentID = restaurant.getId();
            }
            return currentID + 1;
        }
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurant.setId(generateID());
        restaurants.add(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant) {
        int index = 0;
        for (Restaurant r : restaurants) {
            if (r.getId() == restaurant.getId()) {
                break;
            }
            index++;
        }
        restaurants.set(index, restaurant);
    }

    public void deleteAllRestaurants(){
        restaurants.clear();
    }

    public void deleteRestaurant(long id) {
        int index = 0;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == id) {
                break;
            }
            index++;
        }
        restaurants.remove(index);
    }

    public List<Restaurant> searchByNameAndDescription(String name,String description) {
        List<Restaurant> returnList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().contains(name)&&restaurant.getDescription().contains(description)) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchByLocation(double latitude, double longitude, double radius) {
        List<Restaurant> returnList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (findDistance(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude()) <= radius) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchOpening(String time){
        List<Restaurant> returnList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (isOpen(time, restaurant)) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchOpeningNearby(double latitude, double longitude, double radius, String time) {
        List<Restaurant> returnList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (isOpen(time, restaurant) && findDistance(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude()) <= radius) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchNearbyByNameAndDescription(double latitude, double longitude, double radius, String name, String description) {
        List<Restaurant> returnList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (findDistance(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude()) <= radius && restaurant.getName().contains(name) && restaurant.getName().contains(description)) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchOpeningByNameAndDescription(String time, String name, String description) {
        List<Restaurant> returnList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (isOpen(time, restaurant) && restaurant.getName().contains(name) && restaurant.getName().contains(description)) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchNearbyOpeningByNameAndDescription(double latitude, double longitude, double radius, String time, String name, String description) {
        List<Restaurant> returnList = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (isOpen(time, restaurant) && restaurant.getName().contains(name) && restaurant.getName().contains(description) && findDistance(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude()) <= radius) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }
}
