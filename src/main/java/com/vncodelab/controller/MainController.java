package com.vncodelab.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.vncodelab.entity.Cate;
import com.vncodelab.entity.Home;
import com.vncodelab.entity.Lab;
import com.vncodelab.json.LabInfo;
import com.vncodelab.model.AjaxResponseBody;
import com.vncodelab.respository.CateRespository;
import com.vncodelab.respository.LabRespository;
import com.vncodelab.service.serviceImpl.HomeServiceImpl;

@Controller
public class MainController {

	@Autowired
	private LabRespository labRespository;

	@Autowired
	private CateRespository cateRespository;

	@Autowired
	private HomeServiceImpl homeServiceImpl;

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Lab lab) throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec("/Users/xuanlam/go/bin/claat export " + lab.getDocID());
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String line = input.readLine();
		p.waitFor();
		String arr[] = line.split("\t");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arr[1] + "/codelab.json")));
		String totalLine = "";
		while ((line = br.readLine()) != null) {
			totalLine = totalLine + line;
		}
		LabInfo labInfo = new Gson().fromJson(totalLine, LabInfo.class);
		lab.setName(labInfo.getTitle());
		br = new BufferedReader(new InputStreamReader(new FileInputStream(arr[1] + "/index.html")));
		totalLine = "";
		while ((line = br.readLine()) != null) {
			totalLine = totalLine + line;
		}
		lab.setHtml(totalLine);
		lab.setCateID(lab.getCateID());
		AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
		ajaxResponseBody.setUpdate(labRespository.existsByDocID(lab.getDocID()));
		labRespository.save(lab);

		return ResponseEntity.ok().body(ajaxResponseBody);
	}

	@GetMapping("/createCate")
	public String createCate() {
		cateRespository.save(new Cate("Java", ""));
		cateRespository.save(new Cate("Swing", ""));
		cateRespository.save(new Cate("JSP", ""));
		cateRespository.save(new Cate("Servlet", ""));
		cateRespository.save(new Cate("JPA", ""));
		cateRespository.save(new Cate("Hibernate", ""));
		cateRespository.save(new Cate("Spring", ""));
		cateRespository.save(new Cate("Android", ""));

		cateRespository.save(new Cate("C", "", 1));
		cateRespository.save(new Cate("C++", "", 1));
		cateRespository.save(new Cate("PHP", "", 1));
		cateRespository.save(new Cate("JavaScript", "", 1));
		return "done";
	}

	@GetMapping("/lab/{docID}")
	public String lab(Model model, @PathVariable(name = "docID") String docID) {
		Lab lab = labRespository.findByDocID(docID);
		model.addAttribute("lab", lab);
		return "lab";
	}

	@GetMapping("/")
	public String index(Model model) throws InterruptedException, ExecutionException {
		Map<String, Object> infor = homeServiceImpl.getObjectFirebase();
		Home newInfor = homeServiceImpl.getInforFirebase(infor);
		model.addAttribute("infor", newInfor);

		model.addAttribute("labList", labRespository.findAll());
		model.addAttribute("cateList", cateRespository.findAllByType(0));
		model.addAttribute("cateListMore", cateRespository.findAllByType(1));
		return "index";
	}

	@GetMapping("/cate/{cateID}")
	public String cate(Model model, @PathVariable(name = "cateID") int cateID)
			throws InterruptedException, ExecutionException {
		Map<String, Object> infor = homeServiceImpl.getObjectFirebase();
		Home newInfor = homeServiceImpl.getInforFirebase(infor);
		model.addAttribute("infor", newInfor);

		model.addAttribute("labList", labRespository.findAllByCateID(cateID));
		model.addAttribute("cateList", cateRespository.findAllByType(0));
		model.addAttribute("cateListMore", cateRespository.findAllByType(1));
		return "index";
	}

	@GetMapping("/test")
	public String test() {
		return "test";
	}

}
