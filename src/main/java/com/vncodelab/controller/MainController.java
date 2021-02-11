package com.vncodelab.controller;

import com.vncodelab.entity.Lab;
import com.vncodelab.respository.LabRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @Autowired
    private LabRespository labRespository;

    @GetMapping("/lab/{docID}")
    public String lab(Model model, @PathVariable(name = "docID") String docID) {

        Lab lab = labRespository.findByDocID(docID);
        model.addAttribute("lab", lab);
        return "lab";
    }

    @GetMapping("/hello")
    public String hello(Model model) {


        Lab lab = labRespository.findByDocID("1EEGARIc9dEj9mpnmKoYP8n4EA9KNH9qR0W2c6CYEWT0");
     //   Lab lab = new Lab("1EEGARIc9dEj9mpnmKoYP8n4EA9KNH9qR0W2c6CYEWT0", "Tiêu đề");
        model.addAttribute("lab", lab.getName());
        return "hello";
    }


}
