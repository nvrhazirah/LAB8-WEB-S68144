/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Model;

public class Car {

    private int carid;
    private String brand;
    private String model;
    private int cyclinder;
    private Double price;

    public Car() {
    }

    public Car(String brand, String model, int cyclinder, Double price) {
        super();
        this.brand = brand;
        this.model = model;
        this.cyclinder = cyclinder;
        this.price = price;
    }

    public Car(int carid,String brand, String model, int cyclinder, Double price) {
        super();
        this.carid = carid;
        this.brand = brand;
        this.model = model;
        this.cyclinder = cyclinder;
        this.price = price;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCyclinder() {
        return cyclinder;
    }

    public void setCyclinder(int cyclinder) {
        this.cyclinder = cyclinder;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
