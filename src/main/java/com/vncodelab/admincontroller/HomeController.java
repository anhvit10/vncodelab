//
package com.vncodelab.admincontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
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

//	@GetMapping("/home/save")
//	public String create(Model model) throws InterruptedException, ExecutionException {
//		Map<String, Object> infor = homeServiceImpl.getObjectFirebase();
//		
//		model.addAttribute("infor", infor);
//		model.addAttribute("newInfor", new Home());
//		return "admin/edit-home";
//	}

	@GetMapping("/home/edit")
	public String toEditHomePage(Model model) throws InterruptedException, ExecutionException {
		Map<String, Object> infor = homeServiceImpl.getObjectFirebase();
		List<Object> listHome = new ArrayList<>(infor.values());
		
		Gson g = new Gson();
		Home newInfor = g.fromJson(listHome.get(0).toString(), Home.class);
		
		model.addAttribute("newInfor", newInfor);
		return "admin/edit-home";
	}

}
