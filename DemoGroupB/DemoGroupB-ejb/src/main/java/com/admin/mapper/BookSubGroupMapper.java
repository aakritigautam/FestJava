/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.mapper;

import com.admin.dto.BookSubGroupDto;
import com.payrollSystem.entity.common.BookSubGroup;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akriti Gautam
 */
public class BookSubGroupMapper extends AbstractProfileMapper {
   public static BookSubGroupDto convertToDto(BookSubGroup bookSubGroup) {
        BookSubGroupDto bookSubGroupDto = new BookSubGroupDto();
          
        convertCommonParameters(bookSubGroupDto, bookSubGroup);
        return bookSubGroupDto;
    }
     public static List<BookSubGroupDto> convertToDtos(List<BookSubGroup> bookSubGroups) {
        List<BookSubGroupDto> bookSubGroupDtos = new ArrayList<>();
        for (BookSubGroup bookSubGroup : bookSubGroups) {
            bookSubGroupDtos.add(convertToDto(bookSubGroup));
        }
        return bookSubGroupDtos;
    }

    public static BookSubGroupDto convertToDtoForDropDown(BookSubGroup bookSubGroup) {
        BookSubGroupDto bookSubGroupDto = new BookSubGroupDto();
        bookSubGroupDto.setDescription(bookSubGroup.getDescription());
        bookSubGroupDto.setId(bookSubGroup.getId());
        bookSubGroupDto.setName(bookSubGroup.getName());
        return bookSubGroupDto;
    }
    
    public static List<BookSubGroupDto> convertToDtosForDropDown(List<BookSubGroup> bookSubGroups) {
        List<BookSubGroupDto> bookSubGroupDtos = new ArrayList<>();
        for (BookSubGroup bookSubGroup : bookSubGroups) {
            bookSubGroupDtos.add(convertToDtoForDropDown(bookSubGroup));
        }
        return bookSubGroupDtos;
    }

}


    
