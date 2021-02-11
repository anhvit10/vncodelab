package com.vncodelab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/lab")
    public String lab() {
        return "lab";
    }


}
