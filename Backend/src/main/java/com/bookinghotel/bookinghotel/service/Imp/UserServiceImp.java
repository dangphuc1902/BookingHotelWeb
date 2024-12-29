package com.bookinghotel.bookinghotel.service.Imp;

import com.bookinghotel.bookinghotel.dto.UserDTO;
import com.bookinghotel.bookinghotel.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserServiceImp {
    List<UserDTO> getUser();
    UserEntity getUserByEmail(String email);
    void deleteUser(String email, HttpServletRequest request);
}
