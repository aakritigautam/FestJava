/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.dto.BookGroupDto;
import com.payrollSystem.entity.common.BookGroup;
import javax.ejb.Local;

/**
 *
 * @author Akriti Gautam
 */
@Local
public interface BookGroupDao extends StatusableDao<BookGroup> {
    boolean checkIfBookGroupNameAlreadyExists(BookGroupDto bookGroupDto);

    boolean checkIfBookGroupDescriptionAlreadyExists(BookGroupDto bookGroupDto);
}
    

