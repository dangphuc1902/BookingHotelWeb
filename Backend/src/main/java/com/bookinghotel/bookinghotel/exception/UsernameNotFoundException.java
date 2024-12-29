package com.bookinghotel.bookinghotel.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UsernameNotFoundException extends AuthenticationException {
    /**
     * Thrown if an {@link UserDetailsService} implementation cannot locate a {@link User} by
     * its username.
     *
     * @author Ben Alex
     */
    public UsernameNotFoundException(String message){
        super(message);
    }
    /**
     * Constructs a {@code UsernameNotFoundException} with the specified message and root
     * cause.
     * @param msg the detail message.
     * @param cause root cause
     */
    public UsernameNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
