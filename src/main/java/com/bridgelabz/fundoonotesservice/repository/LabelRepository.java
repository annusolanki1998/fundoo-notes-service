package com.bridgelabz.fundoonotesservice.repository;

import com.bridgelabz.fundoonotesservice.model.LabelModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Purpose : Creating repository for fundoo label
 * @author : Annu Kumari
 * @Param : To save data in database
 * Version : 1.0
 */


public interface LabelRepository extends JpaRepository<LabelModel, Long> {

}
