package com.vncodelab.service.serviceImpl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.*;
import com.vncodelab.entity.Cate;
import com.vncodelab.entity.Exercise;
import com.vncodelab.entity.Lab;
import com.vncodelab.respository.ExerciseRepository;
import com.vncodelab.respository.LabRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private LabRespository labRespository;

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("/firebase/vncodelab2-firebase-adminsdk-q7yo7-280f7c5f40.json").getInputStream()))
                    .setDatabaseUrl("https://vncodelab2-default-rtdb.firebaseio.com/")
                    .setStorageBucket("vncodelab2.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveToFirebase(Lab lab, Cate cate, String reference) {
        if(cate == null) {
            final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = mDatabase.getReference(reference).child(lab.getLabID() + "");
            ref.setValueAsync(lab);
        }
        if(lab == null) {
            final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = mDatabase.getReference(reference).child(cate.getCateID() + "");
            ref.setValueAsync(cate);
        }
    }

    public Object[] getFromFirebase(Integer id, String reference) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(reference).child(id.toString());
        if("lab".equals(reference)) {
            Lab[] labs = {null};
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    labs[0] = snapshot.getValue(Lab.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed");
                }
            });
            while (labs[0] == null) {
                Lab l = labs[0];
            }
            return labs;
        } else {
            Cate[] cates = {null};
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    cates[0] = snapshot.getValue(Cate.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed");
                }
            });
            while (cates[0] == null) {
                Cate c = cates[0];
            }
            return cates;
        }
    }

    public void deleteFromFirebase(String reference, Integer id) {
        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = mDatabase.getReference(reference).child(id + "");
        ref.setValueAsync(null);
    }

    public String saveFileToFirebase(MultipartFile multipartFile, Integer labId, LocalDateTime localDateTime, String description) {
        final StorageClient storageClient = StorageClient.getInstance();
        InputStream file = null;
        try {
            file = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String submitTime = localDateTime.format(format);
        String blobString = "exercise/" + submitTime +"/lab"+ labId +"/"+ description+"/"+ multipartFile.getOriginalFilename();
        Blob blob = storageClient.bucket().create(blobString, file);
        String url = blob.signUrl(100, TimeUnit.DAYS).toString();
        Exercise exercise = new Exercise(labRespository.findById(labId).get().getName(),submitTime,description, url);
        exerciseRepository.save(exercise);
        return url;
    }
}