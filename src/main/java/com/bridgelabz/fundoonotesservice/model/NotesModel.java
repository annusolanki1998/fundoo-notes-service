package com.bridgelabz.fundoonotesservice.model;

import com.bridgelabz.fundoonotesservice.dto.NotesDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notes")
@Data
public class NotesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private long userId;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private boolean trash;
    private boolean archieve;
    private boolean pin;
    private long labelId;
    private String emailId;
    private String color;
    private LocalDateTime reminderTime;

    @ElementCollection(targetClass = String.class)
    private List<String> collaborator;

    @JsonIgnore()
    @ManyToMany(cascade = CascadeType.ALL)
    private List<LabelModel> labellist;


    public NotesModel(NotesDTO notesDTO) {
        this.title = notesDTO.getTitle();
        this.description = notesDTO.getDescription();
        this.userId = notesDTO.getUserId();
        this.emailId = notesDTO.getEmailId();
        this.color = notesDTO.getColor();
    }

    public NotesModel() {

    }
}
