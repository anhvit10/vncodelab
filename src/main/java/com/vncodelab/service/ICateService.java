//
package com.vncodelab.service;

import java.util.List;

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

	List<Cate> findAllCate();

	void saveCate(Cate cate, String cateId);

	void deleteCate(Integer cateID) throws Exception;

	Cate getCateById(Integer cateID) throws Exception;

	List<Cate> getAllCates();
}
