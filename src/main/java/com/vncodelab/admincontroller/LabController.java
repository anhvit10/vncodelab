//
package com.vncodelab.admincontroller;

import com.vncodelab.entity.Cate;
import com.vncodelab.entity.Lab;
import com.vncodelab.exception.PageNotFoundException;
import com.vncodelab.service.serviceImpl.CateServiceImpl;
import com.vncodelab.service.serviceImpl.LabServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * This class is .
 *
 * @Description: .
 * @author: NVAnh
 * @create_date: Feb 17, 2021
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Feb 17, 2021
 */
@Controller
@RequestMapping(value = "/admin")
public class LabController {

    @Autowired
    private LabServiceImpl labServiceImpl;

    @Autowired
    private CateServiceImpl cateServiceImpl;

    @GetMapping(value = "/laboratory")
    public String getLab(Model model) {
        List<Lab> lstLabs = labServiceImpl.getAllLabs();
        model.addAttribute("pageLab", lstLabs);
        return "admin2/lab";
    }

    @GetMapping(value = "/laboratory/new")
    public String toSaveLabPage(Model model) {
        List<Cate> allCate = cateServiceImpl.getAllCates();
        model.addAttribute("allCate", allCate);
        model.addAttribute("newLaboratory", new Lab());
        return "admin2/save-lab";
    }

    @PostMapping(value = "/laboratory/save")
    public String saveLab(@RequestParam("labId") String labId,
                          @ModelAttribute("newLaboratory") Lab lab,
                          @RequestParam(value = "image", required = false) MultipartFile exerciseFile) throws PageNotFoundException {

        if(!exerciseFile.isEmpty()) {
            labServiceImpl.saveExerciseToFirebase(exerciseFile);
        }
        labServiceImpl.saveLab(lab, labId);
        return "redirect:/admin/laboratory";
    }

    @GetMapping(value = "/laboratory/delete/{labID}")
    public String deleteLab(@PathVariable("labID") String labID) throws PageNotFoundException {
        labServiceImpl.deleteLab(Integer.parseInt(labID));
        return "redirect:/admin/laboratory";
    }

    @GetMapping(value = "/laboratory/{labID}")
    public String toEditLabPage(Model model, @PathVariable("labID") String labID) throws PageNotFoundException {
        Lab lab = labServiceImpl.getLabById(Integer.parseInt(labID));
        List<Cate> allCate = cateServiceImpl.getAllCates();
        model.addAttribute("allCate", allCate);
        model.addAttribute("newLaboratory", lab);
        model.addAttribute("labId", lab.getLabID());
        return "admin2/save-lab";
    }
}
