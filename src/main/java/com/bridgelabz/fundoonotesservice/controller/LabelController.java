package com.bridgelabz.fundoonotesservice.controller;

import com.bridgelabz.fundoonotesservice.dto.LabelDTO;
import com.bridgelabz.fundoonotesservice.model.LabelModel;
import com.bridgelabz.fundoonotesservice.service.ILabelService;
import com.bridgelabz.fundoonotesservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    ILabelService labelService;

    @PostMapping("/addLabel")
    public ResponseEntity<Response> addLabel(@Valid @RequestBody LabelDTO labelDTO,
                                             @RequestHeader String token) {
        Response response = labelService.addLabel(labelDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/updateLabel/{id}")
    public ResponseEntity<Response> updateLabel(@Valid @RequestBody LabelDTO labelDTO,
                                                @RequestHeader String token,
                                                @RequestParam Long labelId) {
        Response response = labelService.updateLabel(labelDTO, token, labelId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getLabels")
    public ResponseEntity<List> getLabels(@RequestHeader String token) {
        List<LabelModel> responseUtil = labelService.getLabels(token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    @DeleteMapping("/deleteLabel/{id}")
    public ResponseEntity<Response> deleteLabel(@RequestHeader String token,
                                                @RequestParam Long labelId) {
        Response response = labelService.deleteLabel(labelId, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getLabel/{id}")
    public ResponseEntity<Response> getLabel(@RequestParam Long labelId,
                                             @RequestHeader String token) {
        Response response = labelService.getLabel(labelId, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/noteAsLabel")
    public ResponseEntity<Response> noteAsLabel(@RequestHeader String token,
                                                @RequestParam Long labelId,
                                                @RequestParam List<Long> noteId){
        Response response = labelService.noteAsLabel(token,labelId,noteId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
