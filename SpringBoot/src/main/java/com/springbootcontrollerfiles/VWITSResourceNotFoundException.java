package com.springbootcontrollerfiles;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)//404
/*
 * @ResponseStatus marks a method or exception class with the 
 * status code and reason message that should be returned.
 */
public class VWITSResourceNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

 /*public String toString()
 {
	return "HTPP Resource Not Found!";
	 
 }*/
    public VWITSResourceNotFoundException(String message){
        super(message);
    }
 
 }