//
package com.vncodelab.admincontroller;

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
	
	@ExceptionHandler(value = NumberFormatException.class)
	public String handleNumberFormatException() {
		return "index";
	}
}
