//
package com.vncodelab.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.vncodelab.entity.Home;

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
public interface IHomeService {

	Map<String, Object> getObjectFirebase() throws InterruptedException, ExecutionException;

	Home getInforFirebase(Map<String, Object> objectFirebase);

	void saveObjectFirebase(Home home) throws IOException;
}
