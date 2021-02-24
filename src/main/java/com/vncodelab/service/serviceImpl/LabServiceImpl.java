//
package com.vncodelab.service.serviceImpl;

import java.util.List;
import java.util.Optional;

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

	@Override
	public List<Lab> getAllLabs() {
		List<Lab> lstLabs = labRespository.findAll();
		return lstLabs;
	}

	@Override
	public void saveLab(Lab lab, String labId) {
		if ("".equals(labId)) {
			labRespository.save(lab);
		} else {
			Optional<Lab> oldLab = labRespository.findById(Integer.parseInt(labId));
			oldLab.get().setLabID(Integer.parseInt(labId));
			oldLab.get().setName(lab.getName());
			oldLab.get().setDescription(lab.getDescription());
			oldLab.get().setHtml(lab.getHtml());
			oldLab.get().setCateID(lab.getCateID());
			labRespository.save(oldLab.get());
		}
	}

	@Override
	public void deleteLab(Integer labID) throws Exception {
		Optional<Lab> lab = labRespository.findById(labID);
		if (lab.isEmpty()) {
			throw new Exception();
		} else {
			labRespository.deleteById(labID);
		}
	}

	@Override
	public Lab getLabById(Integer labID) throws Exception {
		Optional<Lab> lab = labRespository.findById(labID);
		if (lab.isEmpty()) {
			throw new Exception();
		}
		return lab.get();
	}
}
