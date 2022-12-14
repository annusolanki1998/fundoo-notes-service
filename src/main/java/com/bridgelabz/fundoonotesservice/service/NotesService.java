package com.bridgelabz.fundoonotesservice.service;


import com.bridgelabz.fundoonotesservice.dto.NotesDTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Autowired
    LabelRepository labelRepository;


    /**
     * Purpose : Creating method to add fundoo notes
     *
     * @author : Annu Kumari
     * @Param : notesDTO and token
     */
    @Override
    public Response addNote(NotesDTO notesDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            NotesModel notesModel = new NotesModel(notesDTO);
            notesModel.setUserId(usersId);
            notesModel.setRegisterDate(LocalDateTime.now());
            notesRepository.save(notesModel);
            String body = "Fundoo notes is added successfully with noteId " + notesModel.getId();
            String subject = "Fundoo notes registration successfully";
            mailService.send(notesModel.getEmailId(), subject, body);
            return new Response(200, "Sucessfully", notesModel);
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to update existing fundoo notes
     *
     * @author : Annu Kumari
     * @Param : notesDTO,noteId and token
     */


    @Override
    public Response updateNote(NotesDTO notesDTO, Long noteId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Optional<NotesModel> isNotesPresent = notesRepository.findById(noteId);
                if (isNotesPresent.isPresent()) {
                    isNotesPresent.get().setTitle(notesDTO.getTitle());
                    isNotesPresent.get().setDescription(notesDTO.getDescription());
                    isNotesPresent.get().setEmailId(notesDTO.getEmailId());
                    isNotesPresent.get().setUpdateDate(LocalDateTime.now());
                    notesRepository.save(isNotesPresent.get());
                    String body = "Fundoo notes is added successfully with noteId " + isNotesPresent.get().getId();
                    String subject = "Fundoo notes registration successfully";
                    mailService.send(isNotesPresent.get().getEmailId(), subject, body);
                    return new Response(200, "Sucessfully", isNotesPresent.get());
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to get fundoo notes
     *
     * @author : Annu Kumari
     * @Param : token
     */

    @Override
    public List<NotesModel> getNote(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                List<NotesModel> isNotesPresent = notesRepository.findAll();
                if (isNotesPresent.size() > 0) {
                    return isNotesPresent;
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to delete existing fundoo notes
     *
     * @author : Annu Kumari
     * @Param : noteId and token
     */

    @Override
    public Response deleteNote(Long noteId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Optional<NotesModel> isIdPresent = notesRepository.findById(noteId);
                if (isIdPresent.isPresent()) {
                    notesRepository.delete(isIdPresent.get());
                    return new Response(200, "Sucessfully", isIdPresent.get());
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to get fundoo notes
     *
     * @author : Annu Kumari
     * @Param : noteId and token
     */

    @Override
    public Response getNotes(Long noteId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/note/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Optional<NotesModel> isIdPresent = notesRepository.findById(noteId);
                if (isIdPresent.isPresent()) {
                    return new Response(200, "Sucessfully", isIdPresent.get());
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to permanent delete existing fundoo notes
     *
     * @author : Annu Kumari
     * @Param : noteId and token
     */

    @Override
    public Response deletePermanent(Long noteId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Optional<NotesModel> isIdPresent = notesRepository.findById(noteId);
                if (isIdPresent.isPresent()) {
                    notesRepository.delete(isIdPresent.get());
                    return new Response(200, "Sucessfully", isIdPresent.get());
                }
            }
        }

        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }


    /**
     * Purpose : Creating method to restore fundoo notes
     *
     * @author : Annu Kumari
     * @Param : noteId and token
     */

    @Override
    public Response restoreNote(Long noteId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Optional<NotesModel> isIdPresent = notesRepository.findById(noteId);
                if (isIdPresent.isPresent()) {
                    isIdPresent.get().setTrash(true);
                    isIdPresent.get().setArchieve(false);
                    notesRepository.save(isIdPresent.get());
                    return new Response(200, "Sucessfully", isIdPresent.get());
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }


    /**
     * Purpose : Creating method to pin note in fundoo notes
     *
     * @author : Annu Kumari
     * @Param : noteId and token
     */

    @Override
    public Response pinNote(Long noteId, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Optional<NotesModel> isIdPresent = notesRepository.findById(noteId);
                if (isIdPresent.isPresent()) {
                    isIdPresent.get().setPin(true);
                    isIdPresent.get().setArchieve(false);
                    notesRepository.save(isIdPresent.get());
                    return new Response(200, "Sucessfully", isIdPresent.get());
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }


    /**
     * Purpose : Creating method to add colour in fundoo notes
     *
     * @author : Annu Kumari
     * @Param : noteId and token
     */

    @Override
    public Response addColour(Long noteId, String colour, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Optional<NotesModel> isIdPresent = notesRepository.findById(noteId);
                if (isIdPresent.isPresent()) {
                    isIdPresent.get().setColor(colour);
                    notesRepository.save(isIdPresent.get());
                    return new Response(200, "Sucessfully", isIdPresent.get());
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }


    /**
     * Purpose : Creating method to add collaborator in fundoo notes
     *
     * @author : Annu Kumari
     * @Param : noteId and token
     */

//    @Override
//    public Response addCollaborator(Long noteId, String emailId, List<String> collaborators) {
//        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validateEmail/" + emailId, Boolean.class);
//        if (isUserPresent) {
//            Optional<NotesModel> isNotePresent = notesRepository.findById(noteId);
//            if (isNotePresent.isPresent()) {
//                List<String> collabList = new ArrayList<>();
//                collaborators.stream().forEach(collab -> {
//                    boolean isEmailPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validateEmail/" + emailId, Boolean.class);
//                    if (isEmailPresent) {
//                        collabList.add(collab);
//                    } else {
//                        throw new FundooNotesNotFoundException(400, "Email is not present");
//                    }
//                });
//                isNotePresent.get().setCollaborators(collabList);
//                notesRepository.save((isNotePresent.get()));
//                return new Response(200, "Sucessfully", isNotePresent.get());
//            }
//        }
//        throw new FundooNotesNotFoundException(400, "Email id is not present");
//    }

    /**
     * Purpose : Creating method to many to many mapping in between notes and label
     *
     * @author : Annu Kumari
     * @Param : noteId and token
     */

    @Override
    public Response noteAsLabel(String token, Long noteId, List<Long> labelId) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Optional<NotesModel> isNotePresent = notesRepository.findById(noteId);
                if (isNotePresent.isPresent()) {
                    List<LabelModel> isLabelList = new ArrayList<>();
                    labelId.stream().forEach(labelsId -> {
                        Optional<LabelModel> isIdPresent = labelRepository.findById(labelsId);
                        if (isIdPresent.isPresent()) {
                            isLabelList.add(isIdPresent.get());
                        }
                    });

                    isNotePresent.get().setLabellist(isLabelList);
                    notesRepository.save(isNotePresent.get());
                    return new Response(200, "Sucessfully", isNotePresent.get());
                }
            }
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to add collaborator in fundoo notes
     *
     * @author : Annu Kumari
     * @Param : token,emailId,noteId,collaborator and collaboratorUserId
     */

    @Override
    public Response addCollaborator(String token, String emailId, Long noteId, String collaborator, Long collaboratorUserId) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Object isEmailPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validateEmail/" + emailId, Object.class);
                if (!isEmailPresent.equals(null)) {
                    Optional<NotesModel> isNotePresent = notesRepository.findById(noteId);
                    if (isNotePresent.isPresent()) {
                        List<String> collablist = new ArrayList<>();
                        Object isEmailIdPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validateEmail/" + collaborator,
                                Object.class);
                        if (!isEmailIdPresent.equals(null)) {
                            collablist.add(collaborator);
                        } else {
                            throw new FundooNotesNotFoundException(400, "Email id is not found");
                        }

                        isNotePresent.get().setCollaborator(collaborator);
                        notesRepository.save(isNotePresent.get());
                        List<String> noteList = new ArrayList<>();
                        noteList.add(isNotePresent.get().getEmailId());

                        NotesModel notesModel = new NotesModel();
                        notesModel.setTitle(isNotePresent.get().getTitle());
                        notesModel.setUserId(collaboratorUserId);
                        notesModel.setDescription(isNotePresent.get().getDescription());
                        notesRepository.save(notesModel);
                        return new Response(200, "Sucessfully", isNotePresent.get());
                    } else {
                        throw new FundooNotesNotFoundException(400, "User not found");
                    }
                }
            }
            throw new FundooNotesNotFoundException(400, "Email is not valid");
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose : Creating method to set remainder in fundoo notes
     *
     * @author : Annu Kumari
     * @Param : token,noteId and remainderTime
     */

    @Override
    public Response setRemainder(String token, Long noteId, String remainderTime) {
        boolean isUserPresent = restTemplate.getForObject("http://USER-CLIENT:9091/user/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Long usersId = tokenUtil.decodeToken(token);
            Optional<NotesModel> isUserIdPresent = notesRepository.findByUserId(usersId);
            if (isUserIdPresent.isPresent()) {
                Optional<NotesModel> isNotesPresent = notesRepository.findById(noteId);
                if (isNotesPresent.isPresent()) {
                    LocalDate today = LocalDate.now();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:ss");
                    LocalDate remainder = LocalDate.parse(remainderTime, dateTimeFormatter);
                    if (remainder.isBefore(today)) {
                        throw new FundooNotesNotFoundException(400, "Invalid remainder time");
                    }
                    isNotesPresent.get().setReminderTime(remainderTime);
                    notesRepository.save(isNotesPresent.get());
                }
                throw new FundooNotesNotFoundException(400, "Notes not found");
            }
            throw new FundooNotesNotFoundException(400, "User id not present");
        }
        throw new FundooNotesNotFoundException(400, "Token is wrong");
    }
}










