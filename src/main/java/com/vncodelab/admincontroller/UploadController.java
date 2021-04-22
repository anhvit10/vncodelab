package com.vncodelab.admincontroller;

import com.vncodelab.service.serviceImpl.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
public class UploadController {

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("description") String description,
                                        @RequestParam("labId") Integer labId) {
        String url = firebaseService.saveFileToFirebase(file, labId, LocalDateTime.now(),description);
        System.out.println(url);
        System.out.println(description);
        System.out.println(labId);
        return ResponseEntity.ok("OK");
    }
}