package com.mybookingapp.demo.utils;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {
    private static Integer MIN_RATING = 0;
    private static Integer MAX_RATING = 5;

    public static Boolean isValidRating(Integer rating){
        if(rating<MIN_RATING|| rating>MAX_RATING)
            return false;
        return true;
    }

}
