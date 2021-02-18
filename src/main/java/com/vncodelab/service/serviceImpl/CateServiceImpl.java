//
package com.vncodelab.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vncodelab.entity.Cate;
import com.vncodelab.respository.CateRespository;
import com.vncodelab.service.ICateService;

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
@Service
public class CateServiceImpl implements ICateService {

	@Autowired
	private CateRespository cateRespository;

	/*
	 * @see com.vncodelab.service.ICateService#getPageCate(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public Page<Cate> getPageCate(Integer pageNumber, Integer pageSize) {
		Page<Cate> pageCates = cateRespository.findAll(PageRequest.of(pageNumber - 1, pageSize));
		return pageCates;
	}

}
