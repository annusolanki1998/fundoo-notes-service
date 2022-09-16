package com.bridgelabz.fundoonotesservice.controller;

import com.bridgelabz.fundoonotesservice.dto.NotesDTO;
import com.bridgelabz.fundoonotesservice.model.NotesModel;
import com.bridgelabz.fundoonotesservice.service.INotesService;
import com.bridgelabz.fundoonotesservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {
    @Autowired
    INotesService notesService;

    /*
     * Purpose : Create fundoo notes
     * @author : Annu Kumari
     * @Param : notesDTO and token
     * */

    @PostMapping("/addNote")
    public ResponseEntity<Response> addNote(@Valid @RequestBody NotesDTO notesDTO,
                                            @RequestHeader String token) {
        Response response = notesService.addNote(notesDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Purpose : Update existing fundoo notes details by using id
     * @author : Annu Kumari
     * @Param : noteId,notesDTO and token
     * */

    @PutMapping("/updateNote/{noteId}")
    public ResponseEntity<Response> updateNote(@Valid @RequestBody NotesDTO notesDTO,
                                               @PathVariable Long noteId,
                                               @RequestHeader String token) {
        Response response = notesService.updateNote(notesDTO, noteId, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Purpose : Retrieve all fundoo notes details
     * @author : Annu Kumari
     * @Param :  token
     * */

    @GetMapping("/getNote")
    public ResponseEntity<List> getNote(@RequestHeader String token) {
        List<NotesModel> responseUtil = notesService.getNote(token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    /*
     * Purpose : Delete existing fundoo notes details by using id
     * @author : Annu Kumari
     * @Param : noteId and token
     * */

    @DeleteMapping("/deleteNote/{noteId}")
    public ResponseEntity<Response> deleteNote(@RequestHeader String token,
                                               @RequestParam Long noteId) {
        Response response = notesService.deleteNote(noteId, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Purpose : Retrieve existing fundoo notes details by using id
     * @author : Annu Kumari
     * @Param : noteId and token
     * */

    @GetMapping("/getNotes/{noteId}")
    public ResponseEntity<Response> getNotes(@RequestParam Long noteId,
                                             @RequestHeader String token) {
        Response response = notesService.getNotes(noteId, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Purpose : Delete permanent fundoo notes details by using id
     * @author : Annu Kumari
     * @Param : noteId and token
     * */

    @DeleteMapping("/deletePermanent")
    public ResponseEntity<Response> deletePermanent(@RequestParam Long noteId,
                                                    @RequestHeader String token) {
        Response response = notesService.deletePermanent(noteId, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Purpose : Restore fundoo notes
     * @author : Annu Kumari
     * @Param : noteId and token
     * */


    @PutMapping("/restoreNote")
    public ResponseEntity<Response> restoreNote(@RequestParam Long noteId,
                                                @RequestHeader String token) {
        Response response = notesService.restoreNote(noteId, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Purpose : Update fundoo notes pin
     * @author : Annu Kumari
     * @Param : noteId and token
     * */

    @PutMapping("/pinNote")
    public ResponseEntity<Response> pinNote(@RequestParam Long noteId,
                                            @RequestHeader String token) {
        Response response = notesService.pinNote(noteId, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Purpose : Add fundoo notes colour
     * @author : Annu Kumari
     * @Param : noteId,colour and token
     * */

    @PostMapping("/addColour")
    public ResponseEntity<Response> addColour(@RequestParam Long noteId,
                                              @RequestParam String colour,
                                              @RequestHeader String token) {
        Response response = notesService.addColour(noteId, colour, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Purpose : Add fundoo notes collaborate
     * @author : Annu Kumari
     * @Param : noteId,emailId and collaborators
     * */

//    @PostMapping("/addCollaborator")
//    public ResponseEntity<Response> addCollaborator(@RequestParam Long noteId,
//                                                    @RequestParam String emailId,
//                                                    @RequestParam List<String> collaborators) {
//        Response response = notesService.addCollaborator(noteId, emailId, collaborators);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }



    /*
     * Purpose : Create note as label
     * @author : Annu Kumari
     * @Param : noteId,token and labelId
     * */

    @PostMapping("/noteAsLabel")
    public ResponseEntity<Response> noteAsLabel(@RequestHeader String token,
                                             @RequestParam Long noteId,
                                             @RequestParam List<Long> labelId) {
        Response response = notesService.noteAsLabel(token, noteId, labelId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
     * Purpose : Add collaborator
     * @author : Annu Kumari
     * @Param : token,emailId,noteId collaborator and collaboratorUserId
     * */

    @PostMapping("/addCollaborator")
    public ResponseEntity<Response> addCollaborator(@RequestHeader String token,
                                                    @RequestParam String emailId,
                                                    @RequestParam Long noteId,
                                                    @RequestParam String collaborator,
                                                    @RequestParam Long collaboratorUserId){
        Response response = notesService.addCollaborator(token,emailId,noteId,collaborator,collaboratorUserId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /*
     * Purpose : Add remainder time
     * @author : Annu Kumari
     * @Param : token, noteId and remainderTime
     * */

    @PostMapping("/setRemainder/{noteId}")
    public ResponseEntity<Response> setRemainder(@RequestHeader String token,
                                                 @PathVariable Long noteId,
                                                 @RequestParam String remainderTime){
        Response response = notesService.setRemainder(token,noteId,remainderTime);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
