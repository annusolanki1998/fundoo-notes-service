package com.bridgelabz.fundoonotesservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class NotesDTO {

    @NotNull(message = "Title not be empty")
    private String title;

    @NotNull(message = "Description not be empty")
    private String description;

    @NotNull(message = "User id not be empty")
    private long userId;

    @NotNull(message = "Email id should not be empty")
    @Pattern(regexp = "[a-z][A-Z a-z 0-9]+[@][a-z]+[.][a-z]{2,}", message = "Invalid email id")
    private String emailId;

    private String color;
}
