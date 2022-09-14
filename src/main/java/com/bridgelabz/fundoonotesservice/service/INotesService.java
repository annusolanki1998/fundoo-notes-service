package com.bridgelabz.fundoonotesservice.service;

import com.bridgelabz.fundoonotesservice.dto.NotesDTO;
import com.bridgelabz.fundoonotesservice.model.NotesModel;
import com.bridgelabz.fundoonotesservice.util.Response;

import java.util.List;

public interface INotesService {
    Response addNote(NotesDTO notesDTO, String token);

    Response updateNote(NotesDTO notesDTO, Long id, String token);

    List<NotesModel> getNote(String token);

    Response deleteNote(Long id, String token);

    Response getNotes(Long id, String token);

    Response deletePermanent(Long id, String token);

    Response restoreNote(Long id, String token);

    Response pinNote(Long id, String token);

    Response addColour(Long id, String colour, String token);

    Response addCollaborator(Long noteId, String emailId, List<String> collaborator);
}
