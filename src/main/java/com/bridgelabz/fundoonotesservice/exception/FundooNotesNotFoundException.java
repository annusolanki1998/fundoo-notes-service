package com.bridgelabz.fundoonotesservice.exception;

/*
 * Purpose : AdminNotFoundException is used to handle the exceptions
 * Version : 1.0
 * @author : Annu kumari
 * */

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class FundooNotesNotFoundException extends RuntimeException {
    private int statusCode;
    private String statusMessage;

    public FundooNotesNotFoundException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
