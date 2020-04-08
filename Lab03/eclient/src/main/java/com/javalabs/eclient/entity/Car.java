package com.javalabs.eclient.entity;

import javax.persistence.*;

@Entity
@Table(name = "cars")
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id = -1;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "manufacture_year")
    private int manufactureYear;

    @Column(name = "gearbox")
    private String gearbox;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "horsepower")
    private int horsepower;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
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
