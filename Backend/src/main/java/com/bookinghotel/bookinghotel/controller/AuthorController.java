package com.bookinghotel.bookinghotel.controller;

import com.bookinghotel.bookinghotel.entity.UserEntity;
import com.bookinghotel.bookinghotel.exception.UserAlreadyExistsException;
import com.bookinghotel.bookinghotel.payload.request.LoginRequest;
import com.bookinghotel.bookinghotel.payload.request.UserRequest;
import com.bookinghotel.bookinghotel.payload.response.BaseResponse;
import com.bookinghotel.bookinghotel.service.Imp.AuthorServiceImp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorServiceImp authorServiceImp;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest user){
        try{
            UserEntity userEntity = authorServiceImp.registerUser(user);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("Registration successful!");
            baseResponse.setData(userEntity);
            return ResponseEntity.ok(baseResponse);
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest, HttpServletResponse response){
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
//        String strKey = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println("Kiểm tra key " + strKey);

        String token = authorServiceImp.checkLogin(loginRequest,response);
        System.out.println("Kiểm tra token " + token);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(token.trim().length() > 0 ? 200 : 400);
        baseResponse.setMessage(token.trim().length() > 0 ? "Login Success!" : "Login failed!");
        baseResponse.setData(token);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
