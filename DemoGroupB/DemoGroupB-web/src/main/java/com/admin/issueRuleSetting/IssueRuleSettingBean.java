/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.issueRuleSetting;

import com.admin.constant.MemberTypeConstant;
import com.admin.constant.SemesterConstant;
import com.admin.dto.AdminDto;
import com.admin.dto.IssueRuleSettingDto;
import com.admin.service.IssueRuleSettingService;
import com.admin.util.Utility;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

/**
 *
 * @author Akriti Gautam
 */
@Getter
@Setter
@ManagedBean
@RequestScoped
public class IssueRuleSettingBean implements Serializable {

    @ManagedProperty(value = "#{issueRuleSettingDataModel}")
    private IssueRuleSettingDataModel issueRuleSettingDataModel;
    @EJB
    private IssueRuleSettingService issueRuleSettingService;
    private AdminDto adminDto;

    @PostConstruct
    public void init(){
        adminDto = new AdminDto();
        adminDto.setId(1L);
    }

    public String save(){
        var response=false;
        issueRuleSettingDataModel.getIssueRuleSettingDto().setCreatedByAdminDto(adminDto);
        if (!issueRuleSettingService.checkIfIssueSettingAlreadyExists(issueRuleSettingDataModel.getIssueRuleSettingDto()))
             response = issueRuleSettingService.createIssueRuleSetting(issueRuleSettingDataModel.getIssueRuleSettingDto());
        else
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,"Issuer with same value already exists",null));

        if (response)
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,"Saved Successfully",null));
        return navigateToPage();
    }
    public String update(){
        var response = false;
        issueRuleSettingDataModel.getIssueRuleSettingDto().setUpdatedByAdminDto(adminDto);
        if (!issueRuleSettingService.checkIfIssueSettingAlreadyExists(issueRuleSettingDataModel.getIssueRuleSettingDto()))
             response = issueRuleSettingService.updateIssueRuleSetting(issueRuleSettingDataModel.getIssueRuleSettingDto());
        else
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,"Issuer with same value already exists",null));

        if (response)
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,"Updated Successfully",null));
        return navigateToPage();
    }
    public String saveUpdate(){
        return issueRuleSettingDataModel.getIssueRuleSettingDto().getId() == null ?save():update();
    }

    public String delete(){
        issueRuleSettingDataModel.getIssueRuleSettingDto().setDeletedByAdminDto(adminDto);
        var response = issueRuleSettingService.deleteIssueRuleSetting(issueRuleSettingDataModel.getIssueRuleSettingDto());
        if (response)
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,"Deleted Successfully",null));
        return navigateToPage();
    }

    public String returnToPage(){
        return "issueRuleSetting.xhtml?faces-redirect=true";
    }

    public String initCreate(){
        issueRuleSettingDataModel.setIssueRuleSettingDto(new IssueRuleSettingDto());
        loadDropdownMenus();
        issueRuleSettingDataModel.setInitEdit(true);
        return returnToPage();
    }
    public String navigateToPage(){
        Utility.removeSessionBeanJSFDataModelObject("issueRuleSettingDataModel");
        issueRuleSettingDataModel = (IssueRuleSettingDataModel) Utility.getSessionObject("issueRuleSettingDataModel");
        issueRuleSettingDataModel.setIssueRuleSettingDtos(issueRuleSettingService.getAllIssueRuleSetting());
        return returnToPage();
    }
    public String initEdit(){
        issueRuleSettingDataModel.setInitEdit(true);
        loadDropdownMenus();
        return returnToPage();
    }
    private void loadDropdownMenus(){
        issueRuleSettingDataModel.setBookDtos(issueRuleSettingService.getBookCategoryForDropdown());
        issueRuleSettingDataModel.setMemberList(MemberTypeConstant.membersList());
        issueRuleSettingDataModel.setSemesterList(SemesterConstant.semesterList());

    }
}

