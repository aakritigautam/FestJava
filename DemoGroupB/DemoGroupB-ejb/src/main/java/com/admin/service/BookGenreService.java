/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.service;

import com.admin.dto.BookGenreDto;
import com.admin.dto.CollegeDto;
import java.util.List;

/**
 *
 * @author Akriti Gautam
 */
public interface BookGenreService {
    public boolean save(BookGenreDto bookGenreDto);

    boolean delete(BookGenreDto bookGenreDto);
    
    boolean checkIfBookGenreNameAlreadyExists(BookGenreDto bookGenreDto);

    boolean checkIfBookGenreDescriptionAlreadyExists(BookGenreDto bookGenreDto);
    
    boolean update(BookGenreDto bookGenreDto);
    
    List<BookGenreDto> findByCollegeId(CollegeDto collegeDto);
    
    List<BookGenreDto> findByCollegeIdForDropDown(CollegeDto collegeDto);
}

    

