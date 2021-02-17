//
package com.vncodelab.admincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vncodelab.entity.Lab;
import com.vncodelab.respository.LabRespository;

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
	private LabRespository labRespository;

	@GetMapping(value = "/laboratory")
	public String getLab(Model model) {
		List<Lab> listLab = labRespository.findAll();
		model.addAttribute("listlab", listLab);
		return "admin/lab";
	}

	public String saveLab(Model model) {
		return null;
	}

	public String deleteLab(Model model) {
		return null;
	}

	public String editLab(Model model) {
		return null;
	}
}
