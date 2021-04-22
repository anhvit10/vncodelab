package com.vncodelab.controller;

import com.google.gson.Gson;
import com.vncodelab.entity.Home;
import com.vncodelab.entity.Lab;
import com.vncodelab.json.LabInfo;
import com.vncodelab.model.AjaxResponseBody;
import com.vncodelab.respository.CateRespository;
import com.vncodelab.respository.LabRespository;
import com.vncodelab.service.serviceImpl.FirebaseService;
import com.vncodelab.service.serviceImpl.HomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
public class MainController {

    @Autowired
    private LabRespository labRespository;

    @Autowired
    private CateRespository cateRespository;

    @Autowired
    private HomeServiceImpl homeServiceImpl;

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Lab newLab) throws IOException, InterruptedException {
        Lab lab = labRespository.findByDocID(newLab.getDocID());
        if (lab == null)
            lab = newLab;

        File dir = new File("D:/CodeLab");
        Process p = Runtime.getRuntime().exec("claat export " + newLab.getDocID(), null, dir);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line = input.readLine();
        p.waitFor();
        String arr[] = line.split("\t");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dir + "/" + arr[1] + "/codelab.json")));
        String totalLine = "";
        while ((line = br.readLine()) != null) {
            totalLine = totalLine + line;
        }
        LabInfo labInfo = new Gson().fromJson(totalLine, LabInfo.class);
        lab.setName(labInfo.getTitle());
        br = new BufferedReader(new InputStreamReader(new FileInputStream(dir + "/" + arr[1] + "/index.html")));
        totalLine = "";
        while ((line = br.readLine()) != null) {
            totalLine = totalLine + line + "\n";
        }
        lab.setHtml(totalLine);
        lab.setCateID(newLab.getCateID());
        lab.setDescription(newLab.getDescription());
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        ajaxResponseBody.setUpdate(labRespository.existsByDocID(newLab.getDocID()));


        labRespository.save(lab);
        firebaseService.saveToFirebase(lab, null, "lab");
        return ResponseEntity.ok().body(ajaxResponseBody);
    }

    @GetMapping("/lab/{labID}")
    public String lab(Model model, @PathVariable(name = "labID") int labID) {
        Lab[] lab = (Lab[]) firebaseService.getFromFirebase(labID, "lab");
        model.addAttribute("lab", lab[0]);
        return "lab";
    }

    @GetMapping("/")
    public String index(@RequestParam(name="sk", required = false) String keyword, Model model) throws InterruptedException, ExecutionException {
        showHome(model, 0, keyword);
        return "index";
    }

    @GetMapping("/cate/{cateID}")
    public String cate(Model model, @PathVariable(name = "cateID") int cateID)
            throws InterruptedException, ExecutionException {
        showHome(model, cateID, null);
        return "index";
    }

    void showHome(Model model, int cateID, String keyword) throws ExecutionException, InterruptedException {
        Map<String, Object> infor = homeServiceImpl.getObjectFirebase();
        Home newInfor = homeServiceImpl.getInforFirebase(infor);
        model.addAttribute("infor", newInfor);
        if (cateID == 0) {
            if(keyword == null) {
                model.addAttribute("labList", labRespository.findAll());
            } else {
                model.addAttribute("labList", labRespository.findAllByNameContains(keyword));
            }
        }
        else {
            model.addAttribute("labList", labRespository.findAllByCateID(cateID));
        }
        model.addAttribute("cateList", cateRespository.findAllByType(0));
        model.addAttribute("cateListMore", cateRespository.findAllByType(1));

    }
}
