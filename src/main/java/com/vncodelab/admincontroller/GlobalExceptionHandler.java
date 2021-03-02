//
package com.vncodelab.admincontroller;

import com.vncodelab.exception.PageNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class is .
 *
 * @Description: .
 * @author: NVAnh
 * @create_date: Oct 26, 2020
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Oct 26, 2020
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PageNotFoundException.class})
    public String handlePageNotFoundException() {
        return "redirect:/404";
    }
}
