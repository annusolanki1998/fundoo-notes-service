package com.bridgelabz.fundoonotesservice.model;

import com.bridgelabz.fundoonotesservice.dto.NotesDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notes")
@Data
public class NotesModel {
    @Column
    @ElementCollection(targetClass = String.class)
    List<String> collaborator;
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
