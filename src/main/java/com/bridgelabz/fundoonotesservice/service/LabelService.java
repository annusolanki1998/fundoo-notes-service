package com.bridgelabz.fundoonotesservice.service;

import com.bridgelabz.fundoonotesservice.dto.LabelDTO;
import com.bridgelabz.fundoonotesservice.exception.FundooNotesNotFoundException;
import com.bridgelabz.fundoonotesservice.model.LabelModel;
import com.bridgelabz.fundoonotesservice.model.NotesModel;
import com.bridgelabz.fundoonotesservice.repository.LabelRepository;
import com.bridgelabz.fundoonotesservice.util.Response;
import com.bridgelabz.fundoonotesservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Purpose : Creating service for fundoo label
 *
 * @author : Annu Kumari
 * @Param : business logic is present here
 * Version : 1.0
 */

@Service
public class LabelService implements ILabelService {
    @Autowired
    LabelRepository labelRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    MailService mailService;
    @Autowired
    RestTemplate restTemplate;

    /**
     * Purpose : Creating method to add fundoo label details
     *
     * @author : Annu Kumari
     * @Param : labelDTO and token
     */

    @Override
    public Response addLabel(LabelDTO labelDTO, String token) {
        boolean isLabelPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isLabelPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            LabelModel labelModel = new LabelModel(labelDTO);
            labelModel.setUserId(usersId);
            labelModel.setRegisterDate(LocalDateTime.now());
            labelRepository.save(labelModel);
            String body = "label is added successfully with labelId " + labelModel.getId();
            String subject = "label registration successfully";
            mailService.send(labelModel.getEmailId(), subject, body);
            return new Response(200, "Sucessfully", labelModel);
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to update existing fundoo label
     *
     * @author : Annu Kumari
     * @Param : labelDTO,labelId and token
     */

    @Override
    public Response updateLabel(LabelDTO labelDTO, String token, Long labelId) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = labelRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Optional<LabelModel> isLabelPresent = labelRepository.findById(labelId);
                if (isLabelPresent.isPresent()) {
                    isLabelPresent.get().setLabelName(labelDTO.getLabelName());
                    isLabelPresent.get().setUpdateDate(LocalDateTime.now());
                    labelRepository.save(isLabelPresent.get());
                    String body = "Label is added successfully with labelId " + isLabelPresent.get().getId();
                    String subject = "Label registration successfully";
                    mailService.send(isLabelPresent.get().getEmailId(), subject, body);
                    return new Response(200, "Sucessfully", isLabelPresent.get());
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to get fundoo label details
     *
     * @author : Annu Kumari
     * @Param : token
     */

    @Override
    public List<LabelModel> getLabels(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = labelRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                List<LabelModel> isLabelPresent = labelRepository.findAll();
                if (isLabelPresent.size() > 0) {
                    return isLabelPresent;
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to delete existing fundoo label details
     *
     * @author : Annu Kumari
     * @Param : labelId and token
     */

    @Override
    public Response deleteLabel(Long labelId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            if (isUserPresent) {
                Long usersId = tokenUtil.decodeToken(token);
                Optional<NotesModel> isUserIdPresent = labelRepository.findByUserId(usersId);
                Optional<LabelModel> isIdPresent = labelRepository.findById(labelId);
                if (isIdPresent.isPresent()) {
                    labelRepository.delete(isIdPresent.get());
                    return new Response(200, "Sucessfully", isIdPresent.get());
                } else {
                    throw new FundooNotesNotFoundException(400, "Label not found");
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to get fundoo label details
     *
     * @author : Annu Kumari
     * @Param : labelId and token
     */

    @Override
    public Response getLabel(Long labelId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/note/validate/" + token, Boolean.class);
        if (isUserPresent) {
            if (isUserPresent) {
                Long usersId = tokenUtil.decodeToken(token);
                Optional<NotesModel> isUserIdPresent = labelRepository.findByUserId(usersId);
                Optional<LabelModel> isIdPresent = labelRepository.findById(labelId);
                if (isIdPresent.isPresent()) {
                    return new Response(200, "Sucessfully", isIdPresent.get());
                } else {
                    throw new FundooNotesNotFoundException(400, "Label not found");
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }
}

