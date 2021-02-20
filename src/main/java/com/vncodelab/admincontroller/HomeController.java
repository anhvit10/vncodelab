//
package com.vncodelab.admincontroller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vncodelab.entity.Home;
import com.vncodelab.service.serviceImpl.HomeServiceImpl;

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

	@GetMapping("/home")
	public String get(Model model) throws InterruptedException, ExecutionException {
		Map<String, Object> infor = homeServiceImpl.getObjectFirebase();

		model.addAttribute("infor", infor);
		return "admin/home";
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
		return "admin/edit-home";
	}

}
