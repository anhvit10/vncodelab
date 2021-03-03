//
package com.vncodelab.admincontroller;

import com.vncodelab.entity.Home;
import com.vncodelab.service.serviceImpl.DashboardServiceImpl;
import com.vncodelab.service.serviceImpl.HomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * This class is .
 *
 * @Description: .
 * @author: NVAnh
 * @create_date: Feb 19, 2021
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Feb 19, 2021
 */
@Controller
@RequestMapping(value = "/admin")
public class HomeController {

    @Autowired
    private HomeServiceImpl homeServiceImpl;

    @Autowired
    private DashboardServiceImpl dashboardService;

    @GetMapping("")
    public String toIndex(Model model) {
        Integer totalCates = dashboardService.getTotalCates();
        Integer totalLabs = dashboardService.getTotalLabs();
        model.addAttribute("totalcates", totalCates);
        model.addAttribute("totallabs", totalLabs);
        return "admin2/index";
    }

    @GetMapping("/tables")
    public String toTables() {
        return "admin2/tables";
    }

    @GetMapping("/login")
    public String toLogin() {
        return "admin2/login";
    }

    @GetMapping("/home")
    public String get(Model model) throws InterruptedException, ExecutionException {
        Map<String, Object> infor = homeServiceImpl.getObjectFirebase();

        model.addAttribute("infor", infor);
        return "admin2/home";
    }

    @PostMapping("/home/save")
    public String editHomePage(@ModelAttribute("newInfor") Home newInfor, Model model)
            throws InterruptedException, ExecutionException, IOException {
        homeServiceImpl.saveObjectFirebase(newInfor);
        Map<String, Object> infor = homeServiceImpl.getObjectFirebase();

        model.addAttribute("infor", infor);
        return "redirect:/admin/home";
    }

    @GetMapping("/home/edit")
    public String toEditHomePage(Model model) throws InterruptedException, ExecutionException {
        Map<String, Object> infor = homeServiceImpl.getObjectFirebase();
        Home newInfor = homeServiceImpl.getInforFirebase(infor);

        model.addAttribute("newInfor", newInfor);
        return "admin2/edit-home";
    }

}
