/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dto;

import com.admin.dto.abstracts.AbstractStatusHelperDto;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Akriti Gautam
 */
@Getter
@Setter
public class IssueRuleSettingDto extends AbstractStatusHelperDto {
    
    private String memberType;
    
    private int semester;
    
    private BookDto bookCategoryDto;
    
    private int noOfBookAllowed;
    
    private int noOfRenews;
    
    private int noOfRenewalDays;

    private double finePerExtraDay;

    public IssueRuleSettingDto (){
        this.bookCategoryDto = Optional.ofNullable(bookCategoryDto).orElse(new BookDto());
    }

}
