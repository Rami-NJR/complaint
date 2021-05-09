package com.pwc.complaint.complaint.customException;

public class UserNameExistException extends RuntimeException {

    public UserNameExistException(String message) {
        super(message);
    }
}
