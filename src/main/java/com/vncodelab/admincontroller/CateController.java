//
package com.vncodelab.admincontroller;

import com.vncodelab.entity.Cate;
import com.vncodelab.exception.PageNotFoundException;
import com.vncodelab.service.serviceImpl.CateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
public class CateController {

    @Autowired
    private CateServiceImpl cateServiceImpl;

    @GetMapping(value = "/cate")
    public String getCate(Model model) {
        List<Cate> lstCategories = cateServiceImpl.getAllCates();
        model.addAttribute("pageCate", lstCategories);
        return "admin2/cate";
    }

    @GetMapping(value = "/cate/new")
    public String toSaveCatePage(Model model) {
        model.addAttribute("newCategory", new Cate());
        return "admin2/save-cate";
    }

    @PostMapping(value = "/cate/save")
    public String saveCate(@RequestParam("cateId") String cateId, @ModelAttribute("newCategory") Cate cate) throws PageNotFoundException {
        cateServiceImpl.saveCate(cate, cateId);
        return "redirect:/admin/cate";
    }

    @GetMapping(value = "/cate/delete/{cateID}")
    public String deleteCate(@PathVariable("cateID") String cateID) throws PageNotFoundException {
        cateServiceImpl.deleteCate(Integer.parseInt(cateID));
        return "redirect:/admin/cate";
    }

    @GetMapping(value = "/cate/{cateID}")
    public String toEditCatePage(Model model, @PathVariable("cateID") String cateID) throws PageNotFoundException {
        Cate cate = cateServiceImpl.getCateById(Integer.parseInt(cateID));
        model.addAttribute("newCategory", cate);
        model.addAttribute("cateId", cate.getCateID());
        return "admin2/save-cate";
    }
}
