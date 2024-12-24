package kpp.pz5_webapp.hierarchy.classes;

import java.util.Comparator;

public class BrandComparator implements Comparator<Monitor> {
    @Override
    public int compare(Monitor m1, Monitor m2) {
        return m1.getBrand().compareTo(m2.getBrand());
    }
}
