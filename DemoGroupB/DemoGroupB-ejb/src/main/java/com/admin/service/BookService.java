/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.service;

/**
 *
 * @author Akriti Gautam
 * 
 * 
 */

import com.admin.dto.BookDto;
import com.admin.dto.CollegeDto;


import javax.ejb.Local;
import java.util.List;
@Local
public interface BookService {
   /* boolean addBook(BookDto bookDto);
    boolean updateBook(BookDto bookDto);
    boolean deleteBook(BookDto bookDto);
    BookDto getBook(long id);
     List<BookDto> getAllBooks();
*/
     public boolean save(BookDto bookDto);

    boolean delete(BookDto bookDto);
    
    boolean checkIfBookCategoryNameAlreadyExists(BookDto bookDto);

    boolean checkIfBookCategoryDescriptionAlreadyExists(BookDto bookDto);
    
    boolean update(BookDto bookDto);
    
    List<BookDto> findByCollegeId(CollegeDto collegeDto);
    
    List<BookDto> findByCollegeIdForDropDown(CollegeDto collegeDto);
}

