//
package com.vncodelab.service.serviceImpl;

import com.vncodelab.entity.Lab;
import com.vncodelab.exception.PageNotFoundException;
import com.vncodelab.respository.LabRespository;
import com.vncodelab.service.ILabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private FirebaseService firebaseService;

    @Override
    public List<Lab> getAllLabs() {
        List<Lab> lstLabs = labRespository.findAll();
        return lstLabs;
    }

    @Override
    public void saveLab(Lab lab, String labId) throws PageNotFoundException {
        if ("".equals(labId)) {
            labRespository.save(lab);
            // save new to firebase
            firebaseService.saveToFirebase(lab, null, "lab");
        } else {
            Optional<Lab> oldLab = labRespository.findById(Integer.parseInt(labId));

            oldLab.get().setLabID(Integer.parseInt(labId));
            oldLab.get().setName(lab.getName());
            oldLab.get().setDescription(lab.getDescription());
            oldLab.get().setHtml(lab.getHtml());
            oldLab.get().setCateID(lab.getCateID());
            labRespository.save(oldLab.get());
            // edit in firebase
            firebaseService.saveToFirebase(oldLab.get(), null, "lab");
        }
    }

    @Override
    public void deleteLab(Integer labID) throws PageNotFoundException {
        labRespository.deleteById(labID);
        firebaseService.deleteFromFirebase("lab", labID);
    }

    @Override
    public Lab getLabById(Integer labID) {
        Object[] lab = firebaseService.getFromFirebase(labID, "lab");
        return (Lab) lab[0];
    }
}
