package com.newVehicleRental.newVehicleRental.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private String type;
    private String color;
    private int year;
    private double pricePerDay;
    private boolean available;

    // Constructors
    public vehicle() {
    }

    public vehicle(String make, String model, String type, String color, int year, double pricePerDay, boolean available) {
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

