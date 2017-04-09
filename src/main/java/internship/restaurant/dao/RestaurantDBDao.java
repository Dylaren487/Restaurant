package internship.restaurant.dao;

import com.mongodb.DBAddress;
import com.mongodb.MongoClient;
import internship.restaurant.entity.Restaurant;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/7/2017.
 */
@Profile("database")
@Repository
public class RestaurantDBDao extends AbstractRestaurantDao implements RestaurantDao {

    private MongoOperations mongoOperations;

    @PostConstruct
    private void mock() {
        mongoOperations = new MongoTemplate(new MongoClient(new DBAddress("127.0.0.1:27017")), "restaurantDB");
    }

    public List<Restaurant> getRestaurants() {
        return mongoOperations.findAll(Restaurant.class);
    }

    public Restaurant getRestaurantById(long id) {
        BasicQuery query = new BasicQuery("{ id : " + id + " }");
        return mongoOperations.findOne(query, Restaurant.class);
    }

    public long generateID() {
        try {
            Query query = new Query();
            query.with(new Sort(Sort.Direction.DESC, "_id"));
            query.limit(1);
            Restaurant maxIdUser = mongoOperations.findOne(query, Restaurant.class);
            long nextId = maxIdUser.getId() + 1;
            return nextId;
        } catch (NullPointerException e) {
            return 1;
        }
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurant.setId(generateID());
        mongoOperations.insert(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant) {
        mongoOperations.save(restaurant);
    }

    public void deleteAllRestaurants() {
        mongoOperations.dropCollection(Restaurant.class);
    }

    public void deleteRestaurant(long id) {
        BasicQuery query = new BasicQuery("{ id : " + id + " }");
        mongoOperations.remove(query, Restaurant.class);
    }

    public List<Restaurant> searchByNameAndDescription(String name, String description) {
        List<Restaurant> returnList = new ArrayList<>();
        List<Restaurant> restaurants = getRestaurants();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().contains(name) && restaurant.getDescription().contains(description)) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchByLocation(double latitude, double longitude, double radius) {
        List<Restaurant> returnList = new ArrayList<>();
        List<Restaurant> restaurants = getRestaurants();
        for (Restaurant restaurant : restaurants) {
            if (findDistance(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude()) <= radius) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchOpeningNearby(double latitude, double longitude, double radius, String time) {
        List<Restaurant> returnList = new ArrayList<>();
        List<Restaurant> restaurants = getRestaurants();
        for (Restaurant restaurant : restaurants) {
            if (isOpen(time, restaurant) && findDistance(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude()) <= radius) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchNearbyByNameAndDescription(double latitude, double longitude, double radius, String name, String description) {
        List<Restaurant> returnList = new ArrayList<>();
        List<Restaurant> restaurants = getRestaurants();
        for (Restaurant restaurant : restaurants) {
            if (findDistance(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude()) <= radius && restaurant.getName().contains(name) && restaurant.getName().contains(description)) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchOpeningByNameAndDescription(String time, String name, String description) {
        List<Restaurant> returnList = new ArrayList<>();
        List<Restaurant> restaurants = getRestaurants();
        for (Restaurant restaurant : restaurants) {
            if (isOpen(time, restaurant) && restaurant.getName().contains(name) && restaurant.getName().contains(description)) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }

    public List<Restaurant> searchNearbyOpeningByNameAndDescription(double latitude, double longitude, double radius, String time, String name, String description) {
        List<Restaurant> returnList = new ArrayList<>();
        List<Restaurant> restaurants = getRestaurants();
        for (Restaurant restaurant : restaurants) {
            if (isOpen(time, restaurant) && restaurant.getName().contains(name) && restaurant.getName().contains(description) && findDistance(latitude, longitude, restaurant.getLatitude(), restaurant.getLongitude()) <= radius) {
                returnList.add(restaurant);
            }
        }
        return returnList;
    }
}
