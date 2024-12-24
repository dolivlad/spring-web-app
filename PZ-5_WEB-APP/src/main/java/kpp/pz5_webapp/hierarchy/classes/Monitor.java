package kpp.pz5_webapp.hierarchy.classes;

import java.util.Objects;

public class Monitor implements Comparable<Monitor> {
    private int id;
    private String brand;
    private String model;
    private double screenSize;
    private double price;

    public Monitor(int id, String brand, String model, double screenSize, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
        this.price = price;
    }

    public Monitor(String brand, String model, double screenSize, double price) {
        this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
        this.price = price;
    }

    public Monitor() {

    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) { this.screenSize = screenSize; }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Monitor [brand=" + brand + ", model=" + model + ", screenSize=" + screenSize + ", price=" + price + "]";
    }

    @Override
    public int compareTo(Monitor other) {
        return Double.compare(this.screenSize, other.screenSize);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Monitor other = (Monitor) obj;
        return Double.compare(screenSize, other.screenSize) == 0 &&
                Double.compare(price, other.price) == 0 &&
                brand.equals(other.brand) && model.equals(other.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, screenSize, price);
    }

}
