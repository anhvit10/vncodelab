package com.vncodelab.admincontroller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @GetMapping("/404")
    public String toNotFoundPage(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("error_code", HttpStatus.NOT_FOUND.value());
                model.addAttribute("error_comment", "Page Not Found");
                return "admin2/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("error_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                model.addAttribute("error_comment", "Something Wrong !");
                return "admin2/404";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("error_code", HttpStatus.FORBIDDEN.value());
                model.addAttribute("error_comment", "Access Is Deny !");
                return "admin2/404";
            }
        }
        model.addAttribute("error_code", HttpStatus.NOT_FOUND.value());
        model.addAttribute("error_comment", "Page Not Found");
        return "admin2/404";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
