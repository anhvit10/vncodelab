package com.vncodelab.respository;


import com.vncodelab.entity.Lab;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LabRespository extends CrudRepository<Lab, Long> {

    List<Lab> findByName(String name);

    Lab findById(long id);
}