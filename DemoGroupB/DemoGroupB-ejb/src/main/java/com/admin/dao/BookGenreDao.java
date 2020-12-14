/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import com.admin.dto.BookGenreDto;
import com.payrollSystem.entity.common.BookGenre;
import javax.ejb.Local;

/**
 *
 * @author Akriti Gautam
 */
@Local
public interface BookGenreDao extends StatusableDao<BookGenre> {
    boolean checkIfBookGenreNameAlreadyExists(BookGenreDto bookGenreDto);

    boolean checkIfBookGenreDescriptionAlreadyExists(BookGenreDto bookGenreDto);
}
    
