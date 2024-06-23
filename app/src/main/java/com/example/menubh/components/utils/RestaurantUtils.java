package com.example.menubh.components.utils;

import com.example.menubh.components.classes.RestaurantClass;

import java.util.ArrayList;

public class RestaurantUtils {


// Adiciona 4 restaurantes em cada especialidade, aumente se necessário
    public static void populateFictionCard(ArrayList<RestaurantClass> restaurantList) {
        ArrayList<RestaurantClass> exampleRestaurants = new ArrayList<>();

        for(int i = 0; i <= 3; i++) {
            exampleRestaurants.add(new RestaurantClass("Sushi de Feijoada", "Av. Pres. Antônio Carlos, 6627", "3134095000", "Sushi", 5));
        }

        for(int i = 0; i <= 3; i++) {
            exampleRestaurants.add(new RestaurantClass("Churras&Cia", "Av. Pres. Antônio Carlos, 6627", "3134095000", "Barbecue", 4));
        }

        for(int i = 0; i <= 3; i++) {
            exampleRestaurants.add(new RestaurantClass("Siri Cascudo", "Av. Pres. Antônio Carlos, 6627", "3134095000", "Fish", 3));
        }

        for(int i = 0; i <= 3; i++) {
            exampleRestaurants.add(new RestaurantClass("Pizza do Siri Cascudo", "Av. Pres. Antônio Carlos, 6627", "3134095000", "Pizza", 1));
        }

        restaurantList.addAll(exampleRestaurants);
    }
}
