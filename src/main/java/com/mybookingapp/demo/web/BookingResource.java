package com.mybookingapp.demo.web;

import com.mybookingapp.demo.domain.Restaurant;
import com.mybookingapp.demo.dto.NewRestaurantRequest;
import com.mybookingapp.demo.services.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.mybookingapp.demo.utils.CommonUtils.isValidRating;

@Slf4j
@RestController
@RequestMapping("/restaurant")
public class BookingResource {
    RestaurantService restaurantService;


    @Autowired
    public BookingResource(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/getAllRestaurants")
    List<Restaurant> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PostMapping("/addRestaurant")
    ResponseEntity addRestaurant(@RequestBody NewRestaurantRequest newRestaurantRequest) {
        restaurantService.addRestaurant(newRestaurantRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addTables/{id}")
    ResponseEntity addTables(@RequestBody Map<Integer, Integer> tableMap, @PathVariable("id") String restaurantId) {
        HttpStatus httpStatus = restaurantService.addTables(restaurantId, tableMap);
        return ResponseEntity.status(httpStatus).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteRestaurant(@PathVariable("id") String restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/rateRestaurant/{id}/{rating}")
    ResponseEntity rateRestaurant(@PathVariable("id") String restaurantId, @PathVariable("rating") Integer rating) {

        if (!isValidRating(rating)) {
            log.error("Rating is invalid!!");
            return ResponseEntity.badRequest().body("Invalid Rating");
        }
        HttpStatus httpStatus = restaurantService.rateRestaurant(restaurantId, rating);
        return ResponseEntity.status(httpStatus).build();
    }


}
