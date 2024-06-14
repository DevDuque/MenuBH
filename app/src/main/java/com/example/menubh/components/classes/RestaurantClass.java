package com.example.menubh.components.classes;

import java.io.Serializable;

public class RestaurantClass implements Serializable {
    public RestaurantClass(String restaurantName, String restaurantAddress, String restaurantNumber, String restaurantSpecialty, int restaurantRating) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantNumber = restaurantNumber;
        this.restaurantSpecialty = restaurantSpecialty;
        this.restaurantRating = restaurantRating;
    }

    private String restaurantName;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    private String restaurantAddress;

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    private String restaurantNumber;

    public String getRestaurantNumber() {
        return restaurantNumber;
    }

    public void setRestaurantNumber(String restaurantNumber) {
        this.restaurantNumber = restaurantNumber;
    }

    private String restaurantSpecialty;

    public String getRestaurantSpecialty() {
        return restaurantSpecialty;
    }

    public void setRestaurantSpecialty(String restaurantSpecialty) {
        this.restaurantSpecialty = restaurantSpecialty;
    }

    private int restaurantRating;

    public int getRestaurantRating() {
        return restaurantRating;
    }

    public void setRestaurantRating(int restaurantRating) {
        this.restaurantRating = restaurantRating;
    }

    @Override
    public String toString() {
        return
                "restaurantName= " + restaurantName + '\'' +
                ", restaurantAddress= " + restaurantAddress + '\'' +
                ", restaurantNumber= " + restaurantNumber + '\'' +
                ", restaurantSpecialty= " + restaurantSpecialty + '\'' +
                ", restaurantRating= " + restaurantRating +
                '}';
    }
}
