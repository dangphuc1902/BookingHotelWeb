package com.bookinghotel.bookinghotel.controller;

import com.bookinghotel.bookinghotel.dto.RoleDTO;
import com.bookinghotel.bookinghotel.dto.UserDTO;
import com.bookinghotel.bookinghotel.entity.UserEntity;
import com.bookinghotel.bookinghotel.exception.NotRoleDeleteException;
import com.bookinghotel.bookinghotel.payload.response.BaseResponse;
import com.bookinghotel.bookinghotel.service.Imp.UserServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;
    @GetMapping("all")
    private ResponseEntity<?> getUsers(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userServiceImp.getUser());
        return new ResponseEntity<>(baseResponse, HttpStatus.FOUND);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        try{
            BaseResponse baseResponse = new BaseResponse();
            UserEntity userEntity = userServiceImp.getUserByEmail(email);
            UserDTO userDTO = new UserDTO(userEntity.getId(),
                    userEntity.getEmail(),
                    userEntity.getFirstName(),
                    userEntity.getLastName(),
                    new RoleDTO(userEntity.getRole().getId(),
                            userEntity.getRole().getName())
            );
            baseResponse.setData(userDTO);
            return ResponseEntity.ok(baseResponse);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user");
        }
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") String email, HttpServletRequest request){
        try{
            userServiceImp.deleteUser(email,request);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setMessage("User deleted successfully");
            return ResponseEntity.ok(baseResponse);
        }catch (UsernameNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (NotRoleDeleteException e)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
    }
}
