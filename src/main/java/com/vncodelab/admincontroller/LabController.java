//
package com.vncodelab.admincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vncodelab.constants.CommonConstants;
import com.vncodelab.entity.Cate;
import com.vncodelab.entity.Lab;
import com.vncodelab.service.serviceImpl.CateServiceImpl;
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

	@Autowired
	private CateServiceImpl cateServiceImpl;

	@GetMapping(value = "/laboratory")
	public String getLab(Model model,
			@RequestParam(defaultValue = "1", value = "pageNumber", required = true) String pageNumber)
			throws NumberFormatException {
		Page<Lab> pageLab = labServiceImpl.getPageLab(Integer.parseInt(pageNumber), CommonConstants.LAB_PAGE_SIZE);
		model.addAttribute("pageNum", Integer.parseInt(pageNumber));
		model.addAttribute("pageLab", pageLab);
		model.addAttribute("pageSize", CommonConstants.LAB_PAGE_SIZE);
		return "admin2/lab";
	}

	@GetMapping(value = "/laboratory/new")
	public String toSaveLabPage(Model model) {
		List<Cate> allCate = cateServiceImpl.findAllCate();
		model.addAttribute("allCate", allCate);
		model.addAttribute("newLaboratory", new Lab());
		return "admin2/save-lab";
	}

	@PostMapping(value = "/laboratory/save")
	public String saveLab(@RequestParam("labId") String labId, @ModelAttribute("newLaboratory") Lab lab) {
		labServiceImpl.saveLab(lab, labId);
		return "redirect:/admin/laboratory";
	}

	@GetMapping(value = "/laboratory/delete/{labID}")
	public String deleteLab(@PathVariable("labID") String labID, @RequestParam(value = "pageNum") Integer pageNumber)
			throws NumberFormatException, Exception {
		labServiceImpl.deleteLab(Integer.parseInt(labID));
		return "redirect:/admin/laboratory?pageNumber=" + pageNumber;
	}

	@GetMapping(value = "/laboratory/{labID}")
	public String toEditLabPage(Model model, @PathVariable("labID") String labID)
			throws Exception, NumberFormatException {
		Lab lab = labServiceImpl.getLabById(Integer.parseInt(labID));
		List<Cate> allCate = cateServiceImpl.findAllCate();
		model.addAttribute("allCate", allCate);
		model.addAttribute("newLaboratory", lab);
		model.addAttribute("labId", lab.getLabID());
		return "admin2/save-lab";
	}
}
