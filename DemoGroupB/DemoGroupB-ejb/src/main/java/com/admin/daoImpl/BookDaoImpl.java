/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.daoImpl;


import com.admin.constant.StatusConstants;
import com.admin.dao.BookDao;
import com.admin.dto.BookDto;




import com.payrollSystem.entity.common.BookCategory;

import javax.ejb.Stateless;
import javax.persistence.Query;


/**
 *
 * @author Akriti Gautam
 */
@Stateless
public class BookDaoImpl extends StatusableDaoImpl<BookCategory> implements BookDao  {
    
    public BookDaoImpl(){
        super(BookCategory.class);
 
        
        
       
}
    @Override
    public boolean checkIfBookCategoryNameAlreadyExists(BookDto bookDto) {
        StringBuilder stringBuilder = new StringBuilder("SELECT count(bc.id) FROM BookCategory bc WHERE bc.createdByAdmin.college.id=:collegeId AND bc.name=:bookName  AND bc.status.statusDesc NOT IN (:deletedStatusList)");
        if (bookDto.getId() != null) {
            stringBuilder.append("and bc.id<>:bookId");
        }
        Query query = getEntityManager().createQuery(stringBuilder.toString());
        query.setParameter("collegeId", bookDto.getCreatedByAdminDto().getCollegeDto().getId());
        query.setParameter("bookName", bookDto.getName());
        
        query.setParameter("deletedStatusList", StatusConstants.deleteStatusList());
        if (bookDto.getId() != null) {
            query.setParameter("bookId", bookDto.getId());
        }
        return (Long) query.getSingleResult() > 0;
    }
@Override
    public boolean checkIfBookCategoryDescriptionAlreadyExists(BookDto bookDto) {
        StringBuilder stringBuilder = new StringBuilder("SELECT count(bc.id) FROM BookCategory bc WHERE bc.createdByAdmin.college.id=:collegeId AND bc.description=:bookDescription AND bc.status.statusDesc NOT IN (:deletedStatusList)");
        if (bookDto.getId() != null) {
            stringBuilder.append("and bc.id<>:bookId");
        }
        Query query = getEntityManager().createQuery(stringBuilder.toString());
        query.setParameter("collegeId", bookDto.getCreatedByAdminDto().getCollegeDto().getId());
        query.setParameter("deletedStatusList", StatusConstants.deleteStatusList());
        query.setParameter("bookDescription", bookDto.getDescription());
        if (bookDto.getId() != null) {
            query.setParameter("bookId", bookDto.getId());
        }
        return (Long) query.getSingleResult() > 0;
    }
}


    
   


    
    

