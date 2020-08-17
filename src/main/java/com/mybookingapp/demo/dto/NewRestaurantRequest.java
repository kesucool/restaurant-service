package com.mybookingapp.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewRestaurantRequest {
    private String restaurantName;
    private String restaurantAddress;
    private Integer maxTableCapacity;
    private String restaurantId;
    private Map<Integer, Integer> tableMap;
}
