package com.vncodelab.controller;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Controller
public class TestFirebaseController {
	@GetMapping(path = "/testFirebase")
	public String testFirebase(Model model) throws ExecutionException, InterruptedException {

		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("test");

		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference docRef = dbFirestore.document("labs/lab1");
		ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = docRef.get();

		Map<String, Object> x = documentSnapshotApiFuture.get().getData();

		model.addAttribute("data", x);
		return "test-firebase";
	}
}
