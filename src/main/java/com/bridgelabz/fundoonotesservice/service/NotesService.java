package com.bridgelabz.fundoonotesservice.service;


import com.bridgelabz.fundoonotesservice.dto.NotesDTO;
import com.bridgelabz.fundoonotesservice.exception.FundooNotesNotFoundException;
import com.bridgelabz.fundoonotesservice.model.NotesModel;
import com.bridgelabz.fundoonotesservice.repository.NotesRepository;
import com.bridgelabz.fundoonotesservice.util.Response;
import com.bridgelabz.fundoonotesservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotesService implements INotesService {

    @Autowired
    NotesRepository notesRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    MailService mailService;
    @Autowired
    RestTemplate restTemplate;


    @Override
    public Response addNote(NotesDTO notesDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            NotesModel notesModel = new NotesModel(notesDTO);
            notesModel.setRegisterDate(LocalDateTime.now());
            notesRepository.save(notesModel);
            String body = "Fundoo notes is added successfully with noteId " + notesModel.getId();
            String subject = "Fundoo notes registration successfully";
            mailService.send(notesModel.getEmailId(), subject, body);
            return new Response(200, "Sucessfully", notesModel);
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response updateNote(NotesDTO notesDTO, Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<NotesModel> isNotesPresent = notesRepository.findById(id);
            if (isNotesPresent.isPresent()) {
                isNotesPresent.get().setTitle(notesDTO.getTitle());
                isNotesPresent.get().setDescription(notesDTO.getDescription());
                isNotesPresent.get().setUserId(notesDTO.getUserId());
                isNotesPresent.get().setEmailId(notesDTO.getEmailId());
                isNotesPresent.get().setColor(notesDTO.getColor());
                isNotesPresent.get().setUpdateDate(LocalDateTime.now());
                notesRepository.save(isNotesPresent.get());
                String body = "Fundoo notes is added successfully with noteId " + isNotesPresent.get().getId();
                String subject = "Fundoo notes registration successfully";
                mailService.send(isNotesPresent.get().getEmailId(), subject, body);
                return new Response(200, "Sucessfully", isNotesPresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Fundoo notes not found");
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    @Override
    public List<NotesModel> getNote(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            List<NotesModel> isNotesPresent = notesRepository.findAll();
            if (isNotesPresent.size() > 0) {
                return isNotesPresent;
            } else {
                throw new FundooNotesNotFoundException(400, "Fundoo notes not present");
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response deleteNote(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<NotesModel> isIdPresent = notesRepository.findById(id);
            if (isIdPresent.isPresent()) {
                notesRepository.delete(isIdPresent.get());
                return new Response(200, "Sucessfully", isIdPresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Fundoo notes not found");
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");

    }

    @Override
    public Response getNotes(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http:/localhost:9091/note/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<NotesModel> isIdPresent = notesRepository.findById(id);
            if (isIdPresent.isPresent()) {
                return new Response(200, "Sucessfully", isIdPresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Fundoo note not found");
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");

    }

    @Override
    public Response deletePermanent(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<NotesModel> isIdPresent = notesRepository.findById(id);
            if (isIdPresent.isPresent()) {
                notesRepository.delete(isIdPresent.get());
                return new Response(200, "Sucessfully", isIdPresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Fundoo notes found with this notes id");
            }
        } else {
            throw new FundooNotesNotFoundException(400, "Token is wrong");
        }
    }

    @Override
    public Response restoreNote(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<NotesModel> isIdPresent = notesRepository.findById(id);
            if (isIdPresent.isPresent()) {
                isIdPresent.get().setTrash(true);
                isIdPresent.get().setArchieve(false);
                notesRepository.save(isIdPresent.get());
                return new Response(200, "Sucessfully", isIdPresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Fundoo notes not found with this id");
            }
        } else {
            throw new FundooNotesNotFoundException(400, "Token is wrong");
        }

    }

    @Override
    public Response pinNote(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<NotesModel> isIdPresent = notesRepository.findById(id);
            if (isIdPresent.isPresent()) {
                isIdPresent.get().setPin(true);
                isIdPresent.get().setArchieve(false);
                notesRepository.save(isIdPresent.get());
                return new Response(200, "Sucessfully", isIdPresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Fundoo notes not found with this id");
            }
        } else {
            throw new FundooNotesNotFoundException(400, "Token is wrong");
        }
    }

    @Override
    public Response addColour(Long id, String colour, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<NotesModel> isIdPresent = notesRepository.findById(id);
            if (isIdPresent.isPresent()) {
                isIdPresent.get().setColor(colour);
                notesRepository.save(isIdPresent.get());
                return new Response(200, "Sucessfully", isIdPresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Fundoo notes not found with this id");
            }
        } else {
            throw new FundooNotesNotFoundException(400, "Token is wrong");
        }
    }

    @Override
    public Response addCollaborator(Long noteId, String emailId, List<String> collaborator) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:9091/user/validateEmail/" + emailId, Boolean.class);
        if (isUserPresent) {
            Optional<NotesModel> isNotePresent = notesRepository.findById(noteId);
            if (isNotePresent.isPresent()) {
                isNotePresent.get().setEmailId(emailId);
                isNotePresent.get().setCollaborator(collaborator);
                notesRepository.save(isNotePresent.get());
                return new Response(200, "Sucessfull", isNotePresent.get());
            } else {
                throw new FundooNotesNotFoundException(400, "Note id is not found");
            }
        } else {
            throw new FundooNotesNotFoundException(400, "Email id is not found");
        }
    }


}







