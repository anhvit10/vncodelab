//
package com.vncodelab.admincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vncodelab.constants.CommonConstants;
import com.vncodelab.entity.Lab;
import com.vncodelab.respository.LabRespository;
import com.vncodelab.service.serviceImpl.LabServiceImpl;

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

	@GetMapping(value = "/laboratory")
	public String getLab(Model model,
			@RequestParam(defaultValue = "1", value = "pageNumber", required = true) String pageNumber)
			throws NumberFormatException {
		Page<Lab> pageLab = labServiceImpl.getPageLab(Integer.parseInt(pageNumber), CommonConstants.LAB_PAGE_SIZE);
		model.addAttribute("pageLab", pageLab);
		model.addAttribute("pageSize", CommonConstants.CATE_PAGE_SIZE);
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
