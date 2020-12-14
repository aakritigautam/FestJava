/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.serviceImpl;

import com.admin.constant.StatusConstants;
import com.admin.dao.AdminDao;
import com.admin.dao.BookDao;
import com.admin.dao.IssueRuleSettingDao;
import com.admin.dao.StatusDao;
import com.admin.dto.BookDto;
import com.admin.dto.IssueRuleSettingDto;
import com.admin.mapper.BookCategoryMapper;
import com.admin.mapper.IssueRuleSettingMapper;
import com.admin.service.IssueRuleSettingService;
import com.payrollSystem.entity.common.IssueRuleSetting;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Akriti Gautam
 */
@Stateless
public class IssueRuleSettingServiceImpl implements IssueRuleSettingService {
    @EJB
    private IssueRuleSettingDao issueRuleSettingDao;
    @EJB
    private BookDao bookCategoryDao;
    @EJB
    private StatusDao statusDao;
    @EJB
    private AdminDao adminDao;
    @Override
    public boolean createIssueRuleSetting(IssueRuleSettingDto issueRuleSettingDto) {
        issueRuleSettingDto.setCreatedDate(new Date());
        return issueRuleSettingDao.save(convertToIssueRuleSettingEntity(issueRuleSettingDto));
    }

    @Override
    public boolean updateIssueRuleSetting(IssueRuleSettingDto issueRuleSettingDto) {
        IssueRuleSetting issueRuleSetting = issueRuleSettingDao.getById(issueRuleSettingDto.getId());
        issueRuleSetting.setLastUpdatedDate(new Date());
        issueRuleSetting.setUpdatedByAdmin(adminDao.getById(issueRuleSettingDto.getUpdatedByAdminDto().getId()));
        issueRuleSetting.setStatus(statusDao.getByDesc(StatusConstants.EDIT_APPROVE.getName()));
        setCommonParameters(issueRuleSettingDto,issueRuleSetting);
        return issueRuleSettingDao.modify(issueRuleSetting);
    }

    @Override
    public boolean deleteIssueRuleSetting(IssueRuleSettingDto issueRuleSettingDto) {
        IssueRuleSetting issueRuleSetting = issueRuleSettingDao.getById(issueRuleSettingDto.getId());
        issueRuleSetting.setStatus(statusDao.getByDesc(StatusConstants.DELETED_APPROVE.getName()));
        issueRuleSetting.setDeletedDate(new Date());
        issueRuleSetting.setDeletedByAdmin(adminDao.getById(issueRuleSettingDto.getDeletedByAdminDto().getId()));
        issueRuleSetting.setDeletedReason(issueRuleSettingDto.getDeletedReason());
        return issueRuleSettingDao.modify(issueRuleSetting);
    }

    @Override
    public boolean checkIfMemberAlreadyExists(IssueRuleSettingDto issueRuleSettingDto) {
        return issueRuleSettingDao.checkIfMemberTypeAlreadyExists(issueRuleSettingDto);
    }

    @Override
    public boolean checkIfSemesterAlreadyExists(IssueRuleSettingDto issueRuleSettingDto) {
        return issueRuleSettingDao.checkIfSemesterAlreadyExists(issueRuleSettingDto);
    }

    @Override
    public boolean checkIfBookCategoryAlreadyExists(IssueRuleSettingDto issueRuleSettingDto) {
        return issueRuleSettingDao.checkIfBookCategoryAlreadyExists(issueRuleSettingDto);
    }

    @Override
    public List<IssueRuleSettingDto> getAllIssueRuleSetting() {
        return issueRuleSettingDao.findAll().stream()
                                  .map(IssueRuleSettingMapper::convertToIssueRuleSettingDto)
                                  .collect(Collectors.toList());
    }
    private IssueRuleSetting convertToIssueRuleSettingEntity(IssueRuleSettingDto issueRuleSettingDto){
        IssueRuleSetting issueRuleSetting = new IssueRuleSetting();
        setCommonParameters(issueRuleSettingDto,issueRuleSetting);
        issueRuleSetting.setCreatedDate(issueRuleSettingDto.getCreatedDate());
        issueRuleSetting.setStatus(statusDao.getByDesc(StatusConstants.CREATE_APPROVE.getName()));
        issueRuleSetting.setCreatedByAdmin(adminDao.getById(issueRuleSettingDto.getCreatedByAdminDto().getId()));
        return issueRuleSetting;
    }

    private void setCommonParameters(IssueRuleSettingDto issueRuleSettingDto, IssueRuleSetting issueRuleSetting) {
        issueRuleSetting.setMemberType(issueRuleSettingDto.getMemberType());
        issueRuleSetting.setSemester(issueRuleSettingDto.getSemester());
        issueRuleSetting.setNoOfRenews(issueRuleSettingDto.getNoOfRenews());
        issueRuleSetting.setBookCategory(bookCategoryDao.getById(issueRuleSettingDto.getBookCategoryDto().getId()));
        issueRuleSetting.setNoOfBookAllowed(issueRuleSettingDto.getNoOfBookAllowed());
        issueRuleSetting.setNoOfRenewalDays(issueRuleSettingDto.getNoOfRenewalDays());
        issueRuleSetting.setFinePerExtraDay(issueRuleSettingDto.getFinePerExtraDay());

    }

    @Override
    public boolean checkIfIssueSettingAlreadyExists(IssueRuleSettingDto issueRuleSettingDto){
        return checkIfBookCategoryAlreadyExists(issueRuleSettingDto) &&
                checkIfMemberAlreadyExists(issueRuleSettingDto) &&
                checkIfSemesterAlreadyExists(issueRuleSettingDto);
    }

    @Override
    public List<BookDto> getBookCategoryForDropdown() {
        return bookCategoryDao.findAll().stream().map(BookCategoryMapper::convertToDto).collect(Collectors.toList());
    }
}

