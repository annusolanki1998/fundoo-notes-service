package com.bridgelabz.fundoonotesservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/*
 * Purpose : LabelDTO are used to create and update fundoo label
 * Version : 1.0
 * @author : Annu Kumari
 * */

@Data
public class LabelDTO {
    @NotNull(message = "Label name not be empty")
    private String labelName;
}
