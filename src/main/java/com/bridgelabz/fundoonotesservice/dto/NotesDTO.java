package com.bridgelabz.fundoonotesservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/*
 * Purpose : NotesDTO are used to create and update fundoo notes
 * Version : 1.0
 * @author : Annu Kumari
 * */

@Data
public class NotesDTO {

    @NotNull(message = "Title not be empty")
    private String title;

    @NotNull(message = "Description not be empty")
    private String description;

    @NotNull(message = "Email id should not be empty")
    @Pattern(regexp = "[a-z][A-Z a-z 0-9]+[@][a-z]+[.][a-z]{2,}", message = "Invalid email id")
    private String emailId;

}
