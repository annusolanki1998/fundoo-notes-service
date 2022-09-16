package com.bridgelabz.fundoonotesservice.model;

import com.bridgelabz.fundoonotesservice.dto.NotesDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/*
 * Purpose : NotesModel are used to transfer the data into database
 * Version : 1.0
 * @author : Annu Kumari
 * */

@Entity
@Table(name = "fundoonote")
@Data
@AllArgsConstructor
public class NotesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String title;
    private Long userId;
    private String description;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private boolean trash;
    private boolean archieve;
    private boolean pin;
    private String emailId;
    private String color;
    private String reminderTime;
    private Long collaboratorUserId;

    //@ElementCollection(targetClass = String.class)
    private String collaborator;

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
