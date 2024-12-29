package com.bookinghotel.bookinghotel.service;

import com.bookinghotel.bookinghotel.dto.RoleDTO;
import com.bookinghotel.bookinghotel.dto.UserDTO;
import com.bookinghotel.bookinghotel.entity.UserEntity;
import com.bookinghotel.bookinghotel.exception.NotRoleDeleteException;
import com.bookinghotel.bookinghotel.exception.UsernameNotFoundException;
import com.bookinghotel.bookinghotel.repository.UserRepository;
import com.bookinghotel.bookinghotel.service.Imp.UserServiceImp;
import com.bookinghotel.bookinghotel.utils.JwtUltils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUltils jwtUltils;
    @Autowired
    Gson gson;
    @Override
    public List<UserDTO> getUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOS = userEntities.stream().map(user -> new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                new RoleDTO(user.getRole().getId(),
                        user.getRole().getName())
        )).toList();
        return userDTOS;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    //  TODO:Xóa người dùng:
//
    @Override
    public void deleteUser(String email, HttpServletRequest request) {
        UserEntity userEntity = getUserByEmail(email);
        String role = "";
        String headerAuthen = request.getHeader("Authorization");
        if (headerAuthen != null && headerAuthen.trim().length() > 0){
            String token = headerAuthen.substring(7);
            // Giải mã token;
            String data = jwtUltils.decryptToken(token);
            if (data != null){
                JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
                role = jsonObject.get("name").getAsString();
                System.out.println("User name: "+ getUserNameByCookie(request));
            }
            if (userEntity != null){
                if (role.equals("ROLE_ADMIN") || (role.equals("ROLE_USER") && userEntity.getEmail().equals(getUserNameByCookie(request)))) {
                    userRepository.delete(userEntity);
                }else{
                    throw new NotRoleDeleteException("There is no right to delete.");
                }
            }

        }
    }

    public String getUserNameByCookie(HttpServletRequest request){
        String userName = "";
        // Get Cookie từ request về.
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                try {
                    // Giải mã Cookie
                    userName = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                }catch (Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return userName;
    }
}
