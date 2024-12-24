package kpp.pz5_webapp.Controller;

import kpp.pz5_webapp.DAO.*;
import kpp.pz5_webapp.hierarchy.classes.*;
import kpp.pz5_webapp.myList.MyListImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class OfficeLEDMonitorController {
    private final IMyDAO dao;

    public OfficeLEDMonitorController() throws SQLException {
//        this.dao = DAOFactory.getDAOInstance(TypeDAO.MySQL);
        this.dao = DAOFactory.getDAOInstance(TypeDAO.MyCollection);
    }

    @RequestMapping(value = {"/", "/OfficeLEDMonitors"}, method = RequestMethod.GET)
    public String showAllOfficeLEDMonitors(Model model) throws SQLException {
        MyListImpl<OfficeLEDMonitor> monitors = dao.getAllMonitors();
        model.addAttribute("allOfficeLEDMonitor", monitors);
        return "OfficeLEDMonitors";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showAddOfficeLEDMonitorView(Model model) {
        OfficeLEDMonitor officeLEDMonitor = new OfficeLEDMonitor();
        model.addAttribute("officeLEDMonitor", officeLEDMonitor);
        return "addOfficeLEDMonitor";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String addOfficeLEDMonitor(Model model, OfficeLEDMonitor officeLEDMonitor) throws SQLException {
        dao.addMonitor(officeLEDMonitor);
        return "redirect:/OfficeLEDMonitors";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.GET)
    public String showEditOfficeLEDMonitorView(Model model) {
        return "findOfficeLEDMonitorToEdit";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    public String editOfficeLEDMonitor(@RequestParam("modelAtr") String modelAtr, Model model) throws SQLException {
        OfficeLEDMonitor officeLEDMonitor = dao.getOfficeLEDMonitorByModel(modelAtr);
        if (officeLEDMonitor == null) {
            model.addAttribute("errorMessage", "Office LED Monitor not found");
            return "redirect:/OfficeLEDMonitors";
        }
        model.addAttribute("officeLEDMonitor", officeLEDMonitor);
        return "editOfficeLEDMonitor";
    }

    @RequestMapping(value = {"/edit/save"}, method = RequestMethod.POST)
    public String saveEditedOfficeLEDMonitor(OfficeLEDMonitor officeLEDMonitor) throws SQLException {
        dao.updateMonitor(officeLEDMonitor);
        return "redirect:/OfficeLEDMonitors";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.GET)
    public String showDeleteOfficeLEDMonitorView(Model model) {
        return "deleteOfficeLEDMonitor";
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.POST)
    public String deleteOfficeLEDMonitor(@RequestParam("modelAtr") String modelAtr, Model model) throws SQLException {
        OfficeLEDMonitor officeLEDMonitor = dao.getOfficeLEDMonitorByModel(modelAtr);
        if (officeLEDMonitor == null) {
            model.addAttribute("errorMessage", "Office LED Monitor not found");
            return "deleteOfficeLEDMonitor";
        }
        dao.removeMonitor(modelAtr);
        return "redirect:/OfficeLEDMonitors";
    }

    @RequestMapping(value = {"/OfficeLEDMonitorByBrand"}, method = RequestMethod.GET)
    public String showFindOfficeLEDMonitorByBrandView(Model model) {
        return "findOfficeLEDMonitorByBrand";
    }

    @RequestMapping(value = {"/OfficeLEDMonitorByBrand"}, method = RequestMethod.POST)
    public String findOfficeLEDMonitorByBrand(@RequestParam("brand") String brand, Model model) throws SQLException {
        MyListImpl<OfficeLEDMonitor> monitors = dao.findMonitor(brand);
        model.addAttribute("allOfficeLEDMonitor", monitors);
        return "findOfficeLEDMonitorByBrand";
    }
}
