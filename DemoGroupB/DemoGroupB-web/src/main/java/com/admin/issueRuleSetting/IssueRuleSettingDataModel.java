/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.issueRuleSetting;

import com.admin.dto.BookDto;
import com.admin.dto.IssueRuleSettingDto;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
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

public class IssueRuleSettingDataModel implements Serializable {
    private IssueRuleSettingDto issueRuleSettingDto;
    private List<IssueRuleSettingDto> issueRuleSettingDtos;
    private List<BookDto> bookDtos;
    private List<String> memberList;
    private List<Integer> semesterList;
    private boolean initEdit;

    public IssueRuleSettingDto getIssueRuleSettingDto(){
        return Optional.ofNullable(issueRuleSettingDto).orElse(new IssueRuleSettingDto());
    }
}

    

