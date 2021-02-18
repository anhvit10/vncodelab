//
package com.vncodelab.service.serviceImpl;

import java.util.Optional;

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

	@Override
	public Page<Cate> getPageCate(Integer pageNumber, Integer pageSize) {
		Page<Cate> pageCates = cateRespository.findAll(PageRequest.of(pageNumber - 1, pageSize));
		return pageCates;
	}

	@Override
	public void saveCate(Cate cate) {
		if(0 == cate.getCateID()) {
			cateRespository.save(cate);
		} else {
			Optional<Cate> oldCate = cateRespository.findByCateID(cate.getCateID());
			oldCate.get().setCateID(cate.getCateID());
			oldCate.get().setName(cate.getName());
			oldCate.get().setDescription(cate.getDescription());
			oldCate.get().setType(cate.getType());
			cateRespository.save(oldCate.get());
		}
	}

	@Override
	public void deleteCate(Integer cateID) {
		cateRespository.deleteById(cateID);
	}

	@Override
	public Cate getCateById(Integer cateID) {
		Optional<Cate> cate = cateRespository.findByCateID(cateID);
		if(cate.isEmpty()) {
			return null;
		}
		return cate.get();
	}

}
