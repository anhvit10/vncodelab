package com.vncodelab.respository;

import com.vncodelab.entity.Lab;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRespository extends JpaRepository<Lab, Integer> {

    Lab findByDocID(String docID);

    boolean existsByDocID(String docID);

    List<Lab> findAllByCateID(int cateID);

    Page<Lab> findAll(Pageable pageable);
}