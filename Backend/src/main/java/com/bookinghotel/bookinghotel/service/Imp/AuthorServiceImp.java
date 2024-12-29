package com.bookinghotel.bookinghotel.service.Imp;

import com.bookinghotel.bookinghotel.entity.UserEntity;
import com.bookinghotel.bookinghotel.payload.request.LoginRequest;
import com.bookinghotel.bookinghotel.payload.request.UserRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthorServiceImp {
    String checkLogin(LoginRequest loginRequest, HttpServletResponse response);
    UserEntity registerUser(UserRequest user);
}
