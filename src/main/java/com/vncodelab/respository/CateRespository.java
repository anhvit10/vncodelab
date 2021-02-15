package com.vncodelab.respository;


import com.vncodelab.entity.Cate;
import com.vncodelab.entity.Lab;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CateRespository extends CrudRepository<Cate, String> {
    //  List<Cate> findAllByMore(boolean isMore);
    Cate findByCateID(int cateID);

    List<Cate> findAllByType(int type);
}