package com.bridgelabz.fundoonotesservice.repository;

import com.bridgelabz.fundoonotesservice.model.LabelModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabelRepository extends JpaRepository<LabelModel, Long> {
  //  Optional<LabelModel> findByIdAndUserId(Long labelId, boolean userId);
}
