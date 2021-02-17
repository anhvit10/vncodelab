package com.vncodelab.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vncodelab.entity.Lab;

@Repository
public interface LabRespository extends JpaRepository<Lab, Long> {

	Lab findByDocID(String docID);

	boolean existsByDocID(String docID);

	List<Lab> findAllByCateID(int cateID);
}