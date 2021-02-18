//
package com.vncodelab.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vncodelab.entity.Lab;
import com.vncodelab.respository.LabRespository;
import com.vncodelab.service.ILabService;

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
public class LabServiceImpl implements ILabService {
	
	@Autowired
	private LabRespository labRespository;
	
	/*
	 * @see com.vncodelab.service.ICateService#getPageCate(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public Page<Lab> getPageLab(Integer pageNumber, Integer pageSize) {
		Page<Lab> pageLabs = labRespository.findAll(PageRequest.of(pageNumber - 1, pageSize));
		return pageLabs;
	}
}
