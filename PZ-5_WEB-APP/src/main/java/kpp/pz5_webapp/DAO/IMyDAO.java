package kpp.pz5_webapp.DAO;

import kpp.pz5_webapp.hierarchy.classes.OfficeLEDMonitor;
import kpp.pz5_webapp.myList.MyListImpl;

import java.sql.SQLException;

public interface IMyDAO {
    void addMonitor(OfficeLEDMonitor monitor) throws SQLException;
    void removeMonitor(String model) throws SQLException;
    MyListImpl<OfficeLEDMonitor> findMonitor(String brand) throws SQLException;
    MyListImpl<OfficeLEDMonitor> getAllMonitors() throws SQLException;
    void updateMonitor(OfficeLEDMonitor updatedMonitor) throws SQLException;
    OfficeLEDMonitor getOfficeLEDMonitorByModel(String model) throws SQLException;
}


