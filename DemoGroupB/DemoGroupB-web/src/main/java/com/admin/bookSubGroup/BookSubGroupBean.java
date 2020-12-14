/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.bookSubGroup;

import com.admin.dto.AdminDto;
import com.admin.dto.BookSubGroupDto;
import com.admin.dto.CollegeDto;
import com.admin.service.BookSubGroupService;
import com.admin.util.Utility;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class BookSubGroupBean implements Serializable{
    @ManagedProperty(value = "#{bookSubGroupDataModel}")
    private BookSubGroupDataModel bookSubGroupDataModel;
   
    @EJB
    private BookSubGroupService bookSubGroupService;
    
    private CollegeDto collegeDto;

    private AdminDto adminDto;

        
    
       @PostConstruct
    public void init() {
        collegeDto = new CollegeDto();
        collegeDto.setId(1L);

        adminDto = new AdminDto();
        adminDto.setId(1L);

        adminDto.setCollegeDto(collegeDto);
    }
    public String returnToPage() {
        return "bookSubGroup.xhtml?faces-redirect=true";
    }

    public String initCreate() {
        bookSubGroupDataModel.setBookSubGroupDto(new BookSubGroupDto());
        bookSubGroupDataModel.setCreateEditPanel(true);
        return returnToPage();
    }

    public String saveUpdate() {
        bookSubGroupDataModel.getBookSubGroupDto().setUpdatedByAdminDto(adminDto);
        bookSubGroupDataModel.getBookSubGroupDto().setCreatedByAdminDto(adminDto);
        
        if (bookSubGroupService.checkIfBookSubGroupNameAlreadyExists(bookSubGroupDataModel.getBookSubGroupDto())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book Name Already Exists", null));
            return returnToPage();
        }
        if (bookSubGroupService.checkIfBookSubGroupDescriptionAlreadyExists(bookSubGroupDataModel.getBookSubGroupDto())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book Description Already Exists", null));
            return returnToPage();
        }
        
        if (bookSubGroupDataModel.getBookSubGroupDto().getId() == null) {
            return save();
        } else {
            return update();
        }
    }

    private String update() {
       
        boolean success = bookSubGroupService.update(bookSubGroupDataModel.getBookSubGroupDto());
        if (success) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully", null));
        }
        return navigateToPage();
    }

    private String save() {
        System.out.println("inside saving");
        boolean response = bookSubGroupService.save(bookSubGroupDataModel.getBookSubGroupDto());
        if (response) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Saved", null));
        }
        return navigateToPage();
    }

    public String navigateToPage() {
        Utility.removeSessionBeanJSFDataModelObject("bookSubGroupDataModel");
        bookSubGroupDataModel = (BookSubGroupDataModel) Utility.getSessionObject("bookSubGroupDataModel");
        bookSubGroupDataModel.setBookSubGroupDtos(bookSubGroupService.findByCollegeId(collegeDto));
        return returnToPage();
    }

    public String initEdit() {
        bookSubGroupDataModel.setCreateEditPanel(true);
        return returnToPage();
    }

    public String delete() {
        bookSubGroupDataModel.getBookSubGroupDto().setDeletedByAdminDto(adminDto);
        
        boolean success = bookSubGroupService.delete(bookSubGroupDataModel.getBookSubGroupDto());
        if (success) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted Successfully", null));
        }
        return navigateToPage();
    }

}



    


