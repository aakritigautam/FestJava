/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.mapper;

import com.admin.dto.IssueRuleSettingDto;
import com.payrollSystem.entity.common.IssueRuleSetting;

/**
 *
 * @author Akriti Gautam
 */
public class IssueRuleSettingMapper extends AbstractStatusHelperMapper{
    public static IssueRuleSettingDto convertToIssueRuleSettingDto(IssueRuleSetting issueRuleSetting){
        IssueRuleSettingDto issueRuleSettingDto = new IssueRuleSettingDto();
        convertCommonStatusParameters(issueRuleSettingDto,issueRuleSetting);
        setCommonParameters(issueRuleSettingDto,issueRuleSetting);
        issueRuleSettingDto.setBookCategoryDto(BookCategoryMapper.convertToDto(issueRuleSetting.getBookCategory()));
        return issueRuleSettingDto;
    }
    private static void setCommonParameters(IssueRuleSettingDto issueRuleSettingDto, IssueRuleSetting issueRuleSetting) {

        issueRuleSettingDto.setMemberType(issueRuleSetting.getMemberType());
        issueRuleSettingDto.setSemester(issueRuleSetting.getSemester());
        issueRuleSettingDto.setNoOfRenews(issueRuleSetting.getNoOfRenews());
        issueRuleSettingDto.setNoOfBookAllowed(issueRuleSetting.getNoOfBookAllowed());
        issueRuleSettingDto.setNoOfRenewalDays(issueRuleSetting.getNoOfRenewalDays());
        issueRuleSettingDto.setFinePerExtraDay(issueRuleSetting.getFinePerExtraDay());
    }


}

