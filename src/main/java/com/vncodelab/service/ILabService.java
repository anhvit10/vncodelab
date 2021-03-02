//
package com.vncodelab.service;

import com.vncodelab.entity.Lab;
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
public interface ILabService {

    void saveLab(Lab lab, String labId) throws PageNotFoundException;

    void deleteLab(Integer labID) throws PageNotFoundException;

    Lab getLabById(Integer labID) throws PageNotFoundException;

    List<Lab> getAllLabs();
}
