//
package com.vncodelab.service;

import com.vncodelab.entity.Cate;
import com.vncodelab.exception.PageNotFoundException;

import java.util.List;

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
public interface ICateService {

    void saveCate(Cate cate, String cateId) throws PageNotFoundException;

    void deleteCate(Integer cateID) throws PageNotFoundException;

    Cate getCateById(Integer cateID) throws PageNotFoundException;

    List<Cate> getAllCates();
}
