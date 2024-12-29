package com.bookinghotel.bookinghotel.controller;

import com.bookinghotel.bookinghotel.entity.RolesEntity;
import com.bookinghotel.bookinghotel.exception.RoleAlreadyExistException;
import com.bookinghotel.bookinghotel.payload.request.RoleRequest;
import com.bookinghotel.bookinghotel.payload.response.BaseResponse;
import com.bookinghotel.bookinghotel.service.Imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    private RoleServiceImp roleServiceImp;
    @GetMapping("/all-roles")
    public ResponseEntity<?> getAllRoles(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(roleServiceImp.getAllRole());
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping("/create-new-role")
    public ResponseEntity<?> createRole(@RequestBody RoleRequest theRole){
        try {
            RolesEntity roles = roleServiceImp.createNewRole(theRole);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setData(roles);
            baseResponse.setMessage("New role created successfully!");
            return ResponseEntity.ok(baseResponse);
        }catch (RoleAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable("roleId") int roleId){
        boolean isSucces = roleServiceImp.deleteRole(roleId);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(isSucces ? "Delete successfully!" : "Id not found.");
        return ResponseEntity.ok(baseResponse);
    }
}
