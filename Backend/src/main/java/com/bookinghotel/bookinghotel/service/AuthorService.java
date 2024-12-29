package com.bookinghotel.bookinghotel.service;

import com.bookinghotel.bookinghotel.entity.RolesEntity;
import com.bookinghotel.bookinghotel.entity.UserEntity;
import com.bookinghotel.bookinghotel.exception.UserAlreadyExistsException;
import com.bookinghotel.bookinghotel.payload.request.LoginRequest;
import com.bookinghotel.bookinghotel.payload.request.UserRequest;
import com.bookinghotel.bookinghotel.payload.response.RoleResponse;
import com.bookinghotel.bookinghotel.repository.RoleRepository;
import com.bookinghotel.bookinghotel.repository.UserRepository;
import com.bookinghotel.bookinghotel.service.Imp.AuthorServiceImp;
import com.bookinghotel.bookinghotel.utils.JwtUltils;
import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements AuthorServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private Gson gson;

    @Autowired
    private JwtUltils jwtUltils;
    @Override
    public String checkLogin(LoginRequest loginRequest, HttpServletResponse response) {
        String token = "";
        UserEntity userEntity = userRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);
        if (userEntity != null){
            if (passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassWord())){
                // Tạo Token từ key đã sinh ra và lưu trữ trước đó
                RoleResponse roleResponse = new RoleResponse();
                roleResponse.setName(userEntity.getRole().getName());
                String roles = gson.toJson(roleResponse);
                token = jwtUltils.createToken(roles);
                Cookie saveUserName = new Cookie("userName", loginRequest.getEmail());
                // Đặt HttpOnly để ngăn truy cập từ JavaScript
                saveUserName.setHttpOnly(true);
                // Đảm bảo cookie chỉ được gửi qua HTTPS
                saveUserName.setSecure(true);
                // Cookie được gửi trong mọi request từ domain
                saveUserName.setPath("/");
                // Đặt thời gian tồn tại cho cookie (7 ngày)
                saveUserName.setMaxAge(7 * 24 * 60 * 60);
                // Thêm cookie vào response
                response.addCookie(saveUserName);

            }
        }
        return token;
    }

    @Override
    public UserEntity registerUser(UserRequest user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPassWord(passwordEncoder.encode(userEntity.getPassWord()));
        RolesEntity roles = roleRepository.findByName("ROLE_USER").get();
        userEntity.setRole(roles);
        // Save userEntity to Respository.
        return userRepository.save(userEntity);
    }

}
