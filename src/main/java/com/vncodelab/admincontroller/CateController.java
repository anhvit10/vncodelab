//
package com.vncodelab.admincontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vncodelab.entity.Cate;
import com.vncodelab.respository.CateRespository;

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
    private CateRespository cateRespository;
	
    @GetMapping(value = "/cate")
	public String getCate(Model model) {
    	List<Cate> listCategories = cateRespository.findAll();
    	model.addAttribute("listcategories", listCategories);
		return "admin/cate";
	}
	
	public String saveCate(Model model) {
		return null;
	}
	
	public String deleteCate(Model model) {
		return null;
	}
	
	public String editCate(Model model) {
		return null;
	}
}
