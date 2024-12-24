package kpp.pz5_webapp.hierarchy.classes;

import java.util.Objects;

public class GamingLCDMonitor extends LCDMonitor {
    private boolean hasRGB;

    public GamingLCDMonitor(int id, String brand, String model, double screenSize, double price, int refreshRate, boolean hasRGB) {
        super(id, brand, model, screenSize, price, refreshRate);
        this.hasRGB = hasRGB;
    }

    public GamingLCDMonitor(String brand, String model, double screenSize, double price, int refreshRate, boolean hasRGB) {
        super(brand, model, screenSize, price, refreshRate);
        this.hasRGB = hasRGB;
    }

    public boolean hasRGB() {
        return hasRGB;
    }

    @Override
    public String toString() {
        return "GamingLCDMonitor [brand=" + getBrand() + ", model=" + getModel() + ", screenSize="
                + getScreenSize() + ", price=" + getPrice() + ", refreshRate="
                + getRefreshRate() + ", hasRGB=" + hasRGB + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        GamingLCDMonitor other = (GamingLCDMonitor) obj;
        return hasRGB == other.hasRGB;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasRGB);
    }

}
