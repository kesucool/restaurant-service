package com.mybookingapp.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "restaurant")
@Data
public class Restaurant {
    @Id
    private String id;
    @Indexed(unique = true)
    private String restaurantId;
    private String name;
    private String address;
    private Integer maxTableCapacity;
    private Map<Integer, List<Table>> mapTable;
    private Integer totalRatings;
    private Integer numberOfRatings;
}
