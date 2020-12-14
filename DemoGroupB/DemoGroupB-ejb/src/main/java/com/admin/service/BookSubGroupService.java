/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.service;

import com.admin.dto.BookGroupDto;
import com.admin.dto.BookSubGroupDto;
import com.admin.dto.CollegeDto;
import java.util.List;

/**
 *
 * @author Akriti Gautam
 */
public interface BookSubGroupService {
    public boolean save(BookSubGroupDto bookSubGroupDto);

    boolean delete(BookSubGroupDto bookSubGroupDto);
    
    boolean checkIfBookSubGroupNameAlreadyExists(BookSubGroupDto bookSubGroupDto);

    boolean checkIfBookSubGroupDescriptionAlreadyExists(BookSubGroupDto bookSubGroupDto);
    
    boolean update(BookSubGroupDto bookSubGroupDto);
    
    List<BookSubGroupDto> findByCollegeId(CollegeDto collegeDto);
    
    List<BookSubGroupDto> findByCollegeIdForDropDown(CollegeDto collegeDto);
    List<BookGroupDto> fetchBookGroupForDropDown();
}
    

