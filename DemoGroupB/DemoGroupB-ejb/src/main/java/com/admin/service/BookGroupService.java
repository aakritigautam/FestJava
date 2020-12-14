/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.service;


import com.admin.dto.BookGroupDto;
import com.admin.dto.CollegeDto;
import java.util.List;

/**
 *
 * @author Akriti Gautam
 */
public interface BookGroupService {
     public boolean save(BookGroupDto bookGroupDto);

    boolean delete(BookGroupDto bookGroupDto);
    
    boolean checkIfBookGroupNameAlreadyExists(BookGroupDto bookGroupDto);

    boolean checkIfBookGroupDescriptionAlreadyExists(BookGroupDto bookGroupDto);
    
    boolean update(BookGroupDto bookGroupDto);
    
    List<BookGroupDto> findByCollegeId(CollegeDto collegeDto);
    
    List<BookGroupDto> findByCollegeIdForDropDown(CollegeDto collegeDto);
}

    

