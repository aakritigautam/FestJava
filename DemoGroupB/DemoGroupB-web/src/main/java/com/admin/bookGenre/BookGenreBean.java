/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.bookGenre;

import com.admin.dto.AdminDto;
import com.admin.dto.BookGenreDto;
import com.admin.dto.CollegeDto;
import com.admin.service.BookGenreService;
import com.admin.util.Utility;
import java.io.Serializable;
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
public class BookGenreBean implements Serializable {
    @ManagedProperty(value = "#{bookGenreDataModel}")
    private BookGenreDataModel bookGenreDataModel;
   
    @EJB
    private BookGenreService bookGenreService;
    
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
        return "bookGenre.xhtml?faces-redirect=true";
    }

    public String initCreate() {
        bookGenreDataModel.setBookGenreDto(new BookGenreDto());
        bookGenreDataModel.setCreateEditPanel(true);
        return returnToPage();
    }

    public String saveUpdate() {
        bookGenreDataModel.getBookGenreDto().setUpdatedByAdminDto(adminDto);
        bookGenreDataModel.getBookGenreDto().setCreatedByAdminDto(adminDto);
        
        if (bookGenreService.checkIfBookGenreNameAlreadyExists(bookGenreDataModel.getBookGenreDto())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book Name Already Exists", null));
            return returnToPage();
        }
        if (bookGenreService.checkIfBookGenreDescriptionAlreadyExists(bookGenreDataModel.getBookGenreDto())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book Description Already Exists", null));
            return returnToPage();
        }

        if (bookGenreDataModel.getBookGenreDto().getId() == null) {
            return save();
        } else {
            return update();
        }
    }

    private String update() {
        boolean success = bookGenreService.update(bookGenreDataModel.getBookGenreDto());
        if (success) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully", null));
        }
        return navigateToPage();
    }

    private String save() {
        boolean response = bookGenreService.save(bookGenreDataModel.getBookGenreDto());
        if (response) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Saved", null));
        }
        return navigateToPage();
    }

    public String navigateToPage() {
        Utility.removeSessionBeanJSFDataModelObject("bookGenreDataModel");
        bookGenreDataModel = (BookGenreDataModel) Utility.getSessionObject("bookGenreDataModel");
        bookGenreDataModel.setBookGenreDtos(bookGenreService.findByCollegeId(collegeDto));
        return returnToPage();
    }

    public String initEdit() {
        bookGenreDataModel.setCreateEditPanel(true);
        return returnToPage();
    }

    public String delete() {
        bookGenreDataModel.getBookGenreDto().setDeletedByAdminDto(adminDto);
        
        boolean success = bookGenreService.delete(bookGenreDataModel.getBookGenreDto());
        if (success) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted Successfully", null));
        }
        return navigateToPage();
    }

}



    

