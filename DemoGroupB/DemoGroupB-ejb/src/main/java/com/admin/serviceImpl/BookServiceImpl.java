/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.serviceImpl;

/**
 *
 * @author Akriti Gautam
 */
import com.admin.constant.StatusConstants;
import com.admin.dao.AdminDao;
import com.admin.dao.BookDao;
import com.admin.dao.StatusDao;
import com.admin.dto.BookDto;
import com.admin.mapper.BookCategoryMapper;
import com.admin.service.BookService;
import com.payrollSystem.entity.common.BookCategory;
import java.util.Date;
import com.admin.dto.CollegeDto;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BookServiceImpl implements BookService {
    @EJB
    private AdminDao adminDao;
    @EJB
    private StatusDao statusDao;
    @EJB
    private BookDao bookDao;
    @Override
    public boolean save(BookDto bookDto) {
        return bookDao.save(convertToBookCategory(bookDto));
    }

    private BookCategory convertToBookCategory(BookDto bookDto) {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setCreatedByAdmin(adminDao.getById(bookDto.getCreatedByAdminDto().getId()));
        bookCategory.setCreatedDate(new Date());
        bookCategory.setName(bookDto.getName());
        bookCategory.setDescription(bookDto.getDescription());
        
        bookCategory.setStatus(statusDao.getByDesc(StatusConstants.CREATE_APPROVE.getName()));
        return bookCategory;
    }

    private void setCreateEditCommonParameters(BookCategory bookCategory, BookDto bookDto) {
        bookCategory.setDescription(bookDto.getDescription());
        bookCategory.setName(bookDto.getName());
       
    }

    @Override
    public boolean delete(BookDto bookDto) {
        BookCategory bookCategory = bookDao.getById(bookDto.getId());
        bookCategory.setDeletedDate(new Date());
        bookCategory.setDeletedReason(bookDto.getDeletedReason());
        bookCategory.setDeletedByAdmin(adminDao.getById(bookDto.getDeletedByAdminDto().getId()));
        bookCategory.setStatus(statusDao.getByDesc(StatusConstants.DELETED_APPROVE.getName()));
        return bookDao.modify(bookCategory);
    }

    @Override
    public boolean update(BookDto bookDto) {
        BookCategory bookCategory = bookDao.getById(bookDto.getId());
        bookCategory.setLastUpdatedDate(new Date());
        bookCategory.setUpdatedByAdmin(adminDao.getById(bookDto.getUpdatedByAdminDto().getId()));
        bookCategory.setStatus(statusDao.getByDesc(StatusConstants.EDIT_APPROVE.getName()));
        setCreateEditCommonParameters(bookCategory, bookDto);
        return bookDao.modify(bookCategory);
    }

    @Override
    public boolean checkIfBookCategoryNameAlreadyExists(BookDto bookDto) {
        return bookDao.checkIfBookCategoryNameAlreadyExists(bookDto);
    }

    @Override
    public boolean checkIfBookCategoryDescriptionAlreadyExists(BookDto bookDto) {
        return bookDao.checkIfBookCategoryDescriptionAlreadyExists(bookDto);
    }

    @Override
    public List<BookDto> findByCollegeId(CollegeDto collegeDto) {
        return BookCategoryMapper.convertToDtos(bookDao.findAllByCollegeId(collegeDto));
    }
    
    @Override
    public List<BookDto> findByCollegeIdForDropDown(CollegeDto collegeDto) {
        return BookCategoryMapper.convertToDtosForDropDown(bookDao.findAllByCollegeId(collegeDto));
    }
}

