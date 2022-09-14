package com.bridgelabz.fundoonotesservice.service;

import com.bridgelabz.fundoonotesservice.dto.LabelDTO;
import com.bridgelabz.fundoonotesservice.exception.FundooNotesNotFoundException;
import com.bridgelabz.fundoonotesservice.model.LabelModel;
import com.bridgelabz.fundoonotesservice.model.NotesModel;
import com.bridgelabz.fundoonotesservice.repository.LabelRepository;
import com.bridgelabz.fundoonotesservice.repository.NotesRepository;
import com.bridgelabz.fundoonotesservice.util.Response;
import com.bridgelabz.fundoonotesservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    NotesRepository notesRepository;

    @Override
    public Response addLabel(LabelDTO labelDTO, String token) {
        boolean isLabelPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isLabelPresent) {
            LabelModel labelModel = new LabelModel(labelDTO);
            labelModel.setRegisterDate(LocalDateTime.now());
            labelRepository.save(labelModel);
            String body = "label is added successfully with labelId " + labelModel.getId();
            String subject = "label registration successfully";
            mailService.send(labelModel.getEmailId(), subject, body);
            return new Response(200, "Sucessfully", labelModel);
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response updateLabel(LabelDTO labelDTO, String token, Long labelId) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<LabelModel> isLabelPresent = labelRepository.findById(labelId);
            if (isLabelPresent.isPresent()) {
                isLabelPresent.get().setLabelName(labelDTO.getLabelName());
                isLabelPresent.get().setUpdateDate(LocalDateTime.now());
                labelRepository.save(isLabelPresent.get());
                String body = "Label is added successfully with labelId " + isLabelPresent.get().getId();
                String subject = "Label registration successfully";
                mailService.send(isLabelPresent.get().getEmailId(), subject, body);
                return new Response(200, "Sucessfully", isLabelPresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Label not found");
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    @Override
    public List<LabelModel> getLabels(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            List<LabelModel> isLabelPresent = labelRepository.findAll();
            if (isLabelPresent.size() > 0) {
                return isLabelPresent;
            } else {
                throw new FundooNotesNotFoundException(400, "Labels not found");
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response deleteLabel(Long labelId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<LabelModel> isIdPresent = labelRepository.findById(labelId);
            if (isIdPresent.isPresent()) {
                labelRepository.delete(isIdPresent.get());
                return new Response(200, "Sucessfully", isIdPresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Label not found");
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response getLabel(Long labelId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http:/localhost:9091/note/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<LabelModel> isIdPresent = labelRepository.findById(labelId);
            if (isIdPresent.isPresent()) {
                return new Response(200, "Sucessfully", isIdPresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Label not found");
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response noteAsLabel(String token, Long labelId, List<Long> noteId) {
        boolean isUserPresent = restTemplate.getForObject("http:/localhost:9091/note/validate/" + token, Boolean.class);
        if (isUserPresent) {
            List<NotesModel> notesModels = new ArrayList<>();
            noteId.stream().forEach(note -> {
                Optional<NotesModel> isNotePresent = notesRepository.findById(note);
                if (isNotePresent.isPresent()){
                    notesModels.add(isNotePresent.get());
                }
            });
            Optional<LabelModel> isLabelpresent = labelRepository.findById(labelId);
            if(isLabelpresent.isPresent()){
                isLabelpresent.get().setNotes(notesModels);
                labelRepository.save(isLabelpresent.get());
                return new Response(200,"Sucessfully",isLabelpresent.get());
            }else{
                throw new FundooNotesNotFoundException(400,"label id is not found");
            }
        }
        throw new FundooNotesNotFoundException(400,"Token is wrong");
    }

}

