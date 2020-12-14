/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.bookSubGroup;

import com.admin.dto.BookGroupDto;
import com.admin.dto.BookSubGroupDto;
import com.admin.service.BookSubGroupService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class BookSubGroupDataModel implements Serializable{
    private BookSubGroupDto bookSubGroupDto;
    private boolean createEditPanel;
    private List<BookSubGroupDto> bookSubGroupDtos;
    private List<BookGroupDto> bookGroupListForDropdown;
    @EJB
    private BookSubGroupService bookSubGroupService;
    
    @PostConstruct
    public void init()
    {
         bookGroupListForDropdown = bookSubGroupService.fetchBookGroupForDropDown();
       
         
    }

    public BookSubGroupDto getBookSubGroupDto(){
        if (bookSubGroupDto == null) {
            bookSubGroupDto = new BookSubGroupDto();
        }
        return bookSubGroupDto;
    }
    
}

