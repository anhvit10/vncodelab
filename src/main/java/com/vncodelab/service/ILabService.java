//
package com.vncodelab.service;

import org.springframework.data.domain.Page;

import com.vncodelab.entity.Cate;
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
	
	Page<Lab> getPageLab(Integer pageNum, Integer pageSize);
}
