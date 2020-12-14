/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.bookCategory;

import com.admin.dto.AdminDto;
import com.admin.dto.BookDto;
import com.admin.dto.CollegeDto;
import com.admin.service.BookService;
import com.admin.util.Utility;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
public class BookCategoryBean { // ..BeanJsf is better
    @ManagedProperty(value = "#{bookCategoryDataModel}")
    private BookCategoryDataModel bookCategoryDataModel;
   
    @EJB
    private BookService bookService;
    
    private CollegeDto collegeDto;

    private AdminDto adminDto;

        
    
       @PostConstruct
    public void init() {
        collegeDto = new CollegeDto();
        collegeDto.setId(1l);

        adminDto = new AdminDto();
        adminDto.setId(1L);

        adminDto.setCollegeDto(collegeDto);
    }
    public String returnToPage() {
        return "bookCategory.xhtml?faces-redirect=true";
    }

    public String initCreate() {
        bookCategoryDataModel.setBookDto(new BookDto());
        bookCategoryDataModel.setCreateEditPanel(true);
        return returnToPage();
    }

    public String saveUpdate() {
        bookCategoryDataModel.getBookDto().setUpdatedByAdminDto(adminDto);
        bookCategoryDataModel.getBookDto().setCreatedByAdminDto(adminDto);
        
        if (bookService.checkIfBookCategoryNameAlreadyExists(bookCategoryDataModel.getBookDto())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book Name Already Exists", null));
            return returnToPage();
        }
        if (bookService.checkIfBookCategoryDescriptionAlreadyExists(bookCategoryDataModel.getBookDto())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book Description Already Exists", null));
            return returnToPage();
        }

        if (bookCategoryDataModel.getBookDto().getId() == null) {
            return save();
        } else {
            return update();
        }
    }

    private String update() {
        boolean success = bookService.update(bookCategoryDataModel.getBookDto());
        if (success) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully", null));
        }
        return navigateToPage();
    }

    private String save() {
        boolean response = bookService.save(bookCategoryDataModel.getBookDto());
        if (response) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Saved", null));
        }
        return navigateToPage();
    }

    public String navigateToPage() {
        Utility.removeSessionBeanJSFDataModelObject("bookCategoryDataModel");
        bookCategoryDataModel = (BookCategoryDataModel) Utility.getSessionObject("bookCategoryDataModel");
        bookCategoryDataModel.setBookDtos(bookService.findByCollegeId(collegeDto));
        return returnToPage();
    }

    public String initEdit() {
        bookCategoryDataModel.setCreateEditPanel(true);
        return returnToPage();
    }

    public String delete() {
        bookCategoryDataModel.getBookDto().setDeletedByAdminDto(adminDto);
        
        boolean success = bookService.delete(bookCategoryDataModel.getBookDto());
        if (success) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted Successfully", null));
        }
        return navigateToPage();
    }

}


