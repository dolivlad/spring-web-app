package kpp.pz5_webapp.DAO;

import kpp.pz5_webapp.hierarchy.classes.OfficeLEDMonitor;
import kpp.pz5_webapp.myList.MyListImpl;

public class CollectionDAO implements IMyDAO {
    private final MyListImpl<OfficeLEDMonitor> monitors;

    public CollectionDAO() {
        this.monitors = new MyListImpl<>();
    }

    @Override
    public void addMonitor(OfficeLEDMonitor monitor) {
        monitors.add(monitor);
    }

    @Override
    public void removeMonitor(String model) {
        OfficeLEDMonitor toRemove = null;
        for (OfficeLEDMonitor monitor : monitors) {
            if (monitor.getModel().equals(model)) {
                toRemove = monitor;
                break;
            }
        }
        if (toRemove != null) {
            monitors.remove(toRemove);
            System.out.println("Deleted monitor model: " + model);
        } else {
            System.out.println("No monitor model: " + model);
        }
    }

    @Override
    public MyListImpl<OfficeLEDMonitor> findMonitor(String brand) {
        MyListImpl<OfficeLEDMonitor> result = new MyListImpl<>();
        for (OfficeLEDMonitor monitor : monitors) {
            if (monitor.getBrand().equals(brand)) {
                result.add(monitor);
            }
        }
        return result;
    }

    @Override
    public MyListImpl<OfficeLEDMonitor> getAllMonitors() {
        MyListImpl<OfficeLEDMonitor> result = new MyListImpl<>();
        for (OfficeLEDMonitor monitor : monitors) {
            result.add(monitor);
        }
        return result;
    }

    @Override
    public void updateMonitor(OfficeLEDMonitor updatedMonitor) {
        boolean updated = false;
        for (OfficeLEDMonitor monitor : monitors) {
            if (monitor.getModel().equals(updatedMonitor.getModel())) {
                monitor.setBrand(updatedMonitor.getBrand());
                monitor.setScreenSize(updatedMonitor.getScreenSize());
                monitor.setPrice(updatedMonitor.getPrice());
                monitor.setIsCurved(updatedMonitor.getIsCurved());
                monitor.setHasBlueLightFilter(updatedMonitor.getHasBlueLightFilter());
                updated = true;
                break;
            }
        }
        if (!updated) {
            System.out.println("No monitor found to update: " + updatedMonitor.getModel());
        }
    }

    public OfficeLEDMonitor getOfficeLEDMonitorByModel(String model) {
        for (OfficeLEDMonitor monitor : monitors) {
            if (monitor.getModel().equals(model)) {
                return monitor;
            }
        }
        return null;
    }
}
