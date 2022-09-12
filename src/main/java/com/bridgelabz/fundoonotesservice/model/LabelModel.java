package com.bridgelabz.fundoonotesservice.model;

import com.bridgelabz.fundoonotesservice.dto.LabelDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "label")
public class LabelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String labelName;
    private Long userId;
    private Long noteId;
    private String emailId;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    @ManyToMany(mappedBy = "labellist")
    private List<NotesModel> notes;


    public LabelModel(LabelDTO labelDTO) {
        this.labelName = labelDTO.getLabelName();

    }

    public LabelModel() {

    }
}
