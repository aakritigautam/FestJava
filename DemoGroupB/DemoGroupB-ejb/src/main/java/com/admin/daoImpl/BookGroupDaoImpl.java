/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.daoImpl;

import com.admin.constant.StatusConstants;
import com.admin.dao.BookGroupDao;

import com.admin.dto.BookGroupDto;
import com.payrollSystem.entity.common.BookGroup;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Akriti Gautam
 */
@Stateless
public class BookGroupDaoImpl extends StatusableDaoImpl<BookGroup> implements BookGroupDao {
    public BookGroupDaoImpl(){
        super(BookGroup.class);
 
        
        
       
}
    @Override
    public boolean checkIfBookGroupNameAlreadyExists(BookGroupDto bookGroupDto) {
        StringBuilder stringBuilder = new StringBuilder("SELECT count(bg.id) FROM BookGroup bg WHERE bg.createdByAdmin.college.id=:collegeId AND bg.name=:bookGroupName  AND bg.status.statusDesc NOT IN (:deletedStatusList)");
        if (bookGroupDto.getId() != null) {
            stringBuilder.append("and bg.id<>:bookGroupId");
        }
        Query query = getEntityManager().createQuery(stringBuilder.toString());
        query.setParameter("collegeId", bookGroupDto.getCreatedByAdminDto().getCollegeDto().getId());
        query.setParameter("bookGroupName", bookGroupDto.getName());
        
        query.setParameter("deletedStatusList", StatusConstants.deleteStatusList());
        if (bookGroupDto.getId() != null) {
            query.setParameter("bookGroupId", bookGroupDto.getId());
        }
        return (Long) query.getSingleResult() > 0;
    }
@Override
    public boolean checkIfBookGroupDescriptionAlreadyExists(BookGroupDto bookGroupDto) {
        StringBuilder stringBuilder = new StringBuilder("SELECT count(bg.id) FROM BookGroup bg WHERE bg.createdByAdmin.college.id=:collegeId AND bg.description=:bookGroupDescription AND bg.status.statusDesc NOT IN (:deletedStatusList)");
        if (bookGroupDto.getId() != null) {
            stringBuilder.append("and bg.id<>:bookGroupId");
        }
        Query query = getEntityManager().createQuery(stringBuilder.toString());
        query.setParameter("collegeId", bookGroupDto.getCreatedByAdminDto().getCollegeDto().getId());
        query.setParameter("deletedStatusList", StatusConstants.deleteStatusList());
        query.setParameter("bookGroupDescription", bookGroupDto.getDescription());
        if (bookGroupDto.getId() != null) {
            query.setParameter("bookGroupId", bookGroupDto.getId());
        }
        return (Long) query.getSingleResult() > 0;
    }
}

    

