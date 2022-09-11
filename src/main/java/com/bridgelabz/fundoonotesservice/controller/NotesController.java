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

    @PostMapping("/addNote")
    public ResponseEntity<Response> addNote(@Valid @RequestBody NotesDTO notesDTO,
                                            @RequestHeader String token) {
        Response response = notesService.addNote(notesDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateNote/{id}")
    public ResponseEntity<Response> updateNote(@Valid @RequestBody NotesDTO notesDTO,
                                               @PathVariable Long id,
                                               @RequestHeader String token) {
        Response response = notesService.updateNote(notesDTO, id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getNote")
    public ResponseEntity<List> getNote(@RequestHeader String token) {
        List<NotesModel> responseUtil = notesService.getNote(token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    @DeleteMapping("/deleteNote/{id}")
    public ResponseEntity<Response> deleteNote(@RequestHeader String token,
                                               @RequestParam Long id) {
        Response response = notesService.deleteNote(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getNotes/{id}")
    public ResponseEntity<Response> getNotes(@RequestParam Long id,
                                             @RequestHeader String token) {
        Response response = notesService.getNotes(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deletePermanent")
    public ResponseEntity<Response> deletePermanent(@RequestParam Long id,
                                                    @RequestHeader String token) {
        Response response = notesService.deletePermanent(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/restoreNote")
    public ResponseEntity<Response> restoreNote(@RequestParam Long id,
                                                @RequestHeader String token) {
        Response response = notesService.restoreNote(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/pinNote")
    public ResponseEntity<Response> pinNote(@RequestParam Long id,
                                            @RequestHeader String token) {
        Response response = notesService.pinNote(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addColour")
    public ResponseEntity<Response> addColour(@RequestParam Long id,
                                              @RequestParam String colour,
                                              @RequestHeader String token){
        Response response =notesService.addColour(id,colour,token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
