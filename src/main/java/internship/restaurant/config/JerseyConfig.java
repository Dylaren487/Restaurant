package internship.restaurant.config;

import internship.restaurant.controller.RestaurantController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Admin on 4/7/2017.
 */
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(RestaurantController.class);
    }
}
