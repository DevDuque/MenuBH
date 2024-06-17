package com.example.menubh.components.classes;

import java.io.Serializable;

// Classe que representa um restaurante
public class RestaurantClass implements Serializable {

    // Construtor para inicializar os atributos do restaurante
    public RestaurantClass(String restaurantName, String restaurantAddress, String restaurantNumber, String restaurantSpecialty, int restaurantRating) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantNumber = restaurantNumber;
        this.restaurantSpecialty = restaurantSpecialty;
        this.restaurantRating = restaurantRating;
        this.isFavorite = false;
    }

    // Atributos privados da classe
    private String restaurantName;
    private Boolean isFavorite;
    private String restaurantAddress;
    private String restaurantNumber;
    private String restaurantSpecialty;
    private int restaurantRating;

    // Métodos getters para acessar os atributos privados

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

    // Método setter para definir o estado de favorito do restaurante
    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    // Método toString para retornar uma representação em formato de string do objeto
    @Override
    public String toString() {
        return "RestaurantClass{" +
                "restaurantName='" + restaurantName + '\'' +
                ", isFavorite=" + isFavorite +
                ", restaurantAddress='" + restaurantAddress + '\'' +
                ", restaurantNumber='" + restaurantNumber + '\'' +
                ", restaurantSpecialty='" + restaurantSpecialty + '\'' +
                ", restaurantRating=" + restaurantRating +
                '}';
    }
}
