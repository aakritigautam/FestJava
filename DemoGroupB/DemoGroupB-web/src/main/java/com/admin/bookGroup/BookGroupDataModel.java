/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.bookGroup;


import com.admin.dto.BookGroupDto;
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
public class BookGroupDataModel implements Serializable {
    private BookGroupDto bookGroupDto;
    private boolean createEditPanel;
    private List<BookGroupDto> bookGroupDtos;

    public BookGroupDto getBookGroupDto() {
        if (bookGroupDto == null) {
            bookGroupDto = new BookGroupDto();
        }
        return bookGroupDto;
    }   
}
