//
package com.vncodelab.service;

import org.springframework.data.domain.Page;

import com.vncodelab.entity.Cate;

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
public interface ICateService {

	void saveCate(Cate cate);

	void deleteCate(Integer cateID);

	Cate getCateById(Integer cateID);

	Page<Cate> getPageCate(Integer pageNum, Integer pageSize);
}
