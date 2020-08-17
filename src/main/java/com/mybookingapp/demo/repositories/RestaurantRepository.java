package com.mybookingapp.demo.repositories;

import com.mybookingapp.demo.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    Restaurant findRestaurantByRestaurantId(String restaurantId);

    Long deleteByRestaurantId(String restaurantId);
}
