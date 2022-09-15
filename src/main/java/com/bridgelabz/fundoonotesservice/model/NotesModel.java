package com.bridgelabz.fundoonotesservice.model;

import com.bridgelabz.fundoonotesservice.dto.NotesDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/*
 * Purpose : NotesModel are used to transfer the data into database
 * Version : 1.0
 * @author : Annu Kumari
 * */

@Entity
@Table(name = "notes")
@Data
public class NotesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String title;
    private String description;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private boolean trash;
    private boolean archieve;
    private boolean pin;
    private String emailId;
    private String color;
    private LocalDateTime reminderTime;

    @ElementCollection(targetClass = String.class)
    private List<String> collaborators;

    @JsonIgnore()
    @ManyToMany(cascade = CascadeType.ALL)
    private List<LabelModel> labellist;


    public NotesModel(NotesDTO notesDTO) {
        this.title = notesDTO.getTitle();
        this.description = notesDTO.getDescription();
        this.emailId = notesDTO.getEmailId();

    }

    public NotesModel() {

    }
}
