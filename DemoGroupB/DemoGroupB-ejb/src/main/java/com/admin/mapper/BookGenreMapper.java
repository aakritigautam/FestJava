/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.mapper;

import com.admin.dto.BookGenreDto;
import com.payrollSystem.entity.common.BookGenre;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akriti Gautam
 */
public class BookGenreMapper extends AbstractProfileMapper {
   public static BookGenreDto convertToDto(BookGenre bookGenre) {
        BookGenreDto bookGenreDto = new BookGenreDto();
          
        convertCommonParameters(bookGenreDto, bookGenre);
        return bookGenreDto;
    }
     public static List<BookGenreDto> convertToDtos(List<BookGenre> bookGenres) {
        List<BookGenreDto> bookGenreDtos = new ArrayList<>();
        for (BookGenre bookGenre : bookGenres) {
            bookGenreDtos.add(convertToDto(bookGenre));
        }
        return bookGenreDtos;
    }

    public static BookGenreDto convertToDtoForDropDown(BookGenre bookGenre) {
        BookGenreDto bookGenreDto = new BookGenreDto();
        bookGenreDto.setDescription(bookGenre.getDescription());
        bookGenreDto.setId(bookGenre.getId());
        bookGenreDto.setName(bookGenre.getName());
        return bookGenreDto;
    }
    
    public static List<BookGenreDto> convertToDtosForDropDown(List<BookGenre> bookGenres) {
        List<BookGenreDto> bookGenreDtos = new ArrayList<>();
        for (BookGenre bookGenre : bookGenres) {
            bookGenreDtos.add(convertToDtoForDropDown(bookGenre));
        }
        return bookGenreDtos;
    }

}


    
 
    
