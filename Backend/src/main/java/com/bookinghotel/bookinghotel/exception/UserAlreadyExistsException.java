package com.bookinghotel.bookinghotel.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String messgae)
    {
        super(messgae);
    }
}
