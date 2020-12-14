/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.serviceImpl;

import com.admin.constant.StatusConstants;
import com.admin.dao.AdminDao;
import com.admin.dao.BookGroupDao;
import com.admin.dao.BookSubGroupDao;
import com.admin.dao.StatusDao;
import com.admin.dto.BookGroupDto;
import com.admin.dto.BookSubGroupDto;
import com.admin.dto.CollegeDto;
import com.admin.mapper.BookGroupMapper;
import com.admin.mapper.BookSubGroupMapper;
import com.admin.service.BookSubGroupService;
import com.payrollSystem.entity.common.BookSubGroup;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Akriti Gautam
 */
@Stateless
public class BookSubGroupServiceImpl implements BookSubGroupService{
     @EJB
    private AdminDao adminDao;
    @EJB
    private StatusDao statusDao;
    
    @EJB
    private BookGroupDao bookGroupDao;
    
    @EJB
    private BookSubGroupDao bookSubGroupDao;
    @Override
    public boolean save(BookSubGroupDto bookSubGroupDto) {
        return bookSubGroupDao.save(convertToBookSubGroup(bookSubGroupDto));
    }

    private BookSubGroup convertToBookSubGroup(BookSubGroupDto bookSubGroupDto) {
        BookSubGroup bookSubGroup = new BookSubGroup();
        bookSubGroup.setCreatedByAdmin(adminDao.getById(bookSubGroupDto.getCreatedByAdminDto().getId()));
        bookSubGroup.setCreatedDate(new Date());
        bookSubGroup.setName(bookSubGroupDto.getName());
        bookSubGroup.setBookGroup(bookGroupDao.getById(bookSubGroupDto.getBookGroupDto().getId()));
        bookSubGroup.setDescription(bookSubGroupDto.getDescription());
        bookSubGroup.setStatus(statusDao.getByDesc(StatusConstants.CREATE_APPROVE.getName()));
        return bookSubGroup;
    }

    private void setCreateEditCommonParameters(BookSubGroup bookSubGroup, BookSubGroupDto bookSubGroupDto) {
        bookSubGroup.setDescription(bookSubGroupDto.getDescription());
        bookSubGroup.setName(bookSubGroupDto.getName());
        bookSubGroup.setBookGroup(bookGroupDao.getById(bookSubGroupDto.getBookGroupDto().getId()));      
              
    }

    @Override
    public boolean delete(BookSubGroupDto bookSubGroupDto) {
        BookSubGroup bookSubGroup = bookSubGroupDao.getById(bookSubGroupDto.getId());
        bookSubGroup.setDeletedDate(new Date());
        bookSubGroup.setDeletedReason(bookSubGroupDto.getDeletedReason());
        bookSubGroup.setDeletedByAdmin(adminDao.getById(bookSubGroupDto.getDeletedByAdminDto().getId()));
        bookSubGroup.setStatus(statusDao.getByDesc(StatusConstants.DELETED_APPROVE.getName()));
        return bookSubGroupDao.modify(bookSubGroup);
    }

    @Override
    public boolean update(BookSubGroupDto bookSubGroupDto) {
        BookSubGroup bookSubGroup = bookSubGroupDao.getById(bookSubGroupDto.getId());
        bookSubGroup.setLastUpdatedDate(new Date());
        System.out.println("inside the update----------------------------------------------");
        bookSubGroup.setUpdatedByAdmin(adminDao.getById(bookSubGroupDto.getUpdatedByAdminDto().getId()));
        bookSubGroup.setStatus(statusDao.getByDesc(StatusConstants.EDIT_APPROVE.getName()));
        setCreateEditCommonParameters(bookSubGroup, bookSubGroupDto);
        return bookSubGroupDao.modify(bookSubGroup);
    }
    
    @Override
    public boolean checkIfBookSubGroupNameAlreadyExists(BookSubGroupDto bookSubGroupDto) {
        return bookSubGroupDao.checkIfBookSubGroupNameAlreadyExists(bookSubGroupDto);
    }

    @Override
    public boolean checkIfBookSubGroupDescriptionAlreadyExists(BookSubGroupDto bookSubGroupDto) {
        return bookSubGroupDao.checkIfBookSubGroupDescriptionAlreadyExists(bookSubGroupDto);
    }


    @Override
    public List<BookSubGroupDto> findByCollegeId(CollegeDto collegeDto) {
        return BookSubGroupMapper.convertToDtos(bookSubGroupDao.findAllByCollegeId(collegeDto));
    }
    
    @Override
    public List<BookSubGroupDto> findByCollegeIdForDropDown(CollegeDto collegeDto) {
        return BookSubGroupMapper.convertToDtosForDropDown(bookSubGroupDao.findAllByCollegeId(collegeDto));
    }
    
    @Override
    public List<BookGroupDto> fetchBookGroupForDropDown()
    {
        return BookGroupMapper.convertToDtosForDropDown(bookGroupDao.findAll());


    }
}
