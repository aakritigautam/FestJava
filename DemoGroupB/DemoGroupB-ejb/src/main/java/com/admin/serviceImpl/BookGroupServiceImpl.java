/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.serviceImpl;

import com.admin.constant.StatusConstants;
import com.admin.dao.AdminDao;
import com.admin.dao.BookGroupDao;
import com.admin.dao.StatusDao;

import com.admin.dto.BookGroupDto;
import com.admin.dto.CollegeDto;
import com.admin.mapper.BookGroupMapper;
import com.admin.service.BookGroupService;
import com.payrollSystem.entity.common.BookGroup;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Akriti Gautam
 */
@Stateless
public class BookGroupServiceImpl  implements BookGroupService{
    @EJB
    private AdminDao adminDao;
    @EJB
    private StatusDao statusDao;
    @EJB
    private BookGroupDao bookGroupDao;
    @Override
    public boolean save(BookGroupDto bookGroupDto) {
        return bookGroupDao.save(convertToBookGroup(bookGroupDto));
    }

    private BookGroup convertToBookGroup(BookGroupDto bookGroupDto) {
        BookGroup bookGroup = new BookGroup();
        bookGroup.setCreatedByAdmin(adminDao.getById(bookGroupDto.getCreatedByAdminDto().getId()));
        bookGroup.setCreatedDate(new Date());
        bookGroup.setName(bookGroupDto.getName());
       bookGroup.setDescription(bookGroupDto.getDescription());
        
        bookGroup.setStatus(statusDao.getByDesc(StatusConstants.CREATE_APPROVE.getName()));
        return bookGroup;
    }

    private void setCreateEditCommonParameters(BookGroup bookGroup, BookGroupDto bookGroupDto) {
        bookGroup.setDescription(bookGroupDto.getDescription());
        bookGroup.setName(bookGroupDto.getName());
       
    }

    @Override
    public boolean delete(BookGroupDto bookGroupDto) {
        BookGroup bookGroup = bookGroupDao.getById(bookGroupDto.getId());
        bookGroup.setDeletedDate(new Date());
        bookGroup.setDeletedReason(bookGroupDto.getDeletedReason());
        bookGroup.setDeletedByAdmin(adminDao.getById(bookGroupDto.getDeletedByAdminDto().getId()));
        bookGroup.setStatus(statusDao.getByDesc(StatusConstants.DELETED_APPROVE.getName()));
        return bookGroupDao.modify(bookGroup);
    }

    @Override
    public boolean update(BookGroupDto bookGroupDto) {
        BookGroup bookGroup = bookGroupDao.getById(bookGroupDto.getId());
        bookGroup.setLastUpdatedDate(new Date());
        bookGroup.setUpdatedByAdmin(adminDao.getById(bookGroupDto.getUpdatedByAdminDto().getId()));
        bookGroup.setStatus(statusDao.getByDesc(StatusConstants.EDIT_APPROVE.getName()));
        setCreateEditCommonParameters(bookGroup, bookGroupDto);
        return bookGroupDao.modify(bookGroup);
    }

    @Override
    public boolean checkIfBookGroupNameAlreadyExists(BookGroupDto bookGroupDto) {
        return bookGroupDao.checkIfBookGroupNameAlreadyExists(bookGroupDto);
    }

    @Override
    public boolean checkIfBookGroupDescriptionAlreadyExists(BookGroupDto bookGroupDto) {
        return bookGroupDao.checkIfBookGroupDescriptionAlreadyExists(bookGroupDto);
    }

    @Override
    public List<BookGroupDto> findByCollegeId(CollegeDto collegeDto) {
        return BookGroupMapper.convertToDtos(bookGroupDao.findAllByCollegeId(collegeDto));
    }
    
    @Override
    public List<BookGroupDto> findByCollegeIdForDropDown(CollegeDto collegeDto) {
        return BookGroupMapper.convertToDtosForDropDown(bookGroupDao.findAllByCollegeId(collegeDto));
    }
}


   
