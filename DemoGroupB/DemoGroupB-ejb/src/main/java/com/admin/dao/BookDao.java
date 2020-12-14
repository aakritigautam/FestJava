/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;


import com.admin.dto.BookDto;

import com.payrollSystem.entity.common.BookCategory;
import javax.ejb.Local;

/**
 *
 * @author Akriti Gautam
 */
@Local
public interface BookDao extends StatusableDao<BookCategory> {
    boolean checkIfBookCategoryNameAlreadyExists(BookDto bookDto);

    boolean checkIfBookCategoryDescriptionAlreadyExists(BookDto bookDto);
    
    
    
}
