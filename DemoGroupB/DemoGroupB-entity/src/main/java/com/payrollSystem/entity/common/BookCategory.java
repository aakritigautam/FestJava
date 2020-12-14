/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payrollSystem.entity.common;
import com.payrollSystem.entity.abstracts.AbstractProfile;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.*;


import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "BOOK_CATEGORY")


/**
 *
 * @author Akriti Gautam
 * 
 */
@NamedQueries({
    @NamedQuery(name = "BookCategory.findAll", query = "SELECT bc FROM BookCategory bc"),
    @NamedQuery(name = "BookCategory.findByBookCategoryId", query = "SELECT bc FROM BookCategory bc WHERE bc.id = :id")})
public class BookCategory extends AbstractProfile {
   /* 
    @Column(name = "Book_Name",nullable=false)
    private String bookName;

    @Column(name = "Description")
    private String description;*/

    
}
