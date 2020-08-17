package com.mybookingapp.demo.services;

import com.mybookingapp.demo.domain.Restaurant;
import com.mybookingapp.demo.domain.Table;
import com.mybookingapp.demo.dto.NewRestaurantRequest;
import com.mybookingapp.demo.repositories.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RestaurantService {

    RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public void addRestaurant(NewRestaurantRequest newRestaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(newRestaurantRequest.getRestaurantName());
        restaurant.setAddress(newRestaurantRequest.getRestaurantAddress());
        restaurant.setMaxTableCapacity(newRestaurantRequest.getMaxTableCapacity());
        restaurant.setRestaurantId(newRestaurantRequest.getRestaurantId());
        restaurant.setNumberOfRatings(0);
        restaurant.setTotalRatings(0);
        restaurant.setMapTable(new HashMap<>());
        addTablesInRestaurant(restaurant, newRestaurantRequest.getTableMap());
        restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(String restaurantId) {
        if (restaurantRepository.deleteByRestaurantId(restaurantId) == 1)
            log.info("Successfully deleted restaurant with restaurantId={}", restaurantId);
        else
            log.error("restaurant not deleted because not found");
    }

    public HttpStatus addTables(String restaurantId, Map<Integer, Integer> tableMap) {
        Restaurant restaurant = restaurantRepository.findRestaurantByRestaurantId(restaurantId);
        if (restaurant == null) {
            log.info("No restaurant found with restaurantId={}", restaurantId);
            return HttpStatus.NOT_FOUND;
        }
        addTablesInRestaurant(restaurant, tableMap);
        restaurantRepository.save(restaurant);
        return HttpStatus.NO_CONTENT;

    }

    private void addTablesInRestaurant(Restaurant restaurant, Map<Integer, Integer> tableMap) {
        tableMap.entrySet().stream().forEach(entry -> {
            Integer capacity = entry.getKey();
            Integer noOfTables = entry.getValue();
            if (restaurant.getMapTable().containsKey(capacity)) {
                restaurant.getMapTable().get(capacity).addAll(Collections.nCopies(noOfTables, new Table()));
            } else {
                restaurant.getMapTable().put(capacity, Collections.nCopies(noOfTables, new Table()));
            }
        });
    }

    public HttpStatus rateRestaurant(String restaurantId, Integer rating) {
        Restaurant restaurant = restaurantRepository.findRestaurantByRestaurantId(restaurantId);
        if (restaurant != null) {
            restaurant.setTotalRatings(restaurant.getTotalRatings() + rating);
            restaurant.setNumberOfRatings(restaurant.getNumberOfRatings() + 1);
            restaurantRepository.save(restaurant);
            return HttpStatus.NO_CONTENT;
        } else {
            log.error("Restaurant with restaurantId={} not found!!",restaurantId);
            return HttpStatus.NOT_FOUND;
        }

    }
}
