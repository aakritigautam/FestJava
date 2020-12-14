/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.mapper;


import com.admin.dto.BookGroupDto;

import com.payrollSystem.entity.common.BookGroup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akriti Gautam
 */
public class BookGroupMapper extends AbstractProfileMapper {
   public static BookGroupDto convertToDto(BookGroup bookGroup) {
        BookGroupDto bookGroupDto = new BookGroupDto();
          
        convertCommonParameters(bookGroupDto, bookGroup);
        return bookGroupDto;
    }
     public static List<BookGroupDto> convertToDtos(List<BookGroup> bookGroups) {
        List<BookGroupDto> bookGroupDtos = new ArrayList<>();
        for (BookGroup bookGroup : bookGroups) {
            bookGroupDtos.add(convertToDto(bookGroup));
        }
        return bookGroupDtos;
    }

    public static BookGroupDto convertToDtoForDropDown(BookGroup bookGroup) {
        BookGroupDto bookGroupDto = new BookGroupDto();
        bookGroupDto.setDescription(bookGroup.getDescription());
        bookGroupDto.setId(bookGroup.getId());
        bookGroupDto.setName(bookGroup.getName());
        return bookGroupDto;
    }
    
    public static List<BookGroupDto> convertToDtosForDropDown(List<BookGroup> bookGroups) {
        List<BookGroupDto> bookGroupDtos = new ArrayList<>();
        for (BookGroup bookGroup : bookGroups) {
            bookGroupDtos.add(convertToDtoForDropDown(bookGroup));
        }
        return bookGroupDtos;
    }

}


    
