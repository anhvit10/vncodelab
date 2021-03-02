package com.vncodelab.service.serviceImpl;

import com.vncodelab.respository.CateRespository;
import com.vncodelab.respository.LabRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl {

    @Autowired
    private CateRespository cateRespository;

    @Autowired
    private LabRespository labRespository;

    public Integer getTotalLabs() {
        Integer totalLabs = labRespository.findAll().size();
        return totalLabs;
    }

    public Integer getTotalCates() {
        Integer totalCates = cateRespository.findAll().size();
        return totalCates;
    }

}
