//
package com.vncodelab.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String getCate(Model model,
			@RequestParam(defaultValue = "1", value = "pageNumber", required = true) String pageNumber)
			throws NumberFormatException {
		Page<Cate> pageCategories = cateServiceImpl.getPageCate(Integer.parseInt(pageNumber),
				CommonConstants.CATE_PAGE_SIZE);
		model.addAttribute("pageCate", pageCategories);
		model.addAttribute("pageSize", CommonConstants.CATE_PAGE_SIZE);
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
