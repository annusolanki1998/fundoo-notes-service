package com.bridgelabz.fundoonotesservice.repository;

import com.bridgelabz.fundoonotesservice.model.NotesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Purpose : Creating repository for fundoo notes
 * @author : Annu Kumari
 * @Param : To save data in database
 * Version : 1.0
 */

public interface NotesRepository extends JpaRepository<NotesModel, Long> {
    Optional<NotesModel> findByUserId(Long usersId);
}
