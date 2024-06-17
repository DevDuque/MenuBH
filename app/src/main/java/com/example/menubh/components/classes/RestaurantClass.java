package com.example.menubh.components.classes;

import java.io.Serializable;

public class RestaurantClass implements Serializable {
    public RestaurantClass(String restaurantName, String restaurantAddress, String restaurantNumber, String restaurantSpecialty, int restaurantRating) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantNumber = restaurantNumber;
        this.restaurantSpecialty = restaurantSpecialty;
        this.restaurantRating = restaurantRating;
        this.isFavorite = false;
    }

    private String restaurantName;
    private Boolean isFavorite;
    private String restaurantAddress;
    private String restaurantNumber;
    private String restaurantSpecialty;
    private int restaurantRating;

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public String getRestaurantNumber() {
        return restaurantNumber;
    }

    public String getRestaurantSpecialty() {
        return restaurantSpecialty;
    }

    public int getRestaurantRating() {
        return restaurantRating;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return
                "restaurantName= " + getRestaurantName() + '\'' +
                ", restaurantAddress= " + getRestaurantAddress() + '\'' +
                ", restaurantNumber= " + getRestaurantNumber() + '\'' +
                ", restaurantSpecialty= " + getRestaurantSpecialty() + '\'' +
                ", restaurantRating= " + getRestaurantRating() + '\'' +
                ", restaurantIsFavorite= " + getFavorite() + '\'' +
                '}';
    }
}
