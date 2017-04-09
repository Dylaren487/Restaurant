package internship.restaurant.controller;

import internship.restaurant.entity.CommandInput;
import internship.restaurant.entity.Restaurant;
import internship.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Admin on 4/7/2017.
 */
@Component
@Path("/restaurant")
public class RestaurantController {
    RestaurantService restaurantService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return Response.ok(restaurants).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurantById(@PathParam("id") long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant == null) {
            return Response.status(404).build();
        }
        return Response.ok(restaurant).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRestaurant(Restaurant restaurant) {
        restaurantService.addRestaurant(restaurant);
        return Response.noContent().build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRestaurant(Restaurant restaurant) {
        if (restaurantService.getRestaurantById(restaurant.getId()) == null) {
            return Response.status(404).build();
        }
        restaurantService.updateRestaurant(restaurant);
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteAllRestaurants() {
        restaurantService.deleteAllRestaurants();
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteRestaurant(@PathParam("id") long id) {
        if (restaurantService.getRestaurantById(id) == null) {
            return Response.status(404).build();
        }
        restaurantService.deleteRestaurant(id);
        return Response.noContent().build();
    }

    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchRestaurant(CommandInput commandInput) {
        if (commandInput.getLatitude() != null && commandInput.getLongitude() != null && commandInput.getRadius() != null && commandInput.getDayTime() != null && commandInput.getName() != null || commandInput.getLatitude() != null && commandInput.getLongitude() != null && commandInput.getRadius() != null && commandInput.getDayTime() != null && commandInput.getDescription() != null) {
            if (commandInput.getName() == null) {
                commandInput.setName("");
            }
            if (commandInput.getDescription() == null) {
                commandInput.setDescription("");
            }
            List<Restaurant> restaurants = restaurantService.searchNearbyOpeningByNameAndDescription(commandInput.getLatitude(), commandInput.getLongitude(), commandInput.getRadius(), commandInput.getDayTime(), commandInput.getName(), commandInput.getDescription());
            return Response.ok(restaurants).build();
        } else if ((commandInput.getDayTime() != null && commandInput.getName() != null) || (commandInput.getDayTime() != null && commandInput.getDescription() != null)) {
            if (commandInput.getName() == null) {
                commandInput.setName("");
            }
            if (commandInput.getDescription() == null) {
                commandInput.setDescription("");
            }
            List<Restaurant> restaurants = restaurantService.searchOpeningByNameAndDescription(commandInput.getDayTime(), commandInput.getName(), commandInput.getDescription());
            return Response.ok(restaurants).build();
        } else if (commandInput.getLatitude() != null && commandInput.getLongitude() != null && commandInput.getRadius() != null && commandInput.getDayTime() != null) {
            List<Restaurant> restaurants = restaurantService.searchOpeningNearby(commandInput.getLatitude(), commandInput.getLongitude(), commandInput.getRadius(), commandInput.getDayTime());
            return Response.ok(restaurants).build();
        } else if (commandInput.getLatitude() != null && commandInput.getLongitude() != null && commandInput.getRadius() != null && commandInput.getName() != null || commandInput.getLatitude() != null && commandInput.getLongitude() != null && commandInput.getRadius() != null && commandInput.getDescription() != null) {
            if (commandInput.getName() == null) {
                commandInput.setName("");
            }
            if (commandInput.getDescription() == null) {
                commandInput.setDescription("");
            }
            List<Restaurant> restaurants = restaurantService.searchNearbyByNameAndDescription(commandInput.getLatitude(), commandInput.getLongitude(), commandInput.getRadius(), commandInput.getName(), commandInput.getDescription());
            return Response.ok(restaurants).build();
        } else if (commandInput.getLatitude() != null && commandInput.getLongitude() != null && commandInput.getRadius() != null) {
            List<Restaurant> restaurants = restaurantService.searchByLocation(commandInput.getLatitude(), commandInput.getLongitude(), commandInput.getRadius());
            return Response.ok(restaurants).build();
        } else if (commandInput.getDayTime() != null) {
            List<Restaurant> restaurants = restaurantService.searchOpening(commandInput.getDayTime());
            return Response.ok(restaurants).build();
        } else if (commandInput.getName() != null || commandInput.getName() != null) {
            if (commandInput.getName() == null) {
                commandInput.setName("");
            }
            if (commandInput.getDescription() == null) {
                commandInput.setDescription("");
            }
            List<Restaurant> restaurants = restaurantService.searchByNameAndDescription(commandInput.getName(), commandInput.getDescription());
            return Response.ok(restaurants).build();
        } else {
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/searchByNameAndDescription")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchByNameAndDescription(CommandInput commandInput) {
        List<Restaurant> restaurants = restaurantService.searchByNameAndDescription(commandInput.getName(), commandInput.getDescription());
        return Response.ok(restaurants).build();
    }

    @POST
    @Path("/searchByLocation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchByLocation(CommandInput commandInput) {
        List<Restaurant> restaurants = restaurantService.searchByLocation(commandInput.getLatitude(), commandInput.getLongitude(), commandInput.getRadius());
        return Response.ok(restaurants).build();
    }

    @POST
    @Path("/searchOpening")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchOpening(CommandInput commandInput) {
        List<Restaurant> restaurants = restaurantService.searchOpening(commandInput.getDayTime());
        return Response.ok(restaurants).build();
    }

    @POST
    @Path("/searchOpeningNearby")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchOpeningNearby(CommandInput commandInput) {
        List<Restaurant> restaurants = restaurantService.searchOpeningNearby(commandInput.getLatitude(), commandInput.getLongitude(), commandInput.getRadius(), commandInput.getDayTime());
        return Response.ok(restaurants).build();
    }

    @POST
    @Path("/searchNearbyByNameAndDescription")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchNearbyByNameAndDescription(CommandInput commandInput) {
        List<Restaurant> restaurants = restaurantService.searchNearbyByNameAndDescription(commandInput.getLatitude(), commandInput.getLongitude(), commandInput.getRadius(), commandInput.getName(), commandInput.getDescription());
        return Response.ok(restaurants).build();
    }

    @POST
    @Path("/searchOpeningByNameAndDescription")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchOpeningByNameAndDescription(CommandInput commandInput) {
        List<Restaurant> restaurants = restaurantService.searchOpeningByNameAndDescription(commandInput.getDayTime(), commandInput.getName(), commandInput.getDescription());
        return Response.ok(restaurants).build();
    }

    @POST
    @Path("/searchNearbyOpeningByNameAndDescription")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchNearbyOpeningByNameAndDescription(CommandInput commandInput) {
        List<Restaurant> restaurants = restaurantService.searchNearbyOpeningByNameAndDescription(commandInput.getLatitude(), commandInput.getLongitude(), commandInput.getRadius(), commandInput.getDayTime(), commandInput.getName(), commandInput.getDescription());
        return Response.ok(restaurants).build();
    }
}
