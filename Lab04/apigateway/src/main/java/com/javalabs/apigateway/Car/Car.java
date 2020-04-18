package com.javalabs.apigateway.Car;

public class Car {

    public Car() {}

    public Car(String model, String manufacturer, int manufactureYear, String gearbox, String fuel, int horsepower, String color, float price) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.manufactureYear = manufactureYear;
        this.gearbox = gearbox;
        this.fuel = fuel;
        this.horsepower = horsepower;
        this.color = color;
        this.price = price;
    }

    private int id = -1;
    private String model;
    private String manufacturer;
    private int manufactureYear;
    private String gearbox;
    private String fuel;
    private int horsepower;
    private String color;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}