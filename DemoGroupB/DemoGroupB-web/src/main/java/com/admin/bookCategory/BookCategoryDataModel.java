/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.bookCategory;

import com.admin.dto.BookDto;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Akriti Gautam
 */
@Getter
@Setter
@ManagedBean
@SessionScoped
public class BookCategoryDataModel implements Serializable{
    
    private BookDto bookDto;
    private boolean createEditPanel;
    private List<BookDto> bookDtos;

    public BookDto getBookDto() {
        if (bookDto == null) {
            bookDto = new BookDto();
        }
        return bookDto;
    }   
    
}
