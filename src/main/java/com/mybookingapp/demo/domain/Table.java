package com.mybookingapp.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Table {
    Map<BookingSlot, String> bookings;

    public Table() {
        this.bookings = new HashMap<>();
    }
}
