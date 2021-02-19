//
package com.vncodelab.service.serviceImpl;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.vncodelab.service.IHomeService;

/**
 * This class is . 
 * 
 * @Description: .
 * @author: NVAnh
 * @create_date: Feb 19, 2021
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Feb 19, 2021
 */
@Service
public class HomeServiceImpl implements IHomeService {

	@Override
	public Map<String, Object> getObjectFirebase() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference docRef = dbFirestore.document("home/infor");
		ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = docRef.get();
		return documentSnapshotApiFuture.get().getData();
	}
	
}
