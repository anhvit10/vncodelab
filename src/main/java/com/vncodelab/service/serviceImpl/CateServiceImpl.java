//
package com.vncodelab.service.serviceImpl;

import com.vncodelab.entity.Cate;
import com.vncodelab.exception.PageNotFoundException;
import com.vncodelab.respository.CateRespository;
import com.vncodelab.service.ICateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is .
 *
 * @Description: .
 * @author: NVAnh
 * @create_date: Feb 18, 2021
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Feb 18, 2021
 */
@Service
public class CateServiceImpl implements ICateService {

    @Autowired
    private CateRespository cateRespository;

    @Override
    public List<Cate> getAllCates() {
        List<Cate> lstCates = cateRespository.findAll();
        return lstCates;
    }

    @Override
    public void saveCate(Cate cate, String cateId) throws PageNotFoundException {
        if ("".equals(cateId)) {
            cateRespository.save(cate);
        } else {
            Optional<Cate> oldCate = cateRespository.findByCateID(Integer.parseInt(cateId));
            if (oldCate.isEmpty()) {
                throw new PageNotFoundException();
            }
            oldCate.get().setCateID(Integer.parseInt(cateId));
            oldCate.get().setName(cate.getName());
            oldCate.get().setDescription(cate.getDescription());
            oldCate.get().setType(cate.getType());
            cateRespository.save(oldCate.get());
        }
    }

    @Override
    public void deleteCate(Integer cateID) throws PageNotFoundException {
        Optional<Cate> cate = cateRespository.findByCateID(cateID);
        if (cate.isEmpty()) {
            throw new PageNotFoundException();
        } else {
            cateRespository.deleteById(cateID);
        }
    }

    @Override
    public Cate getCateById(Integer cateID) throws PageNotFoundException {
        Optional<Cate> cate = cateRespository.findByCateID(cateID);
        if (cate.isEmpty()) {
            throw new PageNotFoundException();
        }
        return cate.get();
    }

    @Override
    public List<Cate> findAllCate() {
        return cateRespository.findAll();
    }

}
