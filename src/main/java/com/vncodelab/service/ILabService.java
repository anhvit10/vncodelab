//
package com.vncodelab.service;

import org.springframework.data.domain.Page;

import com.vncodelab.entity.Lab;

/**
 * This class is .
 * 
 * @Description: .
 * @author: NVAnh
 * @create_date: Feb 18, 2021
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Feb 18, 2021
 */
public interface ILabService {

	void saveLab(Lab lab, String labId);

	void deleteLab(Integer labID) throws Exception;
	
	Lab getLabById(Integer labID) throws Exception;

	Page<Lab> getPageLab(Integer pageNum, Integer pageSize);
}
