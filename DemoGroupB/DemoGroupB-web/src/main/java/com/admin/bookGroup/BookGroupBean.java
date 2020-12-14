
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.bookGroup;

import com.admin.dto.AdminDto;
import com.admin.dto.BookGroupDto;
import com.admin.dto.CollegeDto;
import com.admin.service.BookGroupService;
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
public class BookGroupBean implements Serializable {
    @ManagedProperty(value = "#{bookGroupDataModel}")
    private BookGroupDataModel bookGroupDataModel;
   
    @EJB
    private BookGroupService bookGroupService;
    
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
        return "bookGroup.xhtml?faces-redirect=true";
    }

    public String initCreate() {
        bookGroupDataModel.setBookGroupDto(new BookGroupDto());
        bookGroupDataModel.setCreateEditPanel(true);
        return returnToPage();
    }

    public String saveUpdate() {
        bookGroupDataModel.getBookGroupDto().setUpdatedByAdminDto(adminDto);
        bookGroupDataModel.getBookGroupDto().setCreatedByAdminDto(adminDto);
        
        if (bookGroupService.checkIfBookGroupNameAlreadyExists(bookGroupDataModel.getBookGroupDto())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book Name Already Exists", null));
            return returnToPage();
        }
        if (bookGroupService.checkIfBookGroupDescriptionAlreadyExists(bookGroupDataModel.getBookGroupDto())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book Description Already Exists", null));
            return returnToPage();
        }

        if (bookGroupDataModel.getBookGroupDto().getId() == null) {
            return save();
        } else {
            return update();
        }
    }

    private String update() {
        boolean success = bookGroupService.update(bookGroupDataModel.getBookGroupDto());
        if (success) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully", null));
        }
        return navigateToPage();
    }

    private String save() {
        boolean response = bookGroupService.save(bookGroupDataModel.getBookGroupDto());
        if (response) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Saved", null));
        }
        return navigateToPage();
    }

    public String navigateToPage() {
        Utility.removeSessionBeanJSFDataModelObject("bookGroupDataModel");
        bookGroupDataModel = (BookGroupDataModel) Utility.getSessionObject("bookGroupDataModel");
        bookGroupDataModel.setBookGroupDtos(bookGroupService.findByCollegeId(collegeDto));
        return returnToPage();
    }

    public String initEdit() {
        bookGroupDataModel.setCreateEditPanel(true);
        return returnToPage();
    }

    public String delete() {
        bookGroupDataModel.getBookGroupDto().setDeletedByAdminDto(adminDto);
        
        boolean success = bookGroupService.delete(bookGroupDataModel.getBookGroupDto());
        if (success) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted Successfully", null));
        }
        return navigateToPage();
    }

}



    

