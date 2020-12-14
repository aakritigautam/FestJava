/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payrollSystem.entity.common;

import com.payrollSystem.entity.abstracts.AbstractProfile;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Akriti Gautam
 */
@Getter
@Setter
@Entity
@Table(name = "BOOK_GROUP")
@NamedQueries({
    @NamedQuery(name = "BookGroup.findAll", query = "SELECT bg FROM BookGroup bg"),
    @NamedQuery(name = "BookGroup.findByBookGroupId", query = "SELECT bg FROM BookGroup bg WHERE bg.id = :id")})
public class BookGroup extends AbstractProfile {
    
}
