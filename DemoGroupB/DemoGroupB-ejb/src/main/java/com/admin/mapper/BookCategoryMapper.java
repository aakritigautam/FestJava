/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.mapper;

import com.admin.dto.BookDto;
import com.payrollSystem.entity.common.BookCategory;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Akriti Gautam
 */
public class BookCategoryMapper extends AbstractProfileMapper{
    
    public static BookDto convertToDto(BookCategory bookCategory) {
        BookDto bookDto = new BookDto();
          
        convertCommonParameters(bookDto, bookCategory);
        return bookDto;
    }
     public static List<BookDto> convertToDtos(List<BookCategory> bookCategorys) {
        List<BookDto> bookDtos = new ArrayList<>();
        for (BookCategory bookCategory : bookCategorys) {
            bookDtos.add(convertToDto(bookCategory));
        }
        return bookDtos;
    }

    public static BookDto convertToDtoForDropDown(BookCategory bookCategory) {
        BookDto bookDto = new BookDto();
        bookDto.setDescription(bookCategory.getDescription());
        bookDto.setId(bookCategory.getId());
        bookDto.setName(bookCategory.getName());
        return bookDto;
    }
    
    public static List<BookDto> convertToDtosForDropDown(List<BookCategory> bookCategorys) {
        List<BookDto> bookDtos = new ArrayList<>();
        for (BookCategory bookCategory : bookCategorys) {
            bookDtos.add(convertToDtoForDropDown(bookCategory));
        }
        return bookDtos;
    }

}


    


    

