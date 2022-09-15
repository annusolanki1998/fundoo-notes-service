package com.bridgelabz.fundoonotesservice.service;

import com.bridgelabz.fundoonotesservice.dto.LabelDTO;
import com.bridgelabz.fundoonotesservice.model.LabelModel;
import com.bridgelabz.fundoonotesservice.util.Response;

import java.util.List;

/**
 * Purpose : Creating Interface for fundoo label service
 * @author : Annu Kumari
 * @Param : All service methods
 * Version : 1.0
 */

public interface ILabelService {
    Response addLabel(LabelDTO labelDTO, String token);

    Response updateLabel(LabelDTO labelDTO, String token, Long labelId);

    List<LabelModel> getLabels(String token);

    Response deleteLabel(Long labelId, String token);

    Response getLabel(Long labelId, String token);


}
