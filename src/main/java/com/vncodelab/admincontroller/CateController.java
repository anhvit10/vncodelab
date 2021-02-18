//
package com.vncodelab.admincontroller;

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
import com.vncodelab.service.serviceImpl.CateServiceImpl;

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
	public String getCate(Model model, @RequestParam(defaultValue = "1", value = "pageNumber") String pageNumber)
			throws NumberFormatException {
		Page<Cate> pageCategories = cateServiceImpl.getPageCate(Integer.parseInt(pageNumber),
				CommonConstants.CATE_PAGE_SIZE);
		model.addAttribute("pageNum", Integer.parseInt(pageNumber));
		model.addAttribute("pageCate", pageCategories);
		model.addAttribute("pageSize", CommonConstants.CATE_PAGE_SIZE);
		return "admin/cate";
	}

	@GetMapping(value = "/cate/new")
	public String toSaveCatePage(Model model) {
		model.addAttribute("newCategory", new Cate());
		return "admin/save-cate";
	}

	@PostMapping(value = "/cate/save")
	public String saveCate(@ModelAttribute("newCategory") Cate cate) {
		System.out.println(cate.getCateID());
		cateServiceImpl.saveCate(cate);
		return "redirect:";
	}

	@GetMapping(value = "/cate/delete/{cateID}")
	public String deleteCate(@PathVariable("cateID") String cateID,
			@RequestParam(value = "pageNum") Integer pageNumber) {
		cateServiceImpl.deleteCate(Integer.parseInt(cateID));
		return "redirect:/admin/cate?pageNumber=" + pageNumber;
	}

	@GetMapping(value = "/cate/{cateID}")
	public String toEditCatePage(Model model, @PathVariable("cateID") String cateID) {
		Cate cate = cateServiceImpl.getCateById(Integer.parseInt(cateID));
		model.addAttribute("newCategory", cate);
		return "admin/save-cate";
	}
}
