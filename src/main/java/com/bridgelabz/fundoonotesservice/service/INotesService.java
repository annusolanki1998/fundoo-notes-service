package com.bridgelabz.fundoonotesservice.service;

import com.bridgelabz.fundoonotesservice.dto.NotesDTO;
import com.bridgelabz.fundoonotesservice.model.NotesModel;
import com.bridgelabz.fundoonotesservice.util.Response;

import java.util.List;

/**
 * Purpose : Creating Interface for fundoo notes service
 * @author : Annu Kumari
 * @Param : All service methods
 * Version : 1.0
 */

public interface INotesService {
    Response addNote(NotesDTO notesDTO, String token);

    Response updateNote(NotesDTO notesDTO, Long noteId, String token);

    List<NotesModel> getNote(String token);

    Response deleteNote(Long noteId, String token);

    Response getNotes(Long noteId, String token);

    Response deletePermanent(Long noteId, String token);

    Response restoreNote(Long noteId, String token);

    Response pinNote(Long noteId, String token);

    Response addColour(Long noteId, String colour, String token);

    Response addCollaborator(Long noteId, String emailId, List<String> collaborators);

    Response noteAsLabel(String token, Long noteId, List<Long> labelId);
}
