package kpp.pz5_webapp.hierarchy.classes;

import java.util.Objects;

public class LEDMonitor extends Monitor {
    private boolean isCurved;

    public LEDMonitor(int id, String brand, String model, double screenSize, double price, boolean isCurved) {
        super(id, brand, model, screenSize, price);
        this.isCurved = isCurved;
    }

    public LEDMonitor(String brand, String model, double screenSize, double price, boolean isCurved) {
        super(brand, model, screenSize, price);
        this.isCurved = isCurved;
    }

    public LEDMonitor() {
        super();
    }

    public boolean getIsCurved() {
        return isCurved;
    }

    public void setIsCurved(boolean isCurved) { this.isCurved = isCurved; }

    @Override
    public String toString() {
        return "LEDMonitor [brand=" + getBrand() + ", model=" + getModel() + ", screenSize="
                + getScreenSize() + ", price=" + getPrice() + ", isCurved=" + isCurved + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        LEDMonitor other = (LEDMonitor) obj;
        return isCurved == other.isCurved;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isCurved);
    }

}
