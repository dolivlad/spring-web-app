package kpp.pz5_webapp.hierarchy.classes;

import java.util.Objects;

public class OfficeLEDMonitor extends LEDMonitor {
    private boolean hasBlueLightFilter;

    public OfficeLEDMonitor(int id, String brand, String model, double screenSize, double price, boolean isCurved, boolean hasBlueLightFilter) {
        super(id, brand, model, screenSize, price, isCurved);
        this.hasBlueLightFilter = hasBlueLightFilter;
    }

    public OfficeLEDMonitor(String brand, String model, double screenSize, double price, boolean isCurved, boolean hasBlueLightFilter) {
        super(brand, model, screenSize, price, isCurved);
        this.hasBlueLightFilter = hasBlueLightFilter;
    }

    public OfficeLEDMonitor() {
        super();
    }

    public boolean getHasBlueLightFilter() {
        return hasBlueLightFilter;
    }

    public void setHasBlueLightFilter(boolean hasBlueLightFilter) { this.hasBlueLightFilter = hasBlueLightFilter; }

    @Override
    public String toString() {
        return "OfficeLEDMonitor [brand=" + getBrand() + ", model=" + getModel() + ", screenSize="
                + getScreenSize() + ", price=" + getPrice() + ", isCurved="
                + getIsCurved() + ", getHasBlueLightFilter=" + hasBlueLightFilter + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        OfficeLEDMonitor other = (OfficeLEDMonitor) obj;
        return hasBlueLightFilter == other.hasBlueLightFilter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasBlueLightFilter);
    }

}