package kpp.pz5_webapp.hierarchy.classes;

import java.util.Comparator;

public class PriceComparator implements Comparator<Monitor> {
    @Override
    public int compare(Monitor m1, Monitor m2) {
        return Double.compare(m1.getPrice(), m2.getPrice());
    }
}
