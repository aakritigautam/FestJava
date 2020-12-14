/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.daoImpl;

import com.admin.constant.StatusConstants;
import com.admin.dao.BookSubGroupDao;
import com.admin.dto.BookSubGroupDto;
import com.payrollSystem.entity.common.BookSubGroup;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Akriti Gautam
 */
@Stateless
public class BookSubGroupDaoImpl extends StatusableDaoImpl<BookSubGroup> implements BookSubGroupDao {
    public BookSubGroupDaoImpl(){
        super(BookSubGroup.class);
 
        
        
       
}
    @Override
    public boolean checkIfBookSubGroupNameAlreadyExists(BookSubGroupDto bookSubGroupDto) {
        StringBuilder stringBuilder = new StringBuilder("SELECT count(bsg.id) FROM BookGroup bsg WHERE bsg.createdByAdmin.college.id=:collegeId AND bsg.name=:bookSubGroupName  AND bsg.status.statusDesc NOT IN (:deletedStatusList)");
        if (bookSubGroupDto.getId() != null) {
            stringBuilder.append("and bsg.id<>:bookSubGroupId");
        }
        Query query = getEntityManager().createQuery(stringBuilder.toString());
        query.setParameter("collegeId", bookSubGroupDto.getCreatedByAdminDto().getCollegeDto().getId());
        query.setParameter("bookSubGroupName", bookSubGroupDto.getName());
        
        query.setParameter("deletedStatusList", StatusConstants.deleteStatusList());
        if (bookSubGroupDto.getId() != null) {
            query.setParameter("bookSubGroupId", bookSubGroupDto.getId());
        }
        return (Long) query.getSingleResult() > 0;
    }
@Override
    public boolean checkIfBookSubGroupDescriptionAlreadyExists(BookSubGroupDto bookSubGroupDto) {
        StringBuilder stringBuilder = new StringBuilder("SELECT count(bsg.id) FROM BookSubGroup bsg WHERE bsg.createdByAdmin.college.id=:collegeId AND bsg.description=:bookSubGroupDescription AND bsg.status.statusDesc NOT IN (:deletedStatusList)");
        if (bookSubGroupDto.getId() != null) {
            stringBuilder.append("and bsg.id<>:bookSubGroupId");
        }
        Query query = getEntityManager().createQuery(stringBuilder.toString());
        query.setParameter("collegeId", bookSubGroupDto.getCreatedByAdminDto().getCollegeDto().getId());
        query.setParameter("deletedStatusList", StatusConstants.deleteStatusList());
        query.setParameter("bookSubGroupDescription", bookSubGroupDto.getDescription());
        if (bookSubGroupDto.getId() != null) {
            query.setParameter("bookSubGroupId", bookSubGroupDto.getId());
        }
        return (Long) query.getSingleResult() > 0;
    }
}

    

  

