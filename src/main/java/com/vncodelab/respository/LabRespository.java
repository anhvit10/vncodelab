package com.vncodelab.respository;


import com.vncodelab.entity.Lab;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRespository extends CrudRepository<Lab, Long> {
    Lab findByDocID(String docID);
    boolean existsByDocID(String docID);
    List<Lab> findAllByCateID(int cateID);
}