package com.mybookingapp.demo.services;

import com.mybookingapp.demo.domain.Restaurant;
import com.mybookingapp.demo.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Dbseeder implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public Dbseeder(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;

    }

    @Override
    public void run(String... args) throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress("Mumbai");
        restaurant.setName("Marriot");

        this.restaurantRepository.deleteAll();
        this.restaurantRepository.save(restaurant);
    }
}
