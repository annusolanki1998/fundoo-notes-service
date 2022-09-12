package com.bridgelabz.fundoonotesservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LabelDTO {
    @NotNull(message = "Label name not be empty")
    private String labelName;
}
