//
package com.vncodelab.service.serviceImpl;

import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vncodelab.entity.Lab;
import com.vncodelab.exception.PageNotFoundException;
import com.vncodelab.respository.LabRespository;
import com.vncodelab.service.ILabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
    public void saveLab(Lab lab, String labId) throws PageNotFoundException {
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
    public void deleteLab(Integer labID) throws PageNotFoundException {
        Optional<Lab> lab = labRespository.findById(labID);

        labRespository.deleteById(labID);

    }

    @Override
    public Lab getLabById(Integer labID) throws PageNotFoundException {
        Optional<Lab> lab = labRespository.findById(labID);
        return lab.get();
    }

    public void saveLabToFirebase(Lab lab) {
        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = mDatabase.getReference("lab").child(lab.getLabID() + "");
        ref.setValueAsync(lab);
    }

    public void saveExerciseToFirebase(MultipartFile multipartFile) {
        final StorageClient storageClient = StorageClient.getInstance();
        InputStream file = null;
        try {
            file = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String blobString = "testUpload/" + multipartFile.getOriginalFilename();
        Blob blob = storageClient.bucket().create(blobString, file);
        String url = blob.signUrl(1000, TimeUnit.DAYS).toString();
        System.out.println(url);
    }
}
