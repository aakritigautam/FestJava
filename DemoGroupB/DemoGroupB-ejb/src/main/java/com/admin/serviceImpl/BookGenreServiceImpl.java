/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.serviceImpl;

import com.admin.constant.StatusConstants;
import com.admin.dao.AdminDao;
import com.admin.dao.BookGenreDao;
import com.admin.dao.StatusDao;

import com.admin.dto.BookGenreDto;
import com.admin.dto.CollegeDto;
import com.admin.mapper.BookGenreMapper;
import com.admin.service.BookGenreService;
import com.payrollSystem.entity.common.BookGenre;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author Akriti Gautam
 */
@Stateless
public class BookGenreServiceImpl implements BookGenreService{
    @EJB
    private AdminDao adminDao;
    @EJB
    private StatusDao statusDao;
    @EJB
    private BookGenreDao bookGenreDao;
    @Override
    public boolean save(BookGenreDto bookGenreDto) {
        return bookGenreDao.save(convertToBookGenre(bookGenreDto));
    }

    private BookGenre convertToBookGenre(BookGenreDto bookGenreDto) {
        BookGenre  bookGenre  = new BookGenre();
        bookGenre.setCreatedByAdmin(adminDao.getById(bookGenreDto.getCreatedByAdminDto().getId()));
        bookGenre.setCreatedDate(new Date());
        bookGenre.setName(bookGenreDto.getName());
       bookGenre.setDescription(bookGenreDto.getDescription());
        
        bookGenre.setStatus(statusDao.getByDesc(StatusConstants.CREATE_APPROVE.getName()));
        return bookGenre;
    }

    private void setCreateEditCommonParameters(BookGenre bookGenre, BookGenreDto bookGenreDto) {
        bookGenre.setDescription(bookGenreDto.getDescription());
        bookGenre.setName(bookGenreDto.getName());
       
    }

    @Override
    public boolean delete(BookGenreDto bookGenreDto) {
        BookGenre bookGenre = bookGenreDao.getById(bookGenreDto.getId());
        bookGenre.setDeletedDate(new Date());
        bookGenre.setDeletedReason(bookGenreDto.getDeletedReason());
        bookGenre.setDeletedByAdmin(adminDao.getById(bookGenreDto.getDeletedByAdminDto().getId()));
        bookGenre.setStatus(statusDao.getByDesc(StatusConstants.DELETED_APPROVE.getName()));
        return bookGenreDao.modify(bookGenre);
    }

    @Override
    public boolean update(BookGenreDto bookGenreDto) {
        BookGenre bookGenre = bookGenreDao.getById(bookGenreDto.getId());
        bookGenre.setLastUpdatedDate(new Date());
        bookGenre.setUpdatedByAdmin(adminDao.getById(bookGenreDto.getUpdatedByAdminDto().getId()));
        bookGenre.setStatus(statusDao.getByDesc(StatusConstants.EDIT_APPROVE.getName()));
        setCreateEditCommonParameters(bookGenre, bookGenreDto);
        return bookGenreDao.modify(bookGenre);
    }

    @Override
    public boolean checkIfBookGenreNameAlreadyExists(BookGenreDto bookGenreDto) {
        return bookGenreDao.checkIfBookGenreNameAlreadyExists(bookGenreDto);
    }

    @Override
    public boolean checkIfBookGenreDescriptionAlreadyExists(BookGenreDto bookGenreDto) {
        return bookGenreDao.checkIfBookGenreDescriptionAlreadyExists(bookGenreDto);
    }

    @Override
    public List<BookGenreDto> findByCollegeId(CollegeDto collegeDto) {
        return BookGenreMapper.convertToDtos(bookGenreDao.findAllByCollegeId(collegeDto));
    }
    
    @Override
    public List<BookGenreDto> findByCollegeIdForDropDown(CollegeDto collegeDto) {
        return BookGenreMapper.convertToDtosForDropDown(bookGenreDao.findAllByCollegeId(collegeDto));
    }
}


   
 
    

