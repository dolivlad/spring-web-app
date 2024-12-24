package kpp.pz5_webapp.hierarchy.classes;

import java.util.Objects;

public class LCDMonitor extends Monitor {
    private int refreshRate;

    public LCDMonitor(int id, String brand, String model, double screenSize, double price, int refreshRate) {
        super(id, brand, model, screenSize, price);
        this.refreshRate = refreshRate;
    }

    public LCDMonitor(String brand, String model, double screenSize, double price, int refreshRate) {
        super(brand, model, screenSize, price);
        this.refreshRate = refreshRate;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    @Override
    public String toString() {
        return "LCDMonitor [brand=" + getBrand() + ", model=" + getModel() + ", screenSize="
                + getScreenSize() + ", price=" + getPrice() + ", refreshRate=" + refreshRate + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        LCDMonitor other = (LCDMonitor) obj;
        return refreshRate == other.refreshRate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), refreshRate);
    }

}